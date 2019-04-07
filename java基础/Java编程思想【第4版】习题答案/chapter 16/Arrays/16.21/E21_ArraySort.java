//: arrays/E21_ArraySort.java
/****************** Exercise 21 *****************
 * Try to sort an array of the objects in Exercise
 * 18. Implement Comparable to fix the problem. Now
 * create a Comparator to sort the objects into reverse
 * order.
 ************************************************/
package arrays;
import java.util.*;
import java.lang.reflect.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

class ComparableBerylliumSphere extends BerylliumSphere
implements Comparable<BerylliumSphere> {
  // BerylliumSphere.id is private, so we need to use
  // reflection to get its value.
  static long getID(BerylliumSphere bs) {
    try {
      Field fid =
        BerylliumSphere.class.getDeclaredField("id");
      fid.setAccessible(true);
      return fid.getLong(bs);
    } catch(Exception e) {
      e.printStackTrace();
      return 0L;  // Bogus value
    }
  }
  public int compareTo(BerylliumSphere rv) {
    long id = getID(this);
    long rvid = getID(rv);
    return (id < rvid ? -1 : (id == rvid ? 0 : 1));
  }
}

public class E21_ArraySort {
  public static void main(String[] args) {
    Random r = new Random(47);
    BerylliumSphere[] a = Generated.array(
      BerylliumSphere.class, new BSGenerator(), 5);
    Collections.shuffle(Arrays.asList(a), r);
    print("Before sort 1: a = " + Arrays.toString(a));
    try {
      Arrays.sort(a);
      print("After sort 1: a = " + Arrays.toString(a));
    } catch(ClassCastException e) {
      System.out.println("Array cannot be sorted!");
    }
    for(int i = 0; i < a.length; i++)
      a[i] = new ComparableBerylliumSphere();
    Collections.shuffle(Arrays.asList(a), r);
    print("Before sort 2: a = " + Arrays.toString(a));
    Arrays.sort(a);
    print("After sort 2: a = " + Arrays.toString(a));
    Collections.shuffle(Arrays.asList(a), r);
    print("Before rev. sort 3: a = " + Arrays.toString(a));
    Arrays.sort(a, Collections.reverseOrder());
    print("After rev. sort 3: a = " + Arrays.toString(a));
  }
} /* Output:
Before sort 1: a = [Sphere 2, Sphere 0, Sphere 4, Sphere 1, Sphere 3]
Array cannot be sorted!
Before sort 2: a = [Sphere 8, Sphere 5, Sphere 9, Sphere 7, Sphere 6]
After sort 2: a = [Sphere 5, Sphere 6, Sphere 7, Sphere 8, Sphere 9]
Before rev. sort 3: a = [Sphere 8, Sphere 6, Sphere 5, Sphere 9, Sphere 7]
After rev. sort 3: a = [Sphere 9, Sphere 8, Sphere 7, Sphere 6, Sphere 5]
*///:~
