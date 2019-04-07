//: innerclasses/exercise6b/SimpleClass.java
package innerclasses.exercise6b;
import innerclasses.exercise6.*;

public class SimpleClass {
  protected class Inner implements SimpleInterface {
    // Force constructor to be public:
    public Inner() {}
    public void f() {}
  }
} ///:~
