# netty

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

