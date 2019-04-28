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
