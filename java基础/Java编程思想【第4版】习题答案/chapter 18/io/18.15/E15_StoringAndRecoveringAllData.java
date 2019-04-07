//: io/E15_StoringAndRecoveringAllData.java
/****************** Exercise 15 *****************
 * Look up DataOutputStream and DataInputStream in
 * the JDK documentation. Starting with
 * StoringAndRecoveringData.java, create a program
 * that stores and then retrieves all the different
 * possible types provided by the DataOutputStream
 * and DataInputStream classes. Verify that the
 * values are stored and retrieved accurately.
 ***********************************************/
package io;
import java.io.*;
import static net.mindview.util.Print.*;

public class E15_StoringAndRecoveringAllData {
  public static void main(String[] args)
  throws IOException {
    DataOutputStream out = new DataOutputStream(
      new BufferedOutputStream(
        new FileOutputStream("Data.txt")));
    out.writeBoolean(true);
    out.writeByte(100);
    out.writeByte(255);
    out.writeChar('A');
    out.writeFloat(1.41413f);
    out.writeLong(1000000000L);
    out.writeInt(100000);
    out.writeShort(30000);
    out.writeShort(65535);
    out.writeDouble(3.14159);
    out.writeUTF("That was pi");
    out.close();
    DataInputStream in = new DataInputStream(
      new BufferedInputStream(
        new FileInputStream("Data.txt")));
    print(in.readBoolean());
    print(in.readByte());
    print(in.readUnsignedByte());
    print(in.readChar());
    print(in.readFloat());
    print(in.readLong());
    print(in.readInt());
    print(in.readShort());
    print(in.readUnsignedShort());
    print(in.readDouble());
    // Only readUTF() will recover the
    // Java-UTF String properly:
    print(in.readUTF());
  }
} /* Output:
true
100
255
A
1.41413
1000000000
100000
30000
65535
3.14159
That was pi
*///:~
