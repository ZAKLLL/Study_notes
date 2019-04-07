//: concurrency/E14_ManyTimers.java
// {Args: 100}
/****************** Exercise 14 ************************
 * Demonstrate that java.util.Timer scales to large numbers
 * by creating a program that generates many Timer objects
 * that perform some simple task when the timeout completes.
 ******************************************************/
package concurrency;
import java.util.*;
import java.util.concurrent.*;

public class E14_ManyTimers {
  public static void main(String[] args) throws Exception {
    if(args.length < 1) {
      System.err.println(
        "Usage: java E14_ManyTimers <num of timers>");
    }
    int numOfTimers = Integer.parseInt(args[0]);
    for(int i = 0; i < numOfTimers; i++) {
      new Timer().schedule(new TimerTask() {
        public void run() {
          System.out.println(System.currentTimeMillis());
        }
      }, numOfTimers - i);
    }
    // Wait for timers to expire
    TimeUnit.MILLISECONDS.sleep(2 * numOfTimers);
    System.exit(0);
  }
} /* Output: (Sample)
1133967112886
1133967112886
1133967112886
1133967112886
1133967112886
1133967112896
1133967112896
1133967112896
...
1133967112966
1133967112966
1133967112966
1133967112966
*///:~
