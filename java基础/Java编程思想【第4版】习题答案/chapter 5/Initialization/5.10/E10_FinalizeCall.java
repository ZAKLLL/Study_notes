//: initialization/E10_FinalizeCall.java
/****************** Exercise 10 *****************
 * Create a class with a finalize() method that
 * prints a message. In main(), create an object
 * of your class. Explain the behavior of your
 * program.
 ************************************************/
package initialization;

public class E10_FinalizeCall {
  protected void finalize() {
    System.out.println("finalize() called");
  }
  public static void main(String args[]) {
    new E10_FinalizeCall();
  }
} ///:~
