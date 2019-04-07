//: reusing/E02_NewDetergent.java
/****************** Exercise 2 *****************
 * Inherit a new class from class Detergent.
 * Override scrub() and add a new method called
 * sterilize().
 ***********************************************/
package reusing;

class NewDetergent extends Detergent {
  public void scrub() {
    append(" NewDetergent.scrub()");
    super.scrub(); // Doesn't have to be first
  }
  public void sterilize() { append(" sterilize()"); }
}

public class E02_NewDetergent {
  public static void main(String args[]) {
    NewDetergent nd = new NewDetergent();
    nd.dilute();
    nd.scrub();
    nd.sterilize();
    System.out.println(nd);
  }
} /* Output:
Cleanser dilute() NewDetergent.scrub() Detergent.scrub() scrub() sterilize()
*///:~
