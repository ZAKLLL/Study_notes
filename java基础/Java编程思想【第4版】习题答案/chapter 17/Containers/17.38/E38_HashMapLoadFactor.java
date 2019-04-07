//: containers/E38_HashMapLoadFactor.java
// {RunByHand}
/****************** Exercise 38 *****************
 * Look up the HashMap class in the JDK documentation.
 * Create a HashMap, fill it with elements, and
 * determine the load factor. Test the lookup speed
 * with this map, then attempt to increase the speed
 * by making a new HashMap with a larger initial
 * capacity and copying the old map into the new one,
 * then run your lookup speed test again on the new map.
 ***********************************************/
package containers;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

public class E38_HashMapLoadFactor {
  public static
  void testGet(Map<String,String> filledMap, int n) {
    for(int tc = 0; tc < 1000000; tc++)
      for(int i = 0; i < Countries.DATA.length; i++) {
        String key = Countries.DATA[i][0];
        filledMap.get(key);
      }
  }
  public static void main(String args[]) {
    // Initial capacity 16:
    HashMap<String,String> map1 =
      new HashMap<String,String>();
    // Fill to less than threshold:
    map1.putAll(Countries.capitals(11));
    // Initial capacity 32:
    HashMap<String,String> map2 =
      new HashMap<String,String>(32);
    map2.putAll(map1);
    long t1 = System.currentTimeMillis();
    testGet(map1, 11);
    long t2 = System.currentTimeMillis();
    print("map1 : " + (t2 - t1));
    t1 = System.currentTimeMillis();
    testGet(map2, 11);
    t2 = System.currentTimeMillis();
    print("map2 : " + (t2 - t1));
  }
} ///:~
