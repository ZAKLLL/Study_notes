//: generics/E41_Fill3.java
/****************** Exercise 41 *****************
 * Modify Fill2.java to use the classes in
 * typeinfo.pets instead of the Coffee classes.
 ************************************************/
package generics;
import java.util.*;
import static net.mindview.util.Print.*;
import typeinfo.pets.*;

public class E41_Fill3 {
  public static void main(String[] args) throws Exception {
    // Adapt a Collection:
    List<Pet> carrier = new ArrayList<Pet>();
    Fill2.fill(
      new AddableCollectionAdapter<Pet>(carrier),
      Pet.class, 3);
    // Helper method captures the type:
    Fill2.fill(Adapter.collectionAdapter(carrier),
      Mouse.class, 2);
    for(Pet p: carrier)
      print(p);
    print("----------------------");
    // Use an adapted class:
    AddableSimpleQueue<Pet> petQueue =
      new AddableSimpleQueue<Pet>();
    Fill2.fill(petQueue, Mutt.class, 4);
    Fill2.fill(petQueue, Cymric.class, 1);
    for(Pet p: petQueue)
      print(p);
  }
} /* Output:
Pet
Pet
Pet
Mouse
Mouse
----------------------
Mutt
Mutt
Mutt
Mutt
Cymric
*///:~
