//: polymorphism/newshape/Circle.java
package polymorphism.newshape;
import static net.mindview.util.Print.*;

public class Circle extends Shape {
  public void draw() { print("Circle.draw()"); }
  public void erase() { print("Circle.erase()"); }
  public void msg() { print("Circle.msg()"); }
} ///:~
