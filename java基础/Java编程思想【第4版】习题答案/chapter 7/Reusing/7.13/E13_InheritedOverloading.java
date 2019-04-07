//: reusing/E13_InheritedOverloading.java
/****************** Exercise 13 *****************
 * Create a class with a method that is
 * overloaded three times. Inherit a new class,
 * add a new overloading of the method, and show
 * that all four methods are available in the
 * derived class.
 ***********************************************/
package reusing;

class ThreeOverloads {
  public void f(int i) {
    System.out.println("f(int i)");
  }
  public void f(char c) {
    System.out.println("f(char c)");
  }
  public void f(double d) {
    System.out.println("f(double d)");
  }
}

class MoreOverloads extends ThreeOverloads {
  public void f(String s) {
    System.out.println("f(String s)");
  }
}

public class E13_InheritedOverloading {
  public static void main(String args[]) {
    MoreOverloads mo = new MoreOverloads();
    mo.f(1);
    mo.f('c');
    mo.f(1.1);
    mo.f("Hello");
  }
} /* Output:
f(int i)
f(char c)
f(double d)
f(String s)
*///:~
