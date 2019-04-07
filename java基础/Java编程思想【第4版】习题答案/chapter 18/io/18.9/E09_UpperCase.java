//: io/E09_UpperCase.java
// {Args: E09_UpperCase.java}
/****************** Exercise 9 *****************
 * Modify Exercise 8 to force all the lines in
 * the LinkedList to uppercase and send the results
 * to System.out.
 ***********************************************/
package io;
import java.util.*;

public class E09_UpperCase {
  public static void main(String[] args)
  throws java.io.IOException {
    if(args.length != 1) {
      System.err.println(
        "Usage: java E09_UpperCase file");
      return;
    }
    List<String> list = E07_FileIntoList.read(args[0]);
    for(ListIterator<String> it =
        list.listIterator(list.size()); it.hasPrevious();)
      System.out.println(it.previous().toUpperCase());
  }
} /* (Execute to see output) *///:~
