//: exceptions/E19_GuardedLostMessage.java
/****************** Exercise 19 *****************
 * Repair the problem in LostMessage.java by
 * guarding the call in the finally clause.
 ***********************************************/
package exceptions;

public class E19_GuardedLostMessage {
  void f() throws VeryImportantException {
    throw new VeryImportantException();
  }
  void dispose() throws HoHumException {
    throw new HoHumException();
  }
  public static void main(String[] args) {
    try {
      LostMessage lm = new LostMessage();
      try {
        lm.f();
      } finally {
        try {
          lm.dispose();
        } catch(HoHumException e) {
          System.out.println(e);
        }
      }
    } catch(Exception e) {
      System.out.println(e);
    }
  }
} /* Output:
A trivial exception
A very important exception!
*///:~
