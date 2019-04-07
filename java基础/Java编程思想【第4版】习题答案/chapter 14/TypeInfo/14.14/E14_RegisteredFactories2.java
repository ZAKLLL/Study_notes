//: typeinfo/E14_RegisteredFactories2.java
/****************** Exercise 14 *****************
 * A constructor is a kind of factory method. Modify
 * RegisteredFactories.java so that instead of using
 * an explicit factory, the class object is stored
 * in the List, and newInstance() is used to create
 * each object.
 ***********************************************/
package typeinfo;
import java.util.*;

@SuppressWarnings("unchecked")
class Part2 {
  public String toString() {
    return getClass().getSimpleName();
  }
  static List<Class<? extends Part2>> partClasses =
    Arrays.asList(FuelFilter2.class, AirFilter2.class,
      CabinAirFilter2.class, OilFilter2.class,
      FanBelt2.class, PowerSteeringBelt2.class,
      GeneratorBelt2.class);
  private static Random rand = new Random(47);
  public static Part2 createRandom() {
    int n = rand.nextInt(partClasses.size());
    try {
      return partClasses.get(n).newInstance();
    } catch(InstantiationException e) {
      throw new RuntimeException(e);
    } catch(IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}

class Filter2 extends Part2 {}

class FuelFilter2 extends Filter2 {}

class AirFilter2 extends Filter2 {}

class CabinAirFilter2 extends Filter2 {}

class OilFilter2 extends Filter2 {}

class Belt2 extends Part2 {}

class FanBelt2 extends Belt2 {}

class GeneratorBelt2 extends Belt2 {}

class PowerSteeringBelt2 extends Belt2 {}

public class E14_RegisteredFactories2 {
  public static void main(String[] args) {
    for(int i = 0; i < 10; i++)
      System.out.println(Part2.createRandom());
  }
} /* Output:
GeneratorBelt2
CabinAirFilter2
GeneratorBelt2
AirFilter2
PowerSteeringBelt2
CabinAirFilter2
FuelFilter2
PowerSteeringBelt2
PowerSteeringBelt2
FuelFilter2
*///:~
