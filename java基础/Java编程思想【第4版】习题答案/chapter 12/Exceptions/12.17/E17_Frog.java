//: exceptions/E17_Frog.java
/****************** Exercise 17 *****************
 * Modify polymorphism/Frog.java so that it uses 
 * try-finally to guarantee proper cleanup, and 
 * show that this works even if you return from the 
 * middle of the try-finally.
 ***********************************************/
package exceptions;

// Frog.dispose() is protected, cannot be called directly
class Frog2 extends polymorphism.Frog {
  protected void dispose() { super.dispose(); }
}

public class E17_Frog {
  public static void main(String[] args) {
    Frog2 frog = new Frog2();
    try {
      // No return in the middle...
    } finally {
      frog.dispose();
    }
    frog = new Frog2();
    try {
      // With return in the middle...
      return;
    } finally {
      frog.dispose();
    }
  }
} /* Output:
Creating Characteristic is alive
Creating Description Basic Living Creature
LivingCreature()
Creating Characteristic has heart
Creating Description Animal not Vegetable
Animal()
Creating Characteristic can live in water
Creating Description Both water and land
Amphibian()
Creating Characteristic Croaks
Creating Description Eats Bugs
Frog()
Frog dispose
disposing Description Eats Bugs
disposing Characteristic Croaks
Amphibian dispose
disposing Description Both water and land
disposing Characteristic can live in water
Animal dispose
disposing Description Animal not Vegetable
disposing Characteristic has heart
LivingCreature dispose
disposing Description Basic Living Creature
disposing Characteristic is alive
Creating Characteristic is alive
Creating Description Basic Living Creature
LivingCreature()
Creating Characteristic has heart
Creating Description Animal not Vegetable
Animal()
Creating Characteristic can live in water
Creating Description Both water and land
Amphibian()
Creating Characteristic Croaks
Creating Description Eats Bugs
Frog()
Frog dispose
disposing Description Eats Bugs
disposing Characteristic Croaks
Amphibian dispose
disposing Description Both water and land
disposing Characteristic can live in water
Animal dispose
disposing Description Animal not Vegetable
disposing Characteristic has heart
LivingCreature dispose
disposing Description Basic Living Creature
disposing Characteristic is alive
*///:~
