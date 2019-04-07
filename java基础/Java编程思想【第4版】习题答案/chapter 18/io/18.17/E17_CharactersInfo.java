//: io/E17_CharactersInfo.java
/****************** Exercise 17 *****************
 * Using TextFile and a Map<Character,Integer>,
 * create a program that counts the occurrence of
 * all the different characters in a file. (So if
 * there are 12 occurrences of the letter 'a' in
 * the file, the Integer associated with the Character
 * containing 'a' in the Map contains '12').
 ***********************************************/
package io;
import java.util.*;
import net.mindview.util.*;

public class E17_CharactersInfo {
  public static void main(String[] args) {
    Map<Character,Integer> charsStat =
      new HashMap<Character,Integer>();
    for(String word :
      new TextFile("E17_CharactersInfo.java", "\\W+"))
      for(int i = 0; i < word.length(); i++) {
        Character ch = word.charAt(i);
        Integer freq = charsStat.get(ch);
        charsStat.put(ch, freq == null ? 1 : freq + 1);
      }
    List<Character> keys = Arrays.asList(
      charsStat.keySet().toArray(new Character[0]));
    Collections.sort(keys);
    for(Character key : keys)
      System.out.println(key + " => " + charsStat.get(key));
  }
} /* Output: (Sample)
0 => 2
1 => 8
2 => 2
7 => 4
A => 3
C => 12
E => 4
...
u => 10
v => 5
w => 8
x => 3
y => 10
*///:~
