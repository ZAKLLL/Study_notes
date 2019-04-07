//: gui/E34_ColorStars.java
/****************** Exercise 34 *****************
 * Modify ColorBoxes.java so that it begins by
 * sprinkling points ("stars") across the canvas,
 * then randomly changes the colors of those "stars."
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.SwingConsole.*;

class JStar extends JComponent implements Runnable {
  // The number of points on the star:
  private final int num_points;
  private final int pause;
  static Random rand = new Random();
  private Color color = new Color(0);
  // Fields used by the drawing algorithm:
  private final int[] x;
  private final int[] y;
  private final double[] dx;
  private final double[] dy;
  public JStar(int num_points, int pause) {
    this.num_points = num_points;
    this.pause = pause;
    x = new int[num_points * 2];
    y = new int[num_points * 2];
    dx = new double[num_points * 2];
    dy = new double[num_points * 2];
    // Cache calculated data:
    double delta = Math.PI / num_points;
    for(int i = 0; i < num_points * 2; i++) {
      dx[i] = Math.cos(delta * i);
      dy[i] = Math.sin(delta * i);
    }
  }
  public void paintComponent(Graphics g) {
    int width = getWidth();
    int height = getHeight();
    // The coordinates of the centre of the star:
    int centreX = width / 2;
    int centreY = height / 2;
    // The distance from the centre to points of the star
    // and to the inner core of the star, respectively:
    int outerRing, innerRing;
    if(width < height) {
      innerRing = width / 8; 
      outerRing = width / 2;
    } else {
      innerRing = height / 8;
      outerRing = height / 2;
    }
    int d;
    for(int i = 0; i < num_points * 2; i++) {
      d = (i % 2 == 0) ? innerRing : outerRing;
      x[i] = centreX + (int)(d * dx[i]);
      y[i] = centreY + (int)(d * dy[i]);
    }
    g.setColor(color);
    g.fillPolygon(x ,y , num_points * 2);
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        color = new Color(rand.nextInt(0xFFFFFF));
        repaint(); // Asynchronously request a paint()
        TimeUnit.MILLISECONDS.sleep(pause);
      }
    } catch(InterruptedException e) {
      // Acceptable way to exit
    }
  }
}

public class E34_ColorStars extends JFrame {
  private static int grid = 12;
  private static int pause = 50;
  private static ExecutorService exec =
    Executors.newCachedThreadPool();
  public E34_ColorStars() {
    setLayout(new GridLayout(grid, grid));
    for(int i = 0; i < grid * grid; i++) {
      JStar js =
        new JStar(JStar.rand.nextInt(12) + 3, pause);
      add(js);
      exec.execute(js);
    }
  }
  public static void main(String[] args) {
    if(args.length > 0)
      E34_ColorStars.grid = new Integer(args[0]);
    if(args.length > 1)
      E34_ColorStars.pause = new Integer(args[1]);
    E34_ColorStars stars = new E34_ColorStars();
    run(stars, 500, 400);
  }
} ///:~
