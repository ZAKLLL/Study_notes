//: gui/E27_TimerAnimation.java
/*********************** Exercise 27 *********************
 * Modify Exercise 25 so that the javax.swing.Timer class
 * is used to drive the animation. Note the difference
 * between this and java.util.Timer.
 *********************************************************/
package gui;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import static net.mindview.util.SwingConsole.*;

class SineDraw_T extends JPanel {
  private static final int SCALEFACTOR = 200;
  private int cycles;
  private int points;
  private double[] sines;
  private int[] pts;
  private JSlider speed = new JSlider(0, 15, 5);
  private double offset;
  private Timer timer = new Timer(500,
    new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        offset += 0.25;
        for(int i = 0; i < points; i++) {
          double radians = (Math.PI/SCALEFACTOR)
            * i + offset;
          sines[i] = Math.sin(radians);
        }
        repaint();
      }
    });
  SineDraw_T() {
    super(new BorderLayout());
    setCycles(5);
    speed.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        timer.setDelay(speed.getValue() * 100);
      }
    });
    add(BorderLayout.SOUTH, speed);
    speed.setInverted(true);
    timer.setInitialDelay(1000);
    timer.start();
  }
  public void setCycles(int newCycles) {
    cycles = newCycles;
    points = SCALEFACTOR * cycles * 2;
    sines = new double[points];
    pts = new int[points];
    for(int i = 0; i < points; i++) {
      double radians = (Math.PI/SCALEFACTOR) * i;
      sines[i] = Math.sin(radians);
    }
    repaint();
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.RED);
    int maxWidth = getWidth();
    double hstep =(double)maxWidth/(double)points;
    int maxHeight = getHeight();
    for(int i = 0; i < points; i++)
      // Some adjustments here to compensate for
      // the added JSlider in the panel:
      pts[i] = (int)(sines[i] * maxHeight/2 * .94
                     + maxHeight/2 * .96);
    for(int i = 1; i < points; i++) {
      int x1 = (int)((i - 1) * hstep);
      int x2 = (int)(i * hstep);
      int y1 = pts[i-1];
      int y2 = pts[i];
      g.drawLine(x1, y1, x2, y2);
    }
  }
}

class SineWave_T extends JFrame {
  private SineDraw_T sines = new SineDraw_T();
  private JSlider cycles = new JSlider(1, 30, 5);
  public SineWave_T() {
    add(sines);
    cycles.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        sines.setCycles(cycles.getValue());
      }
    });
    add(BorderLayout.SOUTH, cycles);
  }
}

public class E27_TimerAnimation {
  public static void main(String args[]) {
    run(new SineWave_T(), 700, 400);
  }
} ///:~
