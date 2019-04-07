//: generics/E32_FixedSizeStackTest.java
/****************** Exercise 32 *****************
 * Verify that FixedSizeStack in GenericCast.java
 * generates exceptions if you try to go out of
 * its bounds. Does this mean that bounds-checking
 * code is not required?
 ************************************************/
package generics;

public class E32_FixedSizeStackTest {
  public static void main(String[] args) {
    FixedSizeStack<Integer> stack =
      new FixedSizeStack<Integer>(1);
    stack.push(1);
    System.out.println(stack.pop());
    try {
      stack.pop();
    } catch(ArrayIndexOutOfBoundsException e) {
      System.out.println(e.toString());
    }
    stack = new FixedSizeStack<Integer>(1);
    stack.push(2);
    try {
      stack.push(2);
    } catch(ArrayIndexOutOfBoundsException e) {
      System.out.println(e.toString());
    }
  }
} /* Output:
1
java.lang.ArrayIndexOutOfBoundsException: -1
java.lang.ArrayIndexOutOfBoundsException: 1
*///:~
