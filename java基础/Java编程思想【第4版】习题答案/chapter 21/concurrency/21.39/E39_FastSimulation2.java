//: concurrency/E39_FastSimulation2.java
// {RunByHand}
/******************** Exercise 39 ************************
 * Does FastSimulation.java make reasonable assumptions?
 * Change the array to ordinary ints instead of
 * AtomicInteger and use Lock mutexes. Compare 
 * performance between the two versions of the program.
 *********************************************************/
package concurrency;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.*;
import static net.mindview.util.Print.*;

public class E39_FastSimulation2 {
  static final int N_ELEMENTS = 10000;
  static final int N_GENES = 30;
  static final int N_EVOLVERS = 50;
  // Variant 1 (optimistic locking):
  static final AtomicInteger[][] GRID =
    new AtomicInteger[N_ELEMENTS][N_GENES];
  // Variant 2 (explicit locking):
  static final int[][] grid =
    new int[N_ELEMENTS][N_GENES];
  static final ReentrantLock[] lock =
    new ReentrantLock[N_ELEMENTS];
  // Counts the number of evolutions using 'variant 1':
  static final AtomicInteger counter1 = new AtomicInteger();
  // Counts the number of evolutions using 'variant 2':
  static final AtomicInteger counter2 = new AtomicInteger();
  static Random rand = new Random(47);
  static class Evolver1 implements Runnable {
    public void run() {
      while(!Thread.interrupted()) {
        // Randomly select an element to work on:
        int element = rand.nextInt(N_ELEMENTS);
        for(int i = 0; i < N_GENES; i++) {
          int previous = element - 1;
          if(previous < 0) previous = N_ELEMENTS - 1;
          int next = element + 1;
          if(next >= N_ELEMENTS) next = 0;
          int oldvalue = GRID[element][i].get();
          int newvalue = oldvalue +
            GRID[previous][i].get() + GRID[next][i].get();
          newvalue /= 3;
          if(!GRID[element][i]
            .compareAndSet(oldvalue, newvalue)) {
            // Some backup action...
          }
        }
        counter1.incrementAndGet();
      }
    }
  }
  static class Evolver2 implements Runnable {
    public void run() {
      while(!Thread.interrupted()) {
        // Randomly select an element to work on:
        int element = rand.nextInt(N_ELEMENTS);
        // Lock the whole row:
        lock[element].lock();
        try {
          for(int i = 0; i < N_GENES; i++) {
            int previous = element - 1;
            if(previous < 0) previous = N_ELEMENTS - 1;
            int next = element + 1;
            if(next >= N_ELEMENTS) next = 0;
            int newvalue = grid[element][i] +
              grid[previous][i] + grid[next][i];
            newvalue /= 3;
            grid[element][i] = newvalue;
          } 
        } finally { lock[element].unlock(); }
        counter2.incrementAndGet();
      }
    }
  }
  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i = 0; i < N_ELEMENTS; i++)
      for(int j = 0; j < N_GENES; j++)
        GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
    for(int i = 0; i < N_ELEMENTS; i++)
      for(int j = 0; j < N_GENES; j++)
        grid[i][j] = rand.nextInt(1000);
    for(int i = 0; i < N_ELEMENTS; i++)
      lock[i] = new ReentrantLock();
    for(int i = 0; i < N_EVOLVERS; i++) {
      exec.execute(new Evolver1());
      exec.execute(new Evolver2());
    }
    TimeUnit.SECONDS.sleep(5);
    exec.shutdownNow();
    print("Variant 1: " + counter1.get());
    print("Variant 2: " + counter2.get());
  }
} /* (Execute to see output) *///:~
