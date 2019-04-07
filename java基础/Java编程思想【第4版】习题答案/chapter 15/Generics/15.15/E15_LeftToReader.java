//: generics/E15_LeftToReader.java
/****************** Exercise 15 *****************
 * "Notice that f() returns a parameterized
 * TwoTuple object, while f2() returns an
 * unparameterized TwoTuple object. The compiler
 * doesn't warn about f2() in this case because the
 * return value is not being used in a parameterized
 * fashion; in a sense, it is being "upcast" to an
 * unparameterized TwoTuple. However, if you were to
 * try to capture the result of f2() into a
 * parameterized TwoTuple, the compiler would issue a
 * warning."
 *
 * Verify the previous statement.
 ***********************************************/
package generics;

public class E15_LeftToReader {
  public static void main(String args[]) {
    System.out.println("Exercise left to reader");
  }
} ///:~
