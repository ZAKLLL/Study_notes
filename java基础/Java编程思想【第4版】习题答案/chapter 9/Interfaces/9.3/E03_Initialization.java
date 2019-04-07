//: interfaces/E03_Initialization.java
/****************** Exercise 3 *****************
 * Create a base class with an abstract print()
 * method that is overridden in a derived class.
 * The overridden version of the method prints
 * the value of an int variable defined in the
 * derived class. Define this variable with a 
 * nonzero value. Call print() in the
 * base-class constructor. Create an object of 
 * the derived type in main(), then call its 
 * print() method. Explain the results.
 ***********************************************/
package interfaces;

abstract class BaseWithPrint {
  public BaseWithPrint() { print(); }
  public abstract void print();
}

class DerivedWithPrint extends BaseWithPrint {
  int i = 47;
  public void print() {
    System.out.println("i = " + i);
  }
}

public class E03_Initialization {
  public static void main(String args[]) {
    DerivedWithPrint dp = new DerivedWithPrint();
    dp.print();
  }
} /* Output:
i = 0
i = 47
*///:~
