//: holding/E27_CommandQueue.java
/****************** Exercise 27 *****************
 * Write a class called Command that contains a
 * String and has a method operation() that
 * displays the String. Write a second class with
 * a method that fills a Queue with Command objects
 * and returns it. Pass the filled Queue to a method
 * in a third class that consumes the objects in the
 * Queue and calls their operation() methods.
 ***********************************************/ 
package holding;
import java.util.*;

class Command {
  private final String cmd;
  Command(String cmd) { this.cmd = cmd; }
  public void operation() { System.out.println(cmd); }
}

class Producer {
  public static void produce(Queue<Command> q) {
    q.offer(new Command("load"));
    q.offer(new Command("delete"));
    q.offer(new Command("save"));
    q.offer(new Command("exit"));
  }
}

class Consumer {
  public static void consume(Queue<Command> q) {
    while(q.peek() != null)
      q.remove().operation();
  }
}

public class E27_CommandQueue {
  public static void main(String[] args) {
    Queue<Command> cmds = new LinkedList<Command>();
    Producer.produce(cmds);
    Consumer.consume(cmds);
  }
} /* Output:
load
delete
save
exit
*///:~
