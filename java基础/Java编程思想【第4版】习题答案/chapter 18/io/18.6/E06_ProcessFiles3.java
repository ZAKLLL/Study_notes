//: io/E06_ProcessFiles3.java
// {Args: . 1/1/06}
/****************** Exercise 6 *****************
 * Use ProcessFiles to find all the Java
 * source-code files in a particular directory
 * subtree that have been modified after a
 * particular date.
 ***********************************************/
package io;
import java.io.*;
import java.text.*;
import java.util.*;
import net.mindview.util.*;

public class E06_ProcessFiles3 {
  public static void main(String[] args) {
    DateFormat df = DateFormat.getDateInstance(
      DateFormat.SHORT, Locale.US);
    if(args.length != 2) {
      System.err.println(
       "Usage: java E06_ProcessFiles3 path date");
      return;
    }
    long tmp = 0;
    try {
      df.setLenient(false);
      tmp = df.parse(args[1]).getTime();
    } catch(ParseException pe) {
      pe.printStackTrace();
      return;
    }
    final long modTime = tmp;
    new ProcessFiles(new ProcessFiles.Strategy() {
    public void process(File file) {
      if(modTime < file.lastModified())
        System.out.println(file);
    }
  }, "java").start(new String[] {args[0]});
}
} /* (Execute to see output) *///:~
