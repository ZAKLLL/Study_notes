//: reusing/E05_SimpleInheritance.java
/****************** Exercise 5 *****************
 * Create classes A and B with default
 * constructors (empty argument lists) that
 * announce themselves. Inherit a new class
 * called C from A, and create a member of class
 * B inside C. Do not create a constructor for C.
 * Create an object of class C and observe the
 * results.
 ***********************************************/
package reusing;

class A {
  public A() { System.out.println("A()"); }
}

class B {
  public B() { System.out.println("B()"); }
}

class C extends A {
  B b = new B();
}

public class E05_SimpleInheritance {
  public static void main(String args[]) {
    new C();
  }
} /* Output:
A()
B()
*///:~
