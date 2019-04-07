//: containers/E42_AlphaComparable.java
/****************** Exercise 42 *****************
 * Modify Exercise 40 so that an alphabetic sort
 * is used.
 ***********************************************/
package containers;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

class TwoStringAlphabetic
implements Comparable<TwoStringAlphabetic> {
  String s1, s2;
  public TwoStringAlphabetic(String s1i, String s2i) {
    s1 = s1i;
    s2 = s2i;
  }
  public String toString() {
    return "[s1 = " + s1 + ", s2 = " + s2 + "]";
  }
  public int compareTo(TwoStringAlphabetic rv) {
    String rvi = rv.s1;
    return s1.toLowerCase().compareTo(rvi.toLowerCase());
  }
  private static RandomGenerator.String gen =
    new RandomGenerator.String();
  public static Generator<TwoStringAlphabetic> generator() {
    return new Generator<TwoStringAlphabetic>() {
      public TwoStringAlphabetic next() {
        return new TwoStringAlphabetic(
          gen.next(), gen.next());
      }
    };
  }
}

class CompareSecondAlphabetic
implements Comparator<TwoStringAlphabetic> {
  public int compare(TwoStringAlphabetic sc1,
    TwoStringAlphabetic sc2) {
    return sc1.s1.toLowerCase().compareTo(
      sc2.s1.toLowerCase());
  }
}

public class E42_AlphaComparable {
  public static void main(String[] args) {
    TwoStringAlphabetic [] array =
      new TwoStringAlphabetic [10];
    Generated.array(array, TwoStringAlphabetic.generator());
    print("before sorting, array = " +
      Arrays.asList(array));
    Arrays.sort(array);
    print("\nafter sorting, array = " +
      Arrays.asList(array));
    Arrays.sort(array, new CompareSecondAlphabetic());
    print("\nafter sorting with CompareSecondAlphabetic," +
      " array = " +
      Arrays.asList(array));
    ArrayList<TwoStringAlphabetic> list =
      new ArrayList<TwoStringAlphabetic>(
        CollectionData.list(
          TwoStringAlphabetic.generator(), 10));
    TwoStringAlphabetic zeroth = list.get(0);
    print("\nbefore sorting, list = " + list);
    Collections.sort(list);
    print("\nafter sorting, list = " + list);
    Comparator<TwoStringAlphabetic> comp =
      new CompareSecondAlphabetic();
    Collections.sort(list, comp);
    print("\nafter sorting with CompareSecondAlphabetic," +
     " list = "
      + list);
    int index =
      Collections.binarySearch(list, zeroth, comp);
    print("\nFormer zeroth element " +
      zeroth + " now located at " + index);
  }
} /* Output:
before sorting, array = [[s1 = YNzbrny, s2 = GcFOWZn], [s1 = TcQrGse, s2 = GZMmJMR], [s1 = oEsuEcU, s2 = OneOEdL], [s1 = smwHLGE, s2 = ahKcxrE], [s1 = qUCBbkI, s2 = naMesbt], [s1 = WHkjUrU, s2 = kZPgwsq], [s1 = PzDyCyR, s2 = FJQAHxx], [s1 = HvHqXum, s2 = cXZJoog], [s1 = oYWMNvq, s2 = euTpnXs], [s1 = gqiaxxE, s2 = AJJmzMs]]

after sorting, array = [[s1 = gqiaxxE, s2 = AJJmzMs], [s1 = HvHqXum, s2 = cXZJoog], [s1 = oEsuEcU, s2 = OneOEdL], [s1 = oYWMNvq, s2 = euTpnXs], [s1 = PzDyCyR, s2 = FJQAHxx], [s1 = qUCBbkI, s2 = naMesbt], [s1 = smwHLGE, s2 = ahKcxrE], [s1 = TcQrGse, s2 = GZMmJMR], [s1 = WHkjUrU, s2 = kZPgwsq], [s1 = YNzbrny, s2 = GcFOWZn]]

after sorting with CompareSecondAlphabetic, array = [[s1 = gqiaxxE, s2 = AJJmzMs], [s1 = HvHqXum, s2 = cXZJoog], [s1 = oEsuEcU, s2 = OneOEdL], [s1 = oYWMNvq, s2 = euTpnXs], [s1 = PzDyCyR, s2 = FJQAHxx], [s1 = qUCBbkI, s2 = naMesbt], [s1 = smwHLGE, s2 = ahKcxrE], [s1 = TcQrGse, s2 = GZMmJMR], [s1 = WHkjUrU, s2 = kZPgwsq], [s1 = YNzbrny, s2 = GcFOWZn]]

before sorting, list = [[s1 = slJrLvp, s2 = fFvHVEE], [s1 = qjncLdZ, s2 = kpTkWng], [s1 = JjhcYVz, s2 = PCQQBnK], [s1 = SOSdgHz, s2 = IVPJWjR], [s1 = BqtXbJV, s2 = IMLPoVg], [s1 = qUocIBR, s2 = BnFGofp], [s1 = TQzGSrL, s2 = rcVwhKO], [s1 = zqvPZdC, s2 = ohnYVma], [s1 = opwBTwV, s2 = gLhnfDa], [s1 = tviIOeV, s2 = gTALcXW]]

after sorting, list = [[s1 = BqtXbJV, s2 = IMLPoVg], [s1 = JjhcYVz, s2 = PCQQBnK], [s1 = opwBTwV, s2 = gLhnfDa], [s1 = qjncLdZ, s2 = kpTkWng], [s1 = qUocIBR, s2 = BnFGofp], [s1 = slJrLvp, s2 = fFvHVEE], [s1 = SOSdgHz, s2 = IVPJWjR], [s1 = TQzGSrL, s2 = rcVwhKO], [s1 = tviIOeV, s2 = gTALcXW], [s1 = zqvPZdC, s2 = ohnYVma]]

after sorting with CompareSecondAlphabetic, list = [[s1 = BqtXbJV, s2 = IMLPoVg], [s1 = JjhcYVz, s2 = PCQQBnK], [s1 = opwBTwV, s2 = gLhnfDa], [s1 = qjncLdZ, s2 = kpTkWng], [s1 = qUocIBR, s2 = BnFGofp], [s1 = slJrLvp, s2 = fFvHVEE], [s1 = SOSdgHz, s2 = IVPJWjR], [s1 = TQzGSrL, s2 = rcVwhKO], [s1 = tviIOeV, s2 = gTALcXW], [s1 = zqvPZdC, s2 = ohnYVma]]

Former zeroth element [s1 = slJrLvp, s2 = fFvHVEE] now located at 5
*///:~
