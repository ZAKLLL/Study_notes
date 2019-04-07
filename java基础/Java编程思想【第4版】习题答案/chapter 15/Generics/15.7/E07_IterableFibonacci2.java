//: generics/E07_IterableFibonacci2.java
/****************** Exercise 7 *****************
 * Use composition instead of inheritance to adapt
 * Fibonacci to make it Iterable.
 ************************************************/
package generics;
import java.util.*;

class IterableFibonacci implements Iterable<Integer> {
  private Fibonacci fib = new Fibonacci();
  private int n;
  public IterableFibonacci(int count) { n = count; }
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      public boolean hasNext() { return n > 0; }
      public Integer next() {
        --n;
        return fib.next();
      }
      public void remove() { // Not implemented
        throw new UnsupportedOperationException();
      }
    };
  }
}

public class E07_IterableFibonacci2 {
  public static void main(String[] args) {
    for(int i : new IterableFibonacci(18))
      System.out.print(i + " ");
  }
} /* Output:
1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584
*///:~
