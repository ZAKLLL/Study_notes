//: enumerated/E10_VendingMachine2.java
// {Args: VendingMachineInput.txt}
/****************** Exercise 10 *****************
 * Modify class VendingMachine (only) using EnumMap
 * so that one program can have multiple instances
 * of VendingMachine.
 ***********************************************/
package enumerated;
import java.util.*;
import java.util.concurrent.locks.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

class VendingMachine2 {
  // *******************************
  // *** NEWLY ADDED CODE: BEGIN ***
  // *******************************
  private static class Context {
    private State state = State.RESTING;
    private int amount;
    private Input selection;
  }
  private static Map<Machine,Context> em =
    Collections.synchronizedMap(
      new EnumMap<Machine,Context>(Machine.class));
  static {
    for(Machine m : Machine.values())
      em.put(m, new Context());
  }
  enum Machine { M1, M2, M3 }
  private static final ReentrantLock lock =
    new ReentrantLock();
  // *****************************
  // *** NEWLY ADDED CODE: END ***
  // *****************************
  private static State state;
  private static int amount;
  private static Input selection;
  enum StateDuration { TRANSIENT } // Tagging enum
  enum State {
    RESTING {
      void next(Input input) {
        switch(Category.categorize(input)) {
          case MONEY:
            amount += input.amount();
            state = ADDING_MONEY;
            break;
          case SHUT_DOWN:
            state = TERMINAL;
          default:
        }
      }
    },
    ADDING_MONEY {
      void next(Input input) {
        switch(Category.categorize(input)) {
          case MONEY:
            amount += input.amount();
            break;
          case ITEM_SELECTION:
            selection = input;
            if(amount < selection.amount())
              print("Insufficient money for " + selection);
            else state = DISPENSING;
            break;
          case QUIT_TRANSACTION:
            state = GIVING_CHANGE;
            break;
          case SHUT_DOWN:
            state = TERMINAL;
          default:
        }
      }
    },
    DISPENSING(StateDuration.TRANSIENT) {
      void next() {
        print("here is your " + selection);
        amount -= selection.amount();
        state = GIVING_CHANGE;
      }
    },
    GIVING_CHANGE(StateDuration.TRANSIENT) {
      void next() {
        if(amount > 0) {
          print("Your change: " + amount);
          amount = 0;
        }
        state = RESTING;
      }
    },
    TERMINAL { void output() { print("Halted"); } };
    private boolean isTransient = false;
    State() {}
    State(StateDuration trans) { isTransient = true; }
    void next(Input input) {
      throw new RuntimeException("Only call " +
        "next(Input input) for non-transient states");
    }
    void next() {
      throw new RuntimeException("Only call next() for " +
        "StateDuration.TRANSIENT states");
    }
    void output() { print(amount); }
  }
  // This method is now taking an extra parameter denoting
  // the ID of the VendingMachine "instance".
  static void run(Generator<Input> gen, Machine m) {
    Context ctx = em.get(m);
    while(ctx.state != State.TERMINAL) {
      lock.lock();
      state = ctx.state;
      amount = ctx.amount;
      selection = ctx.selection;
      try {
        state.next(gen.next());
        while(state.isTransient)
          state.next();
        state.output();
        ctx.state = state;
        ctx.amount = amount;
        ctx.selection = selection;
        em.put(m, ctx);
      } finally {
        lock.unlock();
      }
      Thread.yield();
    }
  }
}

public class E10_VendingMachine2 {
  public static void main(String[] args) {
    for(final VendingMachine2.Machine m :
        VendingMachine2.Machine.values()) {
      Generator<Input> gen = new RandomInputGenerator();
      if(args.length == 1)
        gen = new FileInputGenerator(args[0]);
      final Generator<Input> g = gen;
      new Thread(new Runnable() {
        public void run() { VendingMachine2.run(g, m); }
      }).start();
    }
  }
} /* Output: (Sample)
25
25
25
50
50
50
75
75
75
here is your CHIPS
0
here is your CHIPS
0
here is your CHIPS
0
100
100
100
200
200
200
here is your TOOTHPASTE
0
here is your TOOTHPASTE
0
here is your TOOTHPASTE
0
25
25
25
35
35
35
Your change: 35
0
Your change: 35
0
Your change: 35
0
25
25
25
35
35
35
Insufficient money for SODA
35
Insufficient money for SODA
35
Insufficient money for SODA
35
60
60
60
70
70
70
75
75
75
Insufficient money for SODA
75
Insufficient money for SODA
75
Insufficient money for SODA
75
Your change: 75
0
Your change: 75
0
Your change: 75
0
Halted
Halted
Halted
*///:~
