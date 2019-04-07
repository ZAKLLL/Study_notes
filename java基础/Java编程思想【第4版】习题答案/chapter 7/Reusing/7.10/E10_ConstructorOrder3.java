//: reusing/E10_ConstructorOrder3.java
/****************** Exercise 10 *****************
 * Modify Exercise 9 so each class only has 
 * non-default constructors.
 ***********************************************/
package reusing;

class Component1b {
  public Component1b(int i) {
    System.out.println("Component1b " + i);
  }
}

class Component2b {
  public Component2b(int i) {
    System.out.println("Component2b " + i);
  }
}

class Component3b {
  public Component3b(int i) {
    System.out.println("Component3b " + i);
  }
}

class Rootb {
  Component1b c1 = new Component1b(1);
  Component2b c2 = new Component2b(2);
  Component3b c3 = new Component3b(3);
  public Rootb(int i) { System.out.println("Rootb"); }
}

class Stemb extends Rootb {
  Component1b c1 = new Component1b(4);
  Component2b c2 = new Component2b(5);
  Component3b c3 = new Component3b(6);
  public Stemb(int i) {
    super(i);
    System.out.println("Stemb");
  }
}

public class E10_ConstructorOrder3 {
  public static void main(String args[]) {
    new Stemb(47);
  }
} /* Output:
Component1b 1
Component2b 2
Component3b 3
Rootb
Component1b 4
Component2b 5
Component3b 6
Stemb
*///:~
