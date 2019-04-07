//: typeinfo/pets2/Pug.java
package typeinfo.pets2;
import typeinfo.pets.Dog;

public class Pug extends Dog {
  public static class Factory
  implements typeinfo.factory.Factory<Pug> {
    public Pug create() { return new Pug(); }
  }
} ///:~
