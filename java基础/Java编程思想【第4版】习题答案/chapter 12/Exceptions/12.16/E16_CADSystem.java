//: exceptions/E16_CADSystem.java
/****************** Exercise 16 *****************
 * Modify reusing/CADSystem.java to demonstrate 
 * that returning from the middle of a try-finally
 * will still perform proper cleanup.
 ***********************************************/
package exceptions;

public class E16_CADSystem {
  public static void main(String[] args) {
    reusing.CADSystem x = new reusing.CADSystem(47);
    try {
      return;
    } finally {
      x.dispose();
    }
  }
} /* Output:
Shape constructor
Shape constructor
Drawing Line: 0, 0
Shape constructor
Drawing Line: 1, 1
Shape constructor
Drawing Line: 2, 4
Shape constructor
Drawing Circle
Shape constructor
Drawing Triangle
Combined constructor
CADSystem.dispose()
Erasing Triangle
Shape dispose
Erasing Circle
Shape dispose
Erasing Line: 2, 4
Shape dispose
Erasing Line: 1, 1
Shape dispose
Erasing Line: 0, 0
Shape dispose
Shape dispose
*///:~
