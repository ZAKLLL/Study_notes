//: polymorphism/newshape/Triangle.java
package polymorphism.newshape;
import static net.mindview.util.Print.*;

public class Triangle extends Shape {
  public void draw() { print("Triangle.draw()"); }
  public void erase() { print("Triangle.erase()"); }
  public void msg() { print("Triangle.msg()"); }
} ///:~
