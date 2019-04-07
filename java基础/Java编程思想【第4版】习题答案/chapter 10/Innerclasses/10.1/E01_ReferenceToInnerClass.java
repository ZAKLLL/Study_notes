//: innerclasses/E01_ReferenceToInnerClass.java
/****************** Exercise 1 *****************
 * Write a class named Outer containing an
 * inner class named Inner. Add a method to Outer
 * that returns an object of type Inner. In
 * main(), create and initialize a reference to
 * an Inner.
 ***********************************************/
package innerclasses;

class Outer {
  class Inner {
    { System.out.println("Inner created"); }
  }
  Inner getInner() { return new Inner(); }
}

public class E01_ReferenceToInnerClass {
  public static void main(String[] args) {
    Outer o = new Outer();
    Outer.Inner i = o.getInner();
  }
} /* Output:
Inner created
*///:~
