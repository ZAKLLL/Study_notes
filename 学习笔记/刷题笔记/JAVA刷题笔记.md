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

     

+ 使用CGLib技术直接操作字节码运行，生成大量的动态类 ：会导致JVM内存溢出(spring 使用cglib/动态代理(运行时织入)，AspectJ(编译期织入)实现AOP)

    + 开启cglib:

        + ```xml
            <aop:aspectj-autoproxy proxy-target-class="true"/>
            ```

+ 基本数据类型比较:

  + 两个数值进行二元操作时，会有如下的转换操作：

    如果两个操作数其中有一个是double类型，另一个操作就会转换为double类型。

    否则，如果其中一个操作数是float类型，另一个将会转换为float类型。

    否则，如果其中一个操作数是long类型，另一个会转换为long类型。

    否则，两个操作数都转换为int类型。
    
  + **自动数据类型转换**

    自动转换按从低到高的顺序转换。不同类型数据间的优先关系如下：
    低 ---------------------------------------------> 高
    byte->short->char-> int -> long -> float -> double

+ 静态变量static在不同的实例中地址一样，在全局区。

+ Queue:
  + A、LinkedBlockingQueue是一个基于节点链接的可选是否有界的阻塞队列，不允许null值。
    B、**LinkedBlockingQueue**是一个线程安全的阻塞队列，实现了先进先出等特性。
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
        static String a = new String("hello World"); //运行时常量
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

+ ArrayList<> 初始容量为10,每次扩容大小为之前的1.5倍

+ 接口中的属性在不提供修饰符修饰的情况下，会自动加上public static final

+ java反射提供了：

  + **在运行时判断任意一个对象所属的类**
  + 在运行时构造任意一个类的对象
  + 在运行时判断任意一个类所具有的成员变量和方法
  + 在运行时调用任意一个对象的方法
  + Java的反射机制会给内存带来额外的开销。例如对永生堆的要求比不通过反射要求的更多

+ try-catch-final的返回问题：

  + **如果try语句里有return，返回的是try语句块中变量值。**
    详细执行过程如下：

    1. 如果有返回值，就把返回值保存到局部变量中；
    2. 执行jsr指令跳到finally语句里执行；
    3. 执行完finally语句后，返回之前保存在局部变量表里的值。
  4. 如果try catch中包含System.exit(0)的语句，则直接退出，不执行finally
  
    **如果try，finally语句里均有return，忽略try的return，而使用finally的return.**
  
    ​	

+ 构造函数不能被继承，构造方法只能被显式(**super()**)或隐式的调用。

+ 反射获取方法：
  + **getMethods()**返回某个类的所有公用（public）方法包括其继承类的公用方法，包括它所实现接口的方法。
  + **getDeclaredMethods()**对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。包括它所实现接口的方法。

+ Jvm:

  + ![1555745612053](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1555745612053.png)

  + ![1568274352276](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1568274352276.png)

  + 1、**堆区（heap）**：用于存放所有对象，是线程共享的（注：数组也属于对象）

    2、**栈区（stack）**：用于存放基本数据类型的数据和对象的引用，是线程私有的（分为：虚拟机栈和本地方法栈）

    3、**方法区（method）**：用于存放**类信息**、常量、静态变量、编译后的字节码等，是线程共享的（也被称为非堆，即 None-Heap）运行时常量池也在这里，用于存放编译器生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放。

    4、**程序计数器**：程序计数器是一块较小的内存空间，它的作用可以看作是当前线程所执行的字节码的行号指示器。在虚拟机的概念模型里字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。(线程独立)
    
    5、**虚拟机栈**：栈帧(Stack Frame)是用于支持虚拟机进行方法调用和方法执行的数据结构，它是虚拟机运行时数据区的虚拟机栈(Virtual Machine Stack)的栈元素。栈帧存储了方法的局部变量表，操作数栈，动态连接和方法返回地址等信息。第一个方法从调用开始到执行完成，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。当调用完方法后，方法自动出栈释放，不需要gc主动释放。
    
    **Java 的垃圾回收器（GC）主要针对堆区** 垃圾回收线程的优先级相当低
    
    + 是否可以被GC:
      + 在java语言中，判断一块内存空间是否符合垃圾收集器收集标准的标准只有两个：
    
        1.给对象引用赋值为null，以下没有调用过。
    
        2.给对象引用赋了新的值，重新分配了内存空间。
      
    + **新生代基本采用复制算法，老年代采用标记-清除算法。cms采用标记清理。**
    
    + 1，新生代：（1）所有对象创建在新生代的Eden区，当Eden区满后触发新生代的Minor GC，将Eden区和非空闲Survivor区存活的对象复制到另外一个空闲的Survivor区中。（2）保证一个Survivor区是空的，新生代**Minor GC**就是在两个Survivor区之间相互复制存活对象，直到Survivor区满为止。
    
      2，老年代：当Survivor区也满了之后就通过**Minor GC**将对象复制到老年代。老年代也满了的话，就将触发**Full GC**(**Major GC**)，针对整个堆（包括新生代、老年代、持久代）进行垃圾回收。
    
      3，持久代：持久代如果满了，将触发**Full GC**
      
    + 直接内存的分配不会受到Java堆大小的限制，但是会受到机器本身内存大小的限制，超过本机最大内存的时候还是会抛oom
    
  + off—heap:堆外内存

    + 为了解决堆内内存过大带来的长时间的GC停顿的问题，以及操作系统对堆内内存不可知的问题，java虚拟机开辟出了堆外内存（off-heap memory）。堆外内存意味着把一些对象的实例分配在Java虚拟机堆内内存以外的内存区域，这些内存直接受操作系统（而不是虚拟机）管理。这样做的结果就是能保持一个较小的堆，以减少垃圾收集对应用的影响。同时因为这部分区域直接受操作系统的管理，别的进程和设备（例如GPU）可以直接通过操作系统对其进行访问，减少了从虚拟机中复制内存数据的过程。

  + 参数设置：

    + java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:SurvivorRatio=4
      +    -Xmx3550m:设置JVM最大可用内存为3550M. 
      + -Xms3550m:设置JVM促使内存为3550m.此值可以设置与-Xmx相同,以避免每次垃圾回收完成后JVM重新分配内存. 
      + -Xmn 2g:设置年轻代大小为2G.
      + -Xss128k:设置每个线程的堆栈大小. 
      + -XX:SurvivorRatio=4:设置年轻代中Eden区与Survivor区的大小比值.设置为4,则两个Survivor区与一个Eden区的比值为2:4,一个Survivor区占整个年轻代的1/6
    + JVM 启动默认参数：-Xmx为物理内存的1/4，-Xms为物理内存的1/64，

+ ThreadLocal:
  + ThreadLocal存放的值是线程封闭，线程间互斥的，主要用于线程内共享一些数据，避免通过参数来传递
  
  + 线程的角度看，每个线程都保持一个对其线程局部变量副本的隐式引用，只要线程是活动的并且 ThreadLocal 实例是可访问的；在线程消失之后，其线程局部实例的所有副本都会被垃圾回收
  
  + 在Thread类中有一个Map，用于存储每一个线程的变量的副本。
  
  + 对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式
  
  + ThreadLocal使用**开放地址**发开处理**散列冲突**
  
  + 开放定址法：
  
    + 所谓的[开放定址法](http://www.nowamagic.net/academy/tag/开放定址法)就是一旦发生了冲突，就去寻找下一个空的散列地址，只要散列表足够大，空的散列地址总能找到，并将记录存入。
  
      公式为：
  
      > fi(key) = (f(key)+di) MOD m (di=1,2,3,......,m-1)
  
+ 引用：
  + 1、强引用：一个对象赋给一个引用就是强引用，比如new一个对象，一个对象被赋值一个对象。
  + 2、软引用：用SoftReference类实现，一般不会轻易回收，只有内存不够才会回收。
  + 3、弱引用：用WeakReference类实现，一旦垃圾回收已启动，就会回收。
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
  + GenericServlet类：抽象类，定义一个通用的、独立于底层协议的Servlet。
  + 大多数Servlet通过从GenericServlet或HttpServlet类进行扩展来实现
  + 抽象类httpServlet,专门用于创建应用于HTTP协议的Servletd
  + ServletConfig接口定义了在Servlet初始化的过程中由Servlet容器传递给Servlet得配置信息对象
  + HttpServletRequest接口扩展ServletRequest接口，为HTTP Servlet提供HTTP请求信息
  
+ init() 方法
    init 方法被设计成只调用一次。它在第一次创建 Servlet 时被调用，用于 Servlet的初始化，初始化的数据，可以在整个生命周期中使用。
  
  + service() 方法
    service() 方法是执行实际任务的主要方法。 Servlet 容器（Tomcat、Jetty等）调用 service() 方法来处理来自客户端（浏览器）的请求，并把相应结果返回给客户端。
    每次 Servlet 容器接收到一个 Http 请求， Servlet 容器会产生一个新的线程并调用 Servlet实例的 service 方法。 service 方法会检查 HTTP 请求类型（GET、POST、PUT、DELETE 等），并在适当的时候调用 doGet、doPost、doPut、doDelete 方法。所以，在编码请求处理逻辑的时候，我们只需要关注 doGet()、或doPost()的具体实现即可。
    
  + destroy() 方法
    
    destroy() 方法也只会被调用一次，在 Servlet 生命周期结束时调用。destroy() 方法主要用来清扫“战场”，执行如关闭数据库连接、释放资源等行为。
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

+ Condition: Condition是在java 1.5中才出现的，它用来替代传统的Object的wait()、notify()实现线程间的协作，相比使用Object的wait()、notify()，使用Condition1的await()、signal()这种方式实现线程间协作更加安全和高效。

+ java CallableStatement ,PreparedStatement继承关系图
  
+ ![1567668851234](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1567668851234.png)
  
  + 具体使用示例：
    
    + ```java
        Statement sta=con.createStatement();
        ResultSet rst = sta.executeQuery("Select * From table");
        ```
    
    + ```java
        PreparedStatement pst=con.prepareStatement("select * from book");
        ResultSet rst=pst.executeQuery();
        ```
  
+ Hibernate优化方案：
  + 1.尽量使用many-to-one，避免使用单项one-to-many
  + 2.灵活使用单向one-to-many
  + 3.不用一对一，使用多对一代替一对一
  + 4.配置对象缓存，不使用集合缓存
  + 5.一对多使用Bag 多对一使用Set
  + 6.继承使用显示多态 HQL:from object polymorphism="exlicit" 避免查处所有对象
  + 7.消除大表，使用二级缓存

+ Hibernate Pojo的三种状态

    + 瞬时态：没有和数据库关联，没有和session关联，new出来的对象，瞬时态对象的改变，不会影响数据库，数据库不会检测到对象内容改变，只是携带数据
    + 持久态：当一旦通过save、update、saveOrupdate操作，变成持久态，和数据库、session都有关联，session一级缓存里会储存对象信息，保存，通过OID来标识每个对象，数据库会检测到对象内容的改变。
    + 游离态（托管态）：session清空，session关闭以后，对象变成游离态，游离态有OID的，不会和数据库、session保持关联，数据库也不会检测到对象内容的改变

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
  
  + **HashMap**使用**链地址法**来解决Hash冲突(hash冲突时直接在Node后面进行添加新的Node)
  
  + Hashtable 是一个**散列表**，它存储的内容是键值对(key-value)映射。
  
  + 由**数组+链表**组成的，基于**哈希表的Map**实现，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的。
  
  + TreeMap也支持<null,null>键值对
    
  
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

+ java基本数据类型(原生类)：

    + ![1567822655223](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1567822655223.png)

+ HashCode和Equals:

    + **如果两个对象equals，那么它们的hashCode必然相等**

    + **但是hashCode相等，equals不一定相等。**
    
    + 重写Equals原则：
    
      + > **自反性**：对于任何非空引用值 x，x.equals(x) 都应返回 true。
    
        > **对称性**：对于任何非空引用值 x 和 y，当且仅当 y.equals(x) 返回 true 时，x.equals(y) 才应返回 true。
    
        > **传递性**：对于任何非空引用值 x、y 和 z，如果 x.equals(y) 返回 true， 并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。
    
        > **一致性**：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false， 前提是对象上 equals 比较中所用的信息没有被修改。
    
        > **非空性**：对于任何非空引用值 x，x.equals(null) 都应返回 false。
    
+ 位运算符：

    + \>>表示右移，如果该数为正，则高位补0，若为负数，则高位补1；
    + \>\>\>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0。

+ 运算优先级：单目>算数运算符>移位>比较>按位>逻辑>三目>赋值
  + 单目运算符：+，-，++，--
  
  + 算数运算符：+，-，*，/，%   
  
  + ​     移位运算符：<<,>>
  
  + 关系运算符：>,<,>=,<=,==,!=
  
  + 位运算符：&，|，~，^, 
  
  + 逻辑运算符：&&，||
  
  + 三目运算符：表达式1？表达式2：表达式3; （如果遇到可以转换为数字的类型，会做自动类型提升）
  
    + ```java
      public static void main(String args[]) {
              Object a = true ? new Integer(10) : new Double(20);
              System.out.print(a);
          }
      //output
      10.0
      ```
  
    + 
  
  + 赋值运算符：=等
  
+ Java体系结构：

    + Java程序设计语言
    + java class文件格式
    + java 应用编程接口(API)
    + Java 虚拟机 JVM

+ Java 基类子类：
  + 子类复写父类方法时，方法的访问权限不能小于父类原方法
  + super()在调用时候只能放在第一行：防止子类后面调用父类属性，在第一行初始化父类；
  + this()和super()不能同时出现在一个构造函数中：this函数指向的构造函数默认有super()方法；
  + 子类能够继承父类所有成员(包括被private修饰的成员变量等,但是无法进行显示调用，可以通过反射)
  
+ Java实现进程同步执行的机制：

    + **同步的两种方式**：同步块和同步方法
    + 对于同步来说都是使用synchronized方法
    + 每一个对象都有一个**监视器**，或者叫做**锁**。
    
+ Javaweb 会话监听：

    + **HttpSessionAttributeListener**：可以实现此侦听器接口获取此web应用程序中会话属性列表更改的通知；
    + **HttpSessionBindingListener**：当该对象从一个会话中被绑定或者解绑时通知该对象，这个对象由HttpSessionBindingEvent对象通知。这可能是servlet程序显式地从会话中解绑定属性的结果，可能是由于会话无效，也可能是由于会话超时；
    + **HttpSessionObjectListener**：没有该接口API；
    + **HttpSessionListener**：当web应用程序中的活动会话列表发生更改时通知该接口的实现类，为了接收该通知事件，必须在web应用程序的部署描述符中配置实现类；
    + **HttpSessionActivationListener**：绑定到会话的对象可以侦听容器事件，通知它们会话将被钝化，会话将被激活。需要一个在虚拟机之间迁移会话或持久会话的容器来通知所有绑定到实现该接口会话的属性。

+ J2EE中常用的名词:

    + JNDI:（Java Naming & Directory Interface）JAVA命名目录服务。主要提供的功能是：提供一个目录系，让其它各地的应用程序在其上面留下自己的索引，从而满足快速查找和定位分布式应用程序的功能。
    + EJB容器：Enterprise java bean 容器
    + JMS：JAVA消息服务。主要实现各个应用程序之间的通讯。包括点对点和广播。
    + JAF：JAVA安全认证框架。提供一些安全控制方面的框架。

+ 抽象类和接口：

    + 抽象类可以具有构造函数。
    + 接口可以多继承
    + 接口不是继承于Object
    + 抽象方法只能够被public和protected修饰。

+ JSP内置对象：

    + pageContext javax.servlet.jsp.PageContext：表示页容器 EL表达式、 标签 、上传
    + request javax.servlet.http.HttpServletRequest：服务器端取得客户端的信息：头信息 、Cookie 、请求参数 ，最大用处在MVC设计模式上
    + response javax.servlet.http.HttpServletResponse：服务器端回应客户端信息：Cookie、重定向
    + session javax.servlet.http.HttpSession： 表示每一个用户，用于登录验证上
    + application javax.servlet.ServletContext：表示整个服务器
    + config javax.serlvet.ServletConfig：取得初始化参数，初始化参数在web.xml文件中配置
    + exception java.lang.Throwable:表示的是错误页的处理操作
    + page java.lang.Object:如同this一样，代表整个jsp页面自身
    + out javax.servlet.jsp.JspWriter: 输出 ，但是尽量使用表达式输出
    
+ weblogic中开发消息Bean时的persistent与non-persisten的差别：

    - **persistent方式的MDB可以保证消息传递的可靠性**,也就是如果EJB容器出现问题而JMS服务器依然会将消息在此MDB可用的时候发送过来。
    - **non－persistent方式的消息将被丢弃**。

+ Switch支持的数据类型：byte,short ,int ,char ,String 



+ Java内部类：
  
+ ![1568206648653](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1568206648653.png)
  
    + **1. 静态内部类：**
    
      ​    **1. 静态内部类本身可以访问外部的静态资源，包括静态私有资源。但是不能访问非静态资源，可以不依赖外部类实例而实例化。**
    
      **2. 成员内部类：**
    
      ​    **1. 成员内部类本身可以访问外部的所有资源，但是自身不能定义静态资源，因为其实例化本身就还依赖着外部类。**
    
      **3. 局部内部类：**
    
      ​    **1. 局部内部类就像一个局部方法，不能被访问修饰符修饰，也不能被static修饰。**
    
      ​    **2. 局部内部类只能访问所在代码块或者方法中被定义为final的局部变量。**
    
      **4. 匿名内部类：**
    
      ​    **1. 没有类名的内部类，不能使用class，extends和implements，没有构造方法。**
    
      ​    **2. 多用于GUI中的事件处理。**
    
      ​    **3. 不能定义静态资源**
    
      ​    **4. 只能创建一个匿名内部类实例。**
    
      ​    **5. 一个匿名内部类一定是在new后面的，这个匿名类必须继承一个父类或者实现一个接口。**
    
      ​    **6. 匿名内部类是局部内部类的特殊形式，所以局部内部类的所有限制对匿名内部类也有效。**
  
+ 分布式系统的CAP理论(三者不可兼得)：
  + 一致性（C）：在分布式系统中的所有数据备份，在同一时刻是否同样的值。（等同于所有节点访问同一份最新的数据副本）
  + 可用性（A）：在集群中一部分节点故障后，集群整体是否还能响应客户端的读写请求。（对数据更新具备高可用性）
  + 分区容忍性（P）：以实际效果而言，分区相当于对通信的时限要求。系统如果不能在时限内达成数据一致性，就意味着发生了分区的情况，必须就当前操作在C和A之间做出选择。
  
+ 在JSP对象中，只有一个**Application**对象：多个用户使用一个Application。

+ Java创建对象的5种方式：

    + new 关键字 ：ClassName a=new ClassName();
    
    + 使用反射的Class类的newInstance()方法： ObjectName obj = ObjectName.class.newInstance();
    
    + 使用反射的Constructor类的newInstance()方法： ObjectName obj = ObjectName.class.getConstructor.newInstance(); 
    
    + 使用对象克隆clone()方法： ObjectName obj = obj.clone();
    
    + 使用反序列化 （ObjectInputStream）的readObject()方法：:
    
      + ```java
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) { ObjectName obj = ois.readObject(); }
        ```
    
+ 多线程关键字：

    + 可见性
    + 有序性
    + 原子性
        + volatile可以保证前两个特性
        + CAS算法，也就是CPU级别的同步指令，相当于乐观锁，可以检测到其他线程对共享数据的变化情况。(ABA问题)

+ Java事务的三种类型：
  + JDBC事务：
    + 事务是用 Connection 对象控制的。JDBC Connection 接口（ java.sql.Connection ）提供了两种事务模式：**自动提交**和**手工提交**。 java.sql.Connection 提供了以下控制事务的方法：
  + JTA(Java TransactionAPI) 事务：
    + 支持多数据库事务同时事务管理,满足分布式系统中的数据的一致性
    + 两阶段提交
    + 事务时间太长,锁数据太长
    + 低性能,低吞吐量
  + 容器事务：
    + 容器事务主要是J2EE应用服务器提供的，容器事务大多是基于JTA完成，这是一个基于JNDI的，相当复杂的API实现。

+ 为什么使用反射加载JDBC:
  
    + > 如果使用new com.mysql.jdbc.Driver()这种方式，会对这个具体的类产生依赖。后续如果你要更换数据库驱动，就得重新修改代码。而使用反射的方式，只需要在配置文件中，更改相应的驱动和url即可。----**解耦**







## 多线程

+ 死锁：

  + 定义：死锁是指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去。”那么我们换一个更加规范的定义：“集合中的每一个进程都在等待只能由本集合中的其他进程才能引发的事件，那么该组进程是死锁的。

  + 产生原因：

    1.  互斥条件：一个资源每次只能被一个进程使用。
    2.  请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
    3.  不剥夺条件:进程已获得的资源，在末使用完之前，不能强行剥夺。
    4.  循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。

  + 死锁怎么产生的：![1571066286602](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1571066286602.png)

  + ```java
    public static void main(String[] args) {
        final Object a = new Object();
        final Object b = new Object();
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                synchronized (a) {
                    try {
                        System.out.println("now i in threadA-locka");
                        Thread.sleep(1000l);
                        synchronized (b) {
                            System.out.println("now i in threadA-lockb");
                        }
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }
        });
    
        Thread threadB = new Thread(new Runnable() {
            public void run() {
                synchronized (b) {
                    try {
                        System.out.println("now i in threadB-lockb");
                        Thread.sleep(1000l);
                        synchronized (a) {
                            System.out.println("now i in threadB-locka");
                        }
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }
        });
    
        threadA.start();
        threadB.start();
    }
    
    ```

  + 解决死锁的方法：

    + 1.以确定的顺序获取锁
    + 2.超时放弃锁

+ Tomcat目录结构：
  
  + ![1571192788405](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1571192788405.png)
+ StackOverflow和OutOfMemery:
  + OutOfMemory:
    + 对于一台服务器而言，每一个用户请求，都会产生一个线程来处理这个请求，每一个线程对应着一个栈，栈会分配内存，此时如果请求过多，这时候内存不够了，就会发生栈内存溢出。
  + StackOverflow:
    + 栈溢出是指不断的调用方法，不断的压栈，最终超出了栈允许的栈深度，就会发生栈溢出，比如递归操作没有终止，死循环。





## 线程池

### 1. 为什么要用线程池？

线程池提供了一种限制和管理资源（包括执行一个任务）。 每个线程池还维护一些基本统计信息，例如已完成任务的数量。

这里借用《Java并发编程的艺术》提到的来说一下使用线程池的好处：

- **降低资源消耗。** 通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
- **提高响应速度。** 当任务到达时，任务可以不需要的等到线程创建就能立即执行。
- **提高线程的可管理性。** 线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。

### 2.  Java 提供了哪几种线程池？他们各自的使用场景是什么？

#### Java 主要提供了下面4种线程池

- **FixedThreadPool：** 该方法返回一个固定线程数量的线程池。该线程池中的线程数量始终不变。当有一个新的任务提交时，线程池中若有空闲线程，则立即执行。若没有，则新的任务会被暂存在一个任务队列中，待有线程空闲时，便处理在任务队列中的任务。
- **SingleThreadExecutor：** 方法返回一个只有一个线程的线程池。若多余一个任务被提交到该线程池，任务会被保存在一个任务队列中，待线程空闲，按先入先出的顺序执行队列中的任务。
- **CachedThreadPool：** 该方法返回一个可根据实际情况调整线程数量的线程池。线程池的线程数量不确定，但若有空闲线程可以复用，则会优先使用可复用的线程。若所有线程均在工作，又有新的任务提交，则会创建新的线程处理任务。所有线程在当前任务执行完毕后，将返回线程池进行复用。
- **ScheduledThreadPoolExecutor：** 主要用来在给定的延迟后运行任务，或者定期执行任务。ScheduledThreadPoolExecutor又分为：ScheduledThreadPoolExecutor（包含多个线程）和SingleThreadScheduledExecutor （只包含一个线程）两种。

#### 各种线程池的适用场景介绍

- **FixedThreadPool：** 适用于为了满足资源管理需求，而需要限制当前线程数量的应用场景。它适用于负载比较重的服务器；
- **SingleThreadExecutor：** 适用于需要保证顺序地执行各个任务并且在任意时间点，不会有多个线程是活动的应用场景。
- **CachedThreadPool：** 适用于执行很多的短期异步任务的小程序，或者是负载较轻的服务器；
- **ScheduledThreadPoolExecutor：** 适用于需要多个后台执行周期任务，同时为了满足资源管理需求而需要限制后台线程的数量的应用场景，
- **SingleThreadScheduledExecutor：** 适用于需要单个后台线程执行周期任务，同时保证顺序地执行各个任务的应用场景。

### 3.  创建的线程池的方式

**（1） 使用 Executors 创建**

我们上面刚刚提到了 Java 提供的几种线程池，通过 Executors 工具类我们可以很轻松的创建我们上面说的几种线程池。但是实际上我们一般都不是直接使用Java提供好的线程池，另外在《阿里巴巴Java开发手册》中强制线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 构造函数 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。

```java
Executors 返回线程池对象的弊端如下：

FixedThreadPool 和 SingleThreadExecutor ： 允许请求的队列长度为 Integer.MAX_VALUE,可能堆积大量的请求，从而导致OOM。
CachedThreadPool 和 ScheduledThreadPool ： 允许创建的线程数量为 Integer.MAX_VALUE ，可能会创建大量线程，从而导致OOM。
```

**（2） ThreadPoolExecutor的构造函数创建**

我们可以自己直接调用 ThreadPoolExecutor 的构造函数来自己创建线程池。在创建的同时，给 BlockQueue 指定容量就可以了。示例如下：

```java
private static ExecutorService executor = new ThreadPoolExecutor(13, 13,
        60L, TimeUnit.SECONDS,
        new ArrayBlockingQueue(13));
```

这种情况下，一旦提交的线程数超过当前可用线程数时，就会抛出java.util.concurrent.RejectedExecutionException，这是因为当前线程池使用的队列是有边界队列，队列已经满了便无法继续处理新的请求。但是异常（Exception）总比发生错误（Error）要好。