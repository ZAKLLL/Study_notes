//: enumerated/E05_VowelsAndConsonants2.java
/****************** Exercise 5 *****************
 * Modify control/VowelsAndConsonants.java so that
 * it uses three enum types: VOWEL, SOMETIMES_A_VOWEL,
 * and CONSONANT. The enum constructor should take
 * the various letters that describe that particular
 * category. Hint: Use varargs, and remember that
 * varargs automatically creates an array for you.
 ***********************************************/
package enumerated;
import java.util.*;
import static net.mindview.util.Print.*;

enum CharacterCategory {
  VOWEL('a', 'e', 'i', 'o', 'u') {
    public String toString() { return "vowel"; }
  },
  SOMETIMES_A_VOWEL('y', 'w') {
    public String toString() {
      return "sometimes a vowel";
    }
  },
  CONSONANT {
    public String toString() { return "consonant"; }
  };
  private HashSet<Character> chars =
    new HashSet<Character>();
  private CharacterCategory(Character... chars) {
    if(chars != null)
      this.chars.addAll(Arrays.asList(chars));
  }
  public static CharacterCategory getCategory(Character c) {
    if(VOWEL.chars.contains(c))
      return VOWEL;
    if(SOMETIMES_A_VOWEL.chars.contains(c))
      return SOMETIMES_A_VOWEL;
    return CONSONANT;
  }
}

public class E05_VowelsAndConsonants2 {
  public static void main(String[] args) {
    Random rand = new Random(47);
    for(int i = 0; i < 100; i++) {
      int c = rand.nextInt(26) + 'a';
      printnb((char)c + ", " + c + ": ");
      print(
        CharacterCategory.getCategory((char)c).toString());
    }
  }
} /* Output: (Sample)
y, 121: sometimes a vowel
n, 110: consonant
z, 122: consonant
b, 98: consonant
r, 114: consonant
...
h, 104: consonant
x, 120: consonant
x, 120: consonant
h, 104: consonant
v, 118: consonant
*///:~
