//: io/E10_FindWords.java
// {Args: E10_FindWords.java import public}
/****************** Exercise 10 *****************
 * Modify Exercise 8 to take additional command-line
 * arguments of words to find in the file. Print
 * all lines in which any of the words match.
 ***********************************************/
package io;
import java.util.*;

public class E10_FindWords {
  public static void main(String[] args)
  throws java.io.IOException {
    if(args.length < 2) {
      System.err.println(
        "Usage: java E10_FindWords file words");
      return;
    }
    Set<String> words = new HashSet<String>();
    for(int i = 1; i < args.length; i++)
      words.add(args[i]);
    List<String> list = E07_FileIntoList.read(args[0]);
    for(ListIterator<String> it =
        list.listIterator(list.size()); it.hasPrevious();) {
      String candidate = it.previous();
      for(String word : words)
        if(candidate.indexOf(word) != -1) {
          System.out.println(candidate);
          break;
        }
    }
  }
} /* (Execute to see output) *///:~
