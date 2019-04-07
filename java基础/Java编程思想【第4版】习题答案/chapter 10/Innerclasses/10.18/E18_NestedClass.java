//: innerclasses/E18_NestedClass.java
/****************** Exercise 18 *****************
 * Create a class containing a nested class.
 * In main(), create an instance of the nested
 * class.
 ************************************************/
package innerclasses;

public class E18_NestedClass {
  static class Nested {
    void f() { System.out.println("Nested.f"); }
  }
  public static void main(String args[]) {
    Nested ne = new Nested();
    ne.f();
  }
}

class Other {
  // Specifying the nested type outside
  // the scope of the class:
  void f() {
    E18_NestedClass.Nested ne =
      new E18_NestedClass.Nested();
  }
} /* Output:
Nested.f
*///:~
