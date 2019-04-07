//: annotations/HashSetCommentedTest.java
package annotations;
import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;

public class HashSetCommentedTest {
  HashSet<String> testObject = new HashSet<String>();
  @TestNote("Tests if the new HashSet is empty ")
  @Test void initialization() {
    assert testObject.isEmpty();
  }
  @TestNote("Tests the HashSet.contains(Object o) method")
  @Test void _contains() {
    testObject.add("one");
    assert testObject.contains("one");
  }
  @TestNote("Tests the HashSet.remove(Object o) method")
  @Test void _remove() {
    testObject.add("one");
    testObject.remove("one");
    assert testObject.isEmpty();
  }
  public static void main(String[] args) throws Exception {
    OSExecute.command("java " +
      " annotations.E11_AtUnit2 HashSetCommentedTest");
  }
} /* Output:
annotations.HashSetCommentedTest
  . initialization  : Tests if the new HashSet is empty
  . _remove  : Tests the HashSet.remove(Object o) method
  . _contains  : Tests the HashSet.contains(Object o) method
OK (3 tests)
*///:~
