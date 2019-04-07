//: gui/E25_AnimatedSine.java
/****************** Exercise 25 ******************
 * Starting with SineWave.java, create a program
 * (an application using the SwingConsole class)
 * that draws an animated sine wave that appears to
 * scroll past the viewing window like an oscilloscope,
 * driving the animation with a java.util.Timer. The
 * speed of the animation should be controlled with
 * a javax.swing.JSlider control.
 ***********************************************/
package gui;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.TimerTask;
import static net.mindview.util.SwingConsole.*;

class ExtSineDraw extends JPanel {
  private static final int SCALEFACTOR = 200;
  private int cycles;
  private int points;
  private double[] sines;
  private int[] pts;
  private java.util.Timer timer = new java.util.Timer();
  private double offset;
  private class MyTimerTask extends TimerTask {
    public void run() {
      offset += 0.25;
      synchronized(ExtSineDraw.this) {
        for(int i = 0; i < points; i++) {
          double radians =
            (Math.PI/SCALEFACTOR) * i + offset;
          sines[i] = Math.sin(radians);
        }
      }
      repaint();
    }
  };
  private JSlider speed = new JSlider(15, 115, 65);
  ExtSineDraw() {
    super(new BorderLayout());
    setCycles(5);
    speed.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        timer.cancel();
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(
          new MyTimerTask(), 0, speed.getValue());
      }
    });
    add(BorderLayout.SOUTH, speed);
    // So that left to right is slow to fast:
    speed.setInverted(true);
    // Incorporate a 1 sec. initial delay to give time
    // for initialization:
    timer.scheduleAtFixedRate(new MyTimerTask(), 1000, 65);
  }
  public synchronized void setCycles(int newCycles) {
    cycles = newCycles;
    points = SCALEFACTOR * cycles * 2;
    sines = new double[points];
    pts = new int[points];
    for(int i = 0; i < points; i++) {
      double radians = (Math.PI / SCALEFACTOR) * i;
      sines[i] = Math.sin(radians);
    }
    repaint();
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int maxWidth = getWidth();
    double hstep = (double)maxWidth / (double)points;
    int maxHeight = getHeight();
    synchronized(this) {
      for(int i = 0; i < points; i++)
        // Some adjustments here to compensate for
        // the added JSlider in the panel:
        pts[i] =
          (int)(sines[i] * maxHeight/2 * .89 + 
          maxHeight/2 * .91);
    }
    g.setColor(Color.RED);
    for(int i = 1; i < points; i++) {
      int x1 = (int)((i - 1) * hstep);
      int x2 = (int)(i * hstep);
      int y1 = pts[i-1];
      int y2 = pts[i];
      g.drawLine(x1, y1, x2, y2);
    }
  }
}

class ExtSineWave extends JFrame {
  private ExtSineDraw sines = new ExtSineDraw();
  private JSlider adjustCycles = new JSlider(1, 30, 5);
  public ExtSineWave() {
    add(sines);
    adjustCycles.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        sines.setCycles(adjustCycles.getValue());
      }
    });
    add(BorderLayout.SOUTH, adjustCycles);
  }
}

public class E25_AnimatedSine {
  public static void main(String[] args) {
    run(new ExtSineWave(), 700, 400);
  }
} ///:~
