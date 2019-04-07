//: polymorphism/E09_Rodents.java
/****************** Exercise 9 *****************
 * Create an inheritance hierarchy of Rodent:
 * Mouse, Gerbil, Hamster, etc. In the base
 * class, provide methods that are common to all
 * Rodents, and override these in the derived
 * classes to perform different behaviors
 * depending on the specific type of Rodent.
 * Create an array of Rodent, fill it with
 * different specific types of Rodents, and call
 * your base-class methods to see what happens.
 ***********************************************/
package polymorphism;
import static net.mindview.util.Print.*;

class Rodent {
  public void hop() { print("Rodent hopping"); }
  public void scurry() { print("Rodent scurrying"); }
  public void reproduce() { print("Making more Rodents"); }
  public String toString() { return "Rodent"; }
}

class Mouse extends Rodent {
  public void hop() { print("Mouse hopping"); }
  public void scurry() { print("Mouse scurrying"); }
  public void reproduce() { print("Making more Mice"); }
  public String toString() { return "Mouse"; }
}

class Gerbil extends Rodent {
  public void hop() { print("Gerbil hopping"); }
  public void scurry() { print("Gerbil scurrying"); }
  public void reproduce() { print("Making more Gerbils"); }
  public String toString() { return "Gerbil"; }
}

class Hamster extends Rodent {
  public void hop() { print("Hamster hopping"); }
  public void scurry() { print("Hamster scurrying"); }
  public void reproduce() { print("Making more Hamsters"); }
  public String toString() { return "Hamster"; }
}

public class E09_Rodents {
  public static void main(String args[]) {
    Rodent[] rodents = {
      new Mouse(),
      new Gerbil(),
      new Hamster(),
    };
    for(Rodent r : rodents) {
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
