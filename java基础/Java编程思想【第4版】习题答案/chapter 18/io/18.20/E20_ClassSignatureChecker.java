//: io/E20_ClassSignatureChecker.java
/****************** Exercise 20 *****************
 * Using Directory.walk() and BinaryFile, verify
 * that all .class files in a directory tree begin
 * with the hex characters 'CAFEBABE'.
 ***********************************************/
package io;
import java.io.*;
import net.mindview.util.*;

public class E20_ClassSignatureChecker {
  final static byte[] signature = 
    {(byte)202, (byte)254, (byte)186, (byte)190};
  public static void main(String[] args)
  throws IOException {
    String dir = ".";
    if(args.length == 1)
      dir = args[0];
    for(File file : Directory.walk(dir, ".*\\.class")) {
      byte[] bt = BinaryFile.read(file);
      for(int i = 0; i< signature.length; i++)
        if(bt[i] != signature[i]) {
          System.err.println(file + " is corrupt!");
          break;
        }
    }
  }
} ///:~
