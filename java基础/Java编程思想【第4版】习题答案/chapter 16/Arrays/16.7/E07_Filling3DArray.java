//: arrays/E07_Filling3DArray.java
/****************** Exercise 7 *****************
 * Repeat the previous exercise for a 3-D array.
 ***********************************************/
package arrays;
import java.util.Arrays;

public class E07_Filling3DArray {
  static BerylliumSphere[][][]
  fill(int xLen, int yLen, int zLen) {
    BerylliumSphere[][][] a =
      new BerylliumSphere[xLen][yLen][zLen];
    for(int i = 0; i < xLen; i++)
      for(int j = 0; j < yLen; j++)
        for(int k = 0; k < zLen; k++)
          a[i][j][k] = new BerylliumSphere();
    return a;
  }
  public static void main(String[] args) {
    System.out.println(Arrays.deepToString(fill(3, 3, 3)));
  }
} /* Output:
[[[Sphere 0, Sphere 1, Sphere 2], [Sphere 3, Sphere 4, Sphere 5], [Sphere 6, Sphere 7, Sphere 8]], [[Sphere 9, Sphere 10, Sphere 11], [Sphere 12, Sphere 13, Sphere 14], [Sphere 15, Sphere 16, Sphere 17]], [[Sphere 18, Sphere 19, Sphere 20], [Sphere 21, Sphere 22, Sphere 23], [Sphere 24, Sphere 25, Sphere 26]]]
*///:~
