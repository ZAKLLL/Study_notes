//: operators/E12_UnsignedRightShift.java
/****************** Exercise 12 *****************
 * Start with a number that is all binary ones. 
 * Left shift it, then use the unsigned right-shift 
 * operator to right shift through all of its binary
 * positions. Display each result using 
 * Integer.toBinaryString().
 ************************************************/
package operators;
import static net.mindview.util.Print.*;

public class E12_UnsignedRightShift {
  public static void main(String[] args) {
    int i = -1 << 1;
    print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
    i >>>= 1;  print(Integer.toBinaryString(i));
  }
} /* Output:
11111111111111111111111111111110
1111111111111111111111111111111
111111111111111111111111111111
11111111111111111111111111111
1111111111111111111111111111
111111111111111111111111111
11111111111111111111111111
1111111111111111111111111
111111111111111111111111
11111111111111111111111
1111111111111111111111
111111111111111111111
11111111111111111111
1111111111111111111
111111111111111111
11111111111111111
1111111111111111
111111111111111
11111111111111
1111111111111
111111111111
11111111111
1111111111
111111111
11111111
1111111
111111
11111
1111
111
11
1
*///:~
