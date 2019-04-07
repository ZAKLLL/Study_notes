//: polymorphism/E17_RTTI.java
// {ThrowsException}
/****************** Exercise 17 ****************
 * Add a balance() method to Unicycle and Bicycle 
 * but not to Tricycle, using the Cycle hierarchy
 * from Exercise 1. Upcast instances of all 
 * three types to an array of Cycle. Try to call 
 * balance() on each element of the  array and 
 * observe the results. Downcast and call 
 * balance() and observe what happens.
 ***********************************************/
package polymorphism;
import polymorphism.cycle.Cycle;
import polymorphism.cycle.Tricycle;
import polymorphism.cycle3.*;

public class E17_RTTI {
  public static void main(String[] args) {
    Cycle[] cycles = new Cycle[]{ new Unicycle(), 
      new Bicycle(), new Tricycle() };
    // Compile time: method not found in Cycle:
    // cycles[0].balance();
    // cycles[1].balance();
    // cycles[2].balance();
    ((Unicycle)cycles[0]).balance();  // Downcast/RTTI
    ((Bicycle)cycles[1]).balance();   // Downcast/RTTI
    ((Unicycle)cycles[2]).balance();  // Exception thrown
  }
} ///:~
