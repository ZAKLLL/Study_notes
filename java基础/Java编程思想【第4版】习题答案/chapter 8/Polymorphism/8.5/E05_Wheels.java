//: polymorphism/E05_Wheels.java
/****************** Exercise 5 ****************
 * Starting from Exercise 1, add a wheels()
 * method in Cycle, which returns the number of
 * wheels. Modify ride() to call wheels() and
 * verify that polymorphism works.
 ***********************************************/
package polymorphism;
import polymorphism.cycle2.*;

public class E05_Wheels {
  public static void ride(Cycle c) {
     System.out.println("Num. of wheels: " + c.wheels());
  }
  public static void main(String[] args) {
    ride(new Unicycle());
    ride(new Bicycle());
    ride(new Tricycle());
  }
} /* Output:
Num. of wheels: 1
Num. of wheels: 2
Num. of wheels: 3
*///:~
