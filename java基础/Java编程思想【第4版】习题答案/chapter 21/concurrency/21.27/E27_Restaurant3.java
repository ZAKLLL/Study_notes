//: concurrency/E27_Restaurant3.java
/********************** Exercise 27 ***********************
 * Modify Restaurant.java to use explicit Lock and Condition
 * objects.
*********************************************************/
package concurrency;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import static net.mindview.util.Print.*;

class WaitPerson3 implements Runnable {
  private Restaurant3 restaurant;
  Lock lock = new ReentrantLock();
  Condition condition = lock.newCondition();
  public WaitPerson3(Restaurant3 r) { restaurant = r; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        lock.lock();
        try {
          while(restaurant.meal == null)
            condition.await();
        } finally {
          lock.unlock();
        }
        print("Waitperson got " + restaurant.meal);
        restaurant.chef.lock.lock();
        try {
          restaurant.meal = null;
          restaurant.chef.condition.signalAll();
        } finally {
          restaurant.chef.lock.unlock();
        }
      }
    } catch(InterruptedException e) {
      print("WaitPerson interrupted");
    }
  }
}

class Chef3 implements Runnable {
  private Restaurant3 restaurant;
  private int count;
  Lock lock = new ReentrantLock();
  Condition condition = lock.newCondition();
  public Chef3(Restaurant3 r) { restaurant = r; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        lock.lock();
        try {
          while(restaurant.meal != null)
            condition.await();
        } finally {
          lock.unlock();
        }
        if(++count == 10) {
          print("Out of food, closing");
          restaurant.exec.shutdownNow();
        }
        printnb("Order up! ");
        restaurant.waitPerson.lock.lock();
        try {
          restaurant.meal = new Meal(count);
          restaurant.waitPerson.condition.signalAll();
        } finally {
          restaurant.waitPerson.lock.unlock();
        }
        TimeUnit.MILLISECONDS.sleep(100);
      }
    } catch(InterruptedException e) {
      print("Chef interrupted");
    }
  }
}

class Restaurant3 {
  Meal meal;
  ExecutorService exec = Executors.newCachedThreadPool();
  WaitPerson3 waitPerson = new WaitPerson3(this);
  Chef3 chef = new Chef3(this);
  public Restaurant3() {
    exec.execute(chef);
    exec.execute(waitPerson);
  }
}

public class E27_Restaurant3 {
  public static void main(String[] args) {
    new Restaurant3();
  }
} /* Output: (Sample)
Order up! Waitperson got Meal 1
Order up! Waitperson got Meal 2
Order up! Waitperson got Meal 3
Order up! Waitperson got Meal 4
Order up! Waitperson got Meal 5
Order up! Waitperson got Meal 6
Order up! Waitperson got Meal 7
Order up! Waitperson got Meal 8
Order up! Waitperson got Meal 9
Out of food, closing
WaitPerson interrupted
Order up! Chef interrupted
*///:~
