//: concurrency/E29_ToastOMatic2.java
/********************** Exercise 29 ***********************
 * Modify ToastOMatic.java to create peanut butter and jelly
 * on toast sandwiches using two separate assembly lines 
 * (one for peanut butter, the second for jelly, then
 * merging the two lines).
*********************************************************/
package concurrency;
import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

class Toast {
  public enum Status { 
    DRY,
    BUTTERED,
    JAMMED,
    READY {
      public String toString() {
        return
          BUTTERED.toString() + " & " + JAMMED.toString();
      }
    }
  }
  private Status status = Status.DRY;
  private final int id;
  public Toast(int idn) { id = idn; }
  public void butter() {
    status =
      (status == Status.DRY) ? Status.BUTTERED :
                               Status.READY;
  }
  public void jam() {
    status =
      (status == Status.DRY) ? Status.JAMMED :
                               Status.READY;
  }
  public Status getStatus() { return status; }
  public int getId() { return id; }
  public String toString() {
    return "Toast " + id + ": " + status;
  }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {}

class Toaster implements Runnable {
  private ToastQueue toastQueue;
  private int count;
  private Random rand = new Random(47);
  public Toaster(ToastQueue tq) { toastQueue = tq; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        TimeUnit.MILLISECONDS.sleep(
          100 + rand.nextInt(500));
        // Make toast
        Toast t = new Toast(count++);
        print(t);
        // Insert into queue
        toastQueue.put(t);
      }
    } catch(InterruptedException e) {
      print("Toaster interrupted");
    }
    print("Toaster off");
  }
}

// Apply butter to toast:
class Butterer implements Runnable {
  private ToastQueue inQueue, butteredQueue;
  public Butterer(ToastQueue in, ToastQueue buttered) {
    inQueue = in;
    butteredQueue = buttered;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until next piece of toast is available:
        Toast t = inQueue.take();
        t.butter();
        print(t);
        butteredQueue.put(t);
      }
    } catch(InterruptedException e) {
      print("Butterer interrupted");
    }
    print("Butterer off");
  }
}

// Apply jam to toast:
class Jammer implements Runnable {
  private ToastQueue inQueue, jammedQueue;
  public Jammer(ToastQueue in, ToastQueue jammed) {
    inQueue = in;
    jammedQueue = jammed;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until next piece of toast is available:
        Toast t = inQueue.take();
        t.jam();
        print(t);
        jammedQueue.put(t);
      }
    } catch(InterruptedException e) {
      print("Jammer interrupted");
    }
    print("Jammer off");
  }
}

// Consume the toast:
class Eater implements Runnable {
  private ToastQueue finishedQueue;
  public Eater(ToastQueue finished) {
    finishedQueue = finished;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until next piece of toast is available:
        Toast t = finishedQueue.take();
        // Verify that all pieces are ready for consumption:
        if(t.getStatus() != Toast.Status.READY) {
          print(">>>> Error: " + t);
          System.exit(1);
        } else
          print("Chomp! " + t);
      }
    } catch(InterruptedException e) {
      print("Eater interrupted");
    }
    print("Eater off");
  }
}

// Outputs alternate inputs on alternate channels:
class Alternator implements Runnable {
  private ToastQueue inQueue, out1Queue, out2Queue;
  private boolean outTo2;  // control alternation
  public Alternator(ToastQueue in, ToastQueue out1,
          ToastQueue out2) {
    inQueue = in;
    out1Queue = out1;
    out2Queue = out2;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until next piece of toast is available:
        Toast t = inQueue.take();
        if(!outTo2)
          out1Queue.put(t);
        else
          out2Queue.put(t);
        outTo2 = !outTo2;  // change state for next time
      }
    } catch(InterruptedException e) {
      print("Alternator interrupted");
    }
    print("Alternator off");
  }
}

// Accepts toasts on either channel, and relays them on to
// a "single" successor
class Merger implements Runnable {
  private ToastQueue in1Queue, in2Queue, toBeButteredQueue,
    toBeJammedQueue, finishedQueue;
  public Merger(ToastQueue in1, ToastQueue in2,
          ToastQueue toBeButtered, ToastQueue toBeJammed,
          ToastQueue finished) {
    in1Queue = in1;
    in2Queue = in2;
    toBeButteredQueue = toBeButtered;
    toBeJammedQueue = toBeJammed;
    finishedQueue = finished;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until next piece of toast is available:
        Toast t = null;
        while(t == null) {
          t = in1Queue.poll(50, TimeUnit.MILLISECONDS);
          if(t != null)
            break;
          t = in2Queue.poll(50, TimeUnit.MILLISECONDS);
        }
        // Relay toast onto the proper queue
        switch(t.getStatus()) {
          case BUTTERED:
            toBeJammedQueue.put(t);
            break;
          case JAMMED:
            toBeButteredQueue.put(t);
            break;
          default:
            finishedQueue.put(t);
        }
      }
    } catch(InterruptedException e) {
      print("Merger interrupted");
    }
    print("Merger off");
  }
}

public class E29_ToastOMatic2 {
  public static void main(String[] args) throws Exception {
    ToastQueue 
      dryQueue = new ToastQueue(),
      butteredQueue = new ToastQueue(),
      toBeButteredQueue = new ToastQueue(),
      jammedQueue = new ToastQueue(),
      toBeJammedQueue = new ToastQueue(),
      finishedQueue = new ToastQueue();
    ExecutorService exec = Executors.newCachedThreadPool();
    exec.execute(new Toaster(dryQueue));
    exec.execute(new Alternator(dryQueue, toBeButteredQueue,
      toBeJammedQueue));
    exec.execute(
      new Butterer(toBeButteredQueue, butteredQueue));
    exec.execute(
      new Jammer(toBeJammedQueue, jammedQueue));
    exec.execute(new Merger(butteredQueue , jammedQueue,
      toBeButteredQueue, toBeJammedQueue, finishedQueue));
    exec.execute(new Eater(finishedQueue));
    TimeUnit.SECONDS.sleep(5);
    exec.shutdownNow();
  }
} /* (Execute to see output) *///:~
