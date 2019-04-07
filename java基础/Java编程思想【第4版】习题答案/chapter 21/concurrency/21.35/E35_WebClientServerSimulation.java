//: concurrency/E35_WebClientServerSimulation.java
// {Args: 8}
/******************** Exercise 35 ************************
 * Modify BankTellerSimulation.java so that it represents
 * Web clients making requests of a fixed number of servers.
 * The goal is to determine the load that the group of
 * servers can handle.
 *********************************************************/
package concurrency;
import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

class WebClient {
  private final int serviceTime;
  public WebClient(int tm) { serviceTime = tm; }
  public int getServiceTime() { return serviceTime; }
  public String toString() {
    return "[" + serviceTime + "]";
  }
}

class WebClientLine extends ArrayBlockingQueue<WebClient> {
  public WebClientLine(int maxLineSize) {
    super(maxLineSize);
  }
  public String toString() {
    if(this.size() == 0)
      return "[Empty]";
    StringBuilder result = new StringBuilder();
    for(WebClient client : this)
      result.append(client);
    return result.toString();
  }
}

class WebClientGenerator implements Runnable {
  private WebClientLine clients;
  volatile int loadFactor = 1;  // Start with one client/sec
  private static Random rand = new Random(47);
  public WebClientGenerator(WebClientLine cq) {
    clients = cq;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        clients.put(new WebClient(rand.nextInt(1000)));
        TimeUnit.MILLISECONDS.sleep(1000 / loadFactor);
      }
    } catch(InterruptedException e) {
      print("WebClientGenerator interrupted");
    }
    print("WebClientGenerator terminating");
  }
}

class Server implements Runnable {
  private static int counter;
  private final int id = counter++;
  private WebClientLine clients;
  public Server(WebClientLine cq) { clients = cq; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        WebClient client = clients.take();
        TimeUnit.MILLISECONDS.sleep(
          client.getServiceTime());
      }
    } catch(InterruptedException e) {
      print(this + "interrupted");
    }
    print(this + "terminating");
  }
  public String toString() { return "Server " + id + " "; }
  public String shortString() { return "T" + id; }
}

class SimulationManager implements Runnable {
  private ExecutorService exec;
  private WebClientGenerator gen;
  private WebClientLine clients;
  private Queue<Server> servers =
    new LinkedList<Server>();
  private int adjustmentPeriod;
  // Indicates whether the queue is stable
  private boolean stable = true;
  private int prevSize;
  public SimulationManager(ExecutorService e,
    WebClientGenerator gen, WebClientLine clients,
    int adjustmentPeriod, int n) {
    exec = e;
    this.gen = gen;
    this.clients = clients;
    this.adjustmentPeriod = adjustmentPeriod;
    // Start with 'n' servers:
    for(int i= 0; i < n; i++) {
      Server server = new Server(clients);
      exec.execute(server);
      servers.add(server);
    }
  }
  public void adjustLoadFactor() {
    // This is actually a control system. By adjusting
    // the numbers, you can reveal stability issues in
    // the control mechanism.
    // If line is stable, increase the 'load factor':
    if(clients.size() > prevSize) {
      if(stable)  // Was stable previous time
        stable = false;
      else if(!stable) {  // Not stable for a second time
        print("Peak load factor: ~" + gen.loadFactor);
        exec.shutdownNow();
      }
    } else {
      print("New load factor: " + ++gen.loadFactor);
      stable = true;
    }
    prevSize = clients.size();
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
        System.out.print(clients + " { ");
        for(Server server : servers)
          printnb(server.shortString() + " ");
        print("}");
        adjustLoadFactor();
      }
    } catch(InterruptedException e) {
      print(this + "interrupted");
    }
    System.out.println(this + "terminating");
  }
  public String toString() { return "SimulationManager "; }
}

public class E35_WebClientServerSimulation {
  static final int MAX_LINE_SIZE = 50;
  static final int NUM_OF_SERVERS = 3;
  static final int ADJUSTMENT_PERIOD = 1000;
  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool();
    WebClientLine clients =
      new WebClientLine(MAX_LINE_SIZE);
    WebClientGenerator g = new WebClientGenerator(clients);
    exec.execute(g);
    exec.execute(new SimulationManager(
      exec, g, clients, ADJUSTMENT_PERIOD, NUM_OF_SERVERS));
    if(args.length > 0) // Optional argument
      TimeUnit.SECONDS.sleep(new Integer(args[0]));
    else {
      System.out.println("Press 'ENTER' to quit");
      System.in.read();
    }
    exec.shutdownNow();
  }
} /* Output: (Sample)
[Empty] { T0 T1 T2 }
New load factor: 2
[Empty] { T0 T1 T2 }
New load factor: 3
[Empty] { T0 T1 T2 }
New load factor: 4
[Empty] { T0 T1 T2 }
New load factor: 5
[Empty] { T0 T1 T2 }
New load factor: 6
[258] { T0 T1 T2 }
[704][383] { T0 T1 T2 }
Peak load factor: ~6
Server 2 interrupted
Server 2 terminating
SimulationManager terminating
Server 1 interrupted
Server 0 interrupted
WebClientGenerator interrupted
Server 1 terminating
Server 0 terminating
WebClientGenerator terminating
*///:~
