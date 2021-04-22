## Netty心跳检测代码实例及源码分析

背景：今天在研读项目netty相关代码时，发现有设备有心跳机制(尽管在本项目中没啥作用)，本着要不试一下的方式,调用下Netty提供的IdleStatHandler这个handler来实现一下心跳检测功能。

+ 尝试：

 1. 在网上搜索了一下netty的心跳检测api，光看到**IdleStatHandler**就直接下手写代码了，想着也就一套调用链的方式，写完测一下没问题就ok了，便写下了如下代码：

    Netty服务端代码：

    ```java
    public class MyServer {
        public static void main(String[] args) {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
    
            try {
    
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap
                        .group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .handler(new LoggingHandler(LogLevel.INFO)) 
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline channelPipeline = ch.pipeline();
                                channelPipeline.addLast(new HeartBeatHandler(3, 0, 0));
                                channelPipeline.addLast(new MyServerHandler());
                     
                            }
                        });
    
    
                ChannelFuture channelFuture = serverBootstrap.bind(10005).sync();
                channelFuture.channel().closeFuture().sync();
    
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        }
    }
    ------------------------------------------------------------------------
    public class HeartBeatHandler  extends IdleStateHandler {
        public HeartBeatHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
            super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
        }
        @Override
        public void read(ChannelHandlerContext ctx) throws Exception {
            System.out.println("HeartBeatHandler----->"+ctx);
            super.read(ctx);
        }
    
        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            System.out.println("HeartBeatHandler 中的userEventTriggered被触发");
            //空闲状态转换
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
                String evenType = null;
    
                switch (idleStateEvent.state()) {
                    case READER_IDLE:
                        evenType = "读空闲";
                        break;
                    case WRITER_IDLE:
                        evenType = "写空闲";
                        break;
                    case ALL_IDLE:
                        evenType = "读写空闲";
                        break;
                }
                System.out.println(ctx.channel().remoteAddress() + "超时事件：" + evenType);
            }
        }
    }
    ---------------------------
    public class MyServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(msg);
            super.channelRead(ctx, msg);
        }
    }
    ```

    Socket测试代码：

    ```java
    public class Test {
        public static void socketTest() throws IOException, InterruptedException {
            Socket socket=new Socket("127.0.0.1",10005);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            for (int i=0;i<100;i++){
                pw.println("HelloWorld");
                pw.flush();
                TimeUnit.SECONDS.sleep(5);
            }
            pw.close();
            socket.close();
        }
        public static void main(String[] args) throws IOException, InterruptedException {
            socketTest();
        }
    }
    ```

	2. 开始自信的运行代码，结果发现光顾着输出helloworld相关的内容了(为什么不直接是helloWorld,因为这里没有做编解码操作，这不是本文讨论重点)

	3. 尝试百度，stackoverflow，也没能查到原由，也没能看到示例代码，基本给的解决方案都是指将**IdlestatHandler**调用链放置在第一位置(我本来就这样放的ORZ),顺便吐槽一下csdn : )


+ 一探究竟

  心有不死，虽不是项目必须实现功能，但是勾起了好奇心，这不得探个究竟怎么睡得着。

  1. Debug 调试：

     跟着断点一步一步进入调用方法链(这里只列出核心代码)：

     ```java
     		//当数据链上的handler中的channelRead方法被调用时,reading 标志位-->true
         @Override
         public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
             if (readerIdleTimeNanos > 0 || allIdleTimeNanos > 0) {
                 reading = true;
                 firstReaderIdleEvent = firstAllIdleEvent = true;
             }
             ctx.fireChannelRead(msg);
         }
     		//根据构造函数中的三个参数设定时间 readerIdleTimeSeconds writerIdleTimeSeconds allIdleTimeSeconds 
     		//IdleStatHandler会启动对应的检测线程，这里以读超时距离，线程通过判断是否已读，以及是否超时组合判断是否需要调用userEventTriggered()函数，这里只提供超时检测线程代码，其他生成相关检测池代码可自行断点调试.
             protected void run(ChannelHandlerContext ctx) {
                 long nextDelay = allIdleTimeNanos;
                 if (!reading) {
                     nextDelay -= ticksInNanos() - Math.max(lastReadTime, lastWriteTime);
                 }
                 if (nextDelay <= 0) {
                     allIdleTimeout = schedule(ctx, this, allIdleTimeNanos, TimeUnit.NANOSECONDS);
                     boolean first = firstAllIdleEvent;
                     firstAllIdleEvent = false;
                     try {
                         //进入触发调用流程
                         channelIdle(ctx, newIdleStateEvent(IdleState.ALL_IDLE, first));
                     } catch (Throwable t) {
                         ctx.fireExceptionCaught(t);
                     }
                 } 
             }
     	
     		//以下三段代码为触发调用流程中的代码：
         protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
             ctx.fireUserEventTriggered(evt);
         }
     
         @Override
         public ChannelHandlerContext fireUserEventTriggered(final Object event) {
             invokeUserEventTriggered(findContextInbound(), event);
             return this;
         }
     		//把这里读懂就明白了为什么上面的示例代码无法正常触发userEventTriggered()函数了
         private AbstractChannelHandlerContext findContextInbound() {
             AbstractChannelHandlerContext ctx = this;
           	//此处的do方法无论条件如何都会先进行一次向后传递，变成next值
           	//因此示例代码中的HeartBeatHandler()虽然存在userEventTriggered(),但是在这个函数中,找的是下一个Handler的ChannelHandlerContext，那可以猜想一下，如果此时MyServerHandler()复写了userEventTriggered()，会被触发吗?
             do {
                 ctx = ctx.next;
             } while (!ctx.inbound);
             return ctx;
         }
     
     
     		//注意在上面的fireUserEventTriggered()函数中，最外层函数是本函数，稍微读一下，也能看出来，这是一个递归函数不断的进行链式递归，直到满足上面的 ctx.inbound=true 即handler处理链中的Inbound已经被调用完毕(如果存在userEventTriggered()的话)，文章最后会提供channelPipeline的Handler调用图。
         static void invokeUserEventTriggered(final AbstractChannelHandlerContext next, final Object event) {
             ObjectUtil.checkNotNull(event, "event");
             EventExecutor executor = next.executor();
             if (executor.inEventLoop()) {
                 next.invokeUserEventTriggered(event);
             } else {
                 executor.execute(new Runnable() {
                     @Override
                     public void run() {
                         next.invokeUserEventTriggered(event);
                     }
                 });
             }
         }
     
     ```

  2. debug结束，思路大概理清了，Idlestathandler通过新开线程来进行耗时检测，通过耗时配合表示位，来决定是否调用**userEventTriggered()**函数，并且在**findContextInbound**由于使用的是do while循环，所以是不会出现调用自己本身的情况，采用这样的编写方式我想不仅仅是不调用自身的触发函数，而是在Inbound找寻到最深处时，可以将ctx自动转换为outbound相关的handlerContext.最后使用递归函数不断递归inbound链，进行链式调用，所有该链上的handler的**userEventTriggered()** 都将被调用(当然，除了第一个，因为 do while的原因 : ) 

  3. 分析完毕，编写新的调用代码示例。

     ```java
     //避免代码重复，只提供调用链代码，其他代码不变
     ChannelPipeline channelPipeline = ch.pipeline();
     channelPipeline.addLast(new HeartBeatHandler(5, 0, 0));
     channelPipeline.addLast(new MyServerHandler());
     //偷个懒加个匿名内部类
     channelPipeline.addLast(new ChannelInboundHandlerAdapter(){
     	@Override
     	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws 	Exception {
           System.out.println("last Trigger触发");
           super.userEventTriggered(ctx, evt);
       }
     ---------------
       //注意这里的MyServerHandler复写了触发函数，用来观察是否被触发
     public class MyServerHandler extends ChannelInboundHandlerAdapter {
         @Override
         public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
             System.out.println(msg);
             super.channelRead(ctx, msg);
         }
         @Override
         public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
             System.out.println("MyServerHandler userEventTriggered 触发");
             super.userEventTriggered(ctx,evt);
         }
     }
       
      //最后，对于后两个调用链的代码，读者可以自行替换位置，尝试运行，观察输出效果，相信一定会对IdlestatHandler触发流程有更深刻的了解。
      
     ```




ChannelPipeline handler调用图：

```
*  +---------------------------------------------------+---------------+
*  |                           ChannelPipeline         |               |
*  |                                                  \|/              |
*  |    +---------------------+            +-----------+----------+    |
*  |    | Inbound Handler  N  |            | Outbound Handler  1  |    |
*  |    +----------+----------+            +-----------+----------+    |
*  |              /|\                                  |               |
*  |               |                                  \|/              |
*  |    +----------+----------+            +-----------+----------+    |
*  |    | Inbound Handler N-1 |            | Outbound Handler  2  |    |
*  |    +----------+----------+            +-----------+----------+    |
*  |              /|\                                  .               |
*  |               .                                   .               |
*  | ChannelHandlerContext.fireIN_EVT() ChannelHandlerContext.OUT_EVT()|
*  |        [ method call]                       [method call]         |
*  |               .                                   .               |
*  |               .                                  \|/              |
*  |    +----------+----------+            +-----------+----------+    |
*  |    | Inbound Handler  2  |            | Outbound Handler M-1 |    |
*  |    +----------+----------+            +-----------+----------+    |
*  |              /|\                                  |               |
*  |               |                                  \|/              |
*  |    +----------+----------+            +-----------+----------+    |
*  |    | Inbound Handler  1  |            | Outbound Handler  M  |    |
*  |    +----------+----------+            +-----------+----------+    |
*  |              /|\                                  |               |
*  +---------------+-----------------------------------+---------------+
*                  |                                  \|/
*  +---------------+-----------------------------------+---------------+
*  |               |                                   |               |
*  |       [ Socket.read() ]                    [ Socket.write() ]     |
*  |                                                                   |
*  |  Netty Internal I/O Threads (Transport Implementation)            |
*  +-------------------------------------------------------------------+
```



+ 总结：保持好奇心，别怕失败，不要气馁。

