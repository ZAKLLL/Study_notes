//: arrays/E01_ArrayInitialization.java
/****************** Exercise 1 ******************
 * Create a method that takes an array of
 * BerylliumSphere as an argument. Call the method,
 * creating the argument dynamically. Demonstrate
 * that ordinary aggregate array initialization
 * doesn't work in this case. Discover the only
 * situations where ordinary aggregate array
 * initialization works, and where dynamic aggregate
 * initialization is redundant.
 ************************************************/
package arrays;

public class E01_ArrayInitialization {
  static void hide(BerylliumSphere[] s) {
    System.out.println("Hiding " + s.length + " sphere(s)");
  }
  public static void main(String[] args) {
    // Dynamic aggregate initialization:
    hide(new BerylliumSphere[]{ new BerylliumSphere(),
      new BerylliumSphere() });
    // The following line produces a compilation error.
    //! hide({ new BerylliumSphere() });
    // Aggregate initialization:
    BerylliumSphere[] d = { new BerylliumSphere(),
      new BerylliumSphere(), new BerylliumSphere() };
    hide(d);
    // Dynamic aggregate initialization is redundant
    // in the next case:
    BerylliumSphere[] a = new BerylliumSphere[]{
      new BerylliumSphere(), new BerylliumSphere() };
    hide(a);
  }
} /* Output:
Hiding 2 sphere(s)
Hiding 3 sphere(s)
Hiding 2 sphere(s)
*///:~
