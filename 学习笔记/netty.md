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

