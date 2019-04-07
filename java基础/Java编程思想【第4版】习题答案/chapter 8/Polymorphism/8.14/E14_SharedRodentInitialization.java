//: polymorphism/E14_SharedRodentInitialization.java
/****************** Exercise 14 *****************
 * Modify Exercise 12 so one of the member
 * objects is a shared object with reference
 * counting, and demonstrate that it works
 * properly.
 ***********************************************/
package polymorphism;
import static net.mindview.util.Print.*;

class NonSharedMember {
  public NonSharedMember(String id) {
    print("Non shared member constructor " + id);
  }
}

class SharedMember {
  private int refcount;
  public void addRef() {
    print("Number of references " + ++refcount);
  }
  protected void dispose() {
    if(--refcount == 0)
      print("Disposing " + this);
  }
  public SharedMember() {
    print("Shared member constructor");
  }
  public String toString() { return "Shared member"; }
}

class Rodent3 {
  private SharedMember m;
  NonSharedMember m1 = new NonSharedMember("r1"),
                  m2 = new NonSharedMember("r2");
  public Rodent3(SharedMember sm) {
    print("Rodent constructor");
    m = sm;
    m.addRef();
  }
  public void hop() { print("Rodent hopping"); }
  public void scurry() { print("Rodent scurrying"); }
  public void reproduce() { print("Making more Rodents"); }
  protected void dispose() {
    print("Disposing " + this);
    m.dispose();
  }
  public String toString() { return "Rodent"; }
}

class Mouse3 extends Rodent3 {
  NonSharedMember m1 = new NonSharedMember("m1"),
                  m2 = new NonSharedMember("m2");
  public Mouse3(SharedMember sm) {
    super(sm);
    print("Mouse constructor");
  }
  public void hop() { print("Mouse hopping"); }
  public void scurry() { print("Mouse scurrying"); }
  public void reproduce() { print("Making more Mice"); }
  public String toString() { return "Mouse"; }
}

class Gerbil3 extends Rodent3 {
  private SharedMember m;
  NonSharedMember m1 = new NonSharedMember("g1"),
                  m2 = new NonSharedMember("g2");
  public Gerbil3(SharedMember sm) {
    super(sm);
    print("Gerbil constructor");
  }
  public void hop() { print("Gerbil hopping"); }
  public void scurry() { print("Gerbil scurrying"); }
  public void reproduce() { 
    print("Making more Gerbils");
  }
  public String toString() { return "Gerbil"; }
}

class Hamster3 extends Rodent3 {
  private SharedMember m;
  NonSharedMember m1 = new NonSharedMember("h1"),
                  m2 = new NonSharedMember("h2");
  public Hamster3(SharedMember sm) {
    super(sm);
    print("Hamster constructor");
  }
  public void hop() { print("Hamster hopping"); }
  public void scurry() { print("Hamster scurrying"); }
  public void reproduce() { 
    print("Making more Hamsters");
  }
  public String toString() { return "Hamster"; }
}

public class E14_SharedRodentInitialization {
  public static void main(String args[]) {
    SharedMember sm = new SharedMember();
    Rodent3[] rodents = {
      new Hamster3(sm),
      new Gerbil3(sm),
      new Mouse3(sm),
    };
    for(Rodent3 r : rodents)
      r.dispose();
  }
} /* Output:
Shared member constructor
Non shared member constructor r1
Non shared member constructor r2
Rodent constructor
Number of references 1
Non shared member constructor h1
Non shared member constructor h2
Hamster constructor
Non shared member constructor r1
Non shared member constructor r2
Rodent constructor
Number of references 2
Non shared member constructor g1
Non shared member constructor g2
Gerbil constructor
Non shared member constructor r1
Non shared member constructor r2
Rodent constructor
Number of references 3
Non shared member constructor m1
Non shared member constructor m2
Mouse constructor
Disposing Hamster
Disposing Gerbil
Disposing Mouse
Disposing Shared member
*///:~
