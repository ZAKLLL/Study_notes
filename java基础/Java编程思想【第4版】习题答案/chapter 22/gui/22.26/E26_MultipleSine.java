//: gui/E26_MultipleSine.java
/****************** Exercise 26 *****************
 * Modify the previous exercise so that multiple
 * sine wave panels are created within the
 * application. The number of sine wave panels should
 * be controlled by command-line parameters.
 ***********************************************/
package gui;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import static net.mindview.util.SwingConsole.*;

class MultiSineWave extends JFrame {
  ExtSineDraw[] sines;
  JSlider cycles = new JSlider(1, 30, 5);
  public MultiSineWave(int panels) {
    int side = Math.round(
      (float)Math.sqrt((double)panels));
    JPanel jp =
      new JPanel(new GridLayout(side, side));
    sines = new ExtSineDraw[panels];
    for(int i = 0; i < sines.length; i++) {
      sines[i] = new ExtSineDraw();
      jp.add(sines[i]);
    }
    add(jp);
    add(BorderLayout.SOUTH, cycles);
    cycles.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        for(int i = 0; i < sines.length; i++)
          sines[i].setCycles(
            ((JSlider)e.getSource()).getValue());
      }});
  }
}

public class E26_MultipleSine {
  public static void main(String[] args) {
    int panels;
    if(args.length != 0)
      panels = Integer.parseInt(args[0]);
    else
      panels = 4;
    run(new MultiSineWave(panels), 700, 400);
  }
} ///:~
