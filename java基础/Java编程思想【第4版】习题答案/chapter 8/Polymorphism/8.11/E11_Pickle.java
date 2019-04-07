//: polymorphism/E11_Pickle.java
/****************** Exercise 11 *****************
 * Add class Pickle to Sandwich.java.
 ***********************************************/
package polymorphism;
import static net.mindview.util.Print.*;

class Pickle {
  Pickle() { print("Pickle()"); }
}

class Sandwich2 extends PortableLunch {
  Bread b = new Bread();
  Cheese c = new Cheese();
  Lettuce l = new Lettuce();
  Pickle p = new Pickle();
  Sandwich2() { print("Sandwich()"); }
}

public class E11_Pickle {
  public static void main(String args[]) {
    new Sandwich2();
  }
} /* Output:
Meal()
Lunch()
PortableLunch()
Bread()
Cheese()
Lettuce()
Pickle()
Sandwich()
*///:~
