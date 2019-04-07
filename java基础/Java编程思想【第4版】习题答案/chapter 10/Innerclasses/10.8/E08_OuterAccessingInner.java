//: innerclasses/E08_OuterAccessingInner.java
/****************** Exercise 8 *****************
 * Determine whether an outer class has access to
 * the private elements of its inner class.
 ***********************************************/
package innerclasses;

class Outer4 {
  class Inner {
    private int j;
    private void h() {
      System.out.println("Inner.h called");
      System.out.println("Inner.j = " + j);
    }
  }
  public void testInnerAccess() {
    Inner i = new Inner();
    i.j = 47;
    i.h();
  }
}

public class E08_OuterAccessingInner {
  public static void main(String args[]) {
    Outer4 o = new Outer4();
    o.testInnerAccess();
  }
} /* Output:
Inner.h called
Inner.j = 47
*///:~
