//: generics/E30_AutoboxingUnboxingTest.java
/****************** Exercise 30 *****************
 * Create a Holder for each of the primitive
 * wrapper types, and show that autoboxing and
 * autounboxing works for the set() and get()
 * methods of each instance.
 ************************************************/
package generics;
import static net.mindview.util.Print.*;

public class E30_AutoboxingUnboxingTest {
  public static void main(String[] args) {
    Holder<Integer> hi = new Holder<Integer>();
    hi.set(1);
    int i = hi.get();
    print(i == 1);
    Holder<Byte> hb = new Holder<Byte>();
    hb.set((byte)1);
    byte b = hb.get();
    print(b == 1);
    Holder<Short> hs = new Holder<Short>();
    hs.set((short)1);
    short s = hs.get();
    print(s == 1);
    Holder<Long> hl = new Holder<Long>();
    hl.set(1L);
    long l = hl.get();
    print(l == 1);
    Holder<Float> hf = new Holder<Float>();
    hf.set(1.0F);
    float f = hf.get();
    print(f == 1.0F);
    Holder<Double> hd = new Holder<Double>();
    hd.set(1.0);
    double d = hd.get();
    print(d == 1.0);
    Holder<Character> hc = new Holder<Character>();
    hc.set('A');
    char c = hc.get();
    print(c == 'A');
    Holder<Boolean> hbool = new Holder<Boolean>();
    hbool.set(true);
    boolean bool = hbool.get();
    print(bool);
  }
} /* Output:
true
true
true
true
true
true
true
true
*///:~
