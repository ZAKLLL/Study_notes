//: access/E05_Other.java
// A separate class in the other package cannot
// access private, protected and package elements:
package access;
import access.local.E05_AccessControl;

public class E05_Other {
  public E05_Other() {
    E05_AccessControl test = new E05_AccessControl();
    test.a = 1;
    //! test.b = 2; // Can't access: private
    //! test.c = 3; // Can't access: protected
    //! test.d = 4; // Can't access: package
    test.f1();
    //! test.f2(); // Can't access:  private
    //! test.f3(); // Can't access: protected
    //! test.f4(); // Can't access: package
  }
} ///:~
