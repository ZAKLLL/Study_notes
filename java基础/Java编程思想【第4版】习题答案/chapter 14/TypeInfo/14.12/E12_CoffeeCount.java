//: typeinfo/E12_CoffeeCount.java
/****************** Exercise 12 *****************
 * Use TypeCounter with the CoffeeGenerator.java
 * class in the Generics chapter.
 ***********************************************/
package typeinfo;
import java.util.*;
import generics.coffee.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

public class E12_CoffeeCount {
  public static void main(String[] args) {
    TypeCounter counter = new TypeCounter(Coffee.class);
    for(Iterator<Coffee> it =
        new CoffeeGenerator(20).iterator(); it.hasNext();) {
      Coffee coffee = it.next();
      printnb(coffee.getClass().getSimpleName() + " ");
      counter.count(coffee);
    }
    print();
    print(counter);
  }
} /* Output:
Americano Latte Americano Mocha Mocha Breve Americano Latte Cappuccino Cappuccino Americano Americano Mocha Breve Breve Americano Americano Mocha Latte Americano
{Latte=3, Americano=8, Cappuccino=2, Coffee=20, Breve=3, Mocha=4}
*///:~
