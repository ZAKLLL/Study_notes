//: typeinfo/pets2/Mouse.java
package typeinfo.pets2;
import typeinfo.pets.Rodent;

public class Mouse extends Rodent {
  public static class Factory
  implements typeinfo.factory.Factory<Mouse> {
    public Mouse create() { return new Mouse(); }
  }
} ///:~
