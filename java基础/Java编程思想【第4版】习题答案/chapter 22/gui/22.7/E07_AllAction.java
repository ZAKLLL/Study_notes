//: gui/E07_AllAction.java
/****************** Exercise 7 ******************
 * Create an application using SwingConsole, and
 * add all the Swing components that have an
 * addActionListener() method. (Look these up in
 * the JDK documentation from http://java.sun.com.
 * Hint: Search for addActionListener() using the
 * index.) Capture their events and display an
 * appropriate message for each inside a text field.
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static net.mindview.util.SwingConsole.*;

class AllAction extends JFrame {
  // The JMenuItem's derivatives JCheckBoxMenuItem, JMenu &
  // JRadioButtonMenuItem will not be shown separately:
  JMenuItem mi = new JMenuItem("Menu Item");
  JTextField txt = new JTextField(30);
  JButton b1 = new JButton("Button 1");
  JComboBox jcb = new JComboBox(new String[]{
    "Elements", "To", "Place", "In", "Combobox"
  });
  JFileChooser jfc = new JFileChooser(".");
  public AllAction() {
    setLayout(new FlowLayout());
    add(mi);
    add(txt);
    add(b1);
    add(jcb);
    add(jfc);
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        txt.setText("Button pressed");
      }
    });
    txt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(
          null,
          "JTexfField ActionListener fired",
          "information",
          JOptionPane.INFORMATION_MESSAGE);
      }
    });
    jcb.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        txt.setText("JComboBox selected: " +
          jcb.getSelectedItem());
      }
    });
    jfc.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        txt.setText(
          "FileChooser ActionListener fired: " +
          jfc.getSelectedFile());
      }
    });
    mi.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        txt.setText("JMenuItem selected");
      }
    });
    new Timer(5000, new ActionListener() {
      int i = 0;
      public void actionPerformed(ActionEvent e) {
        txt.setText("Timer Ticked " + i++);
      }
    }).start();
  }
}

public class E07_AllAction {
  public static void main(String args[]) {
    run(new AllAction(), 550, 400);
  }
} ///:~
