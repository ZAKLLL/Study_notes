//: exceptions/E23_FailingConstructor2.java
/****************** Exercise 23 *****************
 * Add a class with a dispose() method to the
 * previous exercise. Modify FailingConstructor so
 * that the constructor creates one of these
 * disposable objects as a member object, after which
 * the constructor might throw an exception, after
 * which it creates a second disposable member object.
 * Write code to properly guard against failure, and
 * in main() verify that all possible failure
 * situations are covered.
 ***********************************************/
package exceptions;

class WithDispose {
  private final int id;
  WithDispose(int id) { this.id = id; }
  void dispose() {
    System.out.println("WithDispose.dispose(): " + id);
  }
}

class FailingConstructor2 {
  private final WithDispose wd1, wd2;
  FailingConstructor2(boolean fail)
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
}

public class E23_FailingConstructor2 {
  public static void main(String args[]) {
    for(boolean b = false; ; b = !b)
      try {
        System.out.println("Constructing...");
        FailingConstructor2 fc = new FailingConstructor2(b);
        try {
          System.out.println("Processing...");
        } finally {
          // We do not have a decent method to call for
          // cleanup!
          System.out.println("Cleanup...");
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
Constructing...
WithDispose.dispose(): 1
Construction has failed: exceptions.ConstructionException
*///:~
