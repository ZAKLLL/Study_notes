//: arrays/E09_PeelBanana.java
/****************** Exercise 9 *****************
 * Create the classes necessary for the Peel<Banana>
 * example and show that the compiler doesn't accept
 * it. Fix the problem using an ArrayList.
 ***********************************************/
package arrays;
import java.util.*;

class Banana {
  private final int id;
  Banana(int id) { this.id = id; }
  public String toString() {
    return getClass().getSimpleName() + " " + id;
  }
}

class Peel<T> {
  T fruit;
  Peel(T fruit) { this.fruit = fruit; }
  void peel() { System.out.println("Peeling "+ fruit); }
}

public class E09_PeelBanana {
  public static void main(String[] args) {
    // Compile error:
    //! Peel<Banana>[] a = new Peel<Banana>[10];
    ArrayList<Peel<Banana>> a = 
      new ArrayList<Peel<Banana>>();
    for(int i = 0; i < 10; i++)
      a.add(new Peel<Banana>(new Banana(i)));
    for(Peel<Banana> p : a)
      p.peel();
  }
} /* Output:
Peeling Banana 0
Peeling Banana 1
Peeling Banana 2
Peeling Banana 3
Peeling Banana 4
Peeling Banana 5
Peeling Banana 6
Peeling Banana 7
Peeling Banana 8
Peeling Banana 9
*///:~
