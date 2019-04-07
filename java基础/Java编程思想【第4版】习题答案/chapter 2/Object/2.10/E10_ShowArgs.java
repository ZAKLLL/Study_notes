//: object/E10_ShowArgs.java
// {Args: A B C}
/****************** Exercise 10 ****************
 * Write a program that prints three arguments
 * taken from the command line. 
 * You'll need to index into the command-line
 * array of Strings.
 ***********************************************/
package object;

public class E10_ShowArgs {
  public static void main(String[] args) {
    System.out.println(args[0]);
    System.out.println(args[1]);
    System.out.println(args[2]);
  }
} /* Output:
A
B
C
*///:~
