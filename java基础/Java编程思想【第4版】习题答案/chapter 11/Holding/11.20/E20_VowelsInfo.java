//: holding/E20_VowelsInfo.java
/****************** Exercise 20 *****************
 * Modify Exercise 16 to count the occurrence of 
 * each vowel.
 ***********************************************/ 
package holding;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

public class E20_VowelsInfo {
  private final static Set<Character> vowels =
    new HashSet<Character>(Arrays.asList('a', 'e', 'o', 'u',
      'i', 'A', 'E', 'O', 'U', 'I'));
  static void
  updateStat(Map<Character,Integer> stat, char letter) {
    Character ch = Character.toLowerCase(letter);
    Integer freq = stat.get(ch);
    stat.put(ch, freq == null ? 1 : freq + 1);
  }
  public static void main(String[] args) {
    HashMap<Character,Integer> fileStat =
      new HashMap<Character,Integer>();
    HashSet<String> processedWords = new HashSet<String>();
    HashMap<Character,Integer> wordStat =
      new HashMap<Character,Integer>();
    for(String word :
        new TextFile("E20_VowelsInfo.java", "\\W+")) {
      wordStat.clear();
      for(char letter : word.toCharArray())
        if(vowels.contains(letter)) {
          updateStat(wordStat, letter);
          updateStat(fileStat, letter);
        }
      if(!processedWords.contains(word)) {
        processedWords.add(word);
        print("Vowels in " + word + ": " + wordStat);
      }
    }
    print("*************************");
    print("Vowels in the whole file: " + fileStat);
  }
} /* Output: (Sample)
Vowels in holding: {o=1, i=1}
Vowels in E20_VowelsInfo: {o=2, i=1, e=2}
Vowels in java: {a=2}
Vowels in Exercise: {i=1, e=3}
Vowels in 20: {}
Vowels in Modify: {o=1, i=1}
Vowels in 16: {}
Vowels in so: {o=1}
...
Vowels in Vowels: {o=1, e=1}
Vowels in in: {i=1}
Vowels in whole: {o=1, e=1}
Vowels in file: {i=1, e=1}
*************************
Vowels in the whole file: {o=49, i=56, a=79, u=15, e=96}
*///:~
