//: containers/E09_RandTreeSet.java
/****************** Exercise 9 *****************
 * Use RandomGenerator.String to fill a TreeSet,
 * but use alphabetic ordering. Print the TreeSet
 * to verify the sort order.
 ***********************************************/
package containers;
import java.util.*;
import net.mindview.util.*;

public class E09_RandTreeSet {
  public static void main(String args[]) {
    TreeSet<String> ts =
      new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
    ts.addAll(CollectionData.list(
      new RandomGenerator.String(), 10));
    System.out.println("ts = " + ts);
  }
} /* Output:
ts = [ahKcxrE, GcFOWZn, GZMmJMR, naMesbt, oEsuEcU, OneOEdL, qUCBbkI, smwHLGE, TcQrGse, YNzbrny]
*///:~
