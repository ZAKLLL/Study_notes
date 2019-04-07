//: typeinfo/pets2/Cymric.java
/****************** Exercise 15 *****************
 * Implement a new PetCreator using Registered
 * Factories, and modify the Pets Façade so that
 * it uses this one instead of the other two.
 * Ensure that the rest of the examples that use
 * Pets.java still work correctly.
 ***********************************************/
package typeinfo.pets2;

public class Cymric extends Manx {
  public static class Factory
  implements typeinfo.factory.Factory<Cymric> {
    public Cymric create() { return new Cymric(); }
  }
} ///:~
