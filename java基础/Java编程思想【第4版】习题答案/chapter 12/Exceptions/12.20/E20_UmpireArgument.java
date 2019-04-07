//: exceptions/E20_UmpireArgument.java
/****************** Exercise 20 *****************
 * Modify StormyInning.java by adding an
 * UmpireArgument exception type and methods
 * that throw this exception. Test the modified
 * hierarchy.
 ***********************************************/
package exceptions;

class BaseballException extends Exception {}
class Foul extends BaseballException {}
class Strike extends BaseballException {}
class UmpireArgument extends BaseballException {}

abstract class Inning {
  Inning() throws BaseballException {}
  void event () throws BaseballException {}
  abstract void atBat()
    throws Strike, Foul, UmpireArgument;
  abstract void decision() throws UmpireArgument;
  void walk() {} // Throws nothing
}

class StormException extends Exception {}
class RainedOut extends StormException {}
class PopFoul extends Foul {}

interface Storm {
  public void event() throws RainedOut;
  public void rainHard() throws RainedOut;
}

class StormyInning extends Inning
  implements Storm {
  StormyInning() throws RainedOut,
    BaseballException {}
  StormyInning(String s) throws Foul,
    BaseballException {}
  public void rainHard() throws RainedOut {}
  public void event() {}
  void atBat() throws PopFoul, UmpireArgument {
    throw new UmpireArgument();
  }
  void decision() throws UmpireArgument {
    throw new UmpireArgument();
  }
}

public class E20_UmpireArgument {
  public static void main(String[] args) {
    // Same code as before, still catches
    // the new exception:
    try {
      StormyInning si = new StormyInning();
      si.atBat();
    } catch(PopFoul e) {
      System.out.println("Pop foul");
    } catch(RainedOut e) {
      System.out.println("Rained out");
    } catch(BaseballException e) {
      System.out.println("Generic error");
    }
    // Strike not thrown in derived version.
    try {
      Inning i = new StormyInning();
      i.atBat();
    } catch(Strike e) {
      System.out.println("Strike");
    } catch(Foul e) {
      System.out.println("Foul");
    } catch(RainedOut e) {
      System.out.println("Rained out");
    } catch(BaseballException e) {
      System.out.println("Generic baseball exception");
    }
    // Or you can add code to catch the
    // specific type of exception:
    try {
      StormyInning si = new StormyInning();
      si.atBat();
      si.decision();
    } catch(PopFoul e) {
      System.out.println("Pop foul");
    } catch(RainedOut e) {
      System.out.println("Rained out");
    } catch(UmpireArgument e) {
      System.out.println(
        "Argument with the umpire");
    } catch(BaseballException e) {
      System.out.println("Generic error");
    }
  }
} /* Output:
Generic error
Generic baseball exception
Argument with the umpire
*///:~
