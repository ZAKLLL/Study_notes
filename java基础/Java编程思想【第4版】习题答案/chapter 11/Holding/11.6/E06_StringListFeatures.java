//: holding/E06_StringListFeatures.java
/****************** Exercise 6 ******************
 * Using Strings instead of Pets, modify 
 * ListFeatures.java . Explain any difference in
 * results.
 ***********************************************/ 
package holding;
import java.util.*;
import static net.mindview.util.Print.*;

public class E06_StringListFeatures {
  static Random rand = new Random(47);
  public static void main(String[] args) {
    List<String> strs = new ArrayList<String>(
      Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
    print("1: " + strs);
    strs.add("H");
    print("2: " + strs);
    print("3: " + strs.contains("H"));
    strs.remove("H");
    String s1 = strs.get(2);
    print("4: " +  s1 + " " + strs.indexOf(s1));
    String s2 = "A";
    print("5: " + strs.indexOf(s2));
    print("6: " + strs.remove(s2));
    print("7: " + strs.remove(s1));
    print("8: " + strs);
    strs.add(3, "0");
    print("9: " + strs);
    List<String> sub = strs.subList(1, 4);
    print("subList: " + sub);
    print("10: " + strs.containsAll(sub));
    Collections.sort(sub);
    print("sorted subList: " + sub);
    print("11: " + strs.containsAll(sub));
    Collections.shuffle(sub, rand);
    print("shuffled subList: " + sub);
    print("12: " + strs.containsAll(sub));
    List<String> copy = new ArrayList<String>(strs);
    sub = Arrays.asList(strs.get(1), strs.get(4));
    print("sub: " + sub);
    copy.retainAll(sub);
    print("13: " + copy);
    copy = new ArrayList<String>(strs);
    copy.remove(2);
    print("14: " + copy);
    copy.removeAll(sub);
    print("15: " + copy);
    copy.set(1, "I");
    print("16: " + copy);
    copy.addAll(2, sub);
    print("17: " + copy);
    print("18: " + strs.isEmpty());
    strs.clear();
    print("19: " + strs);
    print("20: " + strs.isEmpty());
    strs.addAll(Arrays.asList("A", "B", "C", "D"));
    print("21: " + strs);
    Object[] o = strs.toArray();
    print("22: " + o[3]);
    String[] sa = strs.toArray(new String[0]);
    print("22: " + sa[3]);
  }
} /* Output:
1: [A, B, C, D, E, F, G]
2: [A, B, C, D, E, F, G, H]
3: true
4: C 2
5: 0
6: true
7: true
8: [B, D, E, F, G]
9: [B, D, E, 0, F, G]
subList: [D, E, 0]
10: true
sorted subList: [0, D, E]
11: true
shuffled subList: [D, 0, E]
12: true
sub: [D, F]
13: [D, F]
14: [B, D, E, F, G]
15: [B, E, G]
16: [B, I, G]
17: [B, I, D, F, G]
18: false
19: []
20: true
21: [A, B, C, D]
22: D
22: D
*///:~
