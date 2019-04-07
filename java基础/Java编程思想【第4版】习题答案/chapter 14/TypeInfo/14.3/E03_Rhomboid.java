//: typeinfo/E03_Rhomboid.java
/****************** Exercise 3 ******************
 * Add Rhomboid to Shapes.java. Create a
 * Rhomboid, upcast it to a Shape, then downcast
 * it back to a Rhomboid. Try downcasting to a
 * Circle and see what happens.
 ***********************************************/
package typeinfo;
import java.util.*;

class Rhomboid extends Shape {
  public String toString() { return "Rhomboid"; }
}

public class E03_Rhomboid {
  public static void main(String[] args) {
    List<Shape> shapes = Arrays.asList(
      new Circle(), new Square(), new Triangle(),
      new Rhomboid()
    );
    for(Shape shape : shapes)
      shape.draw();
    // Upcast to shape:
    Shape shape = new Rhomboid();
    // Downcast to Rhomboid:
    Rhomboid r = (Rhomboid)shape;
    // Downcast to Circle. Succeeds at compile time,
    // but fails at runtime with a ClassCastException:
    //! Circle c = (Circle)shape;
  }
} /* Output:
Circle.draw()
Square.draw()
Triangle.draw()
Rhomboid.draw()
*///:~
