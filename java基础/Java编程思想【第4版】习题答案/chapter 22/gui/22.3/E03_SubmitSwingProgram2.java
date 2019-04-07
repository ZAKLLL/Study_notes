//: gui/E03_SubmitSwingProgram2.java
/******************** Exercise 03 ************************
 * Modify SubmitSwingProgram.java so that it uses
 * SwingConsole.
 *********************************************************/
package gui;
import javax.swing.*;
import java.util.concurrent.*;
import static net.mindview.util.SwingConsole.*;

class SubmitSwingProgram2 extends JFrame {
  JLabel label;
  public SubmitSwingProgram2() {
    label = new JLabel("A Label");
    add(label);
  }
}

public class E03_SubmitSwingProgram2 {
  static SubmitSwingProgram2 ssp;
  public static void main(String[] args) throws Exception {
    run(ssp = new SubmitSwingProgram2(), 300, 100);
    TimeUnit.SECONDS.sleep(2);
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        ssp.label.setText("Hey! This is Different!");
      }
    });
  }
} ///:~
