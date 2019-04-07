//: io/E19_BytesInfo.java
/****************** Exercise 19 *****************
 * Using BinaryFile and a Map<Byte,Integer>, create
 * a program that counts the occurrence of all the
 * different bytes in a file.
 ***********************************************/
package io;
import java.io.*;
import java.util.*;
import net.mindview.util.*;

public class E19_BytesInfo {
  public static void main(String[] args)
  throws IOException {
    Map<Byte,Integer> bytesStat =
      new HashMap<Byte,Integer>();
    for(Byte bt : BinaryFile.read("E19_BytesInfo.class")) {
      Integer freq = bytesStat.get(bt);
      bytesStat.put(bt, freq == null ? 1 : freq + 1);
    }
    List<Byte> keys =
      new ArrayList<Byte>(bytesStat.keySet());
    Collections.sort(keys);
    for(Byte key : keys)
      System.out.println(key + " => " + bytesStat.get(key));
  }
} /* Output: (Sample)
-124 => 2
-103 => 1
-94 => 1
-89 => 3
-84 => 1
...
116 => 73
117 => 22
118 => 42
119 => 2
120 => 5
121 => 10
*///:~
