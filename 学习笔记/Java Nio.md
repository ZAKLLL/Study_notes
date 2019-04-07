+ 在java io中，核心概念为流（Stream)，面向流的编程，一个流要么是输出流，要么是输入流，不能够同时是输出流又同时是输入流。

+ java nio中有三个核心概念，Selector,Channel，Buffer，在Nio中，我们是面向块（block)或者缓冲区（buffer)编程。Buffer本身是一块内存，底层实际就是数组，数据的读写，都是通过Buffer来实现的。

+ Buffer:提供了对于数据的结构化访问方式，并且可以追踪到系统的读写过程。在java中，七种原生数据类型都有各自的Buffer类型，如IntBuffer,LongBuffer,ByteBuffer...**Buffer使用flip()方法来改变读写状态**

+ Channel:可以向其中写入或是从中读取数据的对象，类似于java.io中的Stream。所有的Channel的数据读写都是通过Buffer来进行的，永远不能直接向Channel直接读取对象或者直接写入对象。

+ Selector:**Selector** 一般称 为**选择器** ，当然你也可以翻译为 **多路复用器** 。它是Java NIO核心组件中的一个，用于检查一个或多个NIO Channel（通道）的状态是否处于可读、可写。如此可以实现单线程管理多个channels,也就是可以管理多个网络链接。

+ Nio结构模型

  ![1554441773422](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1554441773422.png)

+ Channel与Buffer的关系

  ![1554441824184](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1554441824184.png)

+ Selector:

  ![1554604133948](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1554604133948.png)

  

### Buffer&Channel _Examples

1. Buffer的读写

   ```java
   public class NioTest1 {
       public static void main(String[] args) {
           IntBuffer intBuffer = IntBuffer.allocate(10);
   
           for (int i = 0; i < intBuffer.capacity(); i++) {
               int randomNumber = new Random().nextInt(20);
               intBuffer.put(randomNumber);
           }
           //状态反转，使buffer成为可读状态
           intBuffer.flip();
   
           while (intBuffer.hasRemaining()) {
               System.out.println(intBuffer.get());
           }
       }
   }
   ```

2. 通过Channel把文件读取到程序中(需要使用FileInputStream)

   ```java
   
   public class NioTest2 {
       public static void main(String[] args) throws IOException {
   
   
           //传统io读取数据
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/java/com/zakl/nio/NioTest2.txt"),"UTF-8"));
   
           while (bufferedReader.ready()) {
               System.out.println(bufferedReader.readLine());
           }
   
           System.out.println("----------");
   
           //nio读取数据
           ByteBuffer byteBuffer = ByteBuffer.allocate(512);
           FileChannel fileChannel = new FileInputStream("src/main/java/com/zakl/nio/NioTest2.txt").getChannel();
   
           //将Channel中的数据读取到byteBuffer中
           fileChannel.read(byteBuffer);
   
           //改变bytebuffer状态，由可写变成可读
           byteBuffer.flip();
   
           while (byteBuffer.hasRemaining()) {
               System.out.print((char) byteBuffer.get());
           }
       }
   }
   ```

3. 通过Channel将数据写入到文件中(需要使用FileOutputStream)

   ```java
   public class NioTest3 {
       public static void main(String[] args) throws IOException {
           FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/com/zakl/nio/NioTest3.txt");
           FileChannel channel = fileOutputStream.getChannel();
   
           ByteBuffer byteBuffer = ByteBuffer.allocate(512);
   
           byte[] messages = "message test".getBytes();
   
           for (int i = 0; i < messages.length; i++) {
               byteBuffer.put(messages[i]);
           }
           byteBuffer.flip();
   
           //将byteBuffer中的消息写入到channel中
           channel.write(byteBuffer);
   
           channel.close();
           fileOutputStream.close();
       }
   }
   ```

### Buffer的三个特性：capacity,  limit ,  position （要理解读写模式，是相对的，channel.read(buffer)是将channel中数据读取到buffer中，此时buffer是写模式，buffer的put也是写模式。反之亦然）

​     **0<=mark<=position<=limit<=capacity**

1. capacity:构建Buffer时进行初始化，全局不可变。
2. position: 永远指向下一个读或者写的元素索引。
3. limit:默认位置为capacity的值，当调用flip()时，limit位置指向方法调用前的position位置。
4. ![1554450906686](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1554450906686.png)

+ flip()使得buffer由可写变成可读状态，limit=position,position=0 
+ clear()使得变成可写状态，limit=capacity,position=0
+ rewind()重新使得position的位置置于0;数据不变,可以继续执行读操作，重复取出buffer中的数据

## selector

1. Registered key-set:

   ​	所有与选择器关联的通道所生成的键的集合称为已经注册的键的集合。并不是所有注册过的键都仍然有效。这个	集合通过 **keys()** 方法返回，并且可能是空的。这个已注册的键的集合不是可以直接修改的；试图这么做的话将	引发java.lang.UnsupportedOperationException。

   

2. selected-key:

   ​	所有与选择器关联的通道所生成的键的集合称为已经注册的键的集合。并不是所有注册过的键都仍然有效。这个	集合通过 **keys()** 方法返回，并且可能是空的。这个已注册的键的集合不是可以直接修改的；试图这么做的话将	引发java.lang.UnsupportedOperationException。

   

3. cancelled-key:

   ​	已注册的键的集合的子集，这个集合包含了 **cancel()** 方法被调用过的键(这个键已经被无效化)，但它们还没有被	注销。这个集合是选择器对象的私有成员，因而无法直接访问。

   ​	**注意：** 当键被取消（ 可以通过**isValid( )** 方法来判断）时，它将被放在相关的选择器的已取消的键的集合里。注	册不会立即被取消，但键会立即失效。当再次调用 **select( )** 方法时（或者一个正在进行的select()调用结束     	时），已取消的键的集合中的被取消的键将被清理掉，并且相应的注销也将完成。通道会被注销，而新的	    	SelectionKey将被返回。当通道关闭时，所有相关的键会自动取消（记住，一个通道可以被注册到多个选择器	上）。当选择器关闭时，所有被注册到该选择器的通道都将被注销，并且相关的键将立即被无效化（取消）。一	旦键被无效化，调用它的与选择相关的方法就将抛出CancelledKeyException。

   