//: exceptions/E10_ChangeException.java
/****************** Exercise 10 ******************
 * Create a class with two methods, f() and g().
 * In g(), throw an exception of a new type that
 * you define. In f(), call g(), catch its
 * exception and, in the catch clause, throw a
 * different exception (of a second type that you
 * define). Test your code in main().
 ***********************************************/
package exceptions;

class AnException extends Exception {}
class AnotherException extends Exception {}

public class E10_ChangeException {
  public void g() throws AnException {
    throw new AnException();
  }
  public void f() throws AnotherException {
    try {
      g();
    } catch(AnException e) {
      throw new AnotherException();
    }
  }
  public static void main(String args[]) {
    E10_ChangeException ce = new E10_ChangeException();
    try {
      ce.f();
    } catch(AnotherException e) {
      System.out.println("Caught " + e);
    }
  }
} /* Output:
Caught exceptions.AnotherException
*///:~
