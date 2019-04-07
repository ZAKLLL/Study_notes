//: concurrency/E34_ExchangerDemo2.java
/******************** Exercise 34 ************************
 * Modify ExchangerDemo.java to use your own class instead
 * of Fat.
 *********************************************************/
package concurrency;
import java.util.concurrent.*;
import java.util.*;
import net.mindview.util.*;

class ExchangerProducer<T> implements Runnable {
  private Generator<T> generator;
  private Exchanger<List<T>> exchanger;
  private List<T> holder;
  ExchangerProducer(Exchanger<List<T>> exchg,
  Generator<T> gen, List<T> holder) {
    exchanger = exchg;
    generator = gen;
    this.holder = holder;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        for(int i = 0; i < E34_ExchangerDemo2.size; i++)
          holder.add(generator.next());
        // Exchange full for empty:
        holder = exchanger.exchange(holder);
      }
    } catch(InterruptedException e) {
      // OK to terminate this way.
    }
  }
}

class ExchangerConsumer<T> implements Runnable {
  private Exchanger<List<T>> exchanger;
  private List<T> holder;
  private volatile T value;
  ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder){
    exchanger = ex;
    this.holder = holder;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        holder = exchanger.exchange(holder);
        for(T x : holder) {
          value = x; // Fetch out value
          holder.remove(x); // OK for CopyOnWriteArrayList
        }
      }
    } catch(InterruptedException e) {
      // OK to terminate this way.
    }
    System.out.println("Final value: " + value);
  }
}

public class E34_ExchangerDemo2 {
  static int size = 10;
  static int delay = 5; // Seconds
  public static void main(String[] args) throws Exception {
    if(args.length > 0)
      size = new Integer(args[0]);
    if(args.length > 1)
      delay = new Integer(args[1]);
    ExecutorService exec = Executors.newCachedThreadPool();
    Exchanger<List<Integer>> xc =
      new Exchanger<List<Integer>>();
    List<Integer>
      producerList = new CopyOnWriteArrayList<Integer>(),
      consumerList = new CopyOnWriteArrayList<Integer>();
    exec.execute(new ExchangerProducer<Integer>(xc,
      new CountingGenerator.Integer(), producerList));
    exec.execute(
      new ExchangerConsumer<Integer>(xc, consumerList));
    TimeUnit.SECONDS.sleep(delay);
    exec.shutdownNow();
  }
} /* Output: (Sample)
Final value: 2903359
*///:~
