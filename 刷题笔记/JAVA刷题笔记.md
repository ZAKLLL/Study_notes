+ java中的类是单继承的，但是接口是可以多继承的。

  1. jdk内置工具:

     ```java
     1、jps：查看本机java进程信息。
     
     2、jstack：打印线程的栈信息，制作线程dump文件。
     
     3、jmap：打印内存映射，制作堆dump文件
     
     4、jstat：性能监控工具
     
     5、jhat：内存分析工具
     
     6、jconsole：简易的可视化控制台
     
     7、jvisualvm：功能强大的控制台
     
     ```

     

+ 使用CGLib技术直接操作字节码运行，生成大量的动态类 ：会导致JVM内存溢出(spring 使用cglib/AspectJ 动态代理实现AOP)

+ 基本数据类型比较:

  + 两个数值进行二元操作时，会有如下的转换操作：

    如果两个操作数其中有一个是double类型，另一个操作就会转换为double类型。

    否则，如果其中一个操作数是float类型，另一个将会转换为float类型。

    否则，如果其中一个操作数是long类型，另一个会转换为long类型。

    否则，两个操作数都转换为int类型。
    
  + **自动数据类型转换**

    自动转换按从低到高的顺序转换。不同类型数据间的优先关系如下：
    低 ---------------------------------------------> 高
    byte,short,char-> int -> long -> float -> double

+ 静态变量static在不同的实例中地址一样，在全局区。

+ Queue:
  + A、LinkedBlockingQueue是一个基于节点链接的可选是否有界的阻塞队列，不允许null值。
    B、LinkedBlockingQueue是一个线程安全的阻塞队列，实现了先进先出等特性。
    C、PriorityQueue是一个无界队列，不允许null值，入队和出队的时间复杂度是O（log(n)）。
    D、PriorityQueue是不同于先进先出队列的另一种队列。每次从队列中取出的是具有最高优先权的元素。ConcurrentLinkedQueue是一个基于链接节点的无界线程安全队列，该队列的元素遵循FIFO原则。

+ 类加载顺序（基类）：

  + 加载顺序：静态初始代码块>初始代码块>构造函数

  + 加载子类时：

    + 父类的静态代码块
    + 子类的静态代码块
    + 父类的普通代码块
    + 父类的构造方法
    + 子类的普通代码块
    + 子类的构造方法

  + 编译期常量与运行期常量

    ```java
    public class Nowcoder {
        public static void main(String[] args) {
            System.out.println(B.a);
        }
    }
    class B{
        static final String a = new String("hello World"); //运行时常量
        static {
            System.out.println("This is static Code block");
        }
    }
    output:
    	This is static Code block
    	hello World
    	
    class B{
        static  String a = "hello World";
        static {
            System.out.println("This is static Code block");
        }
    }
    output:
    	This is static Code block
    	hello World
    
    //----------------------------	
    class B{
        static final String a = "hello World"; //编译时常量
        static {
            System.out.println("This is static Code block");
        }
    }
    output:
    	hello World
    
    ```

    

+ 用ClassLoader加载类，是不会导致类的初始化（也就是说不会执行<clinit>方法）.Class.forName(...)加载类，不但会将类加载，还会执行会执行类的初始化方法.

+ ArralyList<> 初始容量为10,每次扩容大小为之前的1.5倍

+ 接口中的属性在不提供修饰符修饰的情况下，会自动加上public static final

+ java反射提供了：

  + **在运行时判断任意一个对象所属的类**
  + 在运行时构造任意一个类的对象
  + 在运行时判断任意一个类所具有的成员变量和方法
  + 在运行时调用任意一个对象的方法

+ try-catch-final的返回问题：

  + **如果try语句里有return，返回的是try语句块中变量值。**
    详细执行过程如下：

    1. 如果有返回值，就把返回值保存到局部变量中；
    2. 执行jsr指令跳到finally语句里执行；
    3. 执行完finally语句后，返回之前保存在局部变量表里的值。

    **如果try，finally语句里均有return，忽略try的return，而使用finally的return.**

+ 构造函数不能被继承，构造方法只能被显式(**super()**)或隐式的调用。

+ 反射获取方法：
  + public Method[] getMethods()返回某个类的所有公用（public）方法包括其继承类的公用方法，包括它所实现接口的方法。
  + public Method[] getDeclaredMethods()对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。包括它所实现接口的方法。

+ Jvm:

  + ![1555745612053](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1555745612053.png)

  + 1、**堆区（heap）**：用于存放所有对象，是线程共享的（注：数组也属于对象）

    2、**栈区（stack）**：用于存放基本数据类型的数据和对象的引用，是线程私有的（分为：虚拟机栈和本地方法栈）

    3、**方法区（method）**：用于存放类信息、常量、静态变量、编译后的字节码等，是线程共享的（也被称为非堆，即 None-Heap）运行时常量池也在这里

    4、**程序计数器**：程序计数器是一块较小的内存空间，它的作用可以看作是当前线程所执行的字节码的行号指示器。在虚拟机的概念模型里字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。
    
    **Java 的垃圾回收器（GC）主要针对堆区** 垃圾回收线程的优先级相当低
    
  + 参数设置：

    + java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:SurvivorRatio=4
      +    -Xmx3550m:设置JVM最大可用内存为3550M. 
      + -Xms3550m:设置JVM促使内存为3550m.此值可以设置与-Xmx相同,以避免每次垃圾回收完成后JVM重新分配内存. 
      + -Xmn 2g:设置年轻代大小为2G.
      + -Xss128k:设置每个线程的堆栈大小. 
      + -XX:SurvivorRatio=4:设置年轻代中Eden区与Survivor区的大小比值.设置为4,则两个Survivor区与一个Eden区的比值为2:4,一个Survivor区占整个年轻代的1/6

+ ThreadLocal:
  + ThreadLocal存放的值是线程封闭，线程间互斥的，主要用于线程内共享一些数据，避免通过参数来传递
  + 线程的角度看，每个线程都保持一个对其线程局部变量副本的隐式引用，只要线程是活动的并且 ThreadLocal 实例是可访问的；在线程消失之后，其线程局部实例的所有副本都会被垃圾回收
  + 在Thread类中有一个Map，用于存储每一个线程的变量的副本。
  + 对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式

+ 引用：
  + 1、强引用：一个对象赋给一个引用就是强引用，比如new一个对象，一个对象被赋值一个对象。
  + 2、软引用：用SoftReference类实现，一般不会轻易回收，只有内存不够才会回收。
  + 3、弱引用：用WeekReference类实现，一旦垃圾回收已启动，就会回收。
  + 4、虚引用：不能单独存在，必须和引用队列联合使用。主要作用是跟踪对象被回收的状态

+   ``` i =  ++(i++);``` //编译不通过

+ 变量：
  
  + ![1567404024665](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1567404024665.png)

+ 线程安全的集合
  + ArrayList线程不安全，Vector线程安全；
  + HashMap线程不安全，HashTable线程安全；
  + StringBuilder线程不安全，StringBuffer线程安全
  + Stack 栈
  + enumeration
  + jdk5.0新增的线程安全集合类：
    + ConcurrentHashMap： 实现了Map，并且线程安全
    + ConcurrentSkipListMap： 实现了Map(可排序)，并且线程安全
    + CopyOnWriteArrayList： 实现了List，并且线程安全
  
+ 线程不安全的类：

  + StringBuilder
  + SimpleDateFormat
  + Servlet

+ 静态方法属于类成员,实例方法属于对象成员。

+ Servlet：
  
  + ![1567421145720](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1567421145720.png)
  
+ init() 方法
    init 方法被设计成只调用一次。它在第一次创建 Servlet 时被调用，用于 Servlet的初始化，初始化的数据，可以在整个生命周期中使用。
  + service() 方法
    service() 方法是执行实际任务的主要方法。 Servlet 容器（Tomcat、Jetty等）调用 service() 方法来处理来自客户端（浏览器）的请求，并把相应结果返回给客户端。
    每次 Servlet 容器接收到一个 Http 请求， Servlet 容器会产生一个新的线程并调用 Servlet实例的 service 方法。 service 方法会检查 HTTP 请求类型（GET、POST、PUT、DELETE 等），并在适当的时候调用 doGet、doPost、doPut、doDelete 方法。所以，在编码请求处理逻辑的时候，我们只需要关注 doGet()、或doPost()的具体实现即可。
    destroy() 方法
  + destroy() 方法也只会被调用一次，在 Servlet 生命周期结束时调用。destroy() 方法主要用来清扫“战场”，执行如关闭数据库连接、释放资源等行为。
    调用 destroy 方法之后，servlet 对象被标记为垃圾回收，等待 JVM 的垃圾回收器进行处理。
  
+ 接口可以多继承

+ Web应用程序中，Web容器负责将HTTP请求转换为HttpServletRequest对象

+ Throwable:

  + 1.Exception（异常）:是程序本身可以处理的异常。

  + 2.Error（错误）: 是程序无法处理的错误。这些错误表示故障发生于虚拟机自身、或者发生在虚拟机试图执行应用时，一般不需要程序处理。

  + 3.检查异常（编译器要求必须处置的异常） ：  除了Error，RuntimeException及其子类以外，其他的Exception类及其子类都属于可查异常。这种异常的特点是Java编译器会检查它，也就是说，当程序中可能出现这类异常，要么用try-catch语句捕获它，要么用throws子句声明抛出它，否则编译不会通过。

  + 4.非检查异常(编译器不要求处置的异常):包括运行时异常（RuntimeException与其子类）和错误（Error）。

  + ![1567512468777](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1567512468777.png)

    

+ java程序的种类：
  + 1、Application―Java应用程序”是可以独立运行的Java程序。由Java解释器控制执行。
  + 2、Applet―Java小程序”不能独立运行（嵌入到Web页中）。由Java兼容浏览器控制执行。
  + 3、Serverlets是Java技术对CGI 编程的解决方案。是运行于Web server上的、作为来自于Web browse或其他HTTP client端的请求和在server上的数据库及其他应用程序之间的中间层程序。

+ Condition: Condition是在java 1.5中才出现的，它用来替代传统的Object的wait()、notify()实现线程间的协作，相比使用Object的wait()、notify()，使用Condition1的await()、signal()这种方式实现线程间协作更加安全和高效。因此通常来说比较推荐使用Condition，在阻塞队列那一篇博文中就讲述到了，阻塞队列实际上是使用了Condition来模拟线程间协作。

+ java CallableStatement ,PreparedStatement继承关系图
  
+ ![1567668851234](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1567668851234.png)
  
+ Hibernate优化方案：
  + 1.尽量使用many-to-one，避免使用单项one-to-many
  + 2.灵活使用单向one-to-many
  + 3.不用一对一，使用多对一代替一对一
  + 4.配置对象缓存，不使用集合缓存
  + 5.一对多使用Bag 多对一使用Set
  + 6.继承使用显示多态 HQL:from object polymorphism="exlicit" 避免查处所有对象
  + 7.消除大表，使用二级缓存

+ HashTable和HashMap的区别：

  + HashMap 不是线程安全的

    HashMap 是 map 接口的实现类，是将键映射到值的对象，其中键和值都是对象，并且不能包含重复键，但可以包含重复值。HashMap 允许 null key 和 null value，而 HashTable 不允许。

  + HashTable 是线程安全 Collection。

    HashMap 是 HashTable 的轻量级实现，他们都完成了Map 接口，主要区别在于 HashMap 允许 null key 和 null value,由于非线程安全，效率上可能高于 Hashtable。

  + HashMap允许将 null 作为一个 entry 的 key 或者 value，而 Hashtable 不允许。

  + HashMap 把 Hashtable 的 contains 方法去掉了，改成 containsValue 和 containsKey。因为 contains 方法容易让人引起误解。

  + HashTable 继承自 Dictionary 类，而 HashMap 是 Java1.2 引进的 Map interface 的一个实现。

  + HashTable 的方法是 Synchronize 的，而 HashMap 不是，在多个线程访问 Hashtable 时，不需要自己为它的方法实现同步，而 HashMap 就必须为之提供外同步。

  + Hashtable 和 HashMap 采用的 hash/rehash 算法都大概一样，所以性能不会有很大的差异。
  
  + HashTable使用Enumeration，HashMap使用Iterator.
  
+ Socket:

    + 客户端通过new Socket()方法创建通信的Socket对象
        服务器端通过new ServerSocket()创建TCP连接对象  accept接纳客户端请求
        

+ orm框架与对象数据模型对应关系：
  + 表对应类
  + 记录对应对象
  + 表的字段对应类的属性
  
+ Webservice:

    + 是跨平台，跨语言的远程调用技术;
    + 通信机制实质就是xml数据交换;
    + 采用了soap协议（简单对象协议）进行通信

+ java基础数据类型：

    + ![1567822655223](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1567822655223.png)

+ 