//: concurrency/E41_ActiveObjectDemo2.java
/******************** Exercise 41 ************************
 * Add a message handler to ActiveObjectDemo.java that
 * has no return value, and call this within main().
 *********************************************************/
package concurrency;
import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

public class E41_ActiveObjectDemo2 {
  private ExecutorService ex =
    Executors.newSingleThreadExecutor();
  private Random rand = new Random(47);
  // Insert a random delay to produce the effect
  // of a calculation time:
  private void pause(int factor) {
    try {
      TimeUnit.MILLISECONDS.sleep(
        100 + rand.nextInt(factor));
    } catch(InterruptedException e) {
      print("sleep() interrupted");
    }
  }
  public Future<Integer>
  calculateInt(final int x, final int y) {
    return ex.submit(new Callable<Integer>() {
      public Integer call() {
        print("starting " + x + " + " + y);
        pause(500);
        return x + y;
      }
    });
  }
  public Future<Float>
  calculateFloat(final float x, final float y) {
    return ex.submit(new Callable<Float>() {
      public Float call() {
        print("starting " + x + " + " + y);
        pause(2000);
        return x + y;
      }
    });
  }
  // Message handler without a return value:
  public void printDocument(final String s) {
    ex.execute(new Runnable() {
      public void run() {
        print("printing document " + s);
        pause(1000);
        print("document " + s + " printed");
      }
    });
  }
  public void shutdown() { ex.shutdown(); }
  public static void main(String[] args) {
    E41_ActiveObjectDemo2 d1 = new E41_ActiveObjectDemo2();
    // Prevents ConcurrentModificationException:
    List<Future<?>> results =
      new CopyOnWriteArrayList<Future<?>>();
    for(float f = 0.0f; f < 1.0f; f += 0.2f)
      results.add(d1.calculateFloat(f, f));
    for(int i = 0; i < 5; i++) {
      results.add(d1.calculateInt(i, i));
      d1.printDocument("DOC_" + i);
    }
    print("All asynch calls made");
    while(results.size() > 0) {
      for(Future<?> f : results)
        if(f.isDone()) {
          try {
            print(f.get());
          } catch(Exception e) {
            throw new RuntimeException(e);
          }
          results.remove(f);
        }
    }
    d1.shutdown();
  }
} /* Output: (Sample)
All asynch calls made
starting 0.0 + 0.0
0.0
starting 0.2 + 0.2
starting 0.4 + 0.4
0.4
starting 0.6 + 0.6
0.8
starting 0.8 + 0.8
1.2
starting 0 + 0
1.6
printing document DOC_0
0
document DOC_0 printed
starting 1 + 1
printing document DOC_1
2
document DOC_1 printed
starting 2 + 2
printing document DOC_2
4
document DOC_2 printed
starting 3 + 3
printing document DOC_3
6
document DOC_3 printed
starting 4 + 4
printing document DOC_4
8
document DOC_4 printed
*///:~
