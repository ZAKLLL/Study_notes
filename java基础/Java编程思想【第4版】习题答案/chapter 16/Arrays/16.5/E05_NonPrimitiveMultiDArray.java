//: arrays/E05_NonPrimitiveMultiDArray.java
/****************** Exercise 5 *****************
 * Demonstrate that multidimensional arrays of
 * non-primitive types are automatically initialized
 * to null.
 ***********************************************/
package arrays;
import java.util.Arrays;

public class E05_NonPrimitiveMultiDArray {
  public static void main(String[] args) {
    System.out.println(
      Arrays.deepToString(new Object[3][3][3]));
  }
} /* Output:
[[[null, null, null], [null, null, null], [null, null, null]], [[null, null, null], [null, null, null], [null, null, null]], [[null, null, null], [null, null, null], [null, null, null]]]
*///:~
