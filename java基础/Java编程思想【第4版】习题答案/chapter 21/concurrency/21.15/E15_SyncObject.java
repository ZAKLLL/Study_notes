//: concurrency/E15_SyncObject.java
/****************** Exercise 15 ************************
 * Create a class with three methods containing critical
 * sections that all synchronize on the same object. Create
 * multiple tasks to demonstrate that only one of these
 * methods can run at a time. Now modify the methods so
 * that each one synchronizes on a different object and
 * show that all three methods can be running at once.
 ******************************************************/
package concurrency;
import static net.mindview.util.Print.*;

class SingleSynch {
  public synchronized void f() {
    for(int i = 0; i < 5; i++) {
      print("f()");
      Thread.yield();
    }
  }
  public synchronized void g() {
    for(int i = 0; i < 5; i++) {
      print("g()");
      Thread.yield();
    }
  }
  public synchronized void h() {
    for(int i = 0; i < 5; i++) {
      print("h()");
      Thread.yield();
    }
  }
}

class TripleSynch {
  private Object syncObjectG = new Object();
  private Object syncObjectH = new Object();
  public synchronized void f() {
    for(int i = 0; i < 5; i++) {
      print("f()");
      Thread.yield();
    }
  }
  public void g() {
    synchronized(syncObjectG) {
      for(int i = 0; i < 5; i++) {
        print("g()");
        Thread.yield();
      }
    }
  }
  public void h() {
    synchronized(syncObjectH) {
      for(int i = 0; i < 5; i++) {
        print("h()");
        Thread.yield();
      }
    }
  }
}

public class E15_SyncObject {
  public static void main(String[] args) throws Exception {
    final SingleSynch singleSync = new SingleSynch();
    final TripleSynch tripleSync = new TripleSynch();
    print("Test SingleSynch...");
    Thread t1 = new Thread() {
      public void run() {
        singleSync.f();
      }
    };
    t1.start();
    Thread t2 = new Thread() {
      public void run() {
        singleSync.g();
      }
    };
    t2.start();
    singleSync.h();
    t1.join();  // Wait for t1 to finish its work
    t2.join();  // Wait for t2 to finish its work
    print("Test TripleSynch...");
    new Thread() {
      public void run() {
        tripleSync.f();
      }
    }.start();
    new Thread() {
      public void run() {
        tripleSync.g();
      }
    }.start();
    tripleSync.h();
  }
} /* Output: (Sample)
Test SingleSynch...
h()
h()
h()
h()
h()
f()
f()
f()
f()
f()
g()
g()
g()
g()
g()
Test TripleSynch...
h()
f()
g()
h()
f()
g()
h()
f()
g()
h()
f()
g()
h()
f()
g()
*///:~
