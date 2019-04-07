//: initialization/E04_OverloadedConstructor.java
/****************** Exercise 4 *****************
 * Add an overloaded constructor to Exercise 3 that
 * takes a String argument and prints it along with 
 * your message.
 ***********************************************/
package initialization;

public class E04_OverloadedConstructor {
  E04_OverloadedConstructor() {
    System.out.println("Default constructor");
  }
  E04_OverloadedConstructor(String s) {
    System.out.println("String arg constructor");
    System.out.println(s);
  }
  public static void main(String args[]) {
    // Call default version:
    new E04_OverloadedConstructor();
    // Call overloaded version:
    new E04_OverloadedConstructor("Overloaded");
  }
} /* Output:
Default constructor
String arg constructor
Overloaded
*///:~
