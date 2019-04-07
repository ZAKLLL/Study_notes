//: exceptions/E14_OnOffSwitch.java
/****************** Exercise 14 *****************
 * Show that OnOffSwitch.java can fail by
 * throwing a RuntimeException inside the try
 * block.
 ***********************************************/
package exceptions;

public class E14_OnOffSwitch {
  static Switch sw = new Switch();
  static void f() throws OnOffException1, OnOffException2 {
    throw new RuntimeException("Inside try");
  }
  public static void main(String[] args) {
    try {
      try {
        sw.on();
        // Code that can throw exceptions...
        f();
        sw.off();
      } catch(OnOffException1 e) {
        System.out.println("OnOffException1");
        sw.off();
      } catch(OnOffException2 e) {
        System.out.println("OnOffException2");
        sw.off();
      }
    } catch(RuntimeException e) {
      System.out.println(sw);
      System.out.println("Oops! the exception '"
        + e + "' slipped through without "
        + "turning the switch off!");
    }
  }
} /* Output:
on
on
Oops! the exception 'java.lang.RuntimeException: Inside try' slipped through without turning the switch off!
*///:~
