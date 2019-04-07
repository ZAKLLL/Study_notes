//: interfaces/E09_AbstractMusic5.java
/****************** Exercise 9 ******************
 * Refactor Music5.java by moving the common
 * methods in Wind, Percussion and Stringed into
 * an abstract class.
 ***********************************************/
package interfaces;
import polymorphism.music.Note;
import static net.mindview.util.Print.*;

abstract class Instrument {
  public void play(Note n) {
    print(this + ".play() " + n);
  }
  public void adjust() { print(this + ".adjust()"); }
  // Forces implementation in derived class:
  public abstract String toString();
}

class Wind extends Instrument {
  public String toString() { return "Wind"; }
}

class Percussion extends Instrument {
  public String toString() { return "Percussion"; }
}

class Stringed extends Instrument {
  public String toString() { return "Stringed"; }
}

class Brass extends Wind {
  public String toString() { return "Brass"; }
}

class Woodwind extends Wind {
  public String toString() { return "Woodwind"; }
}

public class E09_AbstractMusic5 {
  static void tune(Instrument i) {
    i.adjust();
    i.play(Note.MIDDLE_C);
  }
  static void tuneAll(Instrument[] e) {
    for(Instrument i : e)
      tune(i);
  }
  public static void main(String[] args) {
    Instrument[] orchestra = {
      new Wind(),
      new Percussion(),
      new Stringed(),
      new Brass(),
      new Woodwind()
    };
    tuneAll(orchestra);
  }
} /* Output:
Wind.adjust()
Wind.play() MIDDLE_C
Percussion.adjust()
Percussion.play() MIDDLE_C
Stringed.adjust()
Stringed.play() MIDDLE_C
Brass.adjust()
Brass.play() MIDDLE_C
Woodwind.adjust()
Woodwind.play() MIDDLE_C
*///:~
