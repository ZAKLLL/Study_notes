//: operators/E10_BitwiseOperators.java
/****************** Exercise 10 *****************
 * Write a program with two constant values, one
 * with alternating binary ones and zeroes, with
 * a zero in the least-significant digit, and the
 * second, also alternating, with a one in the
 * least-significant digit. (Hint: It's easiest to
 * use hexadecimal constants for this.) Combine 
 * these two values every way possible using the 
 * bitwise operators.  Display the results using 
 * Integer.toBinaryString().
 ************************************************/
package operators;
import static net.mindview.util.Print.*;

public class E10_BitwiseOperators {
  public static void main(String[] args) {
    int i1 = 0xaaaaaaaa;
    int i2 = 0x55555555;
    print("i1 = " + Integer.toBinaryString(i1));
    print("i2 = " + Integer.toBinaryString(i2));
    print("~i1 = " + Integer.toBinaryString(~i1));
    print("~i2 = " + Integer.toBinaryString(~i2));
    print("i1 & i1 = " + Integer.toBinaryString(i1 & i1));
    print("i1 | i1 = " + Integer.toBinaryString(i1 | i1));
    print("i1 ^ i1 = " + Integer.toBinaryString(i1 ^ i1));
    print("i1 & i2 = " + Integer.toBinaryString(i1 & i2));
    print("i1 | i2 = " + Integer.toBinaryString(i1 | i2));
    print("i1 ^ i2 = " + Integer.toBinaryString(i1 ^ i2));
  }
} /* Output:
i1 = 10101010101010101010101010101010
i2 = 1010101010101010101010101010101
~i1 = 1010101010101010101010101010101
~i2 = 10101010101010101010101010101010
i1 & i1 = 10101010101010101010101010101010
i1 | i1 = 10101010101010101010101010101010
i1 ^ i1 = 0
i1 & i2 = 0
i1 | i2 = 11111111111111111111111111111111
i1 ^ i2 = 11111111111111111111111111111111
*///:~
