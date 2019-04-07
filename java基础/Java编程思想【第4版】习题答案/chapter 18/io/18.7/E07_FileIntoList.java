//: io/E07_FileIntoList.java
/****************** Exercise 7 *****************
 * Open a text file so that you can read the file
 * one line at a time. Read each line as a String
 * and place that String object into a LinkedList.
 * Print all of the lines in the LinkedList in reverse
 * order.
 ***********************************************/
package io;
import java.io.*;
import java.util.*;

public class E07_FileIntoList {
  // Throw exceptions to console:
  public static List<String>
  read(String filename) throws IOException {
    // Reading input by lines:
    BufferedReader in = new BufferedReader(
      new FileReader(filename));
    String s;
    List<String> list = new LinkedList<String>();
    while((s = in.readLine())!= null)
      list.add(s);
    in.close();
    return list;
  }
  public static void main(String[] args)
  throws IOException {
    List<String> list = read("E07_FileIntoList.java");
    for(ListIterator<String> it =
        list.listIterator(list.size()); it.hasPrevious();)
      System.out.println(it.previous());
  }
} /* (Execute to see output) *///:~
