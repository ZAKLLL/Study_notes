//: innerclasses/E14_HorrorShow2.java
/****************** Exercise 14 ******************
 * Modify interfaces/HorrorShow.java to implement
 * DangerousMonster and Vampire using anonymous
 * classes.
 ************************************************/
package innerclasses;

public class E14_HorrorShow2 {
  public static void main(String[] args) {
    DangerousMonster barney = new DangerousMonster() {
      public void menace() {}
      public void destroy() {}
    };
    HorrorShow.u(barney);
    HorrorShow.v(barney);
    Vampire vlad = new Vampire() {
      public void menace() {}
      public void destroy() {}
      public void kill() {}
      public void drinkBlood() {}
    };
    HorrorShow.u(vlad);
    HorrorShow.v(vlad);
    HorrorShow.w(vlad);
  }
} ///:~
