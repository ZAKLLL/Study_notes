//: exceptions/E03_ArrayIndexBounds.java
/****************** Exercise 3 ******************
 * Write code to generate and catch an
 * ArrayIndexOutOfBoundsException.
 ***********************************************/
package exceptions;

public class E03_ArrayIndexBounds {
  public static void main(String args[]) {
    char[] array = new char[10];
    try {
      array[10] = 'x';
    } catch(ArrayIndexOutOfBoundsException e) {
      System.out.println("e = " + e);
    }
  }
} /* Output:
e = java.lang.ArrayIndexOutOfBoundsException: 10
*///:~
