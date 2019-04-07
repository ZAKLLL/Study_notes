//: innerclasses/E05_InstanceOfInner.java
/****************** Exercise 5 *****************
 * Create a class with an inner class. In a
 * separate class, make an instance of the inner
 * class.
 ***********************************************/
package innerclasses;

class Outer3 {
  class Inner {
    { System.out.println("Inner created"); }
  }
}

public class E05_InstanceOfInner {
  public static void main(String args[]) {
    Outer3 o = new Outer3();
    Outer3.Inner i = o.new Inner();
  }
} /* Output:
Inner created
*///:~
