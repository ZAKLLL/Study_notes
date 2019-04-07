//: io/E28_BlipCheck.java
// {RunByHand}
/****************** Exercise 28 *****************
 * In Blips.java, copy the file and rename it to
 * BlipCheck.java and rename the class Blip2 to
 * BlipCheck (making it public and removing the
 * public scope from the class Blips in the
 * process). Remove the //! marks in the file and
 * execute the program including the offending
 * lines. Next, comment out the default
 * constructor for BlipCheck. Run it and explain
 * why it works. Note that after compiling, you
 * must execute the program with "java Blips"
 * because the main() method is still in class
 * Blips.
 ***********************************************/
package io;
import java.io.*;
import static net.mindview.util.Print.*;

class Blip1 implements Externalizable {
  public Blip1() {
    print("Blip1 Constructor");
  }
  public void writeExternal(ObjectOutput out)
      throws IOException {
    print("Blip1.writeExternal");
  }
  public void readExternal(ObjectInput in)
     throws IOException, ClassNotFoundException {
    print("Blip1.readExternal");
  }
}

public class E28_BlipCheck implements Externalizable {
//  E28_BlipCheck() {
//    print("BlipCheck Constructor");
//  }
  public void writeExternal(ObjectOutput out)
      throws IOException {
    print("BlipCheck.writeExternal");
  }
  public void readExternal(ObjectInput in)
     throws IOException, ClassNotFoundException {
    print("BlipCheck.readExternal");
  }
  public static void main(String[] args) throws Exception {
    // To make it run with Ant.
    Blips.main(args);
  }
}

class Blips {
  // Throw exceptions to console:
  public static void main(String[] args)
  throws IOException, ClassNotFoundException {
    print("Constructing objects:");
    Blip1 b1 = new Blip1();
    E28_BlipCheck b2 = new E28_BlipCheck();
    ObjectOutputStream o =
      new ObjectOutputStream(
        new FileOutputStream("Blips.out"));
    print("Saving objects:");
    o.writeObject(b1);
    o.writeObject(b2);
    o.close();
    // Now get them back:
    ObjectInputStream in =
      new ObjectInputStream(
        new FileInputStream("Blips.out"));
    print("Recovering b1:");
    b1 = (Blip1)in.readObject();
    // OOPS! Throws an exception:
    print("Recovering b2:");
    b2 = (E28_BlipCheck)in.readObject();
  }
} ///:~
