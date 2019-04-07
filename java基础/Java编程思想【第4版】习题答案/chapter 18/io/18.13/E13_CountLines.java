//: io/E13_CountLines.java
/****************** Exercise 13 *****************
 * Modify BasicFileOutput.java so that it uses
 * LineNumberReader to keep track of the line
 * count. Note that it's much easier to just keep
 * track programmatically.
 ***********************************************/
package io;
import java.io.*;

public class E13_CountLines {
  static String file = "E13_CountLines.out";
  public static void main(String[] args)
  throws IOException {
    // LineNumberReader is inherited from
    // BufferedReader so we don't need to
    // explicitly buffer it:
    LineNumberReader in =
      new LineNumberReader(
        new FileReader("E13_CountLines.java"));
    PrintWriter out = new PrintWriter(
      new BufferedWriter(new FileWriter(file)));
    String s;
    while((s = in.readLine()) != null )
      out.println(in.getLineNumber() + ": " + s);
    out.close();
    // Show the stored file:
    System.out.println(E07_FileIntoList.read(file));
  }
} /* (Execute to see output) *///:~
