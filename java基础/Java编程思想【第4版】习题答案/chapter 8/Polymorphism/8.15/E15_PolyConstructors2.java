//: polymorphism/E15_PolyConstructors2.java
/****************** Exercise 15 *****************
 * Add a RectangularGlyph to PolyConstructors.java
 * and demonstrate the problem described in this
 * section.
 ***********************************************/
package polymorphism;
import static net.mindview.util.Print.*;

class RectangularGlyph extends Glyph {
  private int width = 4;
  private int height = 5;
  RectangularGlyph(int width, int height) {
    this.width = width;
    this.height = height;
    print("RectangularGlyph.RectangularGlyph(), width = " +
      width + ", height = " + height);
  }
  void draw() {
    print("RectangularGlyph.draw(), area = " + width *
      height);
  }
}

public class E15_PolyConstructors2 {
  public static void main(String[] args) {
    new RoundGlyph(5);
    new RectangularGlyph(2,2);
  }
} /* Output:
Glyph() before draw()
RoundGlyph.draw(), radius = 0
Glyph() after draw()
RoundGlyph.RoundGlyph(), radius = 5
Glyph() before draw()
RectangularGlyph.draw(), area = 0
Glyph() after draw()
RectangularGlyph.RectangularGlyph(), width = 2, height = 2
*///:~
