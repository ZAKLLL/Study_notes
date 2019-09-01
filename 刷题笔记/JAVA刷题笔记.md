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

  + 在运行时判断任意一个对象所属的类
  + 在运行时构造任意一个类的对象
  + 在运行时判断任意一个类所具有的成员变量和方法
  + 在运行时调用任意一个对象的方法