//: operators/E09_MinMaxExponents.java
/****************** Exercise 9 *****************
 * Display the largest and smallest numbers for
 * both float and double exponential notation.
 ***********************************************/
package operators;
import static net.mindview.util.Print.*;

public class E09_MinMaxExponents {
  public static void main(String[] args) {
    print("Float MIN: " + Float.MIN_VALUE);
    print("Float MAX: " + Float.MAX_VALUE);
    print("Double MIN: " + Double.MIN_VALUE);
    print("Double MAX: " + Double.MAX_VALUE);
  }
} /* Output:
Float MIN: 1.4E-45
Float MAX: 3.4028235E38
Double MIN: 4.9E-324
Double MAX: 1.7976931348623157E308
*///:~
