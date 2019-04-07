//: strings/E17_JCommentExtractor.java
// {Args: E17_JCommentExtractor.java}
/********************** Exercise 17 *********************
 * Write a program that reads a Java source-code file (you
 * provide the file name on the command line) and displays
 * all the comments.
 ********************************************************/
package strings;
import java.util.regex.*;
import net.mindview.util.*;

public class E17_JCommentExtractor {
  static final String CMNT_EXT_REGEX =
    "(?x)(?m)(?s) # Comments, Multiline & Dotall: ON\n" +
    "/\\*         # Match START OF THE COMMENT\n" +
    "(.*?)        # Match all chars\n" +
    "\\*/         # Match END OF THE COMMENT\n" +
    "| //(.*?)$   # OR Match C++ style comments\n";
  public static void main(String[] args) throws Exception {
    if(args.length < 1) {
      System.out.println(
        "Usage: java E17_JCommentExtractor file");
      System.exit(0);
    }
    String src = TextFile.read(args[0]);
    Pattern p = Pattern.compile(CMNT_EXT_REGEX);
    Matcher m = p.matcher(src);
    while(m.find())
       System.out.println(m.group(1) != null ?
         m.group(1) : m.group(2));
  }
} /* Output: (Sample)
: strings/E17_JCommentExtractor.java
 {Args: E17_JCommentExtractor.java}
********************* Exercise 17 *********************
 * Write a program that reads a Java source-code file (you
 * provide the file name on the command line) and displays
 * all the comments.
 *******************************************************
(.*?)$   # OR Match C++ style comments\n";
/:~
*///:~
