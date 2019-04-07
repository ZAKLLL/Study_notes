//: io/E22_OSExecuteDemo.java
/****************** Exercise 22 *****************
 * Modify OSExecute.java so that, instead of
 * printing the standard output stream, it returns
 * the results of executing the program as a List
 * of Strings. Demonstrate the use of this new
 * version of the utility.
 ***********************************************/
package io;
import java.io.*;
import java.util.*;
import net.mindview.util.*;

class OSExecute2 {
  public static List<String> command(String command) {
    boolean err = false;
    List<String> output = new LinkedList<String>();
    try {
      Process process =
        new ProcessBuilder(command.split(" ")).start();
      BufferedReader results = new BufferedReader(
        new InputStreamReader(process.getInputStream()));
      String s;
      while((s = results.readLine()) != null)
        output.add(s);
      BufferedReader errors = new BufferedReader(
        new InputStreamReader(process.getErrorStream()));
      // Report errors and return nonzero value
      // to calling process if there are problems:
      while((s = errors.readLine()) != null) {
        System.err.println(s);
        err = true;
      }
    } catch(IOException e) {
      if(!command.startsWith("CMD /C"))
        return command("CMD /C " + command);
      throw new RuntimeException(e);
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
    if(err)
      throw new OSExecuteException("Errors executing " +
        command);
    return output;
  }
}

public class E22_OSExecuteDemo {
  public static void main(String[] args) {
    List<String> result =
      OSExecute2.command("javap E22_OSExecuteDemo");
    for(String s : result)
      System.out.println(s);
  }
} /* Output:
Compiled from "E22_OSExecuteDemo.java"
public class io.E22_OSExecuteDemo extends java.lang.Object{
    public io.E22_OSExecuteDemo();
    public static void main(java.lang.String[]);
}
*///:~
