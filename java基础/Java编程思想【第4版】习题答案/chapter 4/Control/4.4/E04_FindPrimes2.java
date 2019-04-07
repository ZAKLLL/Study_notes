//: control/E04_FindPrimes2.java
package control;

import static java.lang.Math.*;
import static net.mindview.util.Print.*;

public class E04_FindPrimes2 {
  public static void main(String[] args) {
    int max = 100;
    // Get the max value from the command line,
    // if the argument has been provided:
    if(args.length != 0)
      max = Integer.parseInt(args[0]);
    boolean[] sieve = new boolean[max + 1];
    int limit = (int)floor(sqrt(max));
    printnb(1 + " ");
    if(max > 1)
      printnb(2 + " ");
    // Detect prime numbers
    for(int i = 3; i <= limit; i += 2)
      if(!sieve[i])
        for(int j = 2 * i; j <= max; j += i)
          sieve[j] = true;
    // Print prime numbers
    for(int i = 3; i <= max; i += 2)
      if(!sieve[i])
        printnb(i + " ");
  }
} /* Output:
1 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97
*///:~
