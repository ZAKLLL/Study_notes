## Socket：

1. client:

   ```java
   public class SocketClient {
       public static void main(String[] args) throws IOException {
   
           Socket client = new Socket("127.0.0.1",9000);
           client.setSoTimeout(4000);
           BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
   
           BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream()));
   
           PrintStream printStream = new PrintStream(client.getOutputStream());
           System.out.println("请输入发送信息:");
           boolean flag = true;
           while (flag) {
   
               String str = sysIn.readLine();
               printStream.println(str);
               if (str.equals("bye")) {
                   flag = false;
   
               }else {
                   System.out.println(buf.readLine());
               }
           }
       }
   }
   ```

2. Server

   ```java
   public class SocketServer {
       public static void main(String[] args) throws Exception{
           //服务端在90000端口监听客户端请求的TCP连接
           ServerSocket server = new ServerSocket(9000);
           Socket client = null;
           boolean f = true;
           while(f){
               //等待客户端的连接，如果没有获取连接
               client = server.accept();
               System.out.println("与客户端连接成功！"+client.getPort());
               //为每个客户端连接开启一个线程
               new Thread(new ServerThread(client)).start();
           }
           server.close();
       }
   }
   ```

3. 工作线程

   ```java
   public class ServerThread implements Runnable {
       private Socket client = null;
       public ServerThread(Socket client){
           this.client = client;
       }
   
       public void run() {
           try{
               //获取Socket的输出流，用来向客户端发送数据
               PrintStream out = new PrintStream(client.getOutputStream());
               //获取Socket的输入流，用来接收从客户端发送过来的数据
               BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
               boolean flag =true;
               while(flag){
                   //接收从客户端发送过来的数据
                   String str =  buf.readLine();
                   if(str == null || "".equals(str)){
                       flag = false;
                   }else{
                       if("bye".equals(str)){
                           System.out.println("客户端退出成功");
                           flag = false;
                       }else{
                           //将接收到的字符串前面加上echo，发送到对应的客户端
                           out.println("echo:" + str);
                       }
                   }
               }
               out.close();
               client.close();
           }catch(Exception e){
               e.printStackTrace();
           }
       }
   }
   ```

该种方法进行网络编程，每一个socket都是阻塞的，每个客户端的连接都将开启一个工作线程。当并发量过高时，太占据系统资源。不适合高并发编程。

## ServerSocketChannel

```java
//ServerSocketChannel 是一个可以监听新进来的TCP连接的通道,包含一个SocketChannel
ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//调整为非阻塞模式
serverSocketChannel.configureBlocking(false);
//将端口绑定到Channel上
serverSocketChannel.bind(new InetSocketAddress(port));
serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
```


## SocketChannel：

​	用来与客户端进行网络I/O操作，一般是非阻塞的，通常从ServerSocketChannel中获得

```java
SocketChannel socketChannel=serverSocketChannel.accept();
```

