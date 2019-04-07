//: generics/E01_PetsHolder.java
/****************** Exercise 1 *****************
 * Use Holder3 with the typeinfo.pets library to
 * show that a Holder3 that is specified to hold
 * a base type can also hold a derived type.
 ************************************************/
package generics;
import typeinfo.pets.*;

public class E01_PetsHolder {
  public static void main(String[] args) {
    Holder3<Pet> h3 = new Holder3<Pet>(new Mouse("Mickey"));
    System.out.println(h3.get());
  }
} /* Output:
Mouse Mickey
*///:~
