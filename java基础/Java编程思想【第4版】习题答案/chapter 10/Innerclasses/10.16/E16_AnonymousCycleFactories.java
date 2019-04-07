//: innerclasses/E16_AnonymousCycleFactories.java
/****************** Exercise 16 ******************
 * Use anonymous inner classes to modify the
 * solution to Exercise 18 from the Interfaces chapter.
 ***********************************************/
package innerclasses;

interface Cycle {
  int wheels();
}

interface CycleFactory {
  Cycle getCycle();
}

class Unicycle implements Cycle {
  public int wheels() { return 1; }
  public static CycleFactory factory =
    new CycleFactory() {
      public Unicycle getCycle() { return new Unicycle(); }
    };
}

class Bicycle implements Cycle {
  public int wheels() { return 2; }
  public static CycleFactory factory =
    new CycleFactory() {
      public Bicycle getCycle() { return new Bicycle(); }
    };
}

class Tricycle implements Cycle {
  public int wheels() { return 3; }
  public static CycleFactory factory =
    new CycleFactory() {
      public Tricycle getCycle() { return new Tricycle(); }
    };
}

public class E16_AnonymousCycleFactories {
  public static void ride(CycleFactory fact) {
     Cycle c = fact.getCycle();
     System.out.println("Num. of wheels: " + c.wheels());
  }
  public static void main(String[] args) {
    ride(Unicycle.factory);
    ride(Bicycle.factory);
    ride(Tricycle.factory);
  }
} /* Output:
Num. of wheels: 1
Num. of wheels: 2
Num. of wheels: 3
*///:~
