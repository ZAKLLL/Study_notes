//: gui/E08_Cursors.java
/****************** Exercise 8 *****************
 * Almost every Swing component is derived from
 * Component, which has a setCursor() method.
 * Look this up in the JDK documentation. Create
 * an application and change the cursor to one of
 * the stock cursors in the Cursor class.
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import static net.mindview.util.SwingConsole.*;

class Cursors extends JFrame {
  JTextField txt = new JTextField(10);
  JButton b1 = new JButton("Button 1");
  Cursor hand =
    Cursor.getPredefinedCursor(
      Cursor.HAND_CURSOR);
  public Cursors() {
    setLayout(new FlowLayout());
    add(txt);
    txt.setCursor(hand);
    add(b1);
    setCursor(hand);
  }
}

public class E08_Cursors {
  public static void main(String args[]) {
    run(new Cursors(), 200, 100);
  }
} ///:~
