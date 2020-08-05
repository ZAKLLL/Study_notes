+ Netty提供的粘包拆包处理

1. 固定长度的拆包器 FixedLengthFrameDecoder

   每个应用层数据包的都拆分成都是固定长度的大小，比如 1024字节。

   这个显然不大适应在 Java 聊天程序 进行实际应用。

2. 行拆包器 LineBasedFrameDecoder

   每个应用层数据包，都以换行符作为分隔符，进行分割拆分。

   这个显然不大适应在 Java 聊天程序 进行实际应用。

3. 分隔符拆包器 DelimiterBasedFrameDecoder

   每个应用层数据包，都通过自定义的分隔符，进行分割拆分。

   这个版本，是LineBasedFrameDecoder 的通用版本，本质上是一样的。

   这个显然不大适应在 Java 聊天程序 进行实际应用。

4. 基于数据包长度的拆包器 LengthFieldBasedFrameDecoder

   将应用层数据包的长度，作为接收端应用层数据包的拆分依据。按照应用层数据包的大小，拆包。这个拆包器，有一个要求，就是应用层协议中包含数据包的长度。