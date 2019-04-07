//: object/E05_DataOnly2.java
/****************** Exercise 5 ******************
 * Modify Exercise 4 so the values
 * of the data in DataOnly are assigned to and
 * printed in main().
 ************************************************/
package object;

public class E05_DataOnly2 {
  public static void main(String[] args) {
    E04_DataOnly d = new E04_DataOnly();
    d.i = 47;
    System.out.println("d.i = " + d.i);
    d.d = 1.1;
    System.out.println("d.d = " + d.d);
    d.b = false;
    System.out.println("d.b = " + d.b);
  }
} /* Output:
d.i = 47
d.d = 1.1
d.b = false
*///:~
