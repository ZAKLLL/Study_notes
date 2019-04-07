//: exceptions/E26_MainException.java
// {ThrowsException}
/****************** Exercise 26 ******************
 * Change the file name string in MainException.java to
 * name a file that doesn't exist. Run the program and
 * note the result
 ************************************************/
package exceptions;
import java.io.*;

public class E26_MainException {
  // Pass all exceptions to the console:
  public static void main(String[] args) throws Exception {
    // Open the file:
    FileInputStream file =
      new FileInputStream("DoesnotExist.file");
    // Use the file ...
    // Close the file:
    file.close();
  }
} ///:~
