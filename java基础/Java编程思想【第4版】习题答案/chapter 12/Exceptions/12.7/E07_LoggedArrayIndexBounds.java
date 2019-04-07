//: exceptions/E07_LoggedArrayIndexBounds.java
/****************** Exercise 7 ******************
 * Modify Exercise 3 so that the catch clause logs
 * the results.
 ***********************************************/
package exceptions;
import java.util.logging.*;
import java.io.*;

public class E07_LoggedArrayIndexBounds {
  private static Logger logger =
    Logger.getLogger("E07_LoggedArrayIndexBounds");
  static void logException(Exception e) {
    StringWriter trace = new StringWriter();
    e.printStackTrace(new PrintWriter(trace));
    logger.severe(trace.toString());
  }
  public static void main(String args[]) {
    char[] array = new char[10];
    try {
      array[10] = 'x';
    } catch(ArrayIndexOutOfBoundsException e) {
      logException(e);
    }
  }
} /* Output: (80% match)
2005.09.16. 10:51:22 E07_LoggedArrayIndexBounds logException
SEVERE: java.lang.ArrayIndexOutOfBoundsException: 10
        at E07_LoggedArrayIndexBounds.main(E07_LoggedArrayIndexBounds.java:20)
*///:~
