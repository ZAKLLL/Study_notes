//: gui/E14_UseTextArea.java
/****************** Exercise 14 *****************
 * Modify TextPane.java to use a JTextArea instead
 * of a JTextPane.
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.mindview.util.*;
import static net.mindview.util.SwingConsole.*;

class TextPane extends JFrame {
  private JButton b = new JButton("Add Text");
  // Only needed to change this one line:
  JTextArea tp = new JTextArea();
  private static Generator<String> sg =
    new RandomGenerator.String(7);
  public TextPane() {
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        for(int i = 1; i < 10; i++)
          tp.setText(tp.getText() + sg.next() + "\n");
      }
    });
    add(new JScrollPane(tp));
    add(BorderLayout.SOUTH, b);
  }
}

public class E14_UseTextArea {
  public static void main(String[] args) {
    run(new TextPane(), 475, 425);
  }
} ///:~
