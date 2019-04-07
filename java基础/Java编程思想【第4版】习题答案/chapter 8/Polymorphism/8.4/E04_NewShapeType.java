//: polymorphism/E04_NewShapeType.java
/****************** Exercise 4 *****************
 * Add a new type of Shape to Shapes.java and
 * verify in main() that polymorphism works for
 * your new type as it does in the old types.
 ***********************************************/
package polymorphism;
import polymorphism.newshape.*;

public class E04_NewShapeType {
  public static void main(String args[]) {
    Shape[] shapes = {
      new Circle(), new Square(), new Triangle(),
      new Tetrahedron()
    };
    // Make polymorphic method calls:
    for(Shape shape : shapes) {
      shape.draw();
      shape.erase();
      shape.msg();
    }
  }
} /* Output:
Circle.draw()
Circle.erase()
Circle.msg()
Square.draw()
Square.erase()
Square.msg()
Triangle.draw()
Triangle.erase()
Triangle.msg()
Tetrahedron.draw()
Tetrahedron.erase()
Tetrahedron.msg()
*///:~
