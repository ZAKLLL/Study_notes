//: strings/E12_Groups2.java
/************************** Exercise 12 ******************
 * Modify Groups.java to count all unique words 
 * with no initial capital letter.
 *********************************************************/
package strings;
import java.util.*;
import java.util.regex.*;
import static net.mindview.util.Print.*;

public class E12_Groups2 {
  public static void main(String[] args) {
    Set<String> words = new HashSet<String>();
    Matcher m =
      Pattern.compile("\\b((?![A-Z])\\w+)\\b")
        .matcher(Groups.POEM);
    while(m.find())
      words.add(m.group(1));
    print("Number of unique words = " + words.size());
    print(words.toString());
  }
} /* Output:
Number of unique words = 25
[catch, jaws, shun, raths, were, brillig, borogoves, mome, frumious, son, the, claws, in, my, wabe, gimble, and, bird, that, bite, slithy, mimsy, outgrabe, gyre, toves]
*///:~
