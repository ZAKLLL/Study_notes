//: typeinfo/pets2/Manx.java
package typeinfo.pets2;
import typeinfo.pets.Cat;

public class Manx extends Cat {
  public static class Factory
  implements typeinfo.factory.Factory<Manx> {
    public Manx create() { return new Manx(); }
  }
} ///:~
