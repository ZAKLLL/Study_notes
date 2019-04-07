//: strings/E14_SplitDemo2.java
/************************** Exercise 14 ******************
 * Rewrite SplitDemo using String.split().
 *********************************************************/
package strings;
import java.util.*;
import static net.mindview.util.Print.*;

public class E14_SplitDemo2 {
  public static void main(String[] args) {
    String input =
      "This!!unusual use!!of exclamation!!points";
    print(Arrays.toString(input.split("!!")));
    // Only do the first three:
    print(Arrays.toString(input.split("!!", 3)));
  }
} /* Output:
[This, unusual use, of exclamation, points]
[This, unusual use, of exclamation!!points]
*///:~
