//: containers/E40_ComparableClass.java
/****************** Exercise 40 *****************
 * Create a class containing two String objects
 * and make it Comparable so that the comparison
 * only cares about the first String. Fill an array
 * and an ArrayList with objects of your class,
 * using the RandomGenerator generator. Demonstrate that
 * sorting works properly. Now make a Comparator that
 * only cares about the second String, and demonstrate
 * that sorting works properly. Also perform a binary
 * search using your Comparator.
 ***********************************************/
package containers;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

class TwoString implements Comparable<TwoString> {
  String s1, s2;
  public TwoString(String s1i, String s2i) {
    s1 = s1i;
    s2 = s2i;
  }
  public String toString() {
    return "[s1 = " + s1 + ", s2 = " + s2 + "]";
  }

  public int compareTo(TwoString rv) {
    String rvi = rv.s1;
    return s1.compareTo(rvi);
  }
  private static RandomGenerator.String gen =
    new RandomGenerator.String();
  public static Generator<TwoString> generator() {
    return new Generator<TwoString>() {
      public TwoString next() {
        return new TwoString(gen.next(), gen.next());
      }
    };
  }
}

class CompareSecond implements Comparator<TwoString> {
  public int compare(TwoString sc1, TwoString sc2) {
    return sc1.s2.compareTo(sc2.s2);
  }
}

public class E40_ComparableClass {
  public static void main(String[] args) {
    TwoString[] array = new TwoString[10];
    Generated.array(array, TwoString.generator());
    print("before sorting, array = " +
      Arrays.asList(array));
    Arrays.sort(array);
    print("\nafter sorting, array = " +
      Arrays.asList(array));
    Arrays.sort(array, new CompareSecond());
    print(
      "\nafter sorting with CompareSecond, array = " +
      Arrays.asList(array));
    ArrayList<TwoString> list = new ArrayList<TwoString>(
      CollectionData.list(TwoString.generator(), 10));
    TwoString zeroth = list.get(0);
    print("\nbefore sorting, list = " + list);
    Collections.sort(list);
    print("\nafter sorting, list = " + list);
    Comparator<TwoString> comp = new CompareSecond();
    Collections.sort(list, comp);
    print(
      "\nafter sorting with CompareSecond, list = "
      + list);
    int index =
      Collections.binarySearch(list, zeroth, comp);
    print("\nFormer zeroth element " +
      zeroth + " now located at " + index);
  }
} /* Output:
before sorting, array = [[s1 = YNzbrny, s2 = GcFOWZn], [s1 = TcQrGse, s2 = GZMmJMR], [s1 = oEsuEcU, s2 = OneOEdL], [s1 = smwHLGE, s2 = ahKcxrE], [s1 = qUCBbkI, s2 = naMesbt], [s1 = WHkjUrU, s2 = kZPgwsq], [s1 = PzDyCyR, s2 = FJQAHxx], [s1 = HvHqXum, s2 = cXZJoog], [s1 = oYWMNvq, s2 = euTpnXs], [s1 = gqiaxxE, s2 = AJJmzMs]]

after sorting, array = [[s1 = HvHqXum, s2 = cXZJoog], [s1 = PzDyCyR, s2 = FJQAHxx], [s1 = TcQrGse, s2 = GZMmJMR], [s1 = WHkjUrU, s2 = kZPgwsq], [s1 = YNzbrny, s2 = GcFOWZn], [s1 = gqiaxxE, s2 = AJJmzMs], [s1 = oEsuEcU, s2 = OneOEdL], [s1 = oYWMNvq, s2 = euTpnXs], [s1 = qUCBbkI, s2 = naMesbt], [s1 = smwHLGE, s2 = ahKcxrE]]

after sorting with CompareSecond, array = [[s1 = gqiaxxE, s2 = AJJmzMs], [s1 = PzDyCyR, s2 = FJQAHxx], [s1 = TcQrGse, s2 = GZMmJMR], [s1 = YNzbrny, s2 = GcFOWZn], [s1 = oEsuEcU, s2 = OneOEdL], [s1 = smwHLGE, s2 = ahKcxrE], [s1 = HvHqXum, s2 = cXZJoog], [s1 = oYWMNvq, s2 = euTpnXs], [s1 = WHkjUrU, s2 = kZPgwsq], [s1 = qUCBbkI, s2 = naMesbt]]

before sorting, list = [[s1 = slJrLvp, s2 = fFvHVEE], [s1 = qjncLdZ, s2 = kpTkWng], [s1 = JjhcYVz, s2 = PCQQBnK], [s1 = SOSdgHz, s2 = IVPJWjR], [s1 = BqtXbJV, s2 = IMLPoVg], [s1 = qUocIBR, s2 = BnFGofp], [s1 = TQzGSrL, s2 = rcVwhKO], [s1 = zqvPZdC, s2 = ohnYVma], [s1 = opwBTwV, s2 = gLhnfDa], [s1 = tviIOeV, s2 = gTALcXW]]

after sorting, list = [[s1 = BqtXbJV, s2 = IMLPoVg], [s1 = JjhcYVz, s2 = PCQQBnK], [s1 = SOSdgHz, s2 = IVPJWjR], [s1 = TQzGSrL, s2 = rcVwhKO], [s1 = opwBTwV, s2 = gLhnfDa], [s1 = qUocIBR, s2 = BnFGofp], [s1 = qjncLdZ, s2 = kpTkWng], [s1 = slJrLvp, s2 = fFvHVEE], [s1 = tviIOeV, s2 = gTALcXW], [s1 = zqvPZdC, s2 = ohnYVma]]

after sorting with CompareSecond, list = [[s1 = qUocIBR, s2 = BnFGofp], [s1 = BqtXbJV, s2 = IMLPoVg], [s1 = SOSdgHz, s2 = IVPJWjR], [s1 = JjhcYVz, s2 = PCQQBnK], [s1 = slJrLvp, s2 = fFvHVEE], [s1 = opwBTwV, s2 = gLhnfDa], [s1 = tviIOeV, s2 = gTALcXW], [s1 = qjncLdZ, s2 = kpTkWng], [s1 = zqvPZdC, s2 = ohnYVma], [s1 = TQzGSrL, s2 = rcVwhKO]]

Former zeroth element [s1 = slJrLvp, s2 = fFvHVEE] now located at 4
*///:~
