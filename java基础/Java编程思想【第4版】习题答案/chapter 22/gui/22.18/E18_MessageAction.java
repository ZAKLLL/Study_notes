//: gui/E18_MessageAction.java
/****************** Exercise 18 ******************
 * Modify MessageBoxes.java so that it has an
 * individual ActionListener for each button (instead
 * of matching the button text).
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static net.mindview.util.SwingConsole.*;

class MessageAction extends JFrame {
  private JButton
    b1 = new JButton("Alert"), 
    b2 = new JButton("Yes/No"),
    b3 = new JButton("Color"), 
    b4 = new JButton("Input"),
    b5 = new JButton("3 Vals");
  private JTextField txt = new JTextField(15);
  public MessageAction() {
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
          "There's a bug on you!", "Hey!",
          JOptionPane.ERROR_MESSAGE);
      }
    });
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(null,
          "or no", "choose yes",
          JOptionPane.YES_NO_OPTION);
      }
    });
    b3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Object[] options = { "Red", "Green" };
        int sel = JOptionPane.showOptionDialog(
          null, "Choose a Color!", "Warning",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.WARNING_MESSAGE, null,
          options, options[0]);
        if(sel != JOptionPane.CLOSED_OPTION)
          txt.setText("Color Selected: " + options[sel]);
      }
    });
    b4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String val = JOptionPane.showInputDialog(
            "How many fingers do you see?");
        txt.setText(val);
      }
    });
    b5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Object[] selections = {"First", "Second", "Third"};
        Object val = JOptionPane.showInputDialog(
          null, "Choose one", "Input",
          JOptionPane.INFORMATION_MESSAGE,
          null, selections, selections[0]);
        if(val != null)
          txt.setText(val.toString());
      }
    });
    setLayout(new FlowLayout());
    add(b1);
    add(b2);
    add(b3);
    add(b4);
    add(b5);
    add(txt);
  }
}

public class E18_MessageAction {
  public static void main(String[] args) {
    run(new MessageAction(), 200, 200);
  }
} ///:~
