//: object/E08_StaticTest.java
/****************** Exercise 8 ******************
 * Write a program to demonstrate that no
 * matter how many objects you create of a
 * particular class, there is only one instance
 * of a particular static field in that class.
 ************************************************/
package object;

public class E08_StaticTest {
  static int i = 47;
  public static void main(String[] args) {
    E08_StaticTest st1 = new E08_StaticTest();
    E08_StaticTest st2 = new E08_StaticTest();
    System.out.println(st1.i + " == " + st2.i);
    st1.i++;
    System.out.println(st1.i + " == " + st2.i);
  }
} /* Output:
47 == 47
48 == 48
*///:~
