//: holding/E08_GerbilIterator.java
/****************** Exercise 8 ******************
 * Modify Exercise 1 so it uses an Iterator to
 * move through the List while calling hop().
 ***********************************************/ 
package holding;
import java.util.*;

public class E08_GerbilIterator {
  public static void main(String args[]) {
    ArrayList<Gerbil> gerbils = new ArrayList<Gerbil>();
    for(int i = 0; i < 10; i++)
      gerbils.add(new Gerbil(i));
    for(Iterator<Gerbil> it = gerbils.iterator();
        it.hasNext();
        it.next().hop());
  }
} /* Output:
gerbil 0 is hopping
gerbil 1 is hopping
gerbil 2 is hopping
gerbil 3 is hopping
gerbil 4 is hopping
gerbil 5 is hopping
gerbil 6 is hopping
gerbil 7 is hopping
gerbil 8 is hopping
gerbil 9 is hopping
*///:~
