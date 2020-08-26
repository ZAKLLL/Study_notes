# Jvm

![1555745612053](../../../images/1555745612053.png)

![1555746018693](../../../images/1555746018693.png)



![1567127222600](../../../images/1567127222600.png)

![1568274341196](../../../images/1568274341196.png)



## 类加载：

+ 加载：
  + 查找并加载类的二进制数据,将类的.class文件中的二进制数据读入到内存中，将其放在运行时数据区的，然后在内存创建一个java.lang.class对象用来封装类在方法区内的数据结构。
  + 加载方式：
    + 从本地系统中直接加载
    + 通过网络下载的.class文件
    + 从zip，jar等归档文件中加载.class文件
    + 将java源文件动态编译为.class文件(反射，jsp等)
  + 用ClassLoader加载类，是不会导致类的初始化（也就是说不会执行**<clinit>**方法）.Class.forName(...)加载类，不但会将类加载，还会执行会执行类的初始化方法.

    + 注意: 并非所有的类都会拥有一个**<clinit>**方法, 满足下列条件之一的类不会拥有**<clinit>**方法:
      1. 该类既没有声明任何类变量，也没有静态初始化语句;
      2. 该类声明了类变量，但没有明确使用类变量初始化语句或静态初始化语句初始化;
      3. 该类仅包含静态 final 变量的类变量初始化语句，并且类变量初始化语句是编译时常量表达式;
+ 连接：
  + 验证：确保被加载类的正确性
    + 类文件的结构检查
    + 语义检查
    + 字节码验证
    + 二进制兼容性的验证
  + 准备：为类的静态变量分配内存，并将其初始化为默认值
  + 解析：把类中符号引用转换为直接引用(将字符串(类和接口的全限定名,字段名称和描述符,方法名称和描述符)转换为直接地址)
+ 初始化：为类的静态变量赋于正确的初始值

+ 类的使用和卸载：
  + 使用
  + 卸载(卸载后如果需要再使用需要重新被类加载器加载)
  
+ 类的使用方式
  + 主动使用(在首次主动使用的时候才初始化目标类)：
    + 创建类的实例
    + 访问某个类或接口的静态变量，或者对该静态变量赋值
    + 调用类的静态方法
    + 反射(Class.forName("com.test.Test"))
    + 初始化一个类的字类
    + JVM启动时被表明为启动类的类
  + 被动使用(并不会导致类的**初始化**)
  
  ```java
  public class MyTest1 {
      public static void main(String[] args) {
          System.out.println(Child.str);
        	//对与Child来说Child类是被动使用，使用的str变量是其父类所拥有的，所以这里是主动使用父类，被动使用字类
      }
  }
  
  class Parent {
      public static String str = "Hello World";
      //    public static static String str = "Hello World";
      static {
          System.out.println("Parent");
      }
  }
  class Child extends Parent {
      public static String str2 = "Fuck";
      static {
          System.out.println("Child");
      }
  }
  
  //output
  Parent 
  HelloWorld 
  
  ```



### ClassLoader

+ 数组类型的对象加载是在运行时被classloader动态创建生成的
+ 如果数组中的对象是原生数据类型(int,long...) ,则改对象的classloade为null
+ 自定义类加载器通过拓展类加载器来拓展JVM动态加载类的能力(通过双亲委托机制)
+ 类加载器通常会被安全管理器所使用来确保类加载过程的安全。