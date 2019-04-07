//: containers/E13_WordCounter.java
/****************** Exercise 13 *****************
 * Use AssociativeArray.java to create a
 * word-occurrence counter, mapping String to Integer.
 * Using the net.mindview.util.TextFile utility in
 * this book, open a text file and break up the
 * words in that file using whitespace and
 * punctuation, and count the occurrence of the
 * words in that file.
 ***********************************************/
package containers;
import java.util.*;
import net.mindview.util.*;

class AssociativeArray<K,V> {
  private Object[][] pairs;
  private int index;
  public AssociativeArray(int length) {
    pairs = new Object[length][2];
  }
  public void put(K key, V value) {
    for(int i = 0; i < index; i++)
      if(key.equals(pairs[i][0])) {
        pairs[i] = new Object[]{ key, value };
        return;
      }
    if(index >= pairs.length)
      throw new ArrayIndexOutOfBoundsException();
    pairs[index++] = new Object[]{ key, value };
  }
  @SuppressWarnings("unchecked")
  public V get(K key) {
    for(int i = 0; i < index; i++)
      if(key.equals(pairs[i][0]))
        return (V)pairs[i][1];
    return null; // Did not find key
  }
  public String toString() {
    StringBuilder result = new StringBuilder();
    for(int i = 0; i < index; i++) {
      result.append(pairs[i][0].toString());
      result.append(" : ");
      result.append(pairs[i][1].toString());
      if(i < index - 1)
        result.append("\n");
    }
    return result.toString();
  }
}

public class E13_WordCounter {
  public static void main(String[] args) {
    List<String> words =
      new TextFile("E12_MapsDemo.java", "\\W+");
    AssociativeArray<String, Integer> map =
      new AssociativeArray<String, Integer>(words.size());
    for(String word : words) {
      Integer freq = map.get(word);
      map.put(word, freq == null ? 1 : freq + 1);
    }
    System.out.println(map);
  }
} /* Output: (Sample)
containers : 1
E12_MapsDemo : 2
java : 3
Exercise : 1
12 : 1
Substitute : 1
a : 3
HashMap : 2
TreeMap : 2
...
objects : 1
get : 1
args : 1
new : 3
Output : 1
*///:~
