//: reusing/E12_Dispose.java
/****************** Exercise 12 *****************
 * Add a proper hierarchy of dispose() methods to
 * all the classes in Exercise 9.
 ***********************************************/
package reusing;

class Component1c {
  public Component1c(int i) {
    System.out.println("Component1c");
  }
  public void dispose() {
    System.out.println("Component1c dispose");
  }
}

class Component2c {
  public Component2c(int i) {
    System.out.println("Component2c");
  }
  public void dispose() {
    System.out.println("Component2c dispose");
  }
}

class Component3c {
  public Component3c(int i) {
    System.out.println("Component3c");
  }
  public void dispose() {
    System.out.println("Component3c dispose");
  }
}

class Rootc {
  Component1c c1 = new Component1c(1);
  Component2c c2 = new Component2c(2);
  Component3c c3 = new Component3c(3);
  public Rootc(int i) { System.out.println("Rootc"); }
  public void dispose() {
    System.out.println("Rootc dispose");
    c3.dispose();
    c2.dispose();
    c1.dispose();
  }
}

class Stemc extends Rootc {
  Component1c c1 = new Component1c(4);
  Component2c c2 = new Component2c(5);
  Component3c c3 = new Component3c(6);
  public Stemc(int i) {
    super(i);
    System.out.println("Stemc");
  }
  public void dispose() {
    System.out.println("Stemc dispose");
    c3.dispose();
    c2.dispose();
    c1.dispose();
    super.dispose();
  }
}

public class E12_Dispose {
  public static void main(String args[]) {
    new Stemc(47).dispose();
  }
} /* Output:
Component1c
Component2c
Component3c
Rootc
Component1c
Component2c
Component3c
Stemc
Stemc dispose
Component3c dispose
Component2c dispose
Component1c dispose
Rootc dispose
Component3c dispose
Component2c dispose
Component1c dispose
*///:~
