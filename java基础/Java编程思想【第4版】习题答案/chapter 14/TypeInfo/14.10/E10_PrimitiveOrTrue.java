//: typeinfo/E10_PrimitiveOrTrue.java
/****************** Exercise 10 *****************
 * Write a program to determine whether an array
 * of char is a primitive type or a true object.
 ***********************************************/
package typeinfo;
import static net.mindview.util.Print.*;

public class E10_PrimitiveOrTrue {
  public static void main(String args[]) {
    char[] ac = "Hello, World!".toCharArray();
    print("ac.getClass() = " + ac.getClass());
    print("ac.getClass().getSuperclass() = "
      + ac.getClass().getSuperclass());
    char c = 'c';
    //! c.getClass(); // Can't do it, primitives
                      // are not true objects.
    int[] ia = new int[3];
    print("ia.getClass() = " + ia.getClass());
    long[] la = new long[3];
    print("la.getClass() = " + la.getClass());
    double[] da = new double[3];
    print("da.getClass() = " + da.getClass());
    String[] sa = new String[3];
    print("sa.getClass() = " + sa.getClass());
    E10_PrimitiveOrTrue[] pot =
      new E10_PrimitiveOrTrue[3];
    print("pot.getClass() = " + pot.getClass());
    // Multi-dimensional arrays:
    int[][][] threed = new int[3][][];
    print("threed.getClass() = " + threed.getClass());
  }
} /* Output:
ac.getClass() = class [C
ac.getClass().getSuperclass() = class java.lang.Object
ia.getClass() = class [I
la.getClass() = class [J
da.getClass() = class [D
sa.getClass() = class [Ljava.lang.String;
pot.getClass() = class [Ltypeinfo.E10_PrimitiveOrTrue;
threed.getClass() = class [[[I
*///:~
