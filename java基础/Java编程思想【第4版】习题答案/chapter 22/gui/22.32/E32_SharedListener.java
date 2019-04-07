//: gui/E32_SharedListener.java
/****************** Exercise 32 *****************
 * Modify Progress.java so that it does not share
 * models, but instead uses a listener to connect
 * the slider and progress bar.
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.border.*;
import static net.mindview.util.SwingConsole.*;

class Progress extends JFrame {
  JProgressBar pb = new JProgressBar();
  JSlider sb =
    new JSlider(JSlider.HORIZONTAL, 0, 100, 60);
  public Progress() {
    setLayout(new GridLayout(2,1));
    add(pb);
    sb.setValue(0);
    sb.setPaintTicks(true);
    sb.setMajorTickSpacing(20);
    sb.setMinorTickSpacing(5);
    sb.setBorder(new TitledBorder("Slide Me"));
//    pb.setModel(sb.getModel()); // Share model
    add(sb);
    sb.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        pb.setValue(sb.getValue());
      }
    });
  }
}

public class E32_SharedListener {
  public static void main(String args[]) {
    run(new Progress(), 300, 200);
  }
} ///:~
