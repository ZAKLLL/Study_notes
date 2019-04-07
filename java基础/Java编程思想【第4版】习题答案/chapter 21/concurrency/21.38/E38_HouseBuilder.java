//: concurrency/E38_HouseBuilder.java
/******************** Exercise 38 ************************
 * Using the approach in CarBuilder.java, model the
 * house-building story that was given in this chapter.
 *********************************************************/
package concurrency;
import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

class House {
  private final int id;
  private boolean
    steel = false, concreteForms = false,
    concreteFoundation = false, plumbing = false,
    concreteSlab = false, framing = false;
  public House(int idn)  { id = idn; }
  public House()  { id = -1; }
  public synchronized int getId() { return id; }
  public synchronized void laySteel() { steel = true; }
  public synchronized void buildConcreteForms() {
    concreteForms = true;
  }
  public synchronized void pourConcreteFoundation() {
    concreteFoundation = true;
  }
  public synchronized void addPlumbing() {
    plumbing = true;
  }
  public synchronized void pourConcreteSlab() {
    concreteSlab = true;
  }
  public synchronized void startFraming() {
    framing = true;
  }
  public synchronized String toString() {
    return "House " + id + " [" + " steel: " + steel
      + " concreteForms: " + concreteForms
      + " concreteFoundation: " + concreteFoundation
      + " plumbing: " + plumbing
      + " concreteSlab: " + concreteSlab
      + " framing: " + framing + "]";
  }
}

class HouseQueue extends LinkedBlockingQueue<House> {}

class FootingsDigger implements Runnable {
  private HouseQueue houseQueue;
  private int counter = 0;
  public FootingsDigger(HouseQueue cq) { houseQueue = cq; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        TimeUnit.MILLISECONDS.sleep(500);
        // Dig footings:
        House h = new House(counter++);
        print("FootingsDigger created " + h);
        houseQueue.put(h);
      }
    } catch(InterruptedException e) {
      print("Interrupted: FootingsDigger");
    }
    print("FootingsDigger off");
  }
}

class HouseBuilder implements Runnable {
  private HouseQueue footingsQueue, finishingQueue;
  private House house;
  private CyclicBarrier barrier1 = new CyclicBarrier(3);
  private CyclicBarrier barrier2 = new CyclicBarrier(2);
  private TeamPool teamPool;
  private boolean secondStage = true;
  public HouseBuilder(HouseQueue hq, HouseQueue fq,
    TeamPool tp){
    footingsQueue = hq;
    finishingQueue = fq;
    teamPool = tp;
  }
  public House house() { return house; }
  public CyclicBarrier barrier() { 
    return secondStage ? barrier1 : barrier2;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until footings are dug:
        house = footingsQueue.take();
        teamPool.hire(SteelTeam.class, this);
        teamPool.hire(ConcreteFormsTeam.class, this);
        barrier1.await();
        secondStage = false;
        teamPool.hire(ConcreteFoundationTeam.class, this);
        barrier2.await();
        teamPool.hire(PlumbingTeam.class, this);
        barrier2.await();
        teamPool.hire(ConcreteSlabTeam.class, this);
        barrier2.await();
        teamPool.hire(FramingTeam.class, this);
        barrier2.await();
        finishingQueue.put(house);
        secondStage = true;
      }
    } catch(InterruptedException e) {
      print("Exiting HouseBuilder via interrupt");
    } catch(BrokenBarrierException e) {
      throw new RuntimeException(e);
    }
    print("HouseBuilder off");
  }
}

class Reporter2 implements Runnable {
  private HouseQueue houseQueue;
  public Reporter2(HouseQueue hq) { houseQueue = hq; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        print(houseQueue.take());
      }
    } catch(InterruptedException e) {
      print("Exiting Reporter via interrupt");
    }
    print("Reporter off");
  }
}

abstract class Team implements Runnable {
  private TeamPool pool;
  public Team(TeamPool p) { pool = p; }
  protected HouseBuilder hb;
  public Team assignHouseBuilder(HouseBuilder hb) {
    this.hb = hb;
    return this;
  }
  private boolean engage = false;
  public synchronized void engage() {
    engage = true;
    notifyAll();
  }
  abstract protected void performService();
  public void run() {
    try {
      rest();
      while(!Thread.interrupted()) {
        performService();
        hb.barrier().await();
        rest();
      }
    } catch(InterruptedException e) {
      print("Exiting " + this + " via interrupt");
    } catch(BrokenBarrierException e) {
      throw new RuntimeException(e);
    }
    print(this + " off");
  }
  private synchronized void
  rest() throws InterruptedException {
    engage = false;
    hb = null;
    // Put ourselves back in the available pool:
    pool.release(this);
    while(engage == false)
      wait();
  }
  public String toString() { return getClass().getName(); }
}

class SteelTeam extends Team {
  public SteelTeam(TeamPool pool) { super(pool); }
  protected void performService() {
    print(this + " laying steel");
    hb.house().laySteel();
  }
}

class ConcreteFormsTeam extends Team {
  public ConcreteFormsTeam(TeamPool pool) { super(pool); }
  protected void performService() {
    print(this + " building concrete forms");
    hb.house().buildConcreteForms();
  }
}

class ConcreteFoundationTeam extends Team {
  public ConcreteFoundationTeam(TeamPool pool) {
    super(pool);
  }
  protected void performService() {
    print(this + " pouring concrete foundation");
    hb.house().pourConcreteFoundation();
  }
}

class PlumbingTeam extends Team {
  public PlumbingTeam(TeamPool pool) { super(pool); }
  protected void performService() {
    print(this + " add plumbing");
    hb.house().addPlumbing();
  }
}

class ConcreteSlabTeam extends Team {
  public ConcreteSlabTeam(TeamPool pool) { super(pool); }
  protected void performService() {
    print(this + " pour concrete slab");
    hb.house().pourConcreteSlab();
  }
}

class FramingTeam extends Team {
  public FramingTeam(TeamPool pool) { super(pool); }
  protected void performService() {
    print(this + " start framing");
    hb.house().startFraming();
  }
}

class TeamPool {
  private Set<Team> pool = new HashSet<Team>();
  public synchronized void add(Team t) {
    pool.add(t);
    notifyAll();
  }
  public synchronized void
  hire(Class<? extends Team> teamType, HouseBuilder hb)
  throws InterruptedException {
    for(Team t : pool)
      if(t.getClass().equals(teamType)) {
        pool.remove(t);
        t.assignHouseBuilder(hb);
        t.engage();
        return;
      }
    wait();
    hire(teamType, hb);
  }
  public void release(Team t) { add(t); }
}

public class E38_HouseBuilder {
  public static void main(String[] args) throws Exception {
    HouseQueue footingsQueue = new HouseQueue(),
             finishingQueue = new HouseQueue();
    ExecutorService exec = Executors.newCachedThreadPool();
    TeamPool teamPool = new TeamPool();
    exec.execute(new SteelTeam(teamPool));
    exec.execute(new ConcreteFormsTeam(teamPool));
    exec.execute(new ConcreteFoundationTeam(teamPool));
    exec.execute(new PlumbingTeam(teamPool));
    exec.execute(new ConcreteSlabTeam(teamPool));
    exec.execute(new FramingTeam(teamPool));
    exec.execute(new HouseBuilder(
      footingsQueue, finishingQueue, teamPool));
    exec.execute(new Reporter2(finishingQueue));
    exec.execute(new FootingsDigger(footingsQueue));
    TimeUnit.SECONDS.sleep(7);
    exec.shutdownNow();
  }
} /* (Execute to see output) *///:~
