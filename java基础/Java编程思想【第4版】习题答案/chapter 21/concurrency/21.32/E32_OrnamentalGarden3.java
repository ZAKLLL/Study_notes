//: concurrency/E32_OrnamentalGarden3.java
/******************** Exercise 32 ************************
 * Use a CountDownLatch to solve the problem of correlating
 * the results from the Entrances in OrnamentalGarden.java.
 * Remove the unnecessary code from the new version of the
 * example.
 *********************************************************/
package concurrency;
import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

class Entrance3 implements Runnable {
  private final CountDownLatch latch;
  private static Count count = new Count();
  private static List<Entrance3> entrances =
    new ArrayList<Entrance3>();
  private int number;
  private final int id;
  private static volatile boolean canceled;
  public static void cancel() { canceled = true; }
  public Entrance3(CountDownLatch ltc, int id) {
    latch = ltc;
    this.id = id;
    entrances.add(this);
  }
  public void run() {
    while(!canceled) {
      synchronized(this) { ++number; }
      print(this + " Total: " + count.increment());
      try {
        TimeUnit.MILLISECONDS.sleep(100);
      } catch(InterruptedException e) {
        print("sleep interrupted");
      }
    }
    latch.countDown();
    print("Stopping " + this);
  }
  public synchronized int getValue() { return number; }
  public String toString() {
    return "Entrance " + id + ": " + getValue();
  }
  public static int getTotalCount() {
    return count.value();
  }
  public static int sumEntrances() {
    int sum = 0;
    for(Entrance3 entrance : entrances)
      sum += entrance.getValue();
    return sum;
  }
}

public class E32_OrnamentalGarden3 {
  public static void main(String[] args) throws Exception {
    // All must share a single CountDownLatch object:
    CountDownLatch latch = new CountDownLatch(5);
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i = 0; i < 5; i++)
      exec.execute(new Entrance3(latch, i));
    TimeUnit.SECONDS.sleep(3);
    Entrance3.cancel();
    exec.shutdown();
    latch.await();  // Wait for results
    print("Total: " + Entrance3.getTotalCount());
    print("Sum of Entrances: " + Entrance3.sumEntrances());
  }
} /* Output: (Sample)
Entrance 1: 1 Total: 2
Entrance 2: 1 Total: 3
Entrance 3: 1 Total: 4
Entrance 4: 1 Total: 5
Entrance 0: 1 Total: 1
Entrance 1: 2 Total: 6
Entrance 2: 2 Total: 7
Entrance 3: 2 Total: 8
Entrance 4: 2 Total: 9
Entrance 0: 2 Total: 10
Entrance 1: 3 Total: 11
Entrance 2: 3 Total: 12
...
Terminating Entrance 2: 30
Terminating Entrance 0: 30
Terminating Entrance 3: 30
Terminating Entrance 4: 30
Terminating Entrance 1: 30
Total: 150
Sum of Entrances: 150
*///:~
