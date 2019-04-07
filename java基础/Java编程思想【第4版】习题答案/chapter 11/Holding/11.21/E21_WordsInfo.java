//: holding/E21_WordsInfo.java
/****************** Exercise 21 *****************
 * Using a Map<String,Integer>, follow the form of
 * UniqueWords.java to create a program that counts
 * the occurrence of words in a file. Sort the
 * results using Collections.sort() with a second
 * argument of String.CASE_INSENSITIVE_ORDER (to
 * produce an alphabetic sort), and display the result.
 ***********************************************/ 
package holding;
import java.util.*;
import net.mindview.util.*;

public class E21_WordsInfo {
  public static void main(String[] args) {
    Map<String,Integer> wordsStat =
      new HashMap<String,Integer>();
    for(String word :
        new TextFile("E21_WordsInfo.java", "\\W+")) {
      Integer freq = wordsStat.get(word);
      wordsStat.put(word, freq == null ? 1 : freq + 1);
    }
    List<String> keys =
      new ArrayList<String>(wordsStat.keySet());
    Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
    for(String key : keys)
      System.out.println(key + " => " + wordsStat.get(key));
  }
} /* Output: (Sample)
0 => 1
1 => 2
21 => 1
a => 4
alphabetic => 1
an => 1
and => 1
args => 1
...
W => 1
with => 1
word => 3
words => 1
wordsStat => 5
*///:~
