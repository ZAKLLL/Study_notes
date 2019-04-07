//: containers/E16_SlowMapTest.java
/****************** Exercise 16 *****************
 * Apply the tests in Maps.java to SlowMap to
 * verify that it works. Fix anything in SlowMap
 * that doesn't work correctly.
 ***********************************************/
package containers;
import java.util.*;
import static net.mindview.util.Print.*;

// Does not support null value as key!
class SlowMap2<K,V> extends AbstractMap<K,V> {
  private List<K> keys = new ArrayList<K>();
  private List<V> values = new ArrayList<V>();
  @Override public V put(K key, V value) {
    if(key == null)
      throw new NullPointerException();
    V oldValue = get(key); // The old value or null
    if(!keys.contains(key)) {
      keys.add(key);
      values.add(value);
    } else
      values.set(keys.indexOf(key), value);
    return oldValue;
  }
  @Override public V get(Object key) {
    if(key == null)
      throw new NullPointerException();
    if(!keys.contains(key))
      return null;
    return values.get(keys.indexOf(key));
  }
  private EntrySet entrySet = new EntrySet();
  @Override public Set<Map.Entry<K,V>> entrySet() {
    return entrySet;
  }
  // Uses the 'Flyweight' pattern
  private class EntrySet
  extends AbstractSet<Map.Entry<K,V>> {
    @Override public Iterator<Map.Entry<K,V>> iterator() {
      return new Iterator<Map.Entry<K,V>>() {
        private int index = -1;
        boolean canRemove;
        public boolean hasNext() {
          return index < keys.size() - 1;
        }
        public Map.Entry<K,V> next() {
          canRemove = true;
          ++index;
          return new MapEntry<K,V>(
            keys.get(index), values.get(index));
        }
        public void remove() {
          if(!canRemove)
            throw new IllegalStateException();
          canRemove = false;
          keys.remove(index);
          values.remove(index--);
        }
      };
    }
    @SuppressWarnings("unchecked")
    @Override public boolean contains(Object o) {
      if(o instanceof MapEntry) {
        MapEntry<K,V> e = (MapEntry<K,V>)o;
        K key = e.getKey();
        if(keys.contains(key))
          return e.equals(
            new MapEntry<K,V>(key, get(key)));
      }
      return false;
    }
    @SuppressWarnings("unchecked")
    @Override public boolean remove(Object o) {
      if(contains(o)) {
        MapEntry<K,V> e = (MapEntry<K,V>)o;
        K key = e.getKey();
        V val = e.getValue();
        keys.remove(key);
        values.remove(val);
        return true;
      }
      return false;
    }
    @Override public int size() { return keys.size(); }
    @Override public void clear() {
      keys.clear();
      values.clear();
    }
  }
}

public class E16_SlowMapTest {
  public static void printKeys(Map<Integer,String> map) {
    printnb("Size = " + map.size() + ", ");
    printnb("Keys: ");
    print(map.keySet()); // Produce a Set of the keys
  }
  public static void test(Map<Integer,String> map) {
    print(map.getClass().getSimpleName());
    map.putAll(new CountingMapData(25));
    // Map has 'Set' behavior for keys:
    map.putAll(new CountingMapData(25));
    printKeys(map);
    // Producing a Collection of the values:
    printnb("Values: ");
    print(map.values());
    print(map);
    print("map.containsKey(11): " + map.containsKey(11));
    print("map.get(11): " + map.get(11));
    print("map.containsValue(\"F0\"): "
      + map.containsValue("F0"));
    Integer key = map.keySet().iterator().next();
    print("First key in map: " + key);
    map.remove(key);
    printKeys(map);
    map.clear();
    print("map.isEmpty(): " + map.isEmpty());
    map.putAll(new CountingMapData(25));
    // Operations on the Set change the Map:
    map.keySet().removeAll(map.keySet());
    print("map.isEmpty(): " + map.isEmpty());
  }
  public static void main(String[] args) {
    System.out.println("Testing SlowMap");
    test(new SlowMap<Integer,String>());
    System.out.println("Testing SlowMap2");
    test(new SlowMap2<Integer,String>());
  }
} /* Output:
Testing SlowMap
SlowMap
Size = 25, Keys: [14, 12, 10, 8, 22, 6, 24, 4, 18, 2, 20, 0, 15, 16, 13, 11, 9, 7, 21, 5, 23, 3, 17, 1, 19]
Values: [O0, M0, K0, I0, W0, G0, Y0, E0, S0, C0, U0, A0, P0, Q0, N0, L0, J0, H0, V0, F0, X0, D0, R0, B0, T0]
{14=O0, 12=M0, 10=K0, 8=I0, 22=W0, 6=G0, 24=Y0, 4=E0, 18=S0, 2=C0, 20=U0, 0=A0, 15=P0, 16=Q0, 13=N0, 11=L0, 9=J0, 7=H0, 21=V0, 5=F0, 23=X0, 3=D0, 17=R0, 1=B0, 19=T0}
map.containsKey(11): true
map.get(11): L0
map.containsValue("F0"): true
First key in map: 14
Size = 25, Keys: [14, 12, 10, 8, 22, 6, 24, 4, 18, 2, 20, 0, 15, 16, 13, 11, 9, 7, 21, 5, 23, 3, 17, 1, 19]
map.isEmpty(): false
map.isEmpty(): false
Testing SlowMap2
SlowMap2
Size = 25, Keys: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
Values: [A0, B0, C0, D0, E0, F0, G0, H0, I0, J0, K0, L0, M0, N0, O0, P0, Q0, R0, S0, T0, U0, V0, W0, X0, Y0]
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0, 10=K0, 11=L0, 12=M0, 13=N0, 14=O0, 15=P0, 16=Q0, 17=R0, 18=S0, 19=T0, 20=U0, 21=V0, 22=W0, 23=X0, 24=Y0}
map.containsKey(11): true
map.get(11): L0
map.containsValue("F0"): true
First key in map: 0
Size = 24, Keys: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
map.isEmpty(): true
map.isEmpty(): true
*///:~
