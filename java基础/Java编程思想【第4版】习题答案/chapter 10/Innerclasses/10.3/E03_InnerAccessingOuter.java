//: innerclasses/E03_InnerAccessingOuter.java
/****************** Exercise 3 *****************
 * Modify Exercise 1 so Outer has a private
 * String field (initialized by the constructor),
 * and Inner has a toString() that displays this
 * field. Create an object of type Inner and
 * display it.
 ***********************************************/
package innerclasses;

class Outer2 {
  private final String data;
  class Inner {
    public String toString() { return data; }
  }
  Outer2(String data) { this.data = data; }
  Inner getInner() { return new Inner(); }
}

public class E03_InnerAccessingOuter {
  public static void main(String[] args) {
    Outer2 o = new Outer2("Inner accessing outer!");
    Outer2.Inner i = o.getInner();
    System.out.println(i.toString());
  }
} /* Output:
Inner accessing outer!
*///:~
