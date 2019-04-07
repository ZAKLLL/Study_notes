//: reusing/E24_JapaneseBeetle.java
/****************** Exercise 24 ****************
 * In Beetle.java, inherit a specific type of
 * beetle from class Beetle, following the same
 * format as the existing classes. Trace and
 * explain the output.
 ***********************************************/
package reusing;
import static net.mindview.util.Print.*;

class JapaneseBeetle extends Beetle {
  int m = printInit("JapaneseBeetle.m initialized");
  JapaneseBeetle() {
    print("m = " + m);
    print("j = " + j);
  }
  static int x3 =
    printInit("static JapaneseBeetle.x3 initialized");
}

public class E24_JapaneseBeetle {
  public static void main(String args[]) {
    new JapaneseBeetle();
  }
} /* Output:
static Insect.x1 initialized
static Beetle.x2 initialized
static JapaneseBeetle.x3 initialized
i = 9, j = 0
Beetle.k initialized
k = 47
j = 39
JapaneseBeetle.m initialized
m = 47
j = 39
*///:~
