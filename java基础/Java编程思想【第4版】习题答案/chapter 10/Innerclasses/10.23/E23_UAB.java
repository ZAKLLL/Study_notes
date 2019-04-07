//: innerclasses/E23_UAB.java
/****************** Exercise 23 *****************
 * Create an interface U with three methods.
 * Create a class A with a method that produces a
 * reference to a U by building an anonymous
 * inner class. Create a second class B that
 * contains an array of U. B should have one
 * method that accepts and stores a reference to
 * a U in the array, a second method that sets a
 * reference in the array (specified by the
 * method argument) to null, and a third method
 * that moves through the array and calls the
 * methods in U. In main(), create a group of A
 * objects and a single B. Fill the B with U
 * references produced by the A objects. Use the
 * B to call back into all the A objects. Remove
 * some of the U references from the B.
 ***********************************************/
package innerclasses;

interface U {
  void f();
  void g();
  void h();
}

class A {
  public U getU() {
    return new U() {
      public void f() { System.out.println("A.f"); }
      public void g() { System.out.println("A.g"); }
      public void h() { System.out.println("A.h"); }
    };
  }
}

class B {
  U[] ua;
  public B(int size) {
    ua = new U[size];
  }
  public boolean add(U elem) {
    for(int i = 0; i < ua.length; i++) {
      if(ua[i] == null) {
        ua[i] = elem;
        return true;
      }
    }
    return false; // Couldn't find any space
  }
  public boolean setNull(int i) {
    if(i < 0 || i >= ua.length)
      return false; // Value out of bounds
    // (Normally throw an exception)
    ua[i] = null;
    return true;
  }
  public void callMethods() {
    for(int i = 0; i < ua.length; i++)
      if(ua[i] != null) {
        ua[i].f();
        ua[i].g();
        ua[i].h();
      }
  }
}

public class E23_UAB {
  public static void main(String args[]) {
    A[] aa = { new A(), new A(), new A() };
    B b = new B(3);
    for(int i = 0; i < aa.length; i++)
      b.add(aa[i].getU());
    b.callMethods();
    System.out.println("****");
    b.setNull(0);
    b.callMethods();
  }
} /* Output:
A.f
A.g
A.h
A.f
A.g
A.h
A.f
A.g
A.h
****
A.f
A.g
A.h
A.f
A.g
A.h
*///:~
