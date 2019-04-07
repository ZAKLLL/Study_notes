//: holding/E25_WordsInfo3.java
/****************** Exercise 25 *****************
 * Create a Map<String,ArrayList<Integer>>. Use
 * net.mindview.TextFile to open a text file and
 * read it in a word at a time (use "\\W+" as the
 * second argument to the TextFile constructor).
 * Count the words as you read them in, and for each
 * word in the file, record in the ArrayList<Integer>
 * the word count associated with that word - this is,
 * in effect, the location in the file where that
 * word was found.
 ***********************************************/ 
package holding;
import java.util.*;
import net.mindview.util.*;

public class E25_WordsInfo3 {
  public static void main(String[] args) {
    Map<String, ArrayList<Integer>> stat =
      new HashMap<String, ArrayList<Integer>>();
    int wordCount = 0;
    for(String word :
        new TextFile("E25_WordsInfo3.java", "\\W+")) {
      ArrayList<Integer> loc = stat.get(word);
      if(loc == null) {
        loc = new ArrayList<Integer>();
        stat.put(word, loc);
      }
      loc.add(++wordCount);
    }
    System.out.println(stat);
  }
} /* Output: (Sample)
{holding=[1], and=[21, 48], argument=[35], time=[29], where=[76], loc=[120, 125, 127, 134, 135], file=[20, 54, 75], ArrayList=[10, 58, 99, 105, 118, 129], use=[30], 0=[109], Count=[40], associated=[63], out=[139], Map=[8, 97], that=[65, 77], println=[140], null=[126], main=[94], open=[17], each=[50], as=[32, 43], constructor=[39], a=[7, 18, 25, 28], location=[72], in=[24, 47, 52, 56, 69, 73], Use=[12], read=[22, 45], for=[49, 110], the=[33, 37, 41, 53, 57, 60, 71, 74], word=[26, 51, 61, 66, 78, 112, 123, 133], class=[89], put=[132], new=[102, 113, 128], with=[64], them=[46], 25=[5], second=[34], you=[44], Integer=[11, 59, 100, 106, 119, 130], text=[19], it=[23], util=[83, 87], this=[67], static=[92], record=[55], get=[122], W=[31, 117], import=[81, 84], net=[13, 85], public=[88, 91], wordCount=[108, 137], stat=[101, 121, 131, 141], void=[93], effect=[70], mindview=[14, 86], E25_WordsInfo3=[2, 90, 115], String=[9, 95, 98, 104, 111], add=[136], Create=[6], args=[96], words=[42], int=[107], System=[138], found=[80], at=[27], was=[79], to=[16, 36], java=[3, 82, 116], TextFile=[15, 38, 114], if=[124], HashMap=[103], Exercise=[4], is=[68], count=[62]}
*///:~
