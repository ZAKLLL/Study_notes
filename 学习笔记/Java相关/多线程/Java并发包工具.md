# JDK并发包-JUC

1. CountDownLatch

2. CyclicBarrier

3. Exchanger

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

4. Semaphore

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

5. Phaser

6. ReentrantLock

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

      

7. ReadWriteLock

8. StampedLock

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

      

9. Condition

10. ForkJoin

    

