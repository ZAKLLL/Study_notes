//: reusing/E01_Composition.java
/****************** Exercise 1 *****************
 * Create a simple class. Inside a second class,
 * define a reference to an object of the first
 * class. Use lazy initialization to instantiate
 * this object.
 ***********************************************/
package reusing;
import static net.mindview.util.Print.*;

class Simple {
  String s;
  public Simple(String si) { s = si; }
  public String toString() { return s; }
  public void setString(String sNew) { s = sNew; }
}

class Second {
  Simple simple;
  String s;
  public Second(String si) {
    s = si; // 'simple' not initialized
  }
  public void check() {
    if(simple == null)
      print("not initialized");
    else
      print("initialized");
  }
  private Simple lazy() {
    if(simple == null) {
      print("Creating Simple");
      simple = new Simple(s);
    }
    return simple;
  }
  public Simple getSimple() { return lazy(); }
  public String toString() {
    return lazy().toString();
  }
  public void setSimple(String sNew) {
    lazy().setString(sNew);
  }
}

public class E01_Composition {
  public static void main(String args[]) {
    Second second = new Second("Init String");
    second.check();
    print(second.getSimple());
    second.check();
    print(second); // toString() call
    second.setSimple("New String");
    print(second);
  }
} /* Output:
not initialized
Creating Simple
Init String
initialized
Init String
New String
*///:~
