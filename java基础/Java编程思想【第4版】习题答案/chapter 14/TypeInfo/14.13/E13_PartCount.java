//: typeinfo/E13_PartCount.java
/****************** Exercise 13 *****************
 * Use TypeCounter with the RegisteredFactories.java
 * example in this chapter.
 ***********************************************/
package typeinfo;
import net.mindview.util.*;
import static net.mindview.util.Print.*;

public class E13_PartCount {
  public static void main(String[] args) {
    TypeCounter counter = new TypeCounter(Part.class);
    Part part;
    for(int i = 0; i < 20; i++) {
      part = Part.createRandom();
      printnb(part.getClass().getSimpleName() + " ");
      counter.count(part);
    }
    print();
    print(counter);
  }
} /* Output:
GeneratorBelt CabinAirFilter GeneratorBelt AirFilter PowerSteeringBelt CabinAirFilter FuelFilter PowerSteeringBelt PowerSteeringBelt FuelFilter CabinAirFilter PowerSteeringBelt FanBelt AirFilter OilFilter OilFilter AirFilter PowerSteeringBelt FuelFilter CabinAirFilter
{OilFilter=2, GeneratorBelt=2, Part=20, Belt=8, FuelFilter=3, PowerSteeringBelt=5, Filter=12, CabinAirFilter=4, FanBelt=1, AirFilter=3}
*///:~
