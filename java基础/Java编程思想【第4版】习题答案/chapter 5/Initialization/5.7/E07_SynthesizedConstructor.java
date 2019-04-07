//: initialization/E07_SynthesizedConstructor.java
/****************** Exercise 7 ******************
 * Create a class without a constructor, then
 * create an object of that class in main() to
 * verify that the default constructor is
 * automatically synthesized.
 ************************************************/
package initialization;

public class E07_SynthesizedConstructor {
  public static void main(String args[]) {
    // Call the synthesized default constructor
    // for this class:
    new E07_SynthesizedConstructor();
  }
} ///:~
