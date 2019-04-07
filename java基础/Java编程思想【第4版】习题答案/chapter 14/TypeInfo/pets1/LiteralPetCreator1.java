//: typeinfo/pets1/LiteralPetCreator1.java
// Using class literals.
package typeinfo.pets1;
import java.util.*;
import typeinfo.pets.*;

@SuppressWarnings("unchecked")
public class LiteralPetCreator1 extends PetCreator {
  // No try block needed.
  public static final List<Class<? extends Pet>> allTypes =
    Collections.unmodifiableList(Arrays.asList(
      Pet.class, Dog.class, Cat.class,  Rodent.class,
      Mutt.class, Pug.class, EgyptianMau.class, Manx.class,
      Cymric.class, Rat.class, Mouse.class,Hamster.class,
      Gerbil.class));
  // Types for random creation:
  private static final List<Class<? extends Pet>> types =
    allTypes.subList(allTypes.indexOf(Mutt.class),
      allTypes.size());
  public List<Class<? extends Pet>> types() {
    return types;
  }
  public static void main(String[] args) {
    System.out.println(types);
  }
} /* Output:
[class typeinfo.pets.Mutt, class typeinfo.pets.Pug, class typeinfo.pets.EgyptianMau, class typeinfo.pets.Manx, class typeinfo.pets.Cymric, class typeinfo.pets.Rat, class typeinfo.pets.Mouse, class typeinfo.pets.Hamster, class typeinfo.pets1.Gerbil]
*///:~
