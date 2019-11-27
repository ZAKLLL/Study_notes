1. **CountDownLatch**

   父线程等待子线程结束完毕后执行(相当于给每个子线程都join)

   ```java
   public class JdkCountDown {
       public static void main(String[] args) throws InterruptedException {
           System.out.println("准备执行多线程任务");
           final Random random = new Random(System.currentTimeMillis());
           final CountDownLatch latch = new CountDownLatch(5);
           IntStream.rangeClosed(1, 5).forEach(i -> new Thread(() -> {
               System.out.println(Thread.currentThread().getName() + "is Working");
               try {
                   Thread.sleep(random.nextInt(1000));
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               latch.countDown();
           }, String.valueOf(i)).start());
           latch.await();
           System.out.println("多线程执行完毕----------");
       }
   }
   ```

   

2. **CyclicBarrier**

   子线程之间互相等待，满足条件后，一起进入线程的下一周期任务执行。可使用Phaser实现

3. **Exchanger**

   1. 成对线程之间进行通信(交换的是实例本身，而不是实例的clone)：

      ```java
      public static void main(String[] args) {
              final Exchanger<String> exchanger = new Exchanger<>();
              new Thread(new Runnable() {
                  String a = "Thread-A";
                  @Override
                  public void run() {
                      try {
                          System.out.println("Thread-A:"+exchanger.exchange(a));
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }, "Thread-A").start();
              new Thread(new Runnable() {
                  String a = "Thread-B";
                  @Override
                  public void run() {
                      try {
                          System.out.println(Thread.currentThread().getName()  + exchanger.exchange(a));
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }, "Thread-B").start();
          }
      ```

4. **Semaphore**

   1. 使用一个信号量完成锁操作：

      ```java
      static class SemaphoreLock {
              private final Semaphore semaphore = new Semaphore(1);
      
              public void lock() throws InterruptedException {
                  semaphore.acquire(); 
              }
      
              public void unlock() {
                  semaphore.release(); //release与acquire对应，拿了多少permit就要release多少
              }
          }
      ```

5. **Phaser**

   1. 常用API:
      1. **getPhase()**：获取当前phase周期数。如果Phaser已经中断，则返回负值。
      2. **arriveAndAwaitAdvance()**：到达，且阻塞直到其他parties都到达，且advance。此方法等同于awaitAdvance(arrive())。如果你希望阻塞机制支持timeout、interrupted响应，可以使用类似的其他方法
      3. **arriveAndDeregister()**：到达，并注销一个parties数量，非阻塞方法。注销，将会导致Phaser内部的parties个数减一（只影响当前phase），即下一个phase需要等待arrive的parties数量将减一。异常机制和返回值，与arrive方法一致。
      4. **Arrival**：Phaser中的arrive()、arriveAndDeregister()方法，这两个方法不会阻塞（block），但是会返回相应的phase数字，当此phase中最后一个party也arrive以后，phase数字将会增加，即phase进入下一个周期，同时触发（onAdvance）那些阻塞在上一phase的线程。这一点类似于CyclicBarrier的barrier到达机制；更灵活的是，我们可以通过重写onAdvance方法来实现更多的触发行为。 
      5. **Phaser(int parties)**：构造函数，初始一定数量的parties；相当于直接regsiter此数量的parties。
      6. **register()**：新注册一个party，导致Phaser内部registerPaties数量加1；如果此时onAdvance方法正在执行，此方法将会等待它执行完毕后才会返回。此方法返回当前的phase周期数，如果Phaser已经中断，将会返回负数。
      7.  **awaitAdvance(int phase)**：阻塞方法，等待phase周期数下其他所有的parties都到达。如果指定的phase与Phaser当前的phase不一致，则立即返回。 
   2. 实现原理(简述)：
      1. **两个计数器**：分别表示parties个数和当前phase。register和deregister会触发parties变更（CAS），全部parties到达（arrive）会触发phase变更。
      2. **一个主要的阻塞队列**：非AQS实现，对于arriveAndWait的线程，会被添加到队列中并被park阻塞，知道当前phase中最后一个party到达后触发唤醒。

6. **ReentrantLock**

   1. 使用ReentrantLock+Condition实现生产消费者队列：

      ```java
      	private final static Lock lock = new ReentrantLock();
          private final static Condition PRODUCE_COND = lock.newCondition();
          private final static Condition CONSUME_COND = lock.newCondition();
          private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();
          private final static int MAX_CAPACITY = 5;
      
      
          private static void pruoduce() {
              try {
                  lock.lock();
                  while (TIMESTAMP_POOL.size() >= MAX_CAPACITY) {
                      PRODUCE_COND.await(); //生产者block 等待被消费
                  }
                  long value = System.currentTimeMillis();
                  TimeUnit.SECONDS.sleep(1);
                  System.out.println(Thread.currentThread().getName() + "-PRODUCE-" + value);
                  TIMESTAMP_POOL.addLast(value);
                  CONSUME_COND.signalAll();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } finally {
                  lock.unlock();
              }
          }
      
          private static void consume() {
              try {
                  lock.lock();
                  while (TIMESTAMP_POOL.isEmpty()) {
                      CONSUME_COND.await();
                  }
                  TimeUnit.SECONDS.sleep(1);
                  System.out.println(Thread.currentThread().getName() + "Consume:" + TIMESTAMP_POOL.pop());
                  PRODUCE_COND.signalAll();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } finally {
                  lock.unlock();
              }
          }
      
      
          public static void main(String[] args) {
              IntStream.range(0, 7).forEach(i -> new Thread(() -> {
                  while (true) {
                      pruoduce();
                      try {
                          TimeUnit.SECONDS.sleep(1);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }, "PRODUCE_THREAD" + i).start());
              IntStream.range(0, 5).forEach(i -> new Thread(() -> {
                  while (true) {
                      consume();
                      try {
                          TimeUnit.SECONDS.sleep(1);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
      
                  }
              }, "CONSUME_THREAD" + i).start());
      
          }
      ```

      

7. **ReadWriteLock**

8. **StampedLock**

   1. 使用StamedLock实现乐观锁

      ```java
      private static void Doread() {
              long stamp = lock.tryOptimisticRead(); //non blocking
             	result=read()
              if (!lock.validate(stamp)) { // 如果在读期间发生了修改,返回false,再次进行加锁的读操作
                  try {
                      stamp = lock.readLock();
                      result= read()
                  } finally {
                      lock.unlockRead(stamp);
                  }
              }
          }
      
      
          private static void write() {
              long stamped = -1;
              try {
                  stamped = lock.writeLock();
                  write(data);
              } finally {
                  lock.unlockWrite(stamped);
              }
          }
      ```

      

9. **Condition**

10. **ForkJoin**

    1. ![1572399149083](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1572399149083.png)

    2. 分而治之递归思想，将大的任务分解成满足条件的小任务进行执行，充分利用CPU计算资源：

       1. **RecursiveTask**(有返回值)：
       
          ```java
          
          public class ForkJoinRecursiveTask {
          
          
              private final static int MAX_THRESHOLD = 50;
          
              //需要继承RecursiveTask类并重写compute函数
              private static class CalculatedRecursiveTask extends RecursiveTask<Integer> {
                  private final int start;
                  private final int end;
          
                  private CalculatedRecursiveTask(int start, int end) {
                      this.start = start;
                      this.end = end;
                  }
          
                  @Override
                  protected Integer compute() {
                      if (end - start <= MAX_THRESHOLD) {
                          return IntStream.rangeClosed(start, end).sum();
                      } else {
                          int middle = (start + end) / 2;
          
                          CalculatedRecursiveTask leftTask = new CalculatedRecursiveTask(start, middle);
                          CalculatedRecursiveTask rightTask = new CalculatedRecursiveTask(middle + 1, end);
                          leftTask.fork();
                          rightTask.fork();
                          return rightTask.join() + leftTask.join();
                      }
                  }
              }
          
              public static void main(String[] args) {
                  final ForkJoinPool forkJoinPool = new ForkJoinPool();
                  ForkJoinTask<Integer> future = forkJoinPool.submit(new CalculatedRecursiveTask(0, 10000));
                  try {
                      int res = future.get(); //这是一个block的调用，会一直等到有结果返回。
                  } catch (InterruptedException | ExecutionException e) {
                      e.printStackTrace();
                  }
                  //普通的单线程计算0到10000的和
                  int res = IntStream.rangeClosed(0, 10000).sum();
              }
          }
          
          ```

       
       2. 使用**RecursiveAction**(无返回值)：
       
          ```java
          private final static AtomicInteger SUM = new AtomicInteger(0);
              private static class CalculateRecursiveAction extends RecursiveAction {
          
                  private final int start;
                  private final int end;
          
                  private CalculateRecursiveAction(int start, int end) {
                      this.start = start;
                      this.end = end;
                  }
          
          
                  @Override
                  protected void compute() {
                      if (end - start <= MAX_THRESHOLD) {
                          SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
                      } else {
                          int mid = (start + end) / 2;
                          CalculateRecursiveAction leftTask = new CalculateRecursiveAction(start, mid);
                          CalculateRecursiveAction rightTask = new CalculateRecursiveAction(mid+1, end);
                          leftTask.fork();
                          rightTask.fork();
                      }
                  }
              }
           
          public static void main(String[] args) throws InterruptedException {
                  ForkJoinPool forkJoinPool = new ForkJoinPool();
                  forkJoinPool.submit(new CalculateRecursiveAction(0, 10000));
                  forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);//不知道任务何时执行完毕，所以需要等待，否则主线程直接结束了。
                  System.out.println(SUM.get());
       }
          
          ```

