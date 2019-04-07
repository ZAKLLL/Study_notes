//: gui/E33_ParallelCallables.java
/****************** Exercise 33 *****************
 * Modify InterruptableLongRunningCallable.java so
 * that it runs all the tasks in parallel rather
 * than sequentially.
 ***********************************************/
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import net.mindview.util.TwoTuple;
import static net.mindview.util.SwingConsole.*;

class CallableTask extends Task
implements Callable<String> {
  public String call() {
    run();
    return "Return value of " + this;
  }
}

class TaskManager<R,C extends Callable<R>>
extends ArrayList<TwoTuple<Future<R>,C>> {
  private ExecutorService exec =
    Executors.newCachedThreadPool();
  public void add(C task) {
    add(new TwoTuple<Future<R>,C>(exec.submit(task),task));
  }
  public List<R> getResults() {
    Iterator<TwoTuple<Future<R>,C>> items = iterator();
    List<R> results = new ArrayList<R>();
    while(items.hasNext()) {
      TwoTuple<Future<R>,C> item = items.next();
      if(item.first.isDone()) {
        try {
          results.add(item.first.get());
        } catch(Exception e) {
          throw new RuntimeException(e);
        }
        items.remove();
      }
    }
    return results;
  }
  public List<String> purge() {
    Iterator<TwoTuple<Future<R>,C>> items = iterator();
    List<String> results = new ArrayList<String>();
    while(items.hasNext()) {
      TwoTuple<Future<R>,C> item = items.next();
      if(!item.first.isDone()) {
        results.add("Cancelling " + item.second);
        item.first.cancel(true); // May interrupt
        items.remove();
      }
    }
    return results;
  }
}

public class E33_ParallelCallables extends JFrame {
  private JButton
    b1 = new JButton("Start Long Running Task"),
    b2 = new JButton("End Long Running Task"),
    b3 = new JButton("Get results");
  private TaskManager<String,CallableTask> manager =
    new TaskManager<String,CallableTask>();
  public E33_ParallelCallables() {
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CallableTask task = new CallableTask();
        manager.add(task);
        System.out.println(task + " added to the queue");
      }
    });
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        for(String result : manager.purge())
          System.out.println(result);
      }
    });
    b3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        for(TwoTuple<Future<String>,CallableTask> tt :
            manager)
          tt.second.id(); // No cast required
        for(String result : manager.getResults())
          System.out.println(result);
      }
    });
    setLayout(new FlowLayout());
    add(b1);
    add(b2);
    add(b3);
  }
  public static void main(String[] args) {
    run(new E33_ParallelCallables(), 200, 150);
  }
} ///:~
