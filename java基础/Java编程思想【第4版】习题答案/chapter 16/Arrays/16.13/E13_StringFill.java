//: arrays/E13_StringFill.java
/****************** Exercise 13 *****************
 * Fill a String using CountingGenerator.Character.
 ************************************************/
package arrays;
import net.mindview.util.*;

public class E13_StringFill {
  public static void main(String[] args) {
    String s = new CountingGenerator.String(15).next();
    System.out.println(s);
  }
} /* Output:
abcdefghijklmno
*///:~
