//: object/E07_Incrementable.java
/****************** Exercise 7 ******************
 * Turn the Incrementable code fragments into a
 * working program.
 ************************************************/
package object;

class StaticTest {
  static int i = 47;
}

public class E07_Incrementable {
  static void increment() { StaticTest.i++; }
  public static void main(String[] args) {
    E07_Incrementable sf = new E07_Incrementable();
    sf.increment();
    E07_Incrementable.increment();
    increment();
  }
} ///:~
