//: arrays/E22_ArrayBinarySearch.java
/****************** Exercise 22 *****************
 * Show that the results of performing a
 * binarySearch() on an unsorted array are
 * unpredictable.
 ************************************************/
package arrays;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

public class E22_ArrayBinarySearch {
  public static void main(String[] args) {
    Generator<Integer> gen =
      new RandomGenerator.Integer(1000);
    int[] a = ConvertTo.primitive(
      Generated.array(new Integer[25], gen));
    print("Unsorted array: " + Arrays.toString(a));
    int location = Arrays.binarySearch(a, a[10]);
    printnb("Location of " + a[10] + " is " + location);
    if(location >= 0)
      print(", a[" + location + "] = " + a[location]);
    else
      print();
    location = Arrays.binarySearch(a, a[5]);
    printnb("Location of " + a[5] + " is " + location);
    if(location >= 0)
      print(", a[" + location + "] = " + a[location]);
  }
} /* Output:
Unsorted array: [258, 555, 693, 861, 961, 429, 868, 200, 522, 207, 288, 128, 551, 589, 809, 278, 998, 861, 520, 258, 916, 140, 511, 322, 704]
Location of 288 is -2
Location of 429 is 5, a[5] = 429
*///:~
