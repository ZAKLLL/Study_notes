//: gui/E17_Password.java
/****************** Exercise 17 ******************
 * Create an application using SwingConsole. In the
 * JDK documentation from http://java.sun.com, find
 * the JPasswordField and add this to the program.
 * If the user types in the correct password, use
 * JOptionPane to provide a success message to the
 * user.
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static net.mindview.util.SwingConsole.*;

class Password extends JFrame {
  JPasswordField pwd = new JPasswordField(10);
  public Password() {
    setLayout(new FlowLayout());
    add(new JLabel("Type in your password:"));
    add(pwd);
    pwd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String message = "Incorrect Password";
        JPasswordField pass =
          (JPasswordField)e.getSource();
        if("Blarth".equals(new String(pass.getPassword())))
          message = "Correct Password";
        JOptionPane.showMessageDialog(
          null,
          message,
          "information",
          JOptionPane.INFORMATION_MESSAGE);
      }
    });
  }
}

public class E17_Password {
  public static void main(String args[]) {
    run(new Password(), 200, 100);
  }
} ///:~
