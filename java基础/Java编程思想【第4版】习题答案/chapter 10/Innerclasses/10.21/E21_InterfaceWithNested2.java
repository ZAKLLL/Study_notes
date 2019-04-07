//: innerclasses/E21_InterfaceWithNested2.java
/****************** Exercise 21 ******************
 * Create an interface with a nested class
 * and a static method that calls the methods
 * of your interface and displays the results.
 * Implement your interface and pass an instance of
 * your implementation to the method.
 ***********************************************/
package innerclasses;

interface I {
  void f();
  void g();
  class Nested {
    static void call(I impl) {
      System.out.println("Calling I.f()");
      impl.f();
      System.out.println("Calling I.g()");
      impl.g();
    }
  }
}

public class E21_InterfaceWithNested2 {
  public static void main(String[] args) {
    I impl = new I() {
      public void f() {}
      public void g() {}
    };
    I.Nested.call(impl);
  }
} /* Output:
Calling I.f()
Calling I.g()
*///:~
