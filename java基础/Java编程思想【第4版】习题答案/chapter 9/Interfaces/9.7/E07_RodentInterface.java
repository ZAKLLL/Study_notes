//: interfaces/E07_RodentInterface.java
/****************** Exercise 7 ******************
 * Change Rodent to an interface in Exercise 9 of 
 * the Polymorphism chapter.
 ***********************************************/
package interfaces;
import static net.mindview.util.Print.*;

interface Rodent2 {
  void hop();
  void scurry();
  void reproduce();
}

class Mouse2 implements Rodent2 {
  public void hop() { print("Mouse hopping"); }
  public void scurry() { print("Mouse scurrying"); }
  public void reproduce() { print("Making more Mice"); }
  public String toString() { return "Mouse"; }
}

class Gerbil2 implements Rodent2 {
  public void hop() { print("Gerbil hopping"); }
  public void scurry() { print("Gerbil scurrying"); }
  public void reproduce() { print("Making more Gerbils"); }
  public String toString() { return "Gerbil"; }
}

class Hamster2 implements Rodent2 {
  public void hop() { print("Hamster hopping"); }
  public void scurry() { print("Hamster scurrying"); }
  public void reproduce() { print("Making more Hamsters"); }
  public String toString() { return "Hamster"; }
}

public class E07_RodentInterface {
  public static void main(String args[]) {
    Rodent2[] rodents = {
      new Mouse2(),
      new Gerbil2(),
      new Hamster2(),
    };
    for(Rodent2 r : rodents) {
      r.hop();
      r.scurry();
      r.reproduce();
      print(r);
    }
  }
} /* Output:
Mouse hopping
Mouse scurrying
Making more Mice
Mouse
Gerbil hopping
Gerbil scurrying
Making more Gerbils
Gerbil
Hamster hopping
Hamster scurrying
Making more Hamsters
Hamster
*///:~
