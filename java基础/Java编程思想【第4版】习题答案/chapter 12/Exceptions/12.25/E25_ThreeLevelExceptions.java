//: exceptions/E25_ThreeLevelExceptions.java
/****************** Exercise 25 ******************
 * Create a three-level hierarchy of exceptions.
 * Now create a base class A with a method that
 * throws an exception at the base of your
 * hierarchy. Inherit B from A and override the
 * method so it throws an exception at level two
 * of your hierarchy. Repeat by inheriting class
 * C from B. In main(), create a C and upcast it
 * to A, then call the method.
 ***********************************************/
package exceptions;

class Level1Exception extends Exception {}
class Level2Exception extends Level1Exception {}
class Level3Exception extends Level2Exception {}

class A {
  public void f() throws Level1Exception {
    throw new Level1Exception();
  }
}

class B extends A {
  public void f() throws Level2Exception {
    throw new Level2Exception();
  }
}

class C extends B {
  public void f() throws Level3Exception {
    throw new Level3Exception();
  }
}

public class E25_ThreeLevelExceptions {
  public static void main(String args[]) {
    A a = new C();
    try {
      a.f();
    } catch(Level1Exception e) {
      System.out.println("Caught " + e);
    }
  }
} /* Output:
Caught exceptions.Level3Exception
*///:~
