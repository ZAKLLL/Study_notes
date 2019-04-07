//: io/E04_DirSize2.java
// {Args: ".*\.java"} All java files
/****************** Exercise 4 *****************
 * Use Directory.walk() to sum the sizes of all
 * files in a directory tree whose names match a
 * particular regular expression.
 ***********************************************/
package io;
import java.io.*;
import net.mindview.util.*;

public class E04_DirSize2 {
  public static void main(String[] args) {
    Directory.TreeInfo ti;
    if(args.length == 0)
      ti = Directory.walk("../object");
    else
      ti = Directory.walk("../object", args[0]);
    long total = 0;
    for(File file : ti)
      total += file.length();
    System.out.println(
      ti.files.size() + " file(s), " + total + " bytes");
  }
} /* Output: (Sample)
16 file(s), 11647 bytes
*///:~
