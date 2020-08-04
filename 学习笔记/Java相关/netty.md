# NETTY

+ websocket协议：ws://server:port/content_path

+ rmi: remote method invocation,只针对java
  + client: stub
  + server: skeleton
+ 序列化与反序列化: 编码与解码
+ RPC: Remote Procedure Call ,远程过程调用，很多RPC框架是跨语言的
  + 定义一个接口说明文件:描述了对象(结构体),对象成员,接口方法等一系列文件
  + 通过RPC框架所提供的编译器，将接口说明文件编译成具体语言文件。
  + 在客户端与服务器端分别引入RPC编译器所生成的文件，即可像调用本地方法一样调用远程方法。
  + 一般通过socket传输


+ ChannelFuture  ChannelFuture的作用是用来保存Channel异步操作的结果。

+ ChannelPipeline ： 可以看做是ChannelHandler的链表，用来添加不同的ChannelHandler

nettydemo:

```java
public class TestServer {
    private static final int port = 10000;

    public static void main(String[] args) throws InterruptedException {

        //看做一个死循环，程序永远保持运行
        EventLoopGroup bossGroup = new NioEventLoopGroup(); //完成线程的接收，将连接发送给worker
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //完成连接的处理
        try {
            //对于相关启动信息进行封装
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup) //注入两个group
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            //绑定端口对端口进行监听,启动服务器
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

```

Initializer：

```java
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //类似于一个拦截器链
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("httpServerCodec", new HttpServerCodec()); //对于web请求进行编解码作用
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());

    }
}
```

自定义处理器ServerHandler(用作逻辑处理)

```java

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    //读取客户端发过来的请求，并且向客户端响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;

            //设置响应内容，以及响应编码格式
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            //指定http协议，响应状态码，响应内容
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain"); //设置响应类型
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes()); //设置响应字节长度

            //将内容返回到客户端
            ctx.writeAndFlush(response);
            ctx.channel().close(); //关闭连接
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registered");
        super.channelRegistered(ctx);
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel unregister");
        super.channelUnregistered(ctx);
    }

}
```

+ 服务端心跳检测：

  + 如果设备断网或者断电后，channelInactive并不会被触发,则需要服务端主动进行监控客户端连接

  + 使用redis解决：

    + 使用redis来实现，每次设备发起心跳，server就更新一次redis，当设备断网超过一定时间，则redis中数据失效。这时候就认为设备失联，可以发送告警。
       每次server重启，从数据库中读取所有设备号，然后储存在内存中，同时启动一个线程，定时根据设备号去redis中获取数据，如果有，则认为设备在线，如果没有，则设备失联。

  + 使用IdleStateHandler:

    + dleStateHandler中的三个参数解释如下：

      1. readerIdleTime：为读超时时间；

      2. writerIdleTime：为写超时时间；

      3. allIdleTime：所有类型的超时时间；

          这里最重要是的readerIdleTime，当设置了readerIdleTime以后，服务端server会每隔readerIdleTime时间去检查一次channelRead方法被调用的情况，如果在readerIdleTime时间内该channel上的channelRead()方法没有被触发，就会调用userEventTriggered方法。





+ **handle**r和**childHandler:**
  + handler在初始化时就会执行，而childHandler会在客户端成功connect后才执行，这是两者的区别。

### ChannelPipeline调用链

