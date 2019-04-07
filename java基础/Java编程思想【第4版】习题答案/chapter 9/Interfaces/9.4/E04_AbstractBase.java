//: interfaces/E04_AbstractBase.java
/****************** Exercise 4 *****************
 * Create an abstract class with no methods.
 * Derive a class and add a method. Create a
 * static method that downcasts a reference from 
 * the base class to the derived class and calls 
 * the method. Demonstrate that it works in main(). 
 * Eliminate the need for the downcast by moving 
 * the abstract declaration to the base class.
 ***********************************************/
package interfaces;

abstract class NoMethods {}

class Extended1 extends NoMethods {
  public void f() {
    System.out.println("Extended1.f");
  }
}

abstract class WithMethods {
  abstract public void f();
}

class Extended2 extends WithMethods {
  public void f() {
    System.out.println("Extended2.f");
  }
}

public class E04_AbstractBase {
  public static void test1(NoMethods nm) {
    // Must downcast to access f():
    ((Extended1)nm).f();
  }
  public static void test2(WithMethods wm) {
    // No downcast necessary:
    wm.f();
  }
  public static void main(String args[]) {
    NoMethods nm = new Extended1();
    test1(nm);
    WithMethods wm = new Extended2();
    test2(wm);
  }
} /* Output:
Extended1.f
Extended2.f
*///:~
