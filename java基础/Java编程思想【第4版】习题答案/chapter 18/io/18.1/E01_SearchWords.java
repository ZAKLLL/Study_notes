//: io/E01_SearchWords.java
// {Args: java E01_SearchWords}
/****************** Exercise 1 *****************
 * Modify DirList.java (or one of its variants) so
 * that the FilenameFilter opens and reads each file
 * (using the net.mindview.util.TextFile utility) and
 * accepts the file based on whether any of the
 * trailing arguments on the command line exist in
 * that file.
 ***********************************************/
package io;
import java.io.*;
import java.util.*;
import net.mindview.util.TextFile;

public class E01_SearchWords {
  public static void main(final String[] args) {
    File path = new File(".");
    String[] list;
    if(args.length == 0)
      list = path.list();
    else
      list = path.list(new FilenameFilter() {
        private String ext = args[0].toLowerCase();
        public boolean accept(File dir, String name) {
          // Only analyze source files with the specified
          // extension (given as the first command line
          // argument)
          if(name.toLowerCase().endsWith(ext)) {
            // Only filter upon file extension?
            if(args.length == 1)
              return true;
            Set<String> words = new HashSet<String>(
              new TextFile(new File(
                dir, name).getAbsolutePath(), "\\W+"));
            for(int i = 1; i < args.length; i++)
              if(words.contains(args[i]))
                return true;
          }
          return false;
        }
      });
    Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
    for(String dirItem : list)
      System.out.println(dirItem);
  }
} /* Output:
E01_SearchWords.java
E02_SortedDirList.java
E03_DirSize.java
*///:~
