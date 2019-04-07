# 关于多线程Runnable中的多线程资源共享问题

首先我们使用常用的卖票模型来进行模拟：

+ 继承Thread类

  + ```java
    class Tick_Thread extends Thread{
        private int tickcount=5;
        public void run(){
            while(tickcount>0){
            System.out.println(Thread.currentThread().getName()+"正在卖票"+"剩余票数"+--tickcount);
            }
        }
    }
    
    public class Ticketsell_Thread {
    
        public static void main(String []args){
            Tick_Thread tq1=new Tick_Thread();
            tq1.setName("窗口1");
            Tick_Thread tq2=new Tick_Thread();
            tq2.setName("窗口2");
            Tick_Thread tq3=new Tick_Thread();
            tq3.setName("窗口3");
    
            tq1.start();
            tq2.start();
            tq3.start();
        }
    }
    
    /*output：
    窗口1正在卖票剩余票数4
    窗口2正在卖票剩余票数4
    窗口1正在卖票剩余票数3
    窗口1正在卖票剩余票数2
    窗口1正在卖票剩余票数1
    窗口3正在卖票剩余票数4
    窗口1正在卖票剩余票数0
    窗口2正在卖票剩余票数3
    窗口2正在卖票剩余票数2
    窗口2正在卖票剩余票数1
    窗口2正在卖票剩余票数0
    窗口3正在卖票剩余票数3
    窗口3正在卖票剩余票数2
    窗口3正在卖票剩余票数1
    窗口3正在卖票剩余票数0
    */
    ```

    这里可以看到总共卖出了15张票，明显是不对的，因为在我们每次都是new了一个新的Tick_Thread类，所以Tick_Thread中的成员变量tick_count每次都会被初始化为5，如果把变量tickcount设置为static全局变量，则不会出现多卖票的问题，这是因为static不会因为类的实例化而被初始化，当然可以通过设置够造方法的方式传入新的tick_count值，这样效果仍然与代码相同。   
+ 实现Runnable接口  

  + ```java
    public class Ticketsell_Thread {
        public static void main(String []args){
            Tick_Runnable t1=new Tick_Runnable();
            Thread th1=new Thread(t1,"th1");
            Thread th2=new Thread(t1,"th2");
            Thread th3=new Thread(t1,"th3");
            
            th1.start();
            th2.start();
            th3.start();
        }
    }
    class Tick_Runnable implements Runnable{
        private int tickcount=5;
        public void run(){
            while(tickcount>0){
                System.out.println(Thread.currentThread().getName()+"正在卖票"+"剩余票数"+--tickcount);  
            }
        }
    }
    /*output:
    th1正在卖票剩余票数4
    th1正在卖票剩余票数1
    th3正在卖票剩余票数2
    th2正在卖票剩余票数3
    th1正在卖票剩余票数0
    */
    ```

    使用实现Runnable似乎就不会出现多卖票的情况了，原因是因为，当我们在初始化线程的时候传入的都是同一个Runnable实现类，三个线程执行时也是共享同一类的资源，tickcount没有被再次初始化，

    如果创建新进程的时候代码如下：

    ```java
    Thread th1=new Thread(new Tick_Runnable(),"th1");
    Thread th2=new Thread(new Tick_Runnable(),"th2");
    Thread th3=new Thread(new Tick_Runnable(),"th3");
    ```

    则输出结果与第一种方式一致。当然，在这种情况下将tickcount设置为static属性，则仍然可以正常卖票。

+ 总结：所以资源共享，资源冲突问题，实质上是成员变量的属性问题。

