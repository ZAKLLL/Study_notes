//: polymorphism/newshape/Square.java
package polymorphism.newshape;
import static net.mindview.util.Print.*;

public class Square extends Shape {
  public void draw() { print("Square.draw()"); }
  public void erase() { print("Square.erase()"); }
  public void msg() { print("Square.msg()"); }
} ///:~
