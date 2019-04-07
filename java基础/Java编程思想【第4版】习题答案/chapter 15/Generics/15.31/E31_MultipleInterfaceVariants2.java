//: generics/E31_MultipleInterfaceVariants2.java
/****************** Exercise 31 *****************
 * Remove all the generics from
 * MultipleInterfaceVariants.java and modify the
 * code so that the example compiles.
 ************************************************/
package generics;
interface Payable {}

class Employee implements Payable {}

class Hourly extends Employee implements Payable {}

public class E31_MultipleInterfaceVariants2 {
  public static void main(String[] args) {
    new Employee();
    new Hourly();
  }
} ///:~
