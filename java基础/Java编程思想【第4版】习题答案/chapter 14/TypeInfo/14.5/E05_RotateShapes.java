//: typeinfo/E05_RotateShapes.java
/****************** Exercise 5 ******************
 * Implement a rotate(Shape) method in Shapes.java,
 * such that it checks to see if it is rotating a 
 * Circle (and, if so, doesn't perform the 
 * operation).
 ***********************************************/
package typeinfo;
import java.util.*;

abstract class RShape {
  void draw() { System.out.println(this + ".draw()"); }
  abstract public String toString();
  void rotate(int degrees) {
    System.out.println("Rotating " + this +
      " by " + degrees + " degrees");
  }
}

class RCircle extends RShape {
  public String toString() { return "Circle"; }
  void rotate(int degrees) {
    throw new UnsupportedOperationException();
  }
}

class RSquare extends RShape {
  public String toString() { return "Square"; }
}

class RTriangle extends RShape {
  public String toString() { return "Triangle"; }
}

public class E05_RotateShapes {
  static void rotateAll(List<RShape> shapes) {
    for(RShape shape : shapes)
      if(!(shape instanceof RCircle))
        shape.rotate(45);
  }

  public static void main(String[] args) {
    List<RShape> shapes = Arrays.asList(
      new RCircle(), new RSquare(), new RTriangle()
    );
    rotateAll(shapes);
  }
} /* Output:
Rotating Square by 45 degrees
Rotating Triangle by 45 degrees
*///:~
