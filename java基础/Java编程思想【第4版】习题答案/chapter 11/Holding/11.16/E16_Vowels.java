//: holding/E16_Vowels.java
/****************** Exercise 16 *****************
 * Create a Set of the vowels. Working from
 * UniqueWords.java, count and display the number of
 * vowels in each input word, and also display the
 * total number of vowels in the input file.
 ***********************************************/ 
package holding;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

public class E16_Vowels {
  private final static Set<Character> vowels =
    new HashSet<Character>(Arrays.asList('a', 'e', 'o', 'u',
      'i', 'A', 'E', 'O', 'U', 'I'));
  public static void main(String[] args) {
    HashSet<String> processedWords = new HashSet<String>();
    int fileVowels = 0;
    int wordVowels;
    for(String word :
        new TextFile("E16_Vowels.java", "\\W+")) {
      wordVowels = 0;
      for(char letter : word.toCharArray())
        if(vowels.contains(letter))
          wordVowels++;
      if(!processedWords.contains(word)) {
        processedWords.add(word);
        print(word + " has " + wordVowels + " vowel(s)");
      }
      fileVowels += wordVowels;
    }
    print("Total number of vowels in file: " + fileVowels);
  }
} /* Output: (Sample)
holding has 2 vowel(s)
E16_Vowels has 3 vowel(s)
java has 2 vowel(s)
Exercise has 4 vowel(s)
16 has 0 vowel(s)
Create has 3 vowel(s)
...
contains has 3 vowel(s)
add has 1 vowel(s)
print has 1 vowel(s)
has has 1 vowel(s)
vowel has 2 vowel(s)
s has 0 vowel(s)
Total has 2 vowel(s)
Total number of vowels in file: 240
*///:~
