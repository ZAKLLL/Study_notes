//: polymorphism/E07_NewInstrument.java
/****************** Exercise 7 *****************
 * Add a new type of Instrument to Music3.java
 * and verify that polymorphism works for your
 * new type.
 ***********************************************/
package polymorphism;
import polymorphism.music.Note;
import static net.mindview.util.Print.*;

class Electronic extends Instrument {
  void play(Note n) { print("Electronic.play() " + n); }
  public String toString() { return "Electronic"; }
}

public class E07_NewInstrument {
  static Instrument[] orchestra = {
    new Wind(),
    new Percussion(),
    new Stringed(),
    new Brass(),
    new Woodwind(),
    new Electronic()
  };
  public static void main(String args[]) {
    for(Instrument i : orchestra) {
      i.play(Note.MIDDLE_C);
      i.adjust();
      print(i);
    }
  }
} /* Output:
Wind.play() MIDDLE_C
Wind
Percussion.play() MIDDLE_C
Percussion
Stringed.play() MIDDLE_C
Stringed
Brass.play() MIDDLE_C
Brass.adjust()
Wind
Woodwind.play() MIDDLE_C
Woodwind
Electronic.play() MIDDLE_C
Electronic
*///:~
