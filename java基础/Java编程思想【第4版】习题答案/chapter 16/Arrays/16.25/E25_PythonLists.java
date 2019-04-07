//: arrays/E25_PythonLists.java
/****************** Exercise 25 *****************
 * Rewrite PythonLists.py in Java.
 ************************************************/
package arrays;
import java.util.*;
import static net.mindview.util.Print.*;

public class E25_PythonLists {
  public static void main(String[] args) {
    List<Integer> aList =
      new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
    print(aList.getClass().getSimpleName());
    print(aList);
    print(aList.get(4));
    aList.add(6);
    aList.addAll(Arrays.asList(7, 8));
    print(aList);
    List<Integer> aSlice = aList.subList(2, 4);
    print(aSlice);
    class MyArrayList<T> extends ArrayList<T> {
      MyArrayList(Collection<? extends T> c) { super(c); }
      MyArrayList(int initialCapacity) {
        super(initialCapacity);
      }
      List<T> getReversed() {
        List<T> reversed = new MyArrayList<T>(size());
        ListIterator<T> it = listIterator(size());
        while(it.hasPrevious())
          reversed.add(it.previous());
        return reversed;
      }
    }
    MyArrayList<Integer> list2 =
      new MyArrayList<Integer>(aList);
    print(list2.getClass().getSimpleName());
    print(list2.getReversed());
  }
} /* Output:
ArrayList
[1, 2, 3, 4, 5]
5
[1, 2, 3, 4, 5, 6, 7, 8]
[3, 4]
MyArrayList
[8, 7, 6, 5, 4, 3, 2, 1]
*///:~
