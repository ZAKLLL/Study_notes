//: annotations/E05_TestSetupFixture2.java
/****************** Exercise 05 *****************
 * Modify the above example to use the inheritance
 * approach.
 ***********************************************/
package annotations;
import java.util.*;
import net.mindview.atunit.*;
import net.mindview.util.*;

public class E05_TestSetupFixture2 extends HashSet<String> {
  @Test void _t1() {
    assert isEmpty();
    add("one");
  }
  @Test void _t2() {
    assert isEmpty();
    add("one");
  }
  public static void main(String[] args) throws Exception {
    OSExecute.command("java " +
      "net.mindview.atunit.AtUnit E05_TestSetupFixture2");
  }
} /* Output:
annotations.E05_TestSetupFixture2
  . _t1
  . _t2
OK (2 tests)
*///:~
