//: generics/E28_GenericReadAndWrite.java
/****************** Exercise 28 *****************
 * Create a generic class Generic1<T> with a single
 * method that takes an argument of type T. Create
 * a second generic class Generic2<T> with a single
 * method that returns an argument of type T. Write
 * a generic method with a contravariant argument of
 * the first generic class that calls its method.
 * Write a second generic method with a covariant
 * argument of the second generic class that calls
 * its method. Test using the typeinfo.pets library.
 ************************************************/
package generics;
import typeinfo.pets.*;

class Generic1<T> {
  public void set(T arg) {}
}

class Generic2<T> {
  public T get() { return null; }
}

public class E28_GenericReadAndWrite {
  static <T> void f1(Generic1<? super T> obj, T item) {
    obj.set(item);
  }
  static <T> T f2(Generic2<? extends T> obj) {
    return obj.get();
  }
  public static void main(String[] args) {
    Generic1<Rodent> g1 = new Generic1<Rodent>();
    f1(g1, new Mouse());  // OK
    // Compile error: Cat is not a Rodent
    // f1(g1, new Cat());  
    Generic2<Pet> g2 = new Generic2<Pet>();
    Pet pet = f2(g2);  // OK
    Generic2<Cat> g3 = new Generic2<Cat>(); 
    Cat cat = f2(g3);  // OK
    pet = f2(g3);  // OK
  }
} ///:~


