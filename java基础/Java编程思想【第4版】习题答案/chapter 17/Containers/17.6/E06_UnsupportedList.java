//: containers/E06_UnsupportedList.java
/****************** Exercise 6 ******************
 * Note that List has additional "optional"
 * operations that are not included in Collection.
 * Write a version of Unsupported.java that tests
 * these additional optional operations.
 ************************************************/
package containers;
import java.util.*;

public class E06_UnsupportedList {
  static void test(String msg, List<String> l) {
    System.out.println("--- " + msg + " ---");
    List<String> subList =
      new ArrayList<String>(l.subList(1,8));
    try { l.add(0, "Test"); } catch(Exception e) {
      System.out.println("add(index, element): " + e);
    }
    try { l.addAll(0, subList); } catch(Exception e) {
      System.out.println("addAll(index, c): " + e);
    }
    try { l.remove(0); } catch(Exception e) {
      System.out.println("remove(index): " + e);
    }
  }
  public static void main(String[] args) {
    List<String> list =
      Arrays.asList("A B C D E F G H I J K L".split(" "));
    test("Modifiable Copy", new ArrayList<String>(list));
    test("Arrays.asList()", list);
    test("unmodifiableList()",
      Collections.unmodifiableList(
        new ArrayList<String>(list)));
  }
} /* Output:
--- Modifiable Copy ---
--- Arrays.asList() ---
add(index, element): java.lang.UnsupportedOperationException
addAll(index, c): java.lang.UnsupportedOperationException
remove(index): java.lang.UnsupportedOperationException
--- unmodifiableList() ---
add(index, element): java.lang.UnsupportedOperationException
addAll(index, c): java.lang.UnsupportedOperationException
remove(index): java.lang.UnsupportedOperationException
*///:~
