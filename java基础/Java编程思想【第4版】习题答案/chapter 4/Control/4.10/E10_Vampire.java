//: control/E10_Vampire.java
/****************** Exercise 10 *********************
 * A vampire number has an even number of digits and
 * is formed by multiplying a pair of numbers containing
 * half the number of digits of the result. The digits
 * are taken from the original number in any order.
 * Pairs of trailing zeroes are not allowed. Examples
 * include:
 * 1260 = 21 * 60
 * 1827 = 21 * 87
 * 2187 = 27 * 81
 * Write a program that finds all the 4-digit vampire
 * numbers. (Suggested by Dan Forhan.)
 ****************************************************/
package control;

public class E10_Vampire {
  public static void main(String[] args) {
    int[] startDigit = new int[4];
    int[] productDigit = new int[4];
    for(int num1 = 10; num1 <= 99; num1++)
      for(int num2 = num1; num2 <= 99; num2++) {
        // Pete Hartley's theoretical result:
        // If x·y is a vampire number then 
        // x·y == x+y (mod 9)
        if((num1 * num2) % 9 != (num1 + num2) % 9)
          continue;
        int product = num1 * num2;
        startDigit[0] = num1 / 10;
        startDigit[1] = num1 % 10;
        startDigit[2] = num2 / 10;
        startDigit[3] = num2 % 10;
        productDigit[0] = product / 1000;
        productDigit[1] = (product % 1000) / 100;
        productDigit[2] = product % 1000 % 100 / 10;
        productDigit[3] = product % 1000 % 100 % 10;
        int count = 0;
        for(int x = 0; x < 4; x++)
          for(int y = 0; y < 4; y++) {
            if(productDigit[x] == startDigit[y]) {
              count++;
              productDigit[x] = -1;
              startDigit[y] = -2;
              if(count == 4)
                System.out.println(num1 + " * " + num2
                  + " : " + product);
            }
          }
      }
  }
} /* Output:
15 * 93 : 1395
21 * 60 : 1260
21 * 87 : 1827
27 * 81 : 2187
30 * 51 : 1530
35 * 41 : 1435
80 * 86 : 6880
*///:~
