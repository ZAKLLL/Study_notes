//: polymorphism/E02_Shapes.java
/****************** Exercise 2 ****************
 * Add the @Override annotation to the shapes 
 * example.
 ***********************************************/
package polymorphism;
import polymorphism.shape.Shape;
import polymorphism.oshape.*;

public class E02_Shapes {
  private static RandomShapeGenerator gen =
    new RandomShapeGenerator();
  public static void main(String[] args) {
    Shape[] shapes = new Shape[9];
    // Fill up the array with shapes:
    for(int i = 0; i < shapes.length; i++)
      shapes[i] = gen.next();
    // Make polymorphic method calls:
    for(Shape shape : shapes)
      shape.draw();
  }
} /* Output:
Triangle.draw()
Triangle.draw()
Square.draw()
Triangle.draw()
Square.draw()
Triangle.draw()
Square.draw()
Triangle.draw()
Circle.draw()
*///:~
