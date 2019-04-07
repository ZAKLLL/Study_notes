//: reusing/E03_CartoonWithDefCtor.java
/****************** Exercise 3 ******************
 * Even if you don't create a constructor for
 * Cartoon(), the compiler will synthesize a 
 * default constructor that calls the base-class
 * constructor. Prove that assertion.
 ***********************************************/
package reusing;

class CartoonWithDefCtor extends Drawing {
//!  CartoonWithDefCtor() {
//!    System.out.println("CartoonWithDefCtor constructor");
//!  }
}

public class E03_CartoonWithDefCtor {
  public static void main(String args[]) {
    new CartoonWithDefCtor ();
  }
} /* Output:
Art constructor
Drawing constructor
*///:~
