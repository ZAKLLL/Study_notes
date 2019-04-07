//: holding/E30_CollectionSequence2.java
/****************** Exercise 30 *****************
 * Modify CollectionSequence.java so that it does
 * not inherit from AbstractCollection, but instead
 * implements Collection.
 ***********************************************/ 
package holding;
import java.util.*;
import typeinfo.pets.*;

class CollectionSequence2 extends PetSequence
implements Collection<Pet> {
  static void display(Iterator<Pet> it) {
    while(it.hasNext()) {
      Pet p = it.next();
      System.out.print(p.id() + ":" + p + " ");
    }
    System.out.println();
  }
  static void display(Collection<Pet> pets) {
    for(Pet p : pets)
      System.out.print(p.id() + ":" + p + " ");
    System.out.println();
  }
  public int size() { return pets.length; }
  public Iterator<Pet> iterator() {
    return new Iterator<Pet>() {
      private int index;
      public boolean hasNext() {
        return index < pets.length;
      }
      public Pet next() { return pets[index++]; }
      public void remove() { // Not implemented
        throw new UnsupportedOperationException();
      }
    };
  }
  // Other methods, which are also need to be provided.
  public boolean add(Pet o) {
    throw new UnsupportedOperationException();
  }
  public boolean addAll(Collection<? extends Pet> c) {
    throw new UnsupportedOperationException();
  }
  public void clear() {
    throw new UnsupportedOperationException();
  }
  public boolean contains(Object o) {
    if(o == null) return false;
    for(int i = 0; i < pets.length; i++)
      if(o.equals(pets[i])) return true;
    return false;
  }
  public boolean containsAll(Collection<?> c) {
    Iterator<?> it = c.iterator();
    while (it.hasNext())
      if(!contains(it.next())) return false;
    return true;
  }
  public boolean isEmpty() { return pets.length == 0; }
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }
  public Object[] toArray() {
    Object[] result = new Object[pets.length];
    System.arraycopy(pets, 0, result, 0, pets.length);
    return result;
  }
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    if (a.length < pets.length)
      a = (T[])java.lang.reflect.Array.newInstance(
        a.getClass().getComponentType(), pets.length);
    T[] result = a;
    System.arraycopy(pets, 0, result, 0, pets.length);
    if (result.length > pets.length)
      result[pets.length] = null;
    return result;
  }
}

public class E30_CollectionSequence2 {
  public static void main(String[] args) {
    CollectionSequence2 c = new CollectionSequence2();
    CollectionSequence2.display(c);
    CollectionSequence2.display(c.iterator());
    System.out.println(
      Arrays.toString(c.toArray(new Pet[0])));
  }
} /* Output:
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
[Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug, Manx]
*///:~
