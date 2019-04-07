//: annotations/E07_LinkedListTest2.java
/****************** Exercise 07 *****************
 * Use the inheritance approach to modify Exercise 6.
 ***********************************************/
package annotations;
import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;

public class E07_LinkedListTest2
extends LinkedList<String> {
  @Test void initialization() {
    assert isEmpty();
  }
  @Test void _contains() {
    add("one");
    assert contains("one");
  }
  @Test void _remove() {
    add("one");
    remove("one");
    assert isEmpty();
  }
  public static void main(String[] args) throws Exception {
    OSExecute.command("java " +
      " net.mindview.atunit.AtUnit E07_LinkedListTest2");
  }
} /* Output:
annotations.E07_LinkedListTest2
  . initialization
  . _remove
  . _contains
OK (3 tests)
*///:~
