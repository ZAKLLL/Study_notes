//: generics/E37_Mixins2.java
/****************** Exercise 37 *****************
 * Add a new mixin class Colored to Mixins.java,
 * mix it in to Mixin, and show that it works.
 ************************************************/
package generics;
import java.awt.Color;
import java.util.*;

interface Colored { Color getColor(); }

class ColoredImp implements Colored {
  private static Random rnd = new Random(47);
  private final Color clr =
    new Color(rnd.nextInt(16777216));  // 2^24
  public Color getColor() { return clr; }
}

class Mixin2 extends Mixin implements Colored {
  private Colored colored = new ColoredImp();
  public Color getColor() { return colored.getColor(); }
}

public class E37_Mixins2 {
  public static void main(String[] args) {
    Mixin2 mixin1 = new Mixin2(), mixin2 = new Mixin2();
    mixin1.set("test string 1");
    mixin2.set("test string 2");
    System.out.println(mixin1.get() + " " +
      mixin1.getStamp() +  " " +
      mixin1.getSerialNumber() + " " + mixin1.getColor());
    System.out.println(mixin2.get() + " " +
      mixin2.getStamp() +  " " +
      mixin2.getSerialNumber() + " " + mixin2.getColor());
  }
} /* Output: (78% match)
test string 1 1135953794432 1 java.awt.Color[r=186,g=36,b=66]
test string 2 1135953794479 2 java.awt.Color[r=102,g=91,b=5]
*///:~
