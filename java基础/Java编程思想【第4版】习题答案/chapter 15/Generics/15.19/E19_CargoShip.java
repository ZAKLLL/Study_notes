//: generics/E19_CargoShip.java
/****************** Exercise 19 *****************
 * Following the form of Store.java, build a model
 * of a containerized cargo ship.
 ************************************************/
package generics;
import java.util.*;

class Container extends ArrayList<Product> {
  public Container(int nProducts) {
    Generators.fill(this, Product.generator, nProducts);
  }
}

class CargoHold extends ArrayList<Container> {
  public CargoHold(int nContainers, int nProducts) {
    for(int i = 0; i < nContainers; i++)
      add(new Container(nProducts));
  }
}

class Crane {}
class CommandSection {}

class CargoShip extends ArrayList<CargoHold> {
  private ArrayList<Crane> cranes = new ArrayList<Crane>();
  private CommandSection cmdSection = new CommandSection();
  public CargoShip(int nCargoHolds, int nContainers,
    int nProducts) {
    for(int i = 0; i < nCargoHolds; i++)
      add(new CargoHold(nContainers, nProducts));
  }
  public String toString() {
    StringBuilder result = new StringBuilder();
    for(CargoHold ch : this)
      for(Container c : ch)
        for(Product p : c) {
          result.append(p);
          result.append("\n");
        }
    return result.toString();
  }
}

public class E19_CargoShip {
  public static void main(String[] args) {
    System.out.println(new CargoShip(14, 5, 10));
  }
} /* Output: (Sample)
258: Test, price: $400.99
861: Test, price: $160.99
868: Test, price: $417.99
207: Test, price: $268.99
551: Test, price: $114.99
278: Test, price: $804.99
520: Test, price: $554.99
140: Test, price: $530.99
704: Test, price: $250.99
575: Test, price: $24.99
674: Test, price: $440.99
826: Test, price: $484.99
...
*///:~
