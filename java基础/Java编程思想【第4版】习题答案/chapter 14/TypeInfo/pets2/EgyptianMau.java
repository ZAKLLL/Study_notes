//: typeinfo/pets2/EgyptianMau.java
package typeinfo.pets2;
import typeinfo.pets.Cat;

public class EgyptianMau extends Cat {
  public static class Factory
  implements typeinfo.factory.Factory<EgyptianMau> {
    public EgyptianMau create() { 
      return new EgyptianMau(); 
    }
  }
} ///:~
