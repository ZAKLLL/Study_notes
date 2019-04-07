//: strings/E03_TurtleRedirected.java
/****************** Exercise 3 ******************
 * Modify Turtle.java so that it sends all output
 * to System.err.
 ***********************************************/
package strings;
import java.util.*;

class Turtle {
  private final String name;
  private final Formatter f;
  public Turtle(String name, Formatter f) {
    this.name = name;
    this.f = f;
  }
  public void move(int x, int y) {
    f.format("%s The Turtle is at (%d,%d)\n", name, x, y);
  }
}

public class E03_TurtleRedirected {
  public static void main(String[] args) {
    Formatter f = new Formatter(System.err);
    Turtle tommy = new Turtle("Tommy",f),
           terry = new Turtle("Terry",f);
    tommy.move(0,0);
    terry.move(4,8);
    tommy.move(3,4);
    terry.move(2,5);
    tommy.move(3,3);
    terry.move(3,3);
  }
} /* Output:
Tommy The Turtle is at (0,0)
Terry The Turtle is at (4,8)
Tommy The Turtle is at (3,4)
Terry The Turtle is at (2,5)
Tommy The Turtle is at (3,3)
Terry The Turtle is at (3,3)
*///:~
