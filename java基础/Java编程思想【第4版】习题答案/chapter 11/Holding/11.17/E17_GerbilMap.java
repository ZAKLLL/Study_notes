//: holding/E17_GerbilMap.java
/****************** Exercise 17 ******************
 * Move the Gerbil class from Exercise 1
 * into a Map, and associate each Gerbil (the value) 
 * with it's name as a String (the key).
 * Use an Iterator for the keySet() to move
 * through the Map, look up the Gerbil for each key,
 * print out the key, and tell the Gerbil to hop().
 ***********************************************/ 
package holding;
import java.util.*;
import java.util.Map.Entry;

public class E17_GerbilMap {
  public static void main(String args[]) {
    Map<String,Gerbil> map = new HashMap<String,Gerbil>();
    map.put("Fuzzy", new Gerbil(1));
    map.put("Spot", new Gerbil(2));
    map.put("Joe", new Gerbil(3));
    map.put("Ted", new Gerbil(4));
    map.put("Heather", new Gerbil(5));
    Iterator<Entry<String, Gerbil>> it = 
      map.entrySet().iterator();
    while(it.hasNext()) {
      Entry<String, Gerbil> entry = it.next();
      System.out.print(entry.getKey() + ": ");
      entry.getValue().hop();
    }
  }
} /* Output:
Ted: gerbil 4 is hopping
Heather: gerbil 5 is hopping
Spot: gerbil 2 is hopping
Joe: gerbil 3 is hopping
Fuzzy: gerbil 1 is hopping
*///:~
