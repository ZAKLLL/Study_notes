//: polymorphism/oshape/Circle.java
package polymorphism.oshape;
import polymorphism.shape.Shape;
import static net.mindview.util.Print.*;

public class Circle extends Shape {
  @Override public void draw() { print("Circle.draw()"); }
  @Override public void erase() { print("Circle.erase()"); }
} ///:~
