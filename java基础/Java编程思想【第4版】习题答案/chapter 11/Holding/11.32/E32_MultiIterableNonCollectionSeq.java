//: holding/E32_MultiIterableNonCollectionSeq.java
/****************** Exercise 32 *****************
 * Following the example of MultiIterableClass,
 * add reversed() and randomized() methods to
 * NonCollectionSequence.java, as well as making
 * NonCollectionSequence implement Iterable, and
 * show that all the approaches work in foreach
 * statements.
 ***********************************************/ 
package holding;
import java.util.*;
import typeinfo.pets.*;

class PetSequence {
  protected Pet[] pets = Pets.createArray(8);
}

class NonCollectionSequence extends PetSequence
implements Iterable<Pet> {
  public Iterable<Pet> reversed() {
    return new Iterable<Pet>() {
      public Iterator<Pet> iterator() {
        return new Iterator<Pet>() {
          int current = pets.length - 1;
          public boolean hasNext() { return current > -1; }
          public Pet next() { return pets[current--]; }
          public void remove() { // Not implemented
            throw new UnsupportedOperationException();
          }
        };
      }
    };
  }
  public Iterable<Pet> randomized() {
    return new Iterable<Pet>() {
      public Iterator<Pet> iterator() {
        List<Pet> shuffled =
          new ArrayList<Pet>(Arrays.asList(pets));
        Collections.shuffle(shuffled, new Random(47));
        return shuffled.iterator();
      }
    };
  }
  public Iterator<Pet> iterator() {
    return new Iterator<Pet>() {
      private int index = 0;
      public boolean hasNext() {
        return index < pets.length;
      }
      public Pet next() { return pets[index++]; }
      public void remove() { // Not implemented
        throw new UnsupportedOperationException();
      }
    };
  }
}

public class E32_MultiIterableNonCollectionSeq {
  public static void main(String[] args) {
    NonCollectionSequence nc = new NonCollectionSequence ();
    for(Pet pet : nc.reversed())
      System.out.print(pet + " ");
    System.out.println();
    for(Pet pet : nc.randomized())
      System.out.print(pet + " ");
    System.out.println();
    for(Pet pet : nc)
      System.out.print(pet + " ");
  }
} /* Output:
Manx Pug Cymric Pug Mutt Cymric Manx Rat
Pug Mutt Pug Rat Manx Manx Cymric Cymric
Rat Manx Cymric Mutt Pug Cymric Pug Manx
*///:~
