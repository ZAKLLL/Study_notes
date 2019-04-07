//: containers/E24_SimpleHashSet.java
/****************** Exercise 24 *****************
 * Following the example in SimpleHashMap.java,
 * create and test a SimpleHashSet.
 ***********************************************/
package containers;
import java.util.*;
import net.mindview.util.*;

class SimpleHashSet<K> extends AbstractSet<K> {
  private final static int SIZE = 997;
  @SuppressWarnings("unchecked")
  private LinkedList<K>[] buckets = new LinkedList[SIZE];
  public boolean add(K key) {
    int index = Math.abs(key.hashCode()) % SIZE;
    if(buckets[index] == null)
      buckets[index] = new LinkedList<K>();
    LinkedList<K> bucket = buckets[index];
    ListIterator<K> it = bucket.listIterator();
    while(it.hasNext())
      if(it.next().equals(key))
        return false;
    // Sets do not permit duplicates and one
    // was already in the set.
    it.add(key);
    return true;
  }
  public boolean contains(Object key) {
    int index = Math.abs(key.hashCode()) % SIZE;
    if(buckets[index] == null) return false;
    Iterator<K> it = buckets[index].iterator();
    while(it.hasNext())
      if(it.next().equals(key))
        return true;
    return false;
  }
  public int size() {
    int sz = 0;
    for(LinkedList<K> bucket : buckets) {
      if(bucket != null)
      sz += bucket.size();
    }
    return sz;
  }
  public Iterator<K> iterator() {
    return new Iterator<K>() {
      private int count;
      private boolean canRemove;
      private int index1, index2;
      public boolean hasNext() { return count < size(); }
      public K next() {
        if(hasNext()) {
          canRemove = true;
          ++count;
          for(;;) {
            // Position of the next non-empty bucket
            while(buckets[index1] == null)
              ++index1;
            // Position of the next item to return
            try {
              return buckets[index1].get(index2++);
            } catch(IndexOutOfBoundsException e) {
              // Continue search from the next bucket
              ++index1;
              index2 = 0;
            }
          }
        }
        throw new NoSuchElementException();
      }
      public void remove() {
        if(canRemove) {
          canRemove = false;
          buckets[index1].remove(--index2);
          if(buckets[index1].isEmpty())  // Housekeeping...
            buckets[index1++] = null;
          --count;
        } else
          throw new IllegalStateException();
      }
    };
  }
}

public class E24_SimpleHashSet {
  public static void main(String[] args) {
    SimpleHashSet<String> m = new SimpleHashSet<String>();
    m.addAll(Countries.names(10));
    m.addAll(Countries.names(10));
    System.out.println("m = " + m);
    System.out.println("m.size() = " + m.size());
    Iterator<String> it = m.iterator();
    System.out.println("it.next()= "+ it.next());
    it.remove();
    System.out.println("it.next()= "+ it.next());
    System.out.println("m = " + m);
    m.remove("ALGERIA");
    System.out.println("m = " + m);
  }
} /* Output:
m = [BOTSWANA, BENIN, ALGERIA, BURKINA FASO, CAMEROON, CENTRAL AFRICAN REPUBLIC, CAPE VERDE, CHAD, ANGOLA, BURUNDI]
m.size() = 10
it.next()= BOTSWANA
it.next()= BENIN
m = [BENIN, ALGERIA, BURKINA FASO, CAMEROON, CENTRAL AFRICAN REPUBLIC, CAPE VERDE, CHAD, ANGOLA, BURUNDI]
m = [BENIN, BURKINA FASO, CAMEROON, CENTRAL AFRICAN REPUBLIC, CAPE VERDE, CHAD, ANGOLA, BURUNDI]
*///:~
