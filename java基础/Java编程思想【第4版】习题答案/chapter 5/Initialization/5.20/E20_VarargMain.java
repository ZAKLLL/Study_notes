//: initialization/E20_VarargMain.java
// {Args: These, are, some, strings}
/****************** Exercise 20 *****************
 * Create a main() that uses varargs instead
 * of the ordinary main() syntax. Print all the
 * elements in the resulting args array. Test it
 * with various numbers of command-line
 * arguments.
 ************************************************/
package initialization;

public class E20_VarargMain {
  public static void main(String... args) {
    E19_VarargStringArray.printStrings(args);
  }
} /* Output:
These,
are,
some,
strings
*///:~
