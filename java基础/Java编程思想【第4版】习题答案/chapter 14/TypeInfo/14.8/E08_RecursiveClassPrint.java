//: typeinfo/E08_RecursiveClassPrint.java
// {Args: typeinfo.Circle typeinfo.FancyToy}
/****************** Exercise 8 ******************
 * Write a method that takes an object and
 * recursively prints all the classes in that
 * object's hierarchy.
 ***********************************************/
package typeinfo;
import static net.mindview.util.Print.*;

public class E08_RecursiveClassPrint {
  static void printClasses(Class<?> c) {
    // getSuperclass() returns null on Object:
    if(c == null) return;
    print(c.getName());
    // Produces the interfaces that this class
    // implements:
    for(Class<?> k : c.getInterfaces()) {
      print("Interface: " + k.getName());
      printClasses(k.getSuperclass());
    }
    printClasses(c.getSuperclass());
  }
  public static void main(String args[]) throws Exception {
    for(int i = 0; i < args.length; i++) {
      print("Displaying " + args[i]);
      printClasses(Class.forName(args[i]));
      if(i < args.length - 1)
        System.out.println("==================");
    }
  }
} /* Output:
Displaying typeinfo.Circle
typeinfo.Circle
typeinfo.Shape
java.lang.Object
==================
Displaying typeinfo.FancyToy
typeinfo.FancyToy
Interface: typeinfo.HasBatteries
Interface: typeinfo.Waterproof
Interface: typeinfo.Shoots
typeinfo.Toy
java.lang.Object
*///:~
