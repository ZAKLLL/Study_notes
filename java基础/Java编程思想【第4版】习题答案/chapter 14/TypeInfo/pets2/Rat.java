//: typeinfo/pets2/Rat.java
package typeinfo.pets2;
import typeinfo.pets.Rodent;

public class Rat extends Rodent {
  public static class Factory
  implements typeinfo.factory.Factory<Rat> {
    public Rat create() { return new Rat(); }
  }
} ///:~
