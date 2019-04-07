//: typeinfo/pets2/Pets.java
// Facade to produce a default PetCreator.
package typeinfo.pets2;
import java.util.*;
import typeinfo.factory.Factory;
import typeinfo.pets.PetCreator;
import typeinfo.pets.Pet;

@SuppressWarnings("unchecked")
public class Pets {
  private static class RFPetCreator extends PetCreator {
    static List<Factory<? extends Pet>> petFactories =
      Arrays.asList(new Mutt.Factory(), new Pug.Factory(),
        new EgyptianMau.Factory(), new Manx.Factory(),
         new Cymric.Factory(), new Rat.Factory(),
         new Mouse.Factory(), new Hamster.Factory());
    @Override public List<Class<? extends Pet>> types() {
      return null;  // Dummy value, this method is not used!
    }
    @Override public Pet randomPet() { // Make 1 random Pet
      int n = rand.nextInt(petFactories.size());
      return petFactories.get(n).create();
    }  
  }
  private static Random rand = new Random(47);
  public static final PetCreator creator = 
    new RFPetCreator();
  public static Pet randomPet() { 
    return creator.randomPet(); 
  }
  public static Pet[] createArray(int size) {
    return creator.createArray(size);
  }
  public static ArrayList<Pet> arrayList(int size) {
    return creator.arrayList(size);
  }
} ///:~
