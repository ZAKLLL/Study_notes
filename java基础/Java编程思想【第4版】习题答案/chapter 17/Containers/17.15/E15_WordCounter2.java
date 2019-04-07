//: containers/E15_WordCounter2.java
/****************** Exercise 15 *****************
 * Repeat Exercise 13 using a SlowMap.
 ***********************************************/
package containers;
import java.util.*;
import net.mindview.util.*;

public class E15_WordCounter2 {
  public static void main(String[] args) {
    List<String> words =
      new TextFile("E12_MapsDemo.java", "\\W+");
    SlowMap<String,Integer> map =
      new SlowMap<String,Integer>();
    for(String word : words) {
      Integer freq = map.get(word);
      map.put(word, freq == null ? 1 : freq + 1);
    }
    System.out.println(map);
  }
} /* Output:
{AssociativeArray=1, test=4, args=1, package=1, Output=1, tree=4, sky=4, import=2, objects=1, happen=1, static=3, Too=1, grass=4, extra=4, get=1, Map=1, class=1, and=1, Print=1, many=1, util=2, String=9, try=1, Substitute=1, put=7, dancing=7, warm=4, catch=1, sun=4, E12_MapsDemo=2, net=1, private=1, 12=1, ocean=5, object=4, map=10, LinkedHashMap=2, tall=4, mindview=1, green=4, e=1, containers=2, Never=1, a=3, HashMap=2, TreeMap=2, Exercise=1, void=2, in=1, print=3, main=2, new=3, blue=4, s=1, java=3, public=2, brown=4, earth=4, ArrayIndexOutOfBoundsException=1}
*///:~
