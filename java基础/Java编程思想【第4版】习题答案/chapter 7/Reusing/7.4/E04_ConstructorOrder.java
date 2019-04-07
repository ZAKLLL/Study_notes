//: reusing/E04_ConstructorOrder.java
/****************** Exercise 4 ******************
 * Prove that base-class constructors are (a)
 * always called and (b) called before
 * derived-class constructors.
 ***********************************************/
package reusing;

class Base1 {
  public Base1() { System.out.println("Base1"); }
}

class Derived1 extends Base1 {
  public Derived1() { System.out.println("Derived1"); }
}

class Derived2 extends Derived1 {
  public Derived2() { System.out.println("Derived2"); }
}

public class E04_ConstructorOrder {
  public static void main(String args[]) {
    new Derived2();
  }
} /* Output:
Base1
Derived1
Derived2
*///:~
