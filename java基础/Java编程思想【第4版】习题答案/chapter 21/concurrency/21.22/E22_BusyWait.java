//: concurrency/E22_BusyWait.java
/****************** Exercise 22 ***************************
 * Create an example of a busy wait. One task sleeps for a
 * while and then sets a flag to true. The second task
 * watches that flag inside a while loop (this is the busy
 * wait) and when the flag becomes true, sets it back to
 * false and reports the change to the console. Note how
 * much wasted time the program spends inside the busy wait,
 * and create a second version of the program that uses
 * wait() instead of the busy wait.
 ********************************************************/
package concurrency;
import java.util.concurrent.*;

public class E22_BusyWait {
  private static volatile boolean flag;
  private static int spins;
  public static void main(String[] args) throws Exception {
    Runnable r1 = new Runnable() {
      public void run() {
        for(;;) {
          try {
            TimeUnit.MILLISECONDS.sleep(10);
          } catch (InterruptedException e) { return; }
          flag = true;
        }
      }
    };
    Runnable r2 = new Runnable() {
      public void run() {
        for(;;) {
          // The busy-wait loop
          while(!flag &&
                !Thread.currentThread().isInterrupted())
            spins++;
          System.out.println("Spun " + spins + " times");
          spins = 0;
          flag = false;
          if(Thread.interrupted())
            return;
        }
      }
    };
    ExecutorService exec = Executors.newCachedThreadPool();
    exec.execute(r1);
    exec.execute(r2);
    TimeUnit.SECONDS.sleep(1);
    exec.shutdownNow();
  }
} /* Output: (Sample)
Spun 5546 times
Spun 6744 times
Spun 20270 times
...
Spun 35944 times
Spun 17733 times
Spun 23670 times
*///:~
