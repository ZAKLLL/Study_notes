//: initialization/E14_StaticStringInitialization.java
/****************** Exercise 14 *****************
 * Create a class with a static String field that
 * is initialized at the point of definition, and
 * another one initialized by the static
 * block. Add a static method that prints both
 * fields and demonstrates that they are both
 * initialized before they are used.
 ************************************************/
package initialization;

public class E14_StaticStringInitialization {
  static String s1 = "Initialized at definition";
  static String s2;
  static { s2 = "Initialized in static block"; }
  public static void main(String args[]) {
    System.out.println("s1 = " + s1);
    System.out.println("s2 = " + s2);
  }
} /* Output:
s1 = Initialized at definition
s2 = Initialized in static block
*///:~
