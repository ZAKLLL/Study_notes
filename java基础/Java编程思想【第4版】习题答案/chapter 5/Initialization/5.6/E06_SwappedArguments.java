//: initialization/E06_SwappedArguments.java
/****************** Exercise 6 ******************
 * Modify Exercise 5 so two of the overloaded 
 * methods have two arguments of two different 
 * types, but in reversed order relative to each 
 * other. Verify that this works.
 ************************************************/
package initialization;

class Dog2 {
  public void bark(int i, double d) {
    System.out.println("int, double bark");
  }
  public void bark(double d, int i) {
    System.out.println("double, int bark");
  }
}

public class E06_SwappedArguments {
  public static void main(String args[]) {
    Dog2 dog = new Dog2();
    dog.bark(1, 2.2);
    dog.bark(2.2, 1);
  }
} /* Output:
int, double bark
double, int bark
*///:~
