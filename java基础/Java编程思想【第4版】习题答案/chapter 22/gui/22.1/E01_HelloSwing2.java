//: gui/E01_HelloSwing2.java
/******************** Exercise 01 ************************
 * Modify HelloSwing.java to prove to yourself that the
 * application will not close without the call to
 * setDefaultCloseOperation().
 *********************************************************/
package gui;
import javax.swing.*;

public class E01_HelloSwing2 {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Hello Swing");
    frame.setSize(300, 100);
    frame.setVisible(true);
  }
} ///:~
