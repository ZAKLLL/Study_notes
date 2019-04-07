//: containers/E03_VerifySet.java
/****************** Exercise 3 ******************
 * Using Countries, fill a Set with the same data
 * multiple times, then verify that the Set ends up
 * with only one of each instance. Try this with
 * HashSet, LinkedHashSet, and TreeSet.
 ************************************************/
package containers;
import java.util.*;
import static net.mindview.util.Countries.*;

public class E03_VerifySet {
  public static void main(String[] args) {
    Set<String> set = new LinkedHashSet<String>();
    for(int i = 0; i < 5; i++)
      set.addAll(names(10));
    System.out.println(set);
  }
} /* Output:
[ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI, CAMEROON, CAPE VERDE, CENTRAL AFRICAN REPUBLIC, CHAD]
*///:~
