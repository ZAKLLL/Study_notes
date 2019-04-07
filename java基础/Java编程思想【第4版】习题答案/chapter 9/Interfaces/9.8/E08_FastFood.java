//: interfaces/E08_FastFood.java
/****************** Exercise 8 ******************
 * Create an interface called FastFood (with 
 * appropriate methods) in 
 * polymorphism.Sandwich.java, and change Sandwich 
 * so it also implements FastFood.
 ***********************************************/
package interfaces;
import polymorphism.Sandwich;
import static net.mindview.util.Print.*;

interface FastFood {
  void rushOrder();
  void gobble();
}

class FastSandwich extends Sandwich implements FastFood {
  public void rushOrder() {
    print("Rushing your sandwich order");
  }
  public void gobble() { print("Chomp! Snort! Gobble!"); }
}

public class E08_FastFood {
  public static void main(String args[]) {
    FastSandwich burger = new FastSandwich();
    print("Fries with that?");
    print("Super Size?");
    burger.rushOrder();
    burger.gobble();
  }
} /* Output:
Meal()
Lunch()
PortableLunch()
Bread()
Cheese()
Lettuce()
Sandwich()
Fries with that?
Super Size?
Rushing your sandwich order
Chomp! Snort! Gobble!
*///:~
