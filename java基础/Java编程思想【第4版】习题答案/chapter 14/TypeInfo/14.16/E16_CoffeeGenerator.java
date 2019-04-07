//: typeinfo/E16_CoffeeGenerator.java
/****************** Exercise 16 *****************
 * Modify the Coffee hierarchy in the Generics
 * chapter to use Registered Factories.
 ***********************************************/
package typeinfo;
import java.util.*;
import net.mindview.util.*;
import typeinfo.coffee2.*;

public class E16_CoffeeGenerator
implements Generator<Coffee>, Iterable<Coffee> {
  public E16_CoffeeGenerator() {}
  private int size = 0;
  public E16_CoffeeGenerator(int sz) { size = sz; }
  public Coffee next() { return Coffee.createRandom(); }
  class CoffeeIterator implements Iterator<Coffee> {
    int count = size;
    public boolean hasNext() { return count > 0; }
    public Coffee next() {
      count--;
      return E16_CoffeeGenerator.this.next();
    }
    public void remove() {} // Not implemented
  };
  public Iterator<Coffee> iterator() {
    return new CoffeeIterator();
  }
  public static void main(String[] args) {
    for(Coffee c : new E16_CoffeeGenerator(10))
      System.out.println(c);
  }
} /* Output:
Mocha 0
Americano 1
Mocha 2
Breve 3
Breve 4
Cappucino 5
Mocha 6
Americano 7
Latte 8
Latte 9
*///:~
