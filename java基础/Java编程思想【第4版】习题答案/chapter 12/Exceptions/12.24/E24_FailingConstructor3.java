//: exceptions/E24_FailingConstructor3.java
/****************** Exercise 24 *****************
 * Add a dispose() method to the FailingConstructor
 * class and write code to properly use this class.
 ***********************************************/
package exceptions;

class FailingConstructor3 {
  private final WithDispose wd1, wd2;
  FailingConstructor3(boolean fail)
    throws ConstructionException {
    wd1 = new WithDispose(1);
    try {
      // "Simulate" other code that might throw exceptions
      if(fail) throw new ConstructionException();
    } catch(ConstructionException e) {
      wd1.dispose();
      throw e;
    }
    wd2 = new WithDispose(2);
  }
  void dispose() {
    wd2.dispose();
    wd1.dispose();
  }
}

public class E24_FailingConstructor3 {
  public static void main(String args[]) {
    for(boolean b = false; ; b = !b)
      try {
        System.out.println("Constructing...");
        FailingConstructor3 fc = new FailingConstructor3(b);
        try {
          System.out.println("Processing...");
        } finally {
          System.out.println("Cleanup...");
          fc.dispose();
        }
      } catch(ConstructionException e) {
        System.out.println("Construction has failed: " + e);
        break;
      }
  }
} /* Output:
Constructing...
Processing...
Cleanup...
WithDispose.dispose(): 2
WithDispose.dispose(): 1
Constructing...
WithDispose.dispose(): 1
Construction has failed: exceptions.ConstructionException
*///:~
