//: concurrency/E26_Restaurant2.java
/********************** Exercise 26 ***********************
 * Add a BusBoy class to Restaurant.java. After the meal is
 * delivered, the WaitPerson should notify the BusBoy to
 * clean up.
*********************************************************/
package concurrency;
import java.util.concurrent.*;
import static net.mindview.util.Print.*;

class WaitPerson2 implements Runnable {
  private Restaurant2 restaurant;
  boolean notified;
  public WaitPerson2(Restaurant2 r) { restaurant = r; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        synchronized(this) {
          while(restaurant.meal == null)
            wait(); // ... for the chef to produce a meal
        }
        print("Waitperson got " + restaurant.meal);
        synchronized(restaurant.busBoy) {
          restaurant.busBoy.notified = true;
          restaurant.busBoy.meal = restaurant.meal;
          restaurant.busBoy.notifyAll(); // Clean up
        }
        synchronized(restaurant.chef) {
          restaurant.meal = null;
          restaurant.chef.notifyAll(); // Ready for another
        }
        synchronized(this) {
          if(!notified)
            wait(); // ... for the bus boy to clean up
          notified = false;
        }
      }
    } catch(InterruptedException e) {
      print("WaitPerson interrupted");
    }
  }
}

class BusBoy implements Runnable {
  private Restaurant2 restaurant;
  boolean notified;
  volatile Meal meal;
  public BusBoy(Restaurant2 r) { restaurant = r; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        synchronized(this) {
          if(!notified)
            wait(); // ... for meal delivery
          notified = false;
        }
        print("Busboy cleaned up " + meal);
        synchronized(restaurant.waitPerson) {
          restaurant.waitPerson.notified = true;
          restaurant.waitPerson.notifyAll();
        }
      }
    } catch(InterruptedException e) {
      print("BusBoy interrupted");
    }
  }
}

class Chef2 implements Runnable {
  private Restaurant2 restaurant;
  private int count = 0;
  public Chef2(Restaurant2 r) { restaurant = r; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        synchronized(this) {
          while(restaurant.meal != null)
            wait(); // ... for the meal to be taken
        }
        if(++count == 10) {
          print("Out of food, closing");
          restaurant.exec.shutdownNow();
        }
        printnb("Order up! ");
        synchronized(restaurant.waitPerson) {
          restaurant.meal = new Meal(count);
          restaurant.waitPerson.notifyAll();
        }
        TimeUnit.MILLISECONDS.sleep(100);
      }
    } catch(InterruptedException e) {
      print("Chef interrupted");
    }
  }
}

class Restaurant2 {
  Meal meal;
  ExecutorService exec = Executors.newCachedThreadPool();
  WaitPerson2 waitPerson = new WaitPerson2(this);
  BusBoy busBoy = new BusBoy(this);
  Chef2 chef = new Chef2(this);
  public Restaurant2() {
    exec.execute(chef);
    exec.execute(waitPerson);
    exec.execute(busBoy);
  }
}

public class E26_Restaurant2 {
  public static void main(String[] args) {
    new Restaurant2();
  }
} /* Output: (Sample)
Order up! Waitperson got Meal 1
Busboy cleaned up Meal 1
Order up! Waitperson got Meal 2
Busboy cleaned up Meal 2
Order up! Waitperson got Meal 3
Busboy cleaned up Meal 3
Order up! Waitperson got Meal 4
Busboy cleaned up Meal 4
Order up! Waitperson got Meal 5
Busboy cleaned up Meal 5
Order up! Waitperson got Meal 6
Busboy cleaned up Meal 6
Order up! Waitperson got Meal 7
Busboy cleaned up Meal 7
Order up! Waitperson got Meal 8
Busboy cleaned up Meal 8
Order up! Waitperson got Meal 9
Busboy cleaned up Meal 9
Out of food, closing
WaitPerson interrupted
BusBoy interrupted
Order up! Chef interrupted
*///:~
