//: control/E09_Fibonacci.java
// {Args: 20}
/****************** Exercise 9 **********************
 * A Fibonacci sequence is the sequence of numbers 1,
 * 1, 2, 3, 5, 8, 13, 21, 34, etc., where each
 * number (from the third on) is the sum of the previous
 * two. Create a method that takes an integer as an
 * argument and displays that many Fibonacci numbers
 * starting from the beginning. If, e.g., you run java
 * Fibonacci 5 (where Fibonacci is the name of the
 * class) the output will be: 1, 1, 2, 3, 5.
 ****************************************************/
package control;

public class E09_Fibonacci {
  static int fib(int n) {
    if (n <= 2)
      return 1;
    return fib(n-1) + fib(n-2);
  }
  public static void main(String[] args) {
    // Get the max value from the command line:
    int n = Integer.parseInt(args[0]);
    if(n < 0) {
      System.out.println("Cannot use negative numbers");
      return;
    }
    for(int i = 1; i <= n; i++)
      System.out.print(fib(i) + ", ");
  }
} /* Output:
1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765,
*///:~
