//: typeinfo/coffee2/Cappucino.java
package typeinfo.coffee2;

public class Cappucino extends Coffee {
  public static class Factory
  implements typeinfo.factory.Factory<Cappucino> {
    public Cappucino create() { return new Cappucino(); }
  }
} ///:~
