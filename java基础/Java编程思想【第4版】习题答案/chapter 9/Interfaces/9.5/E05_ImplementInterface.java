//: interfaces/E05_ImplementInterface.java
/****************** Exercise 5 ******************
 * Create an interface with three methods in its 
 * own package. Implement the interface in a 
 * different package.
 ***********************************************/
package interfaces;
import interfaces.ownpackage.*;
import static net.mindview.util.Print.*;

class ImplementInterface implements AnInterface {
  public void f() { print("ImplementInterface.f"); }
  public void g() { print("ImplementInterface.g"); }
  public void h() { print("ImplementInterface.h"); }
}

public class E05_ImplementInterface {
  public static void main(String args[]) {
    ImplementInterface imp = new ImplementInterface();
    imp.f();
    imp.g();
    imp.h();
  }
} /* Output:
ImplementInterface.f
ImplementInterface.g
ImplementInterface.h
*///:~
