//: gui/E10_TypeableButton.java
/****************** Exercise 10 ******************
 * Create an application using SwingConsole, with a
 * JButton and a JTextField. Write and attach the
 * appropriate listener so that if the button has the
 * focus, characters typed into it will appear in the
 * JTextField.
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static net.mindview.util.SwingConsole.*;

class TypeableButton extends JFrame {
  JTextField txt = new JTextField(10);
  JButton b = new JButton("Button 1");
  public TypeableButton() {
    setLayout(new FlowLayout());
    add(txt);
    add(b);
    b.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent e) {
        txt.setText(txt.getText() + e.getKeyChar());
      }
    });
  }
}

public class E10_TypeableButton {
  public static void main(String args[]) {
    run(new TypeableButton(), 200, 100);
  }
} ///:~
