//: typeinfo/coffee2/Coffee.java
package typeinfo.coffee2;
import java.util.*;
import typeinfo.factory.Factory;

@SuppressWarnings("unchecked")
public class Coffee {
  private static int counter = 0;
  private int id = counter++;
  private static 
    List<Factory<? extends Coffee>> coffeeFactories =
      Arrays.asList(new Americano.Factory(), 
        new Breve.Factory(), new Latte.Factory(), 
        new Mocha.Factory(), new Cappucino.Factory());
  private static Random rand = new Random(47);
  public static Coffee createRandom() {
    int n = rand.nextInt(coffeeFactories.size());
    return coffeeFactories.get(n).create();
  }  
  public String toString() {
    return getClass().getSimpleName() + " " + id;
  }
} ///:~
