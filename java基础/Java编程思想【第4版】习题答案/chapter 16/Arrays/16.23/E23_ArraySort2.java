//: arrays/E23_ArraySort2.java
/****************** Exercise 23 *****************
 * Create an array of Integer, fill it with random
 * int values (using autoboxing), and sort it into
 * reverse order using a Comparator.
 ************************************************/
package arrays;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

public class E23_ArraySort2 {
  public static void main(String[] args) {
    Generator<Integer> gen =
      new RandomGenerator.Integer(1000);
    Integer[] a = Generated.array(new Integer[25], gen);
    print("Unsorted array: " + Arrays.toString(a));
    Arrays.sort(a, Collections.reverseOrder());
    print("Sorted array: " + Arrays.toString(a));
  }
} /* Output:
Unsorted array: [258, 555, 693, 861, 961, 429, 868, 200, 522, 207, 288, 128, 551, 589, 809, 278, 998, 861, 520, 258, 916, 140, 511, 322, 704]
Sorted array: [998, 961, 916, 868, 861, 861, 809, 704, 693, 589, 555, 551, 522, 520, 511, 429, 322, 288, 278, 258, 258, 207, 200, 140, 128]
*///:~
