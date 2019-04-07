//: initialization/E11_FinalizeAlwaysCalled.java
/****************** Exercise 11 ****************
 * Modify Exercise 10 so your
 * finalize() will always be called.
 ***********************************************/
package initialization;

public class E11_FinalizeAlwaysCalled {
  protected void finalize() {
    System.out.println("finalize() called");
  }
  public static void main(String args[]) {
    new E11_FinalizeAlwaysCalled();
    System.gc();
    System.runFinalization();
  }
} /* Output:
finalize() called
*///:~
