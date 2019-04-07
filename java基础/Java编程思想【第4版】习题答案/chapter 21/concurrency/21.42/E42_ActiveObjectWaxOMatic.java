//: concurrency/E42_ActiveObjectWaxOMatic.java
/******************** Exercise 42 ************************
 * Modify WaxOMatic.java so that it implements active
 * objects.
 *********************************************************/
package concurrency;
import java.util.concurrent.*;
import static net.mindview.util.Print.*;

class ActiveCar {
  private ExecutorService ex =
    Executors.newSingleThreadExecutor();
  private enum Action { WAX, BUFF }
  private Action lastAction = Action.BUFF;
  private boolean waxOn;
  public void wax() {
    try {
      ex.execute(waxingTask);
    } catch(RejectedExecutionException e) {}
  }
  public void buff() {
    try {
      ex.execute(buffingTask);
    } catch(RejectedExecutionException e) {}
  }
  public void shutdown() { ex.shutdown(); }
  private static void pause(int sleep_time) {
    try {
      TimeUnit.MILLISECONDS.sleep(sleep_time);
    } catch(InterruptedException e) {
      print("sleep() interrupted");
    }
  }
  private class WaxingTask implements Runnable {
    public void run() {
      if(lastAction != Action.WAX) {
        printnb("Wax On! ");
        pause(200);
        waxOn = true;
        lastAction = Action.WAX;
      }
    }
  }
  private final WaxingTask waxingTask = new WaxingTask();
  private class BuffingTask implements Runnable {
    public void run() {
      if(lastAction != Action.BUFF) {
        printnb("Wax Off! ");
        pause(200);
        waxOn = false;
        lastAction = Action.BUFF;
      }
    }
  }
  private final BuffingTask buffingTask = new BuffingTask();
}

class WaxCar implements Runnable {
  private final ActiveCar car;
  public WaxCar(ActiveCar c) { car = c; }
  public void run() { car.wax(); }
}

class BuffCar implements Runnable {
  private final ActiveCar car;
  public BuffCar(ActiveCar c) { car = c; }
  public void run() { car.buff(); }
}

public class E42_ActiveObjectWaxOMatic {
  public static void main(String[] args) throws Exception {
    ActiveCar car = new ActiveCar();
    ScheduledExecutorService exec = 
      Executors.newScheduledThreadPool(2);
    exec.scheduleAtFixedRate(
      new BuffCar(car), 0, 200, TimeUnit.MILLISECONDS);
    exec.scheduleAtFixedRate(
      new WaxCar(car), 0, 200, TimeUnit.MILLISECONDS);
    TimeUnit.SECONDS.sleep(5); // Run for a while...
    exec.shutdownNow(); // Interrupt all tasks
    car.shutdown();
  }
} /* Output: (Sample)
Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off!
*///:~
