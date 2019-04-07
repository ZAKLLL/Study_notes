//: holding/E28_PriorityQueue.java
/****************** Exercise 28 *****************
 * Fill a PriorityQueue (using offer()) with
 * Double values created using java.util.Random,
 * then remove the elements using poll() and
 * display them.
 ***********************************************/ 
package holding;
import java.util.*;

public class E28_PriorityQueue {
  static Random rand = new Random(47);
  public static void printQ(Queue<?> queue) {
    for(Object data = queue.poll(); data != null;
        data = queue.poll())
      System.out.print(data + " ");
    System.out.println();
  }
  public static void main(String[] args) {
    PriorityQueue<Double> priorityQueue =
      new PriorityQueue<Double>();
    for(int i = 0; i < 10; i++)
      priorityQueue.offer(rand.nextDouble());
    printQ(priorityQueue);
  }
} /* Output:
0.0508673570556899 0.16020656493302599 0.18847866977771732 0.2613610344283964 0.2678662084200585 0.5166020801268457 0.5309454508634242 0.7271157860730044 0.7620665811558285 0.8037155449603999
*///:~
