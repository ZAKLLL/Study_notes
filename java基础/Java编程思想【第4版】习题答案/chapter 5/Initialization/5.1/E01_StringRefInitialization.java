//: initialization/E01_StringRefInitialization.java
/****************** Exercise 01 ****************
 * Create a class with an uninitialized
 * String reference. Demonstrate that this
 * reference is initialized by Java to null.
 ***********************************************/
package initialization;

public class E01_StringRefInitialization {
  String s;
  public static void main(String args[]) {
    E01_StringRefInitialization sri =
      new E01_StringRefInitialization();
    System.out.println("sri.s = " + sri.s);
  }
} /* Output:
sri.s = null
*///:~
