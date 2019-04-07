//: control/E02_RandomInts.java
/****************** Exercise 2 ******************
 * Write a program to generate 25 random int
 * values. Use an if-else statement for each value
 * to classify it as greater than, less than, or
 * equal to a second randomly generated value.
************************************************/
package control;
import java.util.*;

public class E02_RandomInts {
  static Random r = new Random(47);
  public static void compareRand() {
    int a = r.nextInt();
    int b = r.nextInt();
    System.out.println("a = " + a + ", b = " + b);
    if(a < b)
      System.out.println("a < b");
    else if(a > b)
      System.out.println("a > b");
    else
      System.out.println("a = b");
  }
  public static void main(String[] args) {
    for(int i = 0; i < 25; i++)
      compareRand();
  }
} /* Output:
a = -1172028779, b = 1717241110
a < b
a = -2014573909, b = 229403722
a < b
a = 688081923, b = -1812486437
a > b
a = 809509736, b = 1791060401
a < b
a = -2076178252, b = -1128074882
a < b
a = 1150476577, b = -210207040
a > b
a = 1122537102, b = 491149179
a > b
a = 218473618, b = -1946952740
a > b
a = -843035300, b = 865149722
a < b
a = -1021916256, b = -1916708780
a > b
a = -2016789463, b = 674708281
a < b
a = -2020372274, b = 1703464645
a < b
a = 2092435409, b = 1072754767
a > b
a = -846991883, b = 488201151
a < b
a = 100996820, b = -855894611
a > b
a = -1612351948, b = 1891197608
a < b
a = -56789395, b = 849275653
a < b
a = 2078628644, b = -1099465504
a > b
a = 39716067, b = 875665968
a < b
a = 1738084688, b = -914835675
a > b
a = 1169976606, b = 1947946283
a < b
a = 691554276, b = -1004355271
a > b
a = -541407364, b = 1920737378
a < b
a = -1278072925, b = 281473985
a < b
a = -1439435803, b = -955419343
a < b
*///:~
