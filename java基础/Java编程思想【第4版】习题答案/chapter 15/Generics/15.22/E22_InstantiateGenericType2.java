//: generics/E22_InstantiateGenericType2.java
/****************** Exercise 22 *****************
 * Use a type tag along with reflection to create
 * a method that uses the argument version of
 * newInstance() to create an object of a class
 * with a constructor that has arguments.
 ************************************************/
package generics;
import java.lang.reflect.*;
import static net.mindview.util.Print.*;

class ClassAsFactory<T> {
  Class<T> kind;
  public ClassAsFactory(Class<T> kind) { this.kind = kind; }
  public T create(int arg) {
    try {
      // Technique 1 (verbose)
      for(Constructor<?> ctor : kind.getConstructors()) {
        // Look for a constructor with a single parameter:
        Class<?>[] params = ctor.getParameterTypes();
        if(params.length == 1)
          if(params[0] == int.class)
            return kind.cast(ctor.newInstance(arg)); 
      }
      // Technique 2 (direct)
      // Constructor<T> ct = kind.getConstructor(int.class);
      // return ct.newInstance(arg);
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
    return null;
  }
}

public class E22_InstantiateGenericType2 {
  public static void main(String[] args) {
    ClassAsFactory<Employee> fe =
      new ClassAsFactory<Employee>(Employee.class);
    Employee emp = fe.create(1);
    if(emp == null)
      print("Employee cannot be instantiated!");
    ClassAsFactory<Integer> fi =
      new ClassAsFactory<Integer>(Integer.class);
    Integer i = fi.create(0);
    if(i == null)
      print("Integer cannot be instantiated!");
  }
} /* Output:
Employee cannot be instantiated!
*///:~
