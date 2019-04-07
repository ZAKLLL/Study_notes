//: control/E03_RandomInts2.java
// {RunByHand}
/****************** Exercise 3 ******************
 * Modify Exercise 2 so your code is
 * surrounded by an "infinite" while loop. It
 * will then run until you interrupt it, 
 * typically with Control-C.
 ************************************************/
package control;

public class E03_RandomInts2 {
  public static void main(String[] args) {
    while(true)
      E02_RandomInts.compareRand();
  }
} ///:~
