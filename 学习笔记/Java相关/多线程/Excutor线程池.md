## ThreadPool参数

```java
ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), Thread::new, new ThreadPoolExecutor.AbortPolicy());
//创建一个核心数为1，最大线程数为2，超时时间为30s,等待队列最长为1，拒绝策略为直接拒绝(抛出异常)的线程池
```



1. **corePoolSize**:

    线程池核心线程数量，核心线程不会被回收，即使没有任务执行，也会保持空闲状态。如果线程池中的线程少于此数目，则在执行任务时创建。 

2. **maxmumPoolSize**

   池允许最大的线程数，当线程数量达到corePoolSize，且workQueue队列塞满任务了之后，继续创建线程。

3. **KeepAliveTime**

    超过corePoolSize之后的“临时线程”的存活时间。 

4. **unit**

    keepAliveTime的单位。 (TimeUnit.SECONDS)

5. **workQueue**

    当前线程数超过corePoolSize时，新的任务会处在等待状态，并存在workQueue中，BlockingQueue是一个先进先出的阻塞式队列实现，底层实现会涉及Java并发的AQS机制.

6. **ThreadFactory**

    创建线程的工厂类，通常我们会自顶一个threadFactory设置线程的名称，这样我们就可以知道线程是由哪个工厂类创建的，可以快速定位。 

7. **handler**

   线程池执行拒绝策略，当线数量达到maximumPoolSize大小，并且workQueue也已经塞满了任务的情况下，线程池会调用handler拒绝策略来处理请求。

   系统默认的拒绝策略有以下几种：

   1. AbortPolicy：为线程池默认的拒绝策略，该策略直接抛异常处理。
   2. DiscardPolicy：直接抛弃不处理。
   3. DiscardOldestPolicy：丢弃队列中最老的任务。
   4. CallerRunsPolicy：将任务分配给当前执行execute方法线程来处理。

   我们还可以自定义拒绝策略，只需要实现RejectedExecutionHandler接口即可，友好的拒绝策略实现有如下：

   1. 将数据保存到数据，待系统空闲时再进行处理
   2. 将数据用日志进行记录，后由人工处理



## 各种线程池的适用场景介绍

- **FixedThreadPool：** 适用于为了满足资源管理需求，而需要限制当前线程数量的应用场景。它适用于负载比较重的服务器；
- **SingleThreadExecutor：** 适用于需要保证顺序地执行各个任务并且在任意时间点，不会有多个线程是活动的应用场景。
- **CachedThreadPool：** 适用于执行很多的短期异步任务的小程序，或者是负载较轻的服务器；
- **ScheduledThreadPoolExecutor：** 适用于需要多个后台执行周期任务，同时为了满足资源管理需求而需要限制后台线程的数量的应用场景，
- **SingleThreadScheduledExecutor：** 适用于需要单个后台线程执行周期任务，同时保证顺序地执行各个任务的应用场景。

## 优雅地关闭线程池

+ **shutdown()**:

  + 调用之后不允许继续往线程池内继续添加线程;
  + 线程池的状态变为`SHUTDOWN`状态;
  + 所有在调用`shutdown()`方法之前提交到`ExecutorSrvice`的任务都会执行;
  + 一旦所有线程结束执行当前任务，`ExecutorService`才会真正关闭。

+ **shutdownNow()**:

  +  该方法返回尚未执行的 task 的 List;
  + 线程池的状态变为`STOP`状态;
  + 阻止所有正在等待启动的任务,并且停止当前正在执行的任务;
  + 线程池不再接受新的任务，但是仍然会将任务队列中已有的任务执行完毕。

+  **awaitTermination**:

  + 设置定时任务，代码内的意思为 2s 后检测线程池内的线程是否均执行完毕（就像老师告诉学生，“最后给你 2s 钟时间把作业写完”），若没有执行完毕，则调用`shutdownNow()`方法。 

+ 常用的关闭的线程池的方法：

  +  shutdown方法
  + awaitTermination方法
  + shutdownNow方法(发生异常或者是Timeout的时候) 

  + 代码示例：

    ```java
    private static final Random random = new Random(System.currentTimeMillis());
        public static void main(String[] args) throws InterruptedException {
            ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), Thread::new, new ThreadPoolExecutor.AbortPolicy());
            IntStream.range(0, 20).forEach(i -> executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                    System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
            executorService.shutdown(); //标记线程池中的线程，当所有的的线程都执行完毕后，线程池关闭
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                //如果超时还有线程没有结束则强制关闭正在工作的线程，并且关闭线程池
                executorService.shutdownNow();
            }
            System.out.println("===============has Shutdowned========== ");
        }
    ```

  
  ##  线程池拒绝策略--RejectedExecutionHandler
  
  + **AbortPolicy** ：
    
    + 当线程池满了的时候，直接拒接当前提交任务当任务添加到线程池中被拒绝时，它将抛出 RejectedExecutionException异常 。
  + **CallerRunsPolicy **:
    
    +  当任务添加到线程池中被拒绝时，会在调用该execute方法中进行该任务的执行，如main线程。
  + **DiscardOldestPolicy**：
    
    + 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最久的未处理任务，然后将被拒绝的任务添加到等待队列中。 
  + **DiscardPolicy** (不建议使用)：
    
  +   当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务。 
    
  + Demo:
  
    ```java
    public class RejectedExecutionExample {
        public static void main(String[] args) throws InterruptedException {
    //        ExecutorService threadPool = RejectedExecutionHandlerTest(new ThreadPoolExecutor.AbortPolicy());//直接拒绝,抛出 RejectedExecutionException异常
    //        ExecutorService threadPool = RejectedExecutionHandlerTest(new ThreadPoolExecutor.CallerRunsPolicy());// 当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务。
            ExecutorService threadPool = RejectedExecutionHandlerTest(new ThreadPoolExecutor.DiscardOldestPolicy());// 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中。
    //        ExecutorService threadPool = RejectedExecutionHandlerTest(new ThreadPoolExecutor.DiscardPolicy());//直接拒绝
            IntStream.range(0, 4).forEach(i -> threadPool.execute(() -> {
                System.out.println("Thread--> " + i + "  is working");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread--> " + i + "  work done");
            }));
            TimeUnit.SECONDS.sleep(2);
            threadPool.execute(() -> System.out.println("Power By: " + Thread.currentThread().getName()));
            threadPool.shutdown();
            if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        }
    
        public static ExecutorService RejectedExecutionHandlerTest(RejectedExecutionHandler handler) {
            return new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2), Executors.defaultThreadFactory(), handler);
        }
    }
    ```
  
    

- 

