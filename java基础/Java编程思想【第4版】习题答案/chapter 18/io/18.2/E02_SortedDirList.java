//: io/E02_SortedDirList.java
/****************** Exercise 2 ******************
 * Create a class called SortedDirList with a
 * constructor that takes a File object and builds
 * a sorted directory list from the files at that
 * File. Add to this class two overloaded list()
 * methods: the first produces the whole list, and
 * the second produces the subset of the list that
 * matches its argument (which is a regular
 * expression).
 ***********************************************/
package io;
import java.io.*;
import java.util.regex.*;
import java.util.*;
import static net.mindview.util.Print.*;

class SortedDirList {
  private File path;
  public SortedDirList() { path = new File("."); }
  public SortedDirList(File path) { this.path = path; }
  public String[] list() {
    String[] list = path.list();
    Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
    return list;
  }
  public String[] list(final String fn_regex) {
    String[] list =
      path.list(new FilenameFilter() {
        private Pattern pattern = Pattern.compile(fn_regex);
        public boolean accept(File dir, String name) {
          return pattern.matcher(name).matches();
        }
      });
    Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
    return list;
  }
}

public class E02_SortedDirList {
  public static void main(String args[]) {
    // Default constructor == current directory
    SortedDirList dir = new SortedDirList();
    print(Arrays.asList(dir.list("E0[12]_.*\\.java")));
  }
} /* Output:
[E01_SearchWords.java, E02_SortedDirList.java]
*///:~
