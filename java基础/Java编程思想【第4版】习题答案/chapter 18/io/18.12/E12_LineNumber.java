//: io/E12_LineNumber.java
// {Args: E12_LineNumber.java E12_LineNumber.out}
/****************** Exercise 12 *****************
 * Modify Exercise 8 to also open a text file so
 * you can write text into it. Write the lines in the
 * LinkedList, along with line numbers (do not
 * attempt to use the "LineNumber" classes), out
 * to the file.
 ***********************************************/
package io;
import java.io.*;
import java.util.*;

public class E12_LineNumber {
  public static void main(String[] args)
  throws java.io.IOException {
    if(args.length != 2) {
      System.err.println(
        "Usage: java E12_LineNumber infile outfile");
      return;
    }
    List<String> list = E07_FileIntoList.read(args[0]);
    PrintWriter out =
      new PrintWriter(
        new BufferedWriter(
          new FileWriter(args[1])));
    // We need to count backwards...
    int line = list.size();
    for(ListIterator<String> it =
        list.listIterator(list.size()); it.hasPrevious();)
      out.println(line-- + ": " + it.previous());
    out.close();
  }
} ///:~
