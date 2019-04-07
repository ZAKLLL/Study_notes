//: typeinfo/coffee2/Americano.java
package typeinfo.coffee2;

public class Americano extends Coffee {
  public static class Factory
  implements typeinfo.factory.Factory<Americano> {
    public Americano create() { return new Americano(); }
  }
} ///:~
