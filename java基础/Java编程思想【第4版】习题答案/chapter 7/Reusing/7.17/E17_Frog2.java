//: reusing/E17_Frog2.java
/****************** Exercise 17 *****************
 * Modify Exercise 16 so Frog overrides the
 * method definitions from the base class
 * (provides new definitions using the same
 * method signatures). Note what happens in
 * main().
 ***********************************************/
package reusing;

class Frog2 extends Amphibian {
  public void moveInWater() {
    System.out.println("Frog swimming");
  }
  public void moveOnLand() {
    System.out.println("Frog jumping");
  }
}

public class E17_Frog2 {
  public static void main(String args[]) {
    Amphibian a = new Frog2();
    a.moveInWater();
    a.moveOnLand();
  }
} /* Output:
Frog swimming
Frog jumping
*///:~
