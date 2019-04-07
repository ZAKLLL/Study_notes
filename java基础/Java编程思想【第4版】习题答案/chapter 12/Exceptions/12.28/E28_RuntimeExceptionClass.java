//: exceptions/E28_RuntimeExceptionClass.java
// {ThrowsException}
/****************** Exercise 28 ******************
 * Modify Exercise 4 so that the custom exception 
 * class inherits from RuntimeException, and show 
 * that the compiler allows you to leave out the 
 * try block.
 ***********************************************/
package exceptions;

class MyRuntimeException extends RuntimeException {
  public MyRuntimeException(String s) { super(s); }
}

public class E28_RuntimeExceptionClass {
  public static void main(String args[]) {
    throw new MyRuntimeException("MyRuntimeException msg");
  }
} ///:~
