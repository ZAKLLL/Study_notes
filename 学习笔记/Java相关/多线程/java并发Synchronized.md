# Java synchronized关键字

## 对象锁

+ **同步代码块_对象锁**（thread.join可表示为当前线程执行完毕后在进行下一个线程执行）

  + ```java
    public class SynchronizedObjBlock implements Runnable {
        Object object = new Object();
        Object object2 = new Object();
    
        @Override
        public void run() {
            synchronized (object){
                System.out.println(Thread.currentThread().getName() + "占用🔒OBJ");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"釋放🔒OBJ");
            }
            synchronized (object2){
                System.out.println(Thread.currentThread().getName() + "占用🔒OBJ2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"釋放🔒OBJ2");
            }
        }
    
        static SynchronizedObjBlock synchronizedObjBlock = new SynchronizedObjBlock();
        public static void main(String[] args) {
            Thread t1 = new Thread(synchronizedObjBlock);
            Thread t2 = new Thread(synchronizedObjBlock);
            t1.start();
            t2.start();
            while (t1.isAlive() || t2.isAlive()) {
            }
            System.out.println("finish");
        }
    }
    ```

+ **方法锁形式**：用以修饰普通方法，锁对象默认为this

  + ```java
    public class SynchronizedMethodBlock implements Runnable {
        Object object = new Object();
        Object object2 = new Object();
    
        public synchronized void method() throws InterruptedException {
            System.out.println(Thread.currentThread().getName()+"正在执行");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+"结束执行");
        }
        @Override
        public void run() {
            try {
                method();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
        static SynchronizedMethodBlock synchronizedObjBlock = new SynchronizedMethodBlock();
        public static void main(String[] args) {
            Thread t1 = new Thread(synchronizedObjBlock);
            Thread t2 = new Thread(synchronizedObjBlock);
            t1.start();
            t2.start();
            while (t1.isAlive() || t2.isAlive()) {
            }
            System.out.println("finish");
        }
    }
    
    ```


## 类锁

 + 对static方法：

    + ```java
      public class SynchronizedStaticMethodBlock implements Runnable {
          Object object = new Object();
          Object object2 = new Object();
      
          public static synchronized void method() throws InterruptedException {
              System.out.println(Thread.currentThread().getName()+"正在执行");
              Thread.sleep(3000);
              System.out.println(Thread.currentThread().getName()+"结束执行");
          }
          @Override
          public void run() {
              try {
                  method();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      
          static SynchronizedStaticMethodBlock synchronizedObjBlock = new SynchronizedStaticMethodBlock();
          static SynchronizedStaticMethodBlock synchronizedObjBlock2 = new SynchronizedStaticMethodBlock();
          public static void main(String[] args) {
              Thread t1 = new Thread(synchronizedObjBlock);
              Thread t2 = new Thread(synchronizedObjBlock2);
              t1.start();
              t2.start();
              while (t1.isAlive() || t2.isAlive()) {
              }
              System.out.println("finish");
          }
      }
      ```

+ synchronized（*.clasa）代码块

  + ```java
    public class SynchronizedClassBlock implements Runnable {
        Object object = new Object();
        Object object2 = new Object();
    
        public void method() throws InterruptedException {
            synchronized (SynchronizedClassBlock.class) { //点
                System.out.println(Thread.currentThread().getName()+"正在执行");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+"结束执行");
            }
        }
        @Override
        public void run() {
            try {
                method();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
        static SynchronizedClassBlock synchronizedObjBlock = new SynchronizedClassBlock();
        static SynchronizedClassBlock synchronizedObjBlock2 = new SynchronizedClassBlock();
        public static void main(String[] args) {
            Thread t1 = new Thread(synchronizedObjBlock);
            Thread t2 = new Thread(synchronizedObjBlock2);
            t1.start();
            t2.start();
            while (t1.isAlive() || t2.isAlive()) {
            }
            System.out.println("finish");
        }
    }
    
    ```

+ **Synchronized** VS **ReentrantLock**：

    + 不可中断：

        + 一旦这个锁已经被别人获得了，如果我还想获得，我只能选择等待或者阻塞，直到别的线程释放这个锁；如果别的线程永远不释放锁，那么我只能永远等待；

            相比之下，Lock类提供的锁，拥有中断能力；第一，如果我觉得等待的时间太长了，有权中断现在已经获取到锁的线程的执行；第二，如果我觉得等待的时间太长了不想再等待，也可以退出；

    + **① 两者都是可重入锁**

        两者都是可重入锁。“可重入锁”概念是：自己可以再次获取自己的内部锁。比如一个线程获得了某个对象的锁，此时这个对象锁还没有释放，当其再次想要获取这个对象的锁的时候还是可以获取的，如果不可锁重入的话，就会造成死锁。同一个线程每次获取锁，锁的计数器都自增1，所以要等到锁的计数器下降为0时才能释放锁。

        **② synchronized 依赖于 JVM 而 ReenTrantLock 依赖于 API**

        synchronized 是依赖于 JVM 实现的，前面我们也讲到了 虚拟机团队在 JDK1.6 为 synchronized 关键字进行了很多优化，但是这些优化都是在虚拟机层面实现的，并没有直接暴露给我们。ReenTrantLock 是 JDK 层面实现的（也就是 API 层面，需要 lock() 和 unlock 方法配合 try/finally 语句块来完成），所以我们可以通过查看它的源代码，来看它是如何实现的。

        **③ ReenTrantLock 比 synchronized 增加了一些高级功能**

        相比synchronized，ReenTrantLock增加了一些高级功能。主要来说主要有三点：**①等待可中断；②可实现公平锁；③可实现选择性通知（锁可以绑定多个条件）**

        - **ReenTrantLock提供了一种能够中断等待锁的线程的机制**，通过lock.lockInterruptibly()来实现这个机制。也就是说正在等待的线程可以选择放弃等待，改为处理其他事情。
        - **ReenTrantLock可以指定是公平锁还是非公平锁。而synchronized只能是非公平锁。所谓的公平锁就是先等待的线程先获得锁。** ReenTrantLock默认情况是非公平的，可以通过 ReenTrantLock类的`ReentrantLock(boolean fair)`构造方法来制定是否是公平的。
        - synchronized关键字与wait()和notify/notifyAll()方法相结合可以实现等待/通知机制，ReentrantLock类当然也可以实现，但是需要借助于Condition接口与newCondition() 方法。Condition是JDK1.5之后才有的，它具有很好的灵活性，比如可以实现多路通知功能也就是在一个Lock对象中可以创建多个Condition实例（即对象监视器），**线程对象可以注册在指定的Condition中，从而可以有选择性的进行线程通知，在调度线程上更加灵活。 在使用notify/notifyAll()方法进行通知时，被通知的线程是由 JVM 选择的，用ReentrantLock类结合Condition实例可以实现“选择性通知”** ，这个功能非常重要，而且是Condition接口默认提供的。而synchronized关键字就相当于整个Lock对象中只有一个Condition实例，所有的线程都注册在它一个身上。如果执行notifyAll()方法的话就会通知所有处于等待状态的线程这样会造成很大的效率问题，而Condition实例的signalAll()方法 只会唤醒注册在该Condition实例中的所有等待线程。

