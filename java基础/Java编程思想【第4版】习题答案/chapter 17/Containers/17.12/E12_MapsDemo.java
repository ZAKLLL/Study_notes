//: containers/E12_MapsDemo.java
/****************** Exercise 12 *****************
 * Substitute a HashMap, a TreeMap, and a LinkedHashMap
 * in AssociativeArray.java's main().
 ***********************************************/
package containers;
import java.util.*;
import static net.mindview.util.Print.*;

public class E12_MapsDemo {
  private static void test(Map<String, String> map) {
    map.put("sky", "blue");
    map.put("grass", "green");
    map.put("ocean", "dancing");
    map.put("tree", "tall");
    map.put("earth", "brown");
    map.put("sun", "warm");
    try {
      map.put("extra", "object");
    } catch(ArrayIndexOutOfBoundsException e) {
      // Never happen!
      print("Too many objects!");
    }
    print(map);
    print(map.get("ocean"));
  }
  public static void main(String[] args) {
    test(new HashMap<String, String>());
    test(new TreeMap<String, String>());
    test(new LinkedHashMap<String, String>());
  }
} /* Output:
{sky=blue, tree=tall, grass=green, extra=object, ocean=dancing, earth=brown, sun=warm}
dancing
{earth=brown, extra=object, grass=green, ocean=dancing, sky=blue, sun=warm, tree=tall}
dancing
{sky=blue, grass=green, ocean=dancing, tree=tall, earth=brown, sun=warm, extra=object}
dancing
*///:~
