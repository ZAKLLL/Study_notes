//: innerclasses/E11_HiddenInnerClass.java
/****************** Exercise 11 *****************
 * Create a private inner class that implements a
 * public interface. Write a method that returns
 * a reference to an instance of the private
 * inner class, upcast to the interface. Show
 * that the inner class is completely hidden by
 * trying to downcast to it.
 ************************************************/
package innerclasses;
import innerclasses.exercise6.*;

class Outer5 {
  private class Inner implements SimpleInterface {
    public void f() {
      System.out.println("Outer5.Inner.f");
    }
  }
  public SimpleInterface get() { return new Inner(); }
  public Inner get2() { return new Inner(); }
}

public class E11_HiddenInnerClass {
  public static void main(String args[]) {
    Outer5 out = new Outer5();
    SimpleInterface si = out.get();
    si = out.get2();
    // Won't compile -- 'Inner' not visible:
    //! Inner i1 = out.get2();
    //! Inner i2 = (Inner)si;
  }
} ///:~
