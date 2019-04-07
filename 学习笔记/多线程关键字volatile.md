# volatile

+ 线程间通信，使得被volatile修饰的变量具有可见性

  + 一个线程对变量的写一定对之后对这个变量的读的线程可见

  ```java
  public class Volatile_test {
      public static void main(String[] args) throws InterruptedException {
          T t = new T();
          Thread t1 = new Thread(t);
          t1.start();
          Thread.sleep(3000);
          t.setIsrunning(false);
          System.out.println("finish");
      }
  
  }
  
  class T implements Runnable {
      private volatile boolean isrunning = true;
  
      public boolean isIsrunning() {
          return isrunning;
      }
  
      public void setIsrunning(boolean isrunning) {
          this.isrunning = isrunning;
      }
  
      @Override
      public void run() {
          while (isIsrunning()==true){
              System.out.println("is running");
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  ```
