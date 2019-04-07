//: exceptions/E29_StormyInning2.java
// {ThrowsException}
/****************** Exercise 29 ******************
 * Modify all the exception types in StormyInning.java
 * so that they extend RuntimeException, and show 
 * that no exception specifications or try blocks are
 * necessary. Remove the '//!' comments and show how
 * the methods can be compiled without specifications.
 ***********************************************/
package exceptions;

class BaseballException2 extends RuntimeException {}
class Foul2 extends BaseballException2 {}
class Strike2 extends BaseballException2 {}

abstract class Inning2 {
  Inning2() {}
  public void event() {}
  abstract void atBat();
  void walk() {}
}

class StormException2 extends RuntimeException {}
class RainedOut2 extends StormException2 {}
class PopFoul2 extends Foul2 {}

interface Storm2 {
  void event();
  void rainHard();
}

class StormyInning2 extends Inning2 implements Storm2 {
  StormyInning2() {}
  StormyInning2(String s) {}
  public void rainHard() {}
  void atBat() { throw new PopFoul2(); }
}

public class E29_StormyInning2 {
  public static void main(String[] args) {
    StormyInning2 si = new StormyInning2();
    si.atBat();
  }
} ///:~
