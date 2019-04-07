//: io/E21_UpperCaseEcho.java
// {RunByHand}
/****************** Exercise 21 *****************
 * Write a program that takes standard input and
 * capitalizes all characters, then puts the results
 * on standard output. Redirect the contents of a
 * file into this program (the process of
 * redirection will vary depending on your operating
 * system).
 ***********************************************/
package io;
import java.io.*;

public class E21_UpperCaseEcho {
  public static void main(String[] args)
  throws IOException {
    BufferedReader stdin = new BufferedReader(
      new InputStreamReader(System.in));
    String s;
    while((s = stdin.readLine()) != null && s.length() != 0)
      System.out.println(s.toUpperCase());
    // An empty line or Ctrl-Z terminates the program
  }
} ///:~
