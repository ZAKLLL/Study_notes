//: interfaces/E12_CanClimb.java
/****************** Exercise 12 ******************
 * Follow the form of the other
 * interfaces to add an interface called
 * CanClimb in Adventure.java.
 ***********************************************/
package interfaces;

interface CanClimb {
  void climb();
}

class Hero2 extends ActionCharacter
  implements CanFight, CanSwim, CanFly, CanClimb {
  public void swim() {}
  public void fly() {}
  public void climb() {}
}

public class E12_CanClimb {
  static void t(CanFight x) { x.fight(); }
  static void u(CanSwim x) { x.swim(); }
  static void v(CanFly x) { x.fly(); }
  static void z(CanClimb x) { x.climb(); }
  static void w(ActionCharacter x) { x.fight(); }
  public static void main(String[] args) {
    Hero2 h = new Hero2();
    t(h); // Treat it as a CanFight
    u(h); // Treat it as a CanSwim
    v(h); // Treat it as a CanFly
    z(h); // Treat it as a CanClimb
    w(h); // Treat it as an ActionCharacter
  }
} ///:~
