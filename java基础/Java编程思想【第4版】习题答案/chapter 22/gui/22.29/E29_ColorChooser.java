//: gui/E29_ColorChooser.java
/****************** Exercise 29 *****************
 * In the JDK documentation for javax.swing,
 * look up the JColorChooser. Write a program
 * with a button that brings up the color
 * chooser as a dialog.
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static net.mindview.util.SwingConsole.*;

class ColorChooser extends JFrame {
  JButton b1 = new JButton("Color Chooser");
  public ColorChooser() {
    setLayout(new FlowLayout());
    add(b1);
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JColorChooser.showDialog(
          null, "E29_ColorChooser", Color.cyan);
      }
    });
  }
}

public class E29_ColorChooser {
  public static void main(String args[]) {
    run(new ColorChooser(), 150, 75);
  }
} ///:~
