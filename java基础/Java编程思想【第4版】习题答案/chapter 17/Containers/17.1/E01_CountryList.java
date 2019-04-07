//: containers/E01_CountryList.java
/****************** Exercise 1 ******************
 * Create a List (try both ArrayList and LinkedList)
 * and fill it using Countries. Sort the list and
 * print it, then apply Collections.shuffle() to the
 * list repeatedly, printing it each time so you
 * can see how the shuffle() method randomizes the
 * list differently each time.
 ************************************************/
package containers;
import java.util.*;
import static net.mindview.util.Print.*;
import static net.mindview.util.Countries.*;

public class E01_CountryList {
  private static Random rnd = new Random(47);
  public static void main(String[] args) {
    List<String> l = new ArrayList<String>(names(8));
    Collections.sort(l);
    print("sorted: " + l);
    for(int i = 1; i < 5; i++) {
      Collections.shuffle(l, rnd);
      print("shuffled (" + i + "): " + l);
    }
  }
} /* Output:
sorted: [ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI, CAMEROON, CAPE VERDE]
shuffled (1): [BURKINA FASO, BOTSWANA, CAMEROON, ALGERIA, CAPE VERDE, ANGOLA, BENIN, BURUNDI]
shuffled (2): [BENIN, CAMEROON, BURKINA FASO, CAPE VERDE, BURUNDI, BOTSWANA, ANGOLA, ALGERIA]
shuffled (3): [ALGERIA, BURKINA FASO, BOTSWANA, ANGOLA, CAMEROON, BURUNDI, CAPE VERDE, BENIN]
shuffled (4): [BENIN, ANGOLA, BOTSWANA, BURUNDI, CAMEROON, CAPE VERDE, ALGERIA, BURKINA FASO]
*///:~
