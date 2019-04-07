//: holding/E05_IntegerListFeatures.java
/****************** Exercise 5 ******************
 * Use Integers instead of Pets to modify 
 * ListFeatures.java  (remember autoboxing).
 * Explain any difference in results.
 ***********************************************/ 
package holding;
import java.util.*;
import static net.mindview.util.Print.*;

public class E05_IntegerListFeatures {
  static Random rand = new Random(47);
  public static void main(String[] args) {
    List<Integer> ints = new ArrayList<Integer>(
      Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    print("1: " + ints);
    ints.add(8);
    print("2: " + ints);
    print("3: " + ints.contains(8));
    ints.remove(Integer.valueOf(8));
    Integer i = ints.get(2);
    print("4: " +  i + " " + ints.indexOf(i));
    Integer j = Integer.valueOf(1);
    print("5: " + ints.indexOf(j));
    print("6: " + ints.remove(j));
    print("7: " + ints.remove(i));
    print("8: " + ints);
    ints.add(3, 0);
    print("9: " + ints);
    List<Integer> sub = ints.subList(1, 4);
    print("subList: " + sub);
    print("10: " + ints.containsAll(sub));
    Collections.sort(sub);
    print("sorted subList: " + sub);
    print("11: " + ints.containsAll(sub));
    Collections.shuffle(sub, rand);
    print("shuffled subList: " + sub);
    print("12: " + ints.containsAll(sub));
    List<Integer> copy = new ArrayList<Integer>(ints);
    sub = Arrays.asList(ints.get(1), ints.get(4));
    print("sub: " + sub);
    copy.retainAll(sub);
    print("13: " + copy);
    copy = new ArrayList<Integer>(ints);
    copy.remove(2);
    print("14: " + copy);
    copy.removeAll(sub);
    print("15: " + copy);
    copy.set(1, 9);
    print("16: " + copy);
    copy.addAll(2, sub);
    print("17: " + copy);
    print("18: " + ints.isEmpty());
    ints.clear();
    print("19: " + ints);
    print("20: " + ints.isEmpty());
    ints.addAll(Arrays.asList(1, 2, 3, 4));
    print("21: " + ints);
    Object[] o = ints.toArray();
    print("22: " + o[3]);
    Integer[] ia = ints.toArray(new Integer[0]);
    print("22: " + ia[3]);
  }
} /* Output:
1: [1, 2, 3, 4, 5, 6, 7]
2: [1, 2, 3, 4, 5, 6, 7, 8]
3: true
4: 3 2
5: 0
6: true
7: true
8: [2, 4, 5, 6, 7]
9: [2, 4, 5, 0, 6, 7]
subList: [4, 5, 0]
10: true
sorted subList: [0, 4, 5]
11: true
shuffled subList: [4, 0, 5]
12: true
sub: [4, 6]
13: [4, 6]
14: [2, 4, 5, 6, 7]
15: [2, 5, 7]
16: [2, 9, 7]
17: [2, 9, 4, 6, 7]
18: false
19: []
20: true
21: [1, 2, 3, 4]
22: 4
22: 4
*///:~
