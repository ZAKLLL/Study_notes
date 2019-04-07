//: exceptions/E13_Finally.java
// {ThrowsException}
/****************** Exercise 13 *****************
 * Modify Exercise 9 by adding a finally clause.
 * Verify that your finally clause is executed, even
 * if a NullPointerException is thrown.
 ***********************************************/
package exceptions;

public class E13_Finally {
  public static void throwNull() {
    throw new NullPointerException();
  }
  public static void main(String args[]) {
    Thrower2 t = new Thrower2();
    try {
      t.f();
    } catch(ExBase e) {
      System.err.println("caught " + e);
    } finally {
      System.out.println("In finally clause A");
    }
    try {
      throwNull();
      t.f();
    } catch(ExBase e) {
      System.err.println("caught " + e);
    } finally {
      System.out.println("In finally clause B");
    }
  }
} ///:~
