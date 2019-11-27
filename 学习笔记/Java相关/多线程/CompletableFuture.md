# CompletableFuture





##  创建

+ **runAsync**

  + **使用 `runAsync()` 运行异步计算** 如果你想异步的运行一个后台任务并且不想改任务返回任务东西，这时候可以使用 `CompletableFuture.runAsync()`方法，它持有一个Runnable对象，并返回 `CompletableFuture`。

  + ```java
    CompletableFuture<Void> runnable_task = CompletableFuture.runAsync(() -> System.out.println("Runnable task")).whenComplete((v, t) -> {
                //当抛出异常的时候
                if (t != null) {
                    t.printStackTrace();
                }
            });
    s
    ```

+ **supplyAsync**:

  +  **使用 `supplyAsync()` 运行一个异步任务并且返回结果** , 它持有`supplier` 并且返回`CompletableFuture<T>` 是通过调用 传入的supplier取得的值的类型。 

  + ```java
    CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "HELLO").whenComplete((v, t) -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("whenComplete" + v);
            });
            System.out.println(stringCompletableFuture.get());
    ```




## 转换&&消费

+ **thenApply**&**thenAccept**:

  + **thenApply**接受一个**Function**接口(接受R返回T)类型参数，并且继续返回CompletableFuture<T>，对调用者CompletableFuture<?>进行了**转换**，注意与**thenCompose**区分，

  + **thenAccept**接受一个**Comsumer**(接受T无返回值)类型参数,并且返回CompletableFuture<void>,可继续进行链式操作，**消费**。

  + **thenRun**接受一个**Runnable**,该方法不能不能接收来自前面的参数,通常用与链式的结尾

  + ```java
    CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "HELLO").whenComplete((v, t) -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("whenComplete" + v);
            });
    //thenApply接受一个Function接口(接受R返回T)类型参数，并且继续返回CompletableFuture<T>
    //thenAccept接受一个Comsumer(接受T无返回值)类型参数,并且返回CompletableFuture<void>,可继续进行链式操作
    //thenAccept接受一个Runnable,该方法不能不能接收来自前面的参数,通常用与链式的结尾
    stringCompletableFuture.thenApply(str -> str + "World").thenAccept(System.out::println).thenRun(() -> System.out.println("Done"));
    ```



## 组合：

+ **thenCompose**

  + thenCompose（）用来连接两个CompletableFuture，是生成一个新的CompletableFuture,并且前一个Future.get()会成为thenCompose的参数/注意与thenApply的区分

  + ```java
     private static void thenCompose() {
            CompletableFuture.supplyAsync(() -> {
                System.out.println("Start thenCompose 1");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("End thenCompose 1");
                return "HELLO WORLD";
            }).thenCompose(i -> CompletableFuture.supplyAsync(() -> {
                System.out.println("Start thenCompose 2");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Start thenCompose 2");
                return i.length();
            })).whenComplete((i, t) -> System.out.println(i));
        }
    ```

+ **thenCombine**:

  + combine可以用以接受一个新的Completable和一个BiFunction,调用者和传入的CompletableFuture作为BiFunction的参数,再返回一个CompletableFuture<T>,可继续操作。

  + ```java
    private static void thenCombine() {
            CompletableFuture.supplyAsync(() -> {
                System.out.println("start thenCombine 1");
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end thenCombine 1");
                return "12345";
            }).thenCombine(CompletableFuture.supplyAsync(() -> {
                System.out.println("start thenCombine 2");
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end thenCombine 2");
                return "6789";
            }), (i, j) -> i.length() > j.length()).whenComplete((b, t) -> System.out.println(b));
        }
    ```



## 完成时处理：

+ **whenComplete**

  + 接受一个调用者的future.get()以及可能出现的Throwable作为参数,对结果进行消费BiConsumer

  + ```java
     CompletableFuture.supplyAsync(() -> {
                System.out.println("Start work");
                sleep(2);
                System.out.println("End work");
                return "res";
            }).whenComplete((r, t) -> System.out.println(r));
    ```

+ **exceptionally**

  + 倘若出现异常接受异常并进行处理：

  + ```java
      CompletableFuture.supplyAsync(() -> {
                System.out.println("Start work");
                //sleep(2);
                System.out.println("End work");
                return "res";
            }).whenComplete((r, t) -> {
                System.out.println(r);
                if (t == null) {
                    System.out.println("No err");
                }
            });
    ```



## MORE API

+ **thenAcceptBoth**

  + 该函数接受前面的future.get()作为h参数传入BiConsumer,并接受本参数callable的future.get()作为w参数传入BiConsumer

  + ```java
    private static void acceptBoth() {
            CompletableFuture.supplyAsync(() -> {
                System.out.println("Start task1");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Hello";
            }).thenAcceptBoth( //该函数接受前面的future.get()作为h参数传入BiConsumer,并接受本参数callable的future.get()作为w参数传入BiConsumer
                    CompletableFuture.supplyAsync(() -> "World"),
                    (h, w) -> System.out.println(h + "-----" + w));
        }
    ```

+ ***acceptEither***

  + 取先完成的结果.但是没完成的任务也将继续执行

  + ```java
     private static void acceptEither() {
            CompletableFuture.supplyAsync(() -> {
                System.out.println("Start Either1");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("End Either2");
                return "Either1";
            }).acceptEither(CompletableFuture.supplyAsync(() -> {
                System.out.println("Start Either2");
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("End Either2");
                return "Either2";
            }), i -> System.out.println("Final result " + i));
        }
    ```

+ **runAfterBoth**

  + 执行完毕后调用Runnable,不消费

  + ```java
     private static void runAfterBoth() {
            CompletableFuture.supplyAsync(() -> {
                System.out.println("start RunAfterBoth 1");
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end RunAfterBoth 1");
                return "Task 1 Result";
            }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
                System.out.println("start RunAfterBoth 2");
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end RunAfterBoth 2");
                return "Task 2 Result";
            }), () -> System.out.println("Done"));
        }
    ```

+ **getNow**

  + 在调用该方法时，如果已经CompleFuture已经计算完成，则返回future.get()的内容，否者直接返回该方法的参数值(缺省)。

  + ```java
    private static void getNow() throws InterruptedException {
            CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Hello";
            });
            String now = stringCompletableFuture.getNow("NOWWWWWWWWWWWWWWWWWWWWWWWw");
            System.out.println(now);
        	//output NOWWWWWWWWWWWWWWWWWWWWWWWw
            TimeUnit.SECONDS.sleep(3);
            now = stringCompletableFuture.getNow("NOWWWWWWWWWWWWWWWWWWWWWWWw");
            System.out.println(now);
        	//output Hello
        }
    ```

+ **complete**

  + 类似于getNow方法，返回true的时候说明CompletableFuture以及执行完毕，该方法无效，返回false的时候，说明该方法设值成功，CompletableFuture尚未执行完毕，调用future.get()将返回complete的参数值：

  + ```java
    private static void complete() throws InterruptedException, ExecutionException {
            CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "World";
            });
    		//通过注释该行来进行两种情况的测试
            TimeUnit.SECONDS.sleep(3);
            stringCompletableFuture.complete("HELLO");
            System.out.println(stringCompletableFuture.get());
        }
    ```

+ **join**

  + 用法跟get一样阻塞调用但是join不会抛出异常