//: concurrency/E12_AtomicityTest2.java
// {RunByHand}
/****************** Exercise 12 *****************
 * Repair AtomicityTest.java using the synchronized
 * keyword. Can you demonstrate that it is now
 * correct?
 ***********************************************/
package concurrency;
import java.util.concurrent.*;

class AtomicityTest2 implements Runnable {
  private int i;
  public synchronized int getValue() { return i; }
  private synchronized void evenIncrement() { i++;  i++; }
  public void run() {
    while(true)
      evenIncrement();
  }
}

public class E12_AtomicityTest2 {
  public static void main(String[] args) {
    System.out.println("Press Control-C to exit");
    ExecutorService exec = Executors.newCachedThreadPool();
    AtomicityTest2 at = new AtomicityTest2();
    exec.execute(at);
    while(true) {
      int val = at.getValue();
      if(val % 2 != 0) {
        System.out.println(val);
        System.exit(0);
      }
    }
  }
} ///:~
