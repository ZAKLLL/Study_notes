//: holding/E29_PriorityQueueSubtlety.java
// {ThrowsException}
/****************** Exercise 29 *****************
 * Create a simple class that inherits from Object
 * and contains no members, and show that you cannot
 * successfully add multiple elements of that class
 * to a PriorityQueue. This issue will be fully
 * explained in the Containers in Depth chapter.
 ***********************************************/ 
package holding;
import java.util.*;

class Dummy {}

public class E29_PriorityQueueSubtlety {
  public static void main(String[] args) {
    PriorityQueue<Dummy> priorityQueue =
      new PriorityQueue<Dummy>();
    System.out.println("Adding 1st instance...");
    priorityQueue.offer(new Dummy());
    System.out.println("Adding 2nd instance...");
    priorityQueue.offer(new Dummy());
  }
} ///:~
