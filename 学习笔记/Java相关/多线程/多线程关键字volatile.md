# volatile

+ 线程间通信，使得被volatile修饰的变量具有可见性

  + 一个线程对变量的写一定对之后对这个变量的读的线程可见

  ```java
  public class Test {
      public static void main(String[] args) throws InterruptedException {
          T t = new T();
          Thread t1 = new Thread(t);
          t1.start();
          new Thread(()->{
              try {
                  Thread.sleep(5000);
                  t.setIsrunning(false);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }).start();
      }
  
  
  
      static class T implements Runnable {
          private volatile boolean isRunning = true; //如果不添加volatile关键字，则另一个线程修改该状态时，此线程无法发现改变。
          boolean isIsrunning() {
              return isRunning;
          }
  
          void setIsrunning(boolean isrunning) {
              this.isRunning = isrunning;
          }
  
          @Override
          public void run() {
              while (isIsrunning() == true) {
                  System.out.println("is running");
                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
  }
  
  ```

+ 只能在有限的一些情形下使用 volatile 变量替代锁。要使 volatile 变量提供理想的线程安全，必须同时满足下面两个条件：
  - （1）对变量的写操作不依赖于当前值。
  - （2）该变量没有包含在具有其他变量的不变式中
  
+ 多线程关键字：

  - 可见性
  - 有序性 (happens-before)
  - 原子性
    - volatile可以保证前两个特性
    - CAS算法，也就是CPU级别的同步指令，相当于乐观锁，可以检测到其他线程对共享数据的变化情况。

+ 处理器如何实现原子操作：

  + 使用总线锁保证原子性

  + 使用缓存锁保证原子性

    + 缓存一致性会阻止同时修改**由两个以上处理器缓存的内存区域数据**，当其他数据回写已被锁定的缓存行的数据时，会使缓存行无效。

    + 不能使用缓存锁的情况：

      + 当操作的数据不能被缓存在处理器内部，或操作的数据跨多个缓存行（cache line)时，则处理器会调用总线锁定。

      + 有些处理器不支持缓存锁定。

        

