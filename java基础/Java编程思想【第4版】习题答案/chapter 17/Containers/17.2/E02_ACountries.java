//: containers/E02_ACountries.java
/****************** Exercise 2 ******************
 * Produce a Map and a Set containing all the
 * countries that begin with 'A'.
 ************************************************/
package containers;
import java.util.*;
import static net.mindview.util.Countries.*;

public class E02_ACountries {
  public static void main(String[] args) {
    TreeMap<String,String> map =
      new TreeMap<String,String>(capitals());
    TreeSet<String> set = new TreeSet<String>(names());
    String b = null;
    for(String s : map.keySet())
      if(s.startsWith("B")) {
        b = s;
        break;
      }
    Map<String,String> aMap = map.headMap(b);
    Set<String> aSet = set.headSet(b);
    System.out.println("aMap = " + aMap);
    System.out.println("aSet = " + aSet);
  }
} /* Output:
aMap = {AFGHANISTAN=Kabul, ALBANIA=Tirana, ALGERIA=Algiers, ANDORRA=Andorra la Vella, ANGOLA=Luanda, ANTIGUA AND BARBUDA=Saint John's, ARGENTINA=Buenos Aires, ARMENIA=Yerevan, AUSTRALIA=Canberra, AUSTRIA=Vienna, AZERBAIJAN=Baku}
aSet = [AFGHANISTAN, ALBANIA, ALGERIA, ANDORRA, ANGOLA, ANTIGUA AND BARBUDA, ARGENTINA, ARMENIA, AUSTRALIA, AUSTRIA, AZERBAIJAN]
*///:~
