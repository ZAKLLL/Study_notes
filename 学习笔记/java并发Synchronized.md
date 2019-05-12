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

  + 