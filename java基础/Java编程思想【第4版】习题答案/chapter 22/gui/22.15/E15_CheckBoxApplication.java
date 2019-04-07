//: gui/E15_CheckBoxApplication.java
/****************** Exercise 15 *****************
 * Add a check box to the application created in
 * Exercise 5, capture the event, and insert
 * different text into the text field.
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static net.mindview.util.SwingConsole.*;

public class E15_CheckBoxApplication extends JFrame {
  private JButton
    b1 = new JButton("Button 1"),
    b2 = new JButton("Button 2"),
    b3 = new JButton("Button 3");
  private JTextField txt = new JTextField(12);
  JCheckBox check = new JCheckBox("CheckBox");
  private ActionListener bl = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      String name = ((JButton)e.getSource()).getText();
      txt.setText(name);
    }
  };
  public E15_CheckBoxApplication() {
    b1.addActionListener(bl);
    b2.addActionListener(bl);
    b3.addActionListener(bl);
    setLayout(new FlowLayout());
    add(b1);
    add(b2);
    add(b3);
    add(txt);
    add(check);
    check.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JCheckBox jcb = (JCheckBox)e.getSource();
        if(jcb.isSelected())
          txt.setText("Checkbox checked");
        else
          txt.setText("Checkbox unchecked");
      }
    });
  }
  public static void main(String[] args) {
    run(new E15_CheckBoxApplication(), 200, 180);
  }
} ///:~
