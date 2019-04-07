//: io/E18_TextFile2.java
/****************** Exercise 18 *****************
 * Modify TextFile.java so that it passes
 * IOExceptions out to the caller.
 ***********************************************/
package io;
import java.io.*;
import java.util.*;

class TextFile2 extends ArrayList<String> {
  // Read a file as a single string:
  public static String read(String fileName)
  throws IOException {
    StringBuilder sb = new StringBuilder();
    BufferedReader in= new BufferedReader(new FileReader(
      new File(fileName).getAbsoluteFile()));
    try {
      String s;
      while((s = in.readLine()) != null) {
        sb.append(s);
        sb.append("\n");
      }
    } finally {
      in.close();
    }
    return sb.toString();
  }
  // Write a single file in one method call:
  public static void write(String fileName, String text)
  throws IOException {
    PrintWriter out = new PrintWriter(
      new BufferedWriter(new FileWriter(
        new File(fileName).getAbsoluteFile())));
    try {
      out.print(text);
    } finally {
      out.close();
    }
  }
  // Read a file, split by any regular expression:
  public TextFile2(String fileName, String splitter)
  throws IOException {
    super(Arrays.asList(read(fileName).split(splitter)));
    // Regular expression split() often leaves an empty
    // String at the first position:
    if(get(0).equals("")) remove(0);
  }
  // Normally read by lines:
  public TextFile2(String fileName) throws IOException {
    this(fileName, "\n");
  }
  public void write(String fileName) throws IOException {
    PrintWriter out = new PrintWriter(
      new BufferedWriter(new FileWriter(
        new File(fileName).getAbsoluteFile())));
    try {
      for(String item : this)
        out.println(item);
    } finally {
      out.close();
    }
  }
}

public class E18_TextFile2 {
  public static void main(String[] args)
  throws IOException {
    String file =
      TextFile2.read("E18_TextFile2.java");
    TextFile2.write("test.txt", file);
    TextFile2 text = new TextFile2("test.txt");
    text.write("test2.txt");
    // Break into unique sorted list of words:
    TreeSet<String> words = new TreeSet<String>(
      new TextFile2("E18_TextFile2.java", "\\W+"));
    // Display the capitalized words:
    System.out.println(words.headSet("a"));
  }
} /* Output: (95% match)
[0, 18, ArrayList, Arrays, Break, BufferedReader, BufferedWriter, Display, E18_TextFile2, Exercise, File, FileReader, FileWriter, IOException, IOExceptions, Modify, Normally, PrintWriter, Read, Regular, String, StringBuilder, System, TextFile, TextFile2, TreeSet, W, Write]
*///:~
