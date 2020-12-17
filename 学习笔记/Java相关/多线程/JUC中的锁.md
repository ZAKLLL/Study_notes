# ReentrantLock

+ 使用ReentrantLock实现sychronized功能
	```java
	public class Counter {
    private int count;

    public void add(int n) {
        synchronized(this) {
            count += n;
        }
    }
}
	-------------------------------
	public class Counter {
  private final Lock lock = new ReentrantLock();
    private int count;
  
  public void add(int n) {
        lock.lock();
        try {
            count += n;
        } finally {
            lock.unlock();
        }
    }
  }
  ```

+ 使用condition进行线程之间通信

  ```java
  class TaskQueue {
      private final Lock lock = new ReentrantLock();
      private final Condition condition = lock.newCondition();
      private Queue<String> queue = new LinkedList<>();
      public void addTask(String s) {
          lock.lock();
          try {
              queue.add(s);
              condition.signalAll();
          } finally {
              lock.unlock();
          }
      }
  
      public String getTask() {
          lock.lock();
          try {
              while (queue.isEmpty()) {
                  condition.await();
              }
              return queue.remove();
          } finally {
              lock.unlock();
          }
      }
  }
  ```


  可见，使用`Condition`时，引用的`Condition`对象必须从`Lock`实例的`newCondition()`返回，这样才能获得一个绑定了`Lock`实例的`Condition`实例。

  `Condition`提供的`await()`、`signal()`、`signalAll()`原理和`synchronized`锁对象的`wait()`、`notify()`、`notifyAll()`是一致的，并且其行为也是一样的：

  - `await()`会释放当前锁，进入等待状态；
  - `signal()`会唤醒某个等待线程；
  - `signalAll()`会唤醒所有等待线程；
  - 唤醒线程从`await()`返回后需要重新获得锁。

## ReadWriteLock

+ 读写锁:允许多个线程同时读，但只要有一个线程在写，其他线程就必须等待。适合读多写少的场景。

  | 读   | 写     |        |
  | :--- | :----- | ------ |
  | 读   | 允许   | 不允许 |
  | 写   | 不允许 | 不允许 |

+ ```java
  public class Counter {
      private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
      private final Lock rlock = rwlock.readLock();
      private final Lock wlock = rwlock.writeLock();
      private int[] counts = new int[10];
  
      public void inc(int index) {
          wlock.lock(); // 加写锁
          try {
              counts[index] += 1;
          } finally {
              wlock.unlock(); // 释放写锁
          }
      }
  
      public int[] get() {
          rlock.lock(); // 加读锁
          try {
              return Arrays.copyOf(counts, counts.length);
          } finally {
              rlock.unlock(); // 释放读锁
          }
      }
  }
  ```


+ others:锁的目的不是读的数据是错的，是保证连续读逻辑上一致的,假设obj的x，y是[0,1]，某个写线程修改成[2,3]，你读到的要么是[0,1]，要么是[2,3]，但是没有锁，读到的可能是[0,3].

  int x = obj.x;
  // 这里线程可能中断
  int y = obj.y;


# StampedLock

StampedLock:`StampedLock`提供了乐观读锁，可取代`ReadWriteLock`以进一步提升并发性能.`StampedLock`是不可重入锁。

```java
public class Point {
    private final StampedLock stampedLock = new StampedLock();

    private double x;
    private double y;
    
    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock(); // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp); // 释放写锁
        }
    }
    
    public double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead(); // 获得一个乐观读锁
        // 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        double currentX = x;
        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        double currentY = y;
        // 此处已读取到y，如果没有写入，读取是正确的(100,200)
        // 如果有写入，读取是错误的(100,400)
        if (!stampedLock.validate(stamp)) { // 检查乐观读锁后是否有其他写锁发生
            stamp = stampedLock.readLock(); // 获取一个悲观读锁
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
```

+ others:
  + stampedLock的乐观锁的核心思想在于，在读的时候如果发生了写，应该通过重试的方式来获取新的值，而不应该阻塞写操作。这种模式也就是典型的无锁编程思想，和CAS自旋的思想一样。这种操作方式决定了StampedLock在读线程非常多而写线程非常少的场景下非常适用，同时还避免了写饥饿情况的发生。
