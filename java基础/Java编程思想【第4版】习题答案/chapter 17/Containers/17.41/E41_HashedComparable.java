//: containers/E41_HashedComparable.java
/****************** Exercise 41 *****************
 * Modify the class in Exercise 40 so
 * that it works with HashSets and as a key in
 * HashMaps.
 ***********************************************/
package containers;
import java.util.*;
import net.mindview.util.*;

class TwoString2 implements Comparable<TwoString2> {
  String s1, s2;
  public TwoString2(String s1i, String s2i) {
    s1 = s1i;
    s2 = s2i;
  }
  public String toString() {
    return "[s1 = " + s1 + ", s2 = " + s2 + "]";
  }
  public int compareTo(TwoString2 rv) {
    String rvi = rv.s1;
    return s1.compareTo(rvi);
  }
  public int hashCode() {
    // Since the comparisons are based on s1,
    // use s1's hashCode:
    return s1.hashCode();
  }
  public boolean equals(Object obj) {
    return obj instanceof TwoString2 &&
      ((TwoString2)obj).s1.equals(s1);
  }
  private static RandomGenerator.String gen =
    new RandomGenerator.String();
  public static Generator<TwoString2> generator() {
    return new Generator<TwoString2>() {
      public TwoString2 next() {
        return new TwoString2(gen.next(), gen.next());
      }
    };
  }
}

public class E41_HashedComparable {
  public static void main(String[] args) {
    HashSet<TwoString2> hs = new HashSet<TwoString2>();
    HashMap<TwoString2,Integer> hm =
      new HashMap<TwoString2,Integer>();
    Generator<TwoString2> gen = TwoString2.generator();
    hs.addAll(CollectionData.list(gen, 20));
    for(int i = 0; i < 20; i++)
      hm.put(gen.next(), i);
    System.out.println("hs = " + hs);
    System.out.println("hm = " + hm);
  }
} /* Output:
hs = [[s1 = tviIOeV, s2 = gTALcXW], [s1 = SOSdgHz, s2 = IVPJWjR], [s1 = gqiaxxE, s2 = AJJmzMs], [s1 = opwBTwV, s2 = gLhnfDa], [s1 = qUocIBR, s2 = BnFGofp], [s1 = oYWMNvq, s2 = euTpnXs], [s1 = qjncLdZ, s2 = kpTkWng], [s1 = slJrLvp, s2 = fFvHVEE], [s1 = BqtXbJV, s2 = IMLPoVg], [s1 = HvHqXum, s2 = cXZJoog], [s1 = TcQrGse, s2 = GZMmJMR], [s1 = oEsuEcU, s2 = OneOEdL], [s1 = WHkjUrU, s2 = kZPgwsq], [s1 = zqvPZdC, s2 = ohnYVma], [s1 = PzDyCyR, s2 = FJQAHxx], [s1 = JjhcYVz, s2 = PCQQBnK], [s1 = YNzbrny, s2 = GcFOWZn], [s1 = qUCBbkI, s2 = naMesbt], [s1 = TQzGSrL, s2 = rcVwhKO], [s1 = smwHLGE, s2 = ahKcxrE]]
hm = {[s1 = JYmXfyL, s2 = LXdaCcH]=13, [s1 = vWlnklx, s2 = ieETLdl]=12, [s1 = kkXmZpE, s2 = lUnpFsT]=0, [s1 = YOzPgpj, s2 = HZAeudW]=19, [s1 = eRzDqLz, s2 = lEcBfEA]=16, [s1 = DPbmXRQ, s2 = TmKrsgn]=14, [s1 = hLIpVVl, s2 = pNVvUuk]=15, [s1 = vAusWxO, s2 = ikOSUvL]=9, [s1 = BcatxLS, s2 = lXXnWSa]=11, [s1 = fHggOPz, s2 = kihQiHq]=5, [s1 = qYNYZGi, s2 = IhHLLNA]=1, [s1 = WVdlNmu, s2 = TJqCrcf]=6, [s1 = CJIkjyU, s2 = ZPzWRSy]=10, [s1 = xcwSwRL, s2 = RRDDPuI]=18, [s1 = hfFkVrA, s2 = gepwlXE]=2, [s1 = PKNjWUl, s2 = MJOTlae]=3, [s1 = haoyWJS, s2 = yIPMqvD]=7, [s1 = CmEtqPh, s2 = yXPQvPa]=17, [s1 = GpArCjV, s2 = SpNQlmO]=8, [s1 = olwrSJY, s2 = FSRWuZJ]=4}
*///:~
