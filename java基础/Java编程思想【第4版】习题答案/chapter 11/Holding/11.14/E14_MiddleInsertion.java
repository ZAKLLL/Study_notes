//: holding/E14_MiddleInsertion.java
/****************** Exercise 14 *****************
 * Create an empty LinkedList<Integer>. Using a
 * ListIterator, add Integers to the List by always
 * inserting them in the middle of the List.
 ***********************************************/ 
package holding;
import java.util.*;

public class E14_MiddleInsertion {
  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<Integer>();
    ListIterator<Integer> it = list.listIterator();
    for(int i = 1; i <= 10; i++) {
      it.add(i);
      if(i % 2 == 0)
        it.previous();
    }
    System.out.println(list);
  }
} /* Output:
[1, 3, 5, 7, 9, 10, 8, 6, 4, 2]
*///:~
