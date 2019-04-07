//: containers/E35_MapPerformance2.java
// {Args: 100 5000} Small to keep build testing short
/******************** Exercise 35 ************************
 * Modify MapPerformance.java to include tests of SlowMap.
 *********************************************************/
package containers;
import java.util.*;

public class E35_MapPerformance2 {
  static List<Test<Map<Integer,Integer>>> tests =
    new ArrayList<Test<Map<Integer,Integer>>>();
  static {
    tests.add(new Test<Map<Integer,Integer>>("put") {
      int test(Map<Integer,Integer> map, TestParam tp) {
        int loops = tp.loops;
        int size = tp.size;
        for(int i = 0; i < loops; i++) {
          map.clear();
          for(int j = 0; j < size; j++)
            map.put(j, j);
        }
        return loops * size;
      }
    });
    tests.add(new Test<Map<Integer,Integer>>("get") {
      int test(Map<Integer,Integer> map, TestParam tp) {
        int loops = tp.loops;
        int span = tp.size * 2;
        for(int i = 0; i < loops; i++)
          for(int j = 0; j < span; j++)
            map.get(j);
        return loops * span;
      }
    });
    tests.add(new Test<Map<Integer,Integer>>("iterate") {
      int test(Map<Integer,Integer> map, TestParam tp) {
        int loops = tp.loops * 10;
        for(int i = 0; i < loops; i ++) {
          Iterator<Map.Entry<Integer, Integer>> it =
            map.entrySet().iterator();
          while(it.hasNext())
            it.next();
        }
        return loops * map.size();
      }
    });
  }
  public static void main(String[] args) {
    if(args.length > 0)
      Tester.defaultParams = TestParam.array(args);
    Tester.run(new TreeMap<Integer,Integer>(), tests);
    Tester.run(new HashMap<Integer,Integer>(), tests);
    Tester.run(new LinkedHashMap<Integer,Integer>(),tests);
    Tester.run(
      new IdentityHashMap<Integer,Integer>(), tests);
    Tester.run(new WeakHashMap<Integer,Integer>(), tests);
    Tester.run(new Hashtable<Integer,Integer>(), tests);
    Tester.run(new SlowMap2<Integer,Integer>(), tests);
  }
} /* Output: (Sample)
---------- TreeMap ----------
 size     put     get iterate
  100     529     177      34
---------- HashMap ----------
 size     put     get iterate
  100     178      43      64
------- LinkedHashMap -------
 size     put     get iterate
  100     221      55      28
------ IdentityHashMap ------
 size     put     get iterate
  100     270     222      47
-------- WeakHashMap --------
 size     put     get iterate
  100     288     174      83
--------- Hashtable ---------
 size     put     get iterate
  100     163     102      49
---------- SlowMap2 ----------
 size     put     get iterate
  100    2078    1396      79
*///:~
