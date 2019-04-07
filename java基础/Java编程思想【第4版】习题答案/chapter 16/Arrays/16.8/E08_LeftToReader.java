//: arrays/E08_LeftToReader.java
/****************** Exercise 8 *****************
 * "Erasure gets in the way again-this example
 * attempts to create arrays of types that have been
 * erased, and are thus unknown types. Notice that
 * you can create an array of Object, and cast it,
 * but you get an "unchecked" warning at compile
 * time because the array doesn't really hold or
 * dynamically check for type T. That is, if I create
 * a String[], Java will enforce at both compile time
 * and run time that I can only place String objects
 * in that array. However, if I create an Object[],
 * I can put anything except primitive types in that
 * array."
 *
 * Demonstrate the assertions in the previous
 * paragraph.
 ***********************************************/
package arrays;

public class E08_LeftToReader {
  public static void main(String args[]) {
    System.out.println("Exercise left to reader");
  }
} ///:~
