//: holding/E12_ListIterators.java
/****************** Exercise 12 *****************
 * Create two List<Integer>s of the same size, 
 * and populate one of them. Use ListIterators 
 * to insert elements from the first List into
 * the second in reverse order. (You may want to
 * explore a number of different ways to solve
 * this problem.)
 ***********************************************/ 
package holding;
import java.util.*;

public class E12_ListIterators {
  static void reverse(List<Integer> list) {
    ListIterator<Integer> fwd = list.listIterator();
    ListIterator<Integer> rev =
      list.listIterator(list.size());
    int mid = list.size() >> 1;
    for(int i = 0; i < mid; i++) {
      Integer tmp = fwd.next();
      fwd.set(rev.previous());
      rev.set(tmp);
    }
  }
  public static void main(String[] args) {
    List<Integer> src =
      Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Integer> dest = new LinkedList<Integer>(src);
    System.out.println("source: " + src);
    System.out.println("destination: " + dest);
    reverse(dest);
    System.out.println("source: " + src);
    System.out.println("destination: " + dest);
  }
} /* Output:
source: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
destination: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
source: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
destination: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
*///:~
