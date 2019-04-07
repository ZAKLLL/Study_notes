//: typeinfo/pets2/Mutt.java
package typeinfo.pets2;
import typeinfo.pets.Dog;

public class Mutt extends Dog {
  public static class Factory
  implements typeinfo.factory.Factory<Mutt> {
    public Mutt create() { return new Mutt(); }
  }
} ///:~
