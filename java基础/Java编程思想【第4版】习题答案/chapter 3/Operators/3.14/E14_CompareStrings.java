//: operators/E14_CompareStrings.java
/****************** Exercise 14 *****************
 * Write a method that compares two String arguments 
 * using all the Boolean comparisons and print the 
 * results. Perform the equals() test for the == and 
 * !=. In main(), call your method with different 
 * String objects.
 ************************************************/
package operators;

public class E14_CompareStrings {
  public static void p(String s, boolean b) {
    System.out.println(s + ": " + b);
  }
  public static void compare(String lval, String rval) {
    System.out.println("lval: " + lval + " rval: " + rval);
    //! p("lval < rval: " + lval < rval);
    //! p("lval > rval: " + lval > rval);
    //! p("lval <= rval: " + lval <= rval);
    //! p("lval >= rval: " + lval >= rval);
    p("lval == rval", lval == rval);
    p("lval != rval", lval != rval);
    p("lval.equals(rval)", lval.equals(rval));
  }
  public static void main(String[] args) {
    compare("Hello", "Hello");
    // Force creation of separate object:
    String s = new String("Hello");
    compare("Hello", s);
    compare("Hello", "Goodbye");
  }
} /* Output:
lval: Hello rval: Hello
lval == rval: true
lval != rval: false
lval.equals(rval): true
lval: Hello rval: Hello
lval == rval: false
lval != rval: true
lval.equals(rval): true
lval: Hello rval: Goodbye
lval == rval: false
lval != rval: true
lval.equals(rval): false
*///:~
