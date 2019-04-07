//: annotations/E08_TestPrivateMethod.java
/****************** Exercise 08 *****************
 * Add a non-private @TestProperty method (described
 * above) to create a class with a private method. 
 * Call this method in your test code.
 ***********************************************/
package annotations;
import net.mindview.atunit.*;
import net.mindview.util.*;

class PrivateMethod {
  private boolean hidden() { return true; }
  @TestProperty boolean visible() { return hidden(); }
}

public class E08_TestPrivateMethod extends PrivateMethod {
  @Test void _hidden() { assert visible(); }
  public static void main(String[] args) {
    OSExecute.command("java " +
      " net.mindview.atunit.AtUnit E08_TestPrivateMethod");
  }
} /* Output:
annotations.E08_TestPrivateMethod
  . _hidden
OK (1 tests)
*///:~
