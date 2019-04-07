//: exceptions/E06_LoggingExceptions.java
/****************** Exercise 6 ******************
 * Create two exception classes, each of which
 * performs its own logging automatically.
 * Demonstrate that these work.
 ***********************************************/
package exceptions;
import java.util.logging.*;
import java.io.*;

class LoggingException1 extends Exception {
  private static Logger logger =
    Logger.getLogger("LoggingException1");
  public LoggingException1() {
    StringWriter trace = new StringWriter();
    printStackTrace(new PrintWriter(trace));
    logger.severe(trace.toString());
  }
}

class LoggingException2 extends Exception {
  private static Logger logger =
    Logger.getLogger("LoggingException2");
  public LoggingException2() {
    StringWriter trace = new StringWriter();
    printStackTrace(new PrintWriter(trace));
    logger.severe(trace.toString());
  }
}

public class E06_LoggingExceptions {
  public static void main(String[] args) {
    try {
      throw new LoggingException1();
    } catch(LoggingException1 e) {
      System.err.println("Caught " + e);
    }
    try {
      throw new LoggingException2();
    } catch(LoggingException2 e) {
      System.err.println("Caught " + e);
    }
  }
} /* Output: (45% match)
Sep 12, 2007 3:10:36 PM exceptions.LoggingException1 <init>
SEVERE: exceptions.LoggingException1
        at exceptions.E06_LoggingExceptions.main(E06_LoggingExceptions.java:34)

Caught exceptions.LoggingException1
Sep 12, 2007 3:10:36 PM exceptions.LoggingException2 <init>
SEVERE: exceptions.LoggingException2
        at exceptions.E06_LoggingExceptions.main(E06_LoggingExceptions.java:39)

Caught exceptions.LoggingException2
*///:~
