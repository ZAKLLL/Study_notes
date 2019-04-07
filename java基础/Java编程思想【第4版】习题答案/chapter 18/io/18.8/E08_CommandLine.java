//: io/E08_CommandLine.java
// {Args: E08_CommandLine.java}
/****************** Exercise 8 *****************
 * Modify Exercise 7 so that the name of the file
 * you read is provided as a command-line argument.
 ***********************************************/
package io;
import java.util.*;

public class E08_CommandLine {
  public static void main(String[] args)
  throws java.io.IOException {
    if(args.length != 1) {
      System.err.println(
        "Usage: java E08_CommandLine file");
      return;
    }
    List<String> list = E07_FileIntoList.read(args[0]);
    for(ListIterator<String> it =
        list.listIterator(list.size()); it.hasPrevious();)
      System.out.println(it.previous());
  }
} /* (Execute to see output) *///:~
