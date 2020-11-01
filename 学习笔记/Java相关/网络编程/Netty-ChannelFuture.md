# ChannelFuture

+ ChannelFuture是Channel I/O的异步操作的结果，所有的Netty I/O操作都是异步的，Netty 提供了几种方法来获取异步I/O线程的结果

+ ChannelFuture 状态图

  ```
                                          +---------------------------+
                                          | Completed successfully    |
                                          +---------------------------+
                                     +---->      isDone() = true      |
     +--------------------------+    |    |   isSuccess() = true      |
     |        Uncompleted       |    |    +===========================+
     +--------------------------+    |    | Completed with failure    |
     |      isDone() = false    |    |    +---------------------------+
     |   isSuccess() = false    |----+---->      isDone() = true      |
     | isCancelled() = false    |    |    |       cause() = non-null  |
     |       cause() = null     |    |    +===========================+
     +--------------------------+    |    | Completed by cancellation |
                                     |    +---------------------------+
                                     +---->      isDone() = true      |
                                          | isCancelled() = true      |
                                          +---------------------------+
  ```

+ Prefer addListener(GenericFutureListener) to await()

  ChannelHandler中的事件处理程序方法通常由I/O线程调用。 如果aevent（）是由事件处理程序方法（由I/O线程调用）调用的，则它正在等待的I/O操作可能永远不会完成，因为await（）会阻塞它正在等待的I/O操作， 这是一个死锁。

  ```java
     // BAD - NEVER DO THIS
      @Override
     public void channelRead(ChannelHandlerContext ctx, Object msg) {
         ChannelFuture future = ctx.channel().close();
         future.awaitUninterruptibly();
         // Perform post-closure operation
         // ...
     }
    
     // GOOD
      @Override
     public void channelRead(ChannelHandlerContext ctx, Object msg) {
         ChannelFuture future = ctx.channel().close();
         future.addListener(new ChannelFutureListener() {
             public void operationComplete(ChannelFuture future) {
                 // Perform post-closure operation
                 // ...
             }
         });
     }
  ```

+ 正确区分I/O超时与连接超时

  用 await(long)、 await(long, TimeUnit)、 awaitUninterruptibly(long)或 awaitUninterruptibly(long, TimeUnit)指定的超时值与 I/O 超时完全没有关系。如果一个I/O操作超时，未来将被标记为 "失败完成"，如上图所示。例如，连接超时应该通过传输专用选项来配置。

  ```java
     // BAD - NEVER DO THIS
     Bootstrap b = ...;
     ChannelFuture f = b.connect(...);
  	//此处最长等待10秒，但是不一定真正的连接成功
     f.awaitUninterruptibly(10, TimeUnit.SECONDS);
     if (f.isCancelled()) {
         // Connection attempt cancelled by user
     } else if (!f.isSuccess()) {
         // You might get a NullPointerException here because the future
         // might not be completed yet.
         //此时连接可能尚未建立成功
         f.cause().printStackTrace();
     } else {
         // Connection established successfully
     }
    
     // GOOD
     Bootstrap b = ...;
     // Configure the connect timeout option.
  	//配置连接超时时间
     b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
     ChannelFuture f = b.connect(...);
  	//阻塞方法，如果连接超时，也将往下执行，并且future.isDone()==ture
     f.awaitUninterruptibly();
    
     // Now we are sure the future is completed.
     assert f.isDone();
    
     if (f.isCancelled()) {
         // Connection attempt cancelled by user
     } else if (!f.isSuccess()) {
         f.cause().printStackTrace();
     } else {
         // Connection established successfully
     }
  ```

  