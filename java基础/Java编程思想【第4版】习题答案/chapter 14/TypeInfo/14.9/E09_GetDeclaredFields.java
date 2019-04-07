//: typeinfo/E09_GetDeclaredFields.java
// {Args: typeinfo.Derived}
/****************** Exercise 9 ******************
 * Modify the previous exercise so that it uses
 * Class.getDeclaredFields() to also display
 * information about the fields in a class.
 ***********************************************/
package typeinfo;
import java.lang.reflect.*;
import java.util.*;
import static net.mindview.util.Print.*;

interface Iface {
  int i = 47;
  void f();
}

class Base implements Iface {
  String s;
  double d;
  public void f() { System.out.println("Base.f"); }
}

class Composed {
  Base b;
}

class Derived extends Base {
  Composed c;
  String s;
}

public class E09_GetDeclaredFields {
  static Set<Class<?>> alreadyDisplayed =
    new HashSet<Class<?>>();
  static void printClasses(Class<?> c) {
    // getSuperclass() returns null on Object:
    if(c == null) return;
    print(c.getName());
    Field[] fields = c.getDeclaredFields();
    if(fields.length != 0)
      print("Fields:");
    for(Field fld : fields) {
      print("   " + fld);
    }
    for(Field fld : fields) {
      Class<?> k = fld.getType();
      if(!alreadyDisplayed.contains(k)) {
        printClasses(k);
        alreadyDisplayed.add(k);
      }
    }
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
Displaying typeinfo.Derived
typeinfo.Derived
Fields:
   typeinfo.Composed typeinfo.Derived.c
   java.lang.String typeinfo.Derived.s
typeinfo.Composed
Fields:
   typeinfo.Base typeinfo.Composed.b
typeinfo.Base
Fields:
   java.lang.String typeinfo.Base.s
   double typeinfo.Base.d
java.lang.String
Fields:
   private final char[] java.lang.String.value
   private final int java.lang.String.offset
   private final int java.lang.String.count
   private int java.lang.String.hash
   private static final long java.lang.String.serialVersionUID
   private static final java.io.ObjectStreamField[] java.lang.String.serialPersistentFields
   public static final java.util.Comparator java.lang.String.CASE_INSENSITIVE_ORDER
[C
Interface: java.lang.Cloneable
Interface: java.io.Serializable
java.lang.Object
int
long
[Ljava.io.ObjectStreamField;
Interface: java.lang.Cloneable
Interface: java.io.Serializable
java.lang.Object
java.util.Comparator
Interface: java.io.Serializable
Interface: java.lang.Comparable
Interface: java.lang.CharSequence
java.lang.Object
double
Interface: typeinfo.Iface
java.lang.Object
java.lang.Object
typeinfo.Base
Fields:
   java.lang.String typeinfo.Base.s
   double typeinfo.Base.d
Interface: typeinfo.Iface
java.lang.Object
*///:~
