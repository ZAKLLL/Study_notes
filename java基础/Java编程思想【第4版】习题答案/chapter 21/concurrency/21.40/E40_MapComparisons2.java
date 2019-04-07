//: concurrency/E40_MapComparisons2.java
// {Args: 1 10 10} (Fast verification check during build)
/******************** Exercise 40 ************************
 * Following the example of ReaderWriterList.java, create
 * a ReaderWriterMap using a HashMap. Investigate its
 * performance by modifying MapComparisons.java. How does
 * it compare to a synchronized HashMap and a
 * ConcurrentHashMap?
 *********************************************************/
package concurrency;
import java.util.concurrent.locks.*;
import java.util.*;
import net.mindview.util.*;

class ReaderWriterMap<K,V> extends AbstractMap<K,V> {
  private HashMap<K,V> lockedMap;
  private ReentrantReadWriteLock lock =
    new ReentrantReadWriteLock(true);
  public ReaderWriterMap(Generator<K> genK, int size,
    V initialValue) {
    lockedMap = new HashMap<K,V>(
      MapData.map(genK, initialValue, size));
  }
  public V put(K key, V value) {
    Lock wlock = lock.writeLock();
    wlock.lock();
    try {
      return lockedMap.put(key, value);
    } finally {
      wlock.unlock();
    }
  }
  public V get(Object key) {
    Lock rlock = lock.readLock();
    rlock.lock();
    try {
      // Comment out if you would like to trace how many
      // readers are acquiring the lock simultaneously:
//    if(lock.getReadLockCount() > 1)
//      print(lock.getReadLockCount());
      return lockedMap.get(key);
    } finally {
      rlock.unlock();
    }
  }
  // Dummy implementation
  public Set<Map.Entry<K,V>> entrySet() { return null; }
}

class ReaderWriterMapTest extends MapTest {
  Map<Integer,Integer> containerInitializer() {
    return new ReaderWriterMap<Integer,Integer>(
      new CountingGenerator.Integer(), containerSize,
      1);
  }
  ReaderWriterMapTest(int nReaders, int nWriters) {
    super("ReaderWriterMap", nReaders, nWriters);
  }
}

public class E40_MapComparisons2 {
  public static void main(String[] args) {
    Tester.initMain(args);
    new SynchronizedHashMapTest(10, 0);
    new SynchronizedHashMapTest(9, 1);
    new SynchronizedHashMapTest(5, 5);
    new ConcurrentHashMapTest(10, 0);
    new ConcurrentHashMapTest(9, 1);
    new ConcurrentHashMapTest(5, 5);
    new ReaderWriterMapTest(10, 0);
    new ReaderWriterMapTest(9, 1);
    new ReaderWriterMapTest(5, 5);
    Thread.yield();
    Tester.exec.shutdown();
  }
} /* Output: (Sample)
Type                             Read time     Write time
Synched HashMap 10r 0w             4034312              0
Synched HashMap 9r 1w              9416558        1530920
readTime + writeTime =            10947478
Synched HashMap 5r 5w               119009        2369854
readTime + writeTime =             2488863
ConcurrentHashMap 10r 0w           2892266              0
ConcurrentHashMap 9r 1w            7266285         689193
readTime + writeTime =             7955478
ConcurrentHashMap 5r 5w             132698        3805790
readTime + writeTime =             3938488
ReaderWriterMap 10r 0w             6972675              0
ReaderWriterMap 9r 1w             13310350        2396394
readTime + writeTime =            15706744
ReaderWriterMap 5r 5w               204774       10277563
readTime + writeTime =            10482337
*///:~
