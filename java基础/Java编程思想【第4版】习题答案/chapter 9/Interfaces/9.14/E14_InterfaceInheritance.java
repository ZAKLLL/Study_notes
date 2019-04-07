//: interfaces/E14_InterfaceInheritance.java
/****************** Exercise 14 ******************
 * Create three interfaces, each with two methods. 
 * Inherit a new interface from each, adding 
 * a new method. Use the new interface to create 
 * a class, and inherit from a concrete class. 
 * Now write four methods, each of which takes one 
 * of the four interfaces as an argument. Create 
 * an object of your class in main(), and pass it 
 * to each of the methods.
 ***********************************************/
package interfaces;
import static net.mindview.util.Print.*;

interface Interface1 {
  void f1();
  void g1();
}

interface Interface2 {
  void f2();
  void g2();
}

interface Interface3 {
  void f3();
  void g3();
}

interface Multiple
  extends Interface1, Interface2, Interface3 {
  void h();
}

class Concrete {
  String s;
  public Concrete(String s) { this.s = s; }
}

class All extends Concrete implements Multiple {
  All() { super("All"); }
  public void h() { print("All.h"); }
  public void f1() { print("All.f1"); }
  public void g1() { print("All.g1"); }
  public void f2() { print("All.f2"); }
  public void g2() { print("All.g2"); }
  public void f3() { print("All.f3"); }
  public void g3() { print("All.g3"); }
}

public class E14_InterfaceInheritance {
  static void takes1(Interface1 i) {
    i.f1();
    i.g1();
  }
  static void takes2(Interface2 i) {
    i.f2();
    i.g2();
  }
  static void takes3(Interface3 i) {
    i.f3();
    i.g3();
  }
  static void takesAll(All a) {
    a.f1();
    a.g1();
    a.f2();
    a.g2();
    a.f3();
    a.g3();
    a.h();
  }
  public static void main(String args[]) {
    All a = new All();
    takes1(a);
    takes2(a);
    takes3(a);
    takesAll(a);
  }
} /* Output:
All.f1
All.g1
All.f2
All.g2
All.f3
All.g3
All.f1
All.g1
All.f2
All.g2
All.f3
All.g3
All.h
*///:~
