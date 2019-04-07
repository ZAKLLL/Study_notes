//: operators/E11_SignedRightShift.java
/****************** Exercise 11 *****************
 * Start with a number that has a binary one in 
 * the most significant position. (Hint: Use a 
 * hexadecimal constant.) Use the signed 
 * right-shift operator to right shift your
 * number through all its binary positions. 
 * Display each result using Integer.toBinaryString().
 ************************************************/
package operators;
import static net.mindview.util.Print.*;

public class E11_SignedRightShift {
  public static void main(String[] args) {
    int i = 0x80000000;
    print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
    i >>= 1;  print(Integer.toBinaryString(i));
  }
} /* Output:
10000000000000000000000000000000
11000000000000000000000000000000
11100000000000000000000000000000
11110000000000000000000000000000
11111000000000000000000000000000
11111100000000000000000000000000
11111110000000000000000000000000
11111111000000000000000000000000
11111111100000000000000000000000
11111111110000000000000000000000
11111111111000000000000000000000
11111111111100000000000000000000
11111111111110000000000000000000
11111111111111000000000000000000
11111111111111100000000000000000
11111111111111110000000000000000
11111111111111111000000000000000
11111111111111111100000000000000
11111111111111111110000000000000
11111111111111111111000000000000
11111111111111111111100000000000
11111111111111111111110000000000
11111111111111111111111000000000
11111111111111111111111100000000
11111111111111111111111110000000
11111111111111111111111111000000
11111111111111111111111111100000
11111111111111111111111111110000
11111111111111111111111111111000
11111111111111111111111111111100
11111111111111111111111111111110
11111111111111111111111111111111
*///:~
