//: typeinfo/E26_ClearSpitValve.java
/****************** Exercise 26 *****************
 * Implement clearSpitValve() as described in the
 * summary.
 ************************************************/
package typeinfo;
// The summary reads:
/*
One option is to put a clearSpitValve() method in
the base class Instrument, but this is confusing
because it implies that Percussion, Stringed and
Electronic instruments also have spit valves. RTTI
provides a much more reasonable solution because you
can place the method in the specific class where it's
appropriate (Wind, in this case). At the same time,
you may discover that there's a more sensible
solution-here, a prepareInstrument() method in the
base class. However, you might not see such a solution
when you're first solving the problem and could
mistakenly assume that you must use RTTI.
*/
// We'll use the last-defined version of the
// instrument hierarchy:
import static net.mindview.util.Print.*;

interface Instrument {
  void play();
  String what();
  void adjust();
  void prepareInstrument();
}

class Wind implements Instrument {
  public void play() { print("Wind.play()"); }
  public String what() { return "Wind"; }
  public void adjust() {}
  public void clearSpitValve() {
    print("Wind.clearSpitValve");
  }
  public void prepareInstrument() {
    clearSpitValve();
  }
}

class Percussion implements Instrument {
  public void play() { print("Percussion.play()"); }
  public String what() { return "Percussion"; }
  public void adjust() {}
  public void prepareInstrument() {
    print("Percussion.prepareInstrument");
  }
}

class Stringed implements Instrument {
  public void play() { print("Stringed.play()"); }
  public String what() { return "Stringed"; }
  public void adjust() {}
  public void prepareInstrument() {
    print("Stringed.prepareInstrument");
  }
}

class Brass extends Wind {
  public void play() { print("Brass.play()"); }
  public void adjust() { print("Brass.adjust()"); }
  public void clearSpitValve() {
    print("Brass.clearSpitValve");
  }
}

class Woodwind extends Wind {
  public void play() { print("Woodwind.play()"); }
  public String what() { return "Woodwind"; }
  public void clearSpitValve() {
    print("Woodwind.clearSpitValve");
  }
}

class Music5 {
  static void tune(Instrument i) {
    // ...
    i.play();
  }
  static void tuneAll(Instrument[] e) {
    for(Instrument instrument : e)
      tune(instrument);
  }
  static void prepareAll(Instrument[] e) {
    for(Instrument instrument : e)
      instrument.prepareInstrument();
  }
}

public class E26_ClearSpitValve {
  public static void main(String[] args) {
    Instrument[] orchestra = {
      new Wind(), new Percussion(),
      new Stringed(), new Brass(),
      new Woodwind(),
    };
    Music5.prepareAll(orchestra);
    Music5.tuneAll(orchestra);
  }
} /* Output:
Wind.clearSpitValve
Percussion.prepareInstrument
Stringed.prepareInstrument
Brass.clearSpitValve
Woodwind.clearSpitValve
Wind.play()
Percussion.play()
Stringed.play()
Brass.play()
Woodwind.play()
*///:~
