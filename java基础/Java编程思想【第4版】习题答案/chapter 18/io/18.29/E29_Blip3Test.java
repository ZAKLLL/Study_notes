//: io/E29_Blip3Test.java
/****************** Exercise 29 *****************
 * In Blip3.java, comment out the two lines after
 * the phrases "You must do this:" and run the
 * program. Explain the result and why it differs
 * from when the two lines are in the program.
 ***********************************************/
package io;
import java.io.*;
import static net.mindview.util.Print.*;

class Blip3B extends Blip3 {
  public Blip3B() {}
  public Blip3B(String x, int a) { super(x, a); }
  @Override public void writeExternal(ObjectOutput out)
  throws IOException {
    print("Blip3B.writeExternal");
    // You must do this:
//    out.writeObject(s);
//    out.writeInt(i);
  }
  @Override public void readExternal(ObjectInput in)
  throws IOException, ClassNotFoundException {
    print("Blip3B.readExternal");
    // You must do this:
//    s = (String)in.readObject();
//    i = in.readInt();
  }
}

public class E29_Blip3Test {
  public static void main(String[] args)
  throws IOException, ClassNotFoundException {
    print("Constructing objects:");
    Blip3B b3 = new Blip3B("A String ", 47);
    print(b3);
    ObjectOutputStream o = new ObjectOutputStream(
      new FileOutputStream("Blip3B.out"));
    print("Saving object:");
    o.writeObject(b3);
    o.close();
    // Now get it back:
    ObjectInputStream in = new ObjectInputStream(
      new FileInputStream("Blip3B.out"));
    print("Recovering b3:");
    b3 = (Blip3B)in.readObject();
    print(b3);
  }
} /* Output:
Constructing objects:
Blip3(String x, int a)
A String 47
Saving object:
Blip3B.writeExternal
Recovering b3:
Blip3 Constructor
Blip3B.readExternal
null0
*///:~
