//: arrays/E12_GeneratedDArray.java
/****************** Exercise 12 *****************
 * Create an initialized array of double using
 * CountingGenerator. Print the results.
 ************************************************/
package arrays;
import java.util.Arrays;
import net.mindview.util.*;

public class E12_GeneratedDArray {
  public static void main(String[] args) {
    double[] d = ConvertTo.primitive(Generated.array(
      Double.class, new CountingGenerator.Double(), 15));
    System.out.println(Arrays.toString(d));
  }
} /* Output:
[0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0]
*///:~
