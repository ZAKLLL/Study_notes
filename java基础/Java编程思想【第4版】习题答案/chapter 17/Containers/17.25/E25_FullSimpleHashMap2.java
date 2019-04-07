//: containers/E25_FullSimpleHashMap2.java
/****************** Exercise 25 *****************
 * Instead of using a ListIterator for each bucket,
 * modify MapEntry so it is a self-contained
 * singly-linked list (each MapEntry should have a
 * forward link to the next MapEntry). Modify the
 * rest of the code in SimpleHashMap.java so
 * this new approach works correctly.
 ***********************************************/
package containers;
import java.util.*;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

class SimpleHashMap5<K,V> extends AbstractMap<K,V> {
  static class Entry<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;
    Entry<K,V> next;
    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
    public K getKey() { return key; }
    public V getValue() { return value; }
    public V setValue(V v) {
      V result = value;
      value = v;
      return result;
    }
    public int hashCode() {
      return key.hashCode() ^
        (value == null ? 0 : value.hashCode());
    }
    public boolean equals(Object o) {
      if(o instanceof Entry) {
        @SuppressWarnings("unchecked")
        Entry<K,V> e = (Entry<K,V>)o;
        Object key1 = getKey();
        Object key2 = e.getKey();
        if(key1.equals(key2)) {
          Object val1 = getValue();
          Object val2 = e.getValue();
          return
            val1 == null ? val2 == null : val1.equals(val2);
        }
      }
      return false;
    }
    public String toString() { return key + "=" + value; }
  }
  static final int SIZE = 997;
  @SuppressWarnings("unchecked")
  Entry<K,V>[] buckets = new Entry[SIZE];
  public V put(K key, V value) {
    V oldValue = null;
    int index = Math.abs(key.hashCode()) % SIZE;
    Entry<K,V> newPair = new Entry<K,V>(key, value);
    if(buckets[index] == null)
      buckets[index] = newPair;
    Entry<K,V> prevPair = null;        // Previous element
    boolean found = false;
    for(Entry<K,V> pair = buckets[index] ; pair != null;
      pair = pair.next) {
      if(pair.getKey().equals(key)) {
        oldValue = pair.getValue();
        // Replace old with new
        if(prevPair != null)
          prevPair.next = newPair;
        else
          buckets[index] = newPair;
        newPair.next = pair.next;
        found = true;
        break;
      }
      prevPair = pair;
    }
    if(!found)
      prevPair.next = newPair;
    return oldValue;
  }
  public V get(Object key) {
    int index = Math.abs(key.hashCode()) % SIZE;
    for(Entry<K,V> pair = buckets[index]; pair != null;
      pair = pair.next)
      if(pair.getKey().equals(key))
        return pair.getValue();
    return null;
  }
  public Set<Map.Entry<K,V>> entrySet() {
    Set<Map.Entry<K,V>> set = new HashSet<Map.Entry<K,V>>();
    for(Entry<K,V> bucket : buckets) {
      for(Entry<K,V> pair = bucket; pair != null;
        pair = pair.next)
        set.add(pair);
    }
    return set;
  }
  @SuppressWarnings("unchecked")
  public void clear() {
    // Effectively erase everything by allocating
    // a new empty array of buckets:
    buckets = new Entry[SIZE];
  }
  public V remove(Object key) {
    // Code is copied from get(), except that
    // before returning the value, the Map.Entry is
    // removed from the "list":
    int index = Math.abs(key.hashCode()) % SIZE;
    Entry<K,V> prevPair = null;        // Previous element
    for(Entry<K,V> pair = buckets[index] ; pair != null;
      pair = pair.next) {
      if(pair.getKey().equals(key)) {
        V value = pair.getValue();
        if(prevPair != null)
          prevPair.next = pair.next;
        else
          buckets[index] = null;
        return value;
      }
    }
    return null;
  }
  public int size() {
    // Could rely on the inherited implementation, which
    // returns entrySet().size(), but this is faster:
    int sz = 0;
    for(Entry<K,V> bucket : buckets)
      for(Entry<K,V> pair = bucket; pair != null;
        pair = pair.next)
        sz++;
    return sz;
  }
  public boolean isEmpty() {
    // Could just say return size() == 0;
    // but this is faster:
    for(Entry<K,V> bucket : buckets)
      if(bucket != null)
        return false;
    return true;
  }
  public boolean containsKey(Object key) {
    // A slight modification of the previous method:
    for(Entry<K,V> bucket : buckets) {
      for(Entry<K,V> pair = bucket; pair != null;
        pair = pair.next)
        if(pair.getKey().equals(key))
          return true;
    }
    return false;
  }
}

public class E25_FullSimpleHashMap2 {
  public static void main(String args[]) {
    SimpleHashMap5<String,String> m =
      new SimpleHashMap5<String,String>(),
    m2 = new SimpleHashMap5<String,String>();
    m.putAll(Countries.capitals(10));
    m2.putAll(Countries.capitals(10));
    print("m.size() = " + m.size());
    print("m.isEmpty() = " + m.isEmpty());
    print("m.equals(m2) = " + m.equals(m2));
    print("m.containsKey(\"BENIN\") = " +
      m.containsKey("BENIN"));
    print("m.containsKey(\"MARS\") = " +
      m.containsKey("MARS"));
    print("m.keySet() = " + m.keySet());
    print("m.values() = " + m.values());
    m.remove("ALGERIA");
    print("m = " + m);
  }
} /* Output:
m.size() = 10
m.isEmpty() = false
m.equals(m2) = true
m.containsKey("BENIN") = true
m.containsKey("MARS") = false
m.keySet() = [ANGOLA, CHAD, CAPE VERDE, ALGERIA, BURKINA FASO, CENTRAL AFRICAN REPUBLIC, BENIN, BOTSWANA, BURUNDI, CAMEROON]
m.values() = [Luanda, N'djamena, Praia, Algiers, Ouagadougou, Bangui, Porto-Novo, Gaberone, Bujumbura, Yaounde]
m = {ANGOLA=Luanda, CHAD=N'djamena, CAPE VERDE=Praia, BURKINA FASO=Ouagadougou, CENTRAL AFRICAN REPUBLIC=Bangui, BENIN=Porto-Novo, BOTSWANA=Gaberone, BURUNDI=Bujumbura, CAMEROON=Yaounde}
*///:~
