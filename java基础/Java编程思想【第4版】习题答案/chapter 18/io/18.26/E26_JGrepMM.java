//: io/E26_JGrepMM.java
// {Args: E26_JGrepMM.java \b[Ssct]\w+}
/********************** Exercise 26 *********************
 * Modify strings/JGrep.java to use Java nio memory-mapped
 * files.
 ********************************************************/
package io;
import java.io.*;
import java.util.regex.*;
import java.nio.channels.*;
import java.nio.*;
import java.nio.charset.*;
import static net.mindview.util.Print.*;

public class E26_JGrepMM {
  public static void main(String[] args) throws Exception {
    if(args.length < 2) {
      print("Usage: java E26_JGrepMM file regex");
      System.exit(0);
    }
    Pattern p = Pattern.compile(args[1]);
    FileChannel fc =
      new FileInputStream(args[0]).getChannel();
    ByteBuffer buffer =
      fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
    CharBuffer cb = Charset.forName(
      System.getProperty("file.encoding")).decode(buffer);
    String[] fileAsArray = cb.toString().split("\n");
    int index = 0;
    Matcher m = p.matcher("");
    for(String line : fileAsArray) {
      m.reset(line);
      while(m.find())
        print(index++ + ": " + m.group() +
          ": " + m.start());
    }
    fc.close();
  }
} /* Output: (Sample)
0: Ssct: 30
1: strings: 10
2: to: 29
3: channels: 16
4: charset: 16
5: static: 7
6: class: 7
7: static: 9
8: String: 26
9: throws: 41
10: System: 6
11: compile: 24
12: size: 50
13: cb: 15
14: System: 6
15: String: 4
16: cb: 27
17: toString: 30
18: split: 41
19: String: 8
20: start: 19
21: close: 7
*///:~
