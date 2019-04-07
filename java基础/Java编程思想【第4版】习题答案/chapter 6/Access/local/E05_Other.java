//: access/local/E05_Other.java
// A separate class in the same package cannot
// access private elements:
package access.local;

public class E05_Other {
  public E05_Other() {
    E05_AccessControl test = new E05_AccessControl();
    test.a = 1;
    //! test.b = 2; // Can't access:  private
    test.c = 3;
    test.d = 4;
    test.f1();
    //! test.f2(); // Can't access:  private
    test.f3();
    test.f4();
  }
} ///:~
