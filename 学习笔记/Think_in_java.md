# Think in java 中的记录

+ return的作用

  1. 一种是返回参数所用的关键字，假如一个有返回值的方法执行完了之后需要返回一个参数

  ```java
  public string functionTest(){
  String a = "abc";
  return a;
  }
  string result = functionTest();//返回结果为abc的result
  ```

  2. 代码执行到此处终止。比如当代码执行到某个地方会出现几种结果，然后其中一种结果就不能执行后续代码，这时候在那里加上一个return就可以终止后面的代码执行。

+ 数组

  +  String[] a 对象数组象中的每个元素都是对的管理者而非对象本身

+ super:

  1. super.xxx;(xxx为变量名或对象名)
     这种方法意义为，获取父类中的名字为xxx的变量或方法引用。
     使用这种方法可以直接访问父类中的变量或对象，进行修改赋值等操作
  2. super.xxx();(xxx为方法名)
     这种方法意义为，直接访问并调用父类中的方法。  
  3. super();
     这种方法意义为，调用父类的初始化方法，其实就是调用父类中的public xxx()方法； 
+ 子类父类关系：
  子类不能调用父类的private 变量 protected可以 出现同名成员变量是，就近原则
  子类也是父类的一种扩展 父类可以直接调用子类
  子类的对象可以赋给父类的对象
  父类对象转化为子类对象 ：

  ```java
  -  Son son=(Son)Parent parent; //只有当parent这个变量实际管理的是son才可以 ，这称为造型
     Parent parent=（Parent）Son son；//向上造型 拿一个子类对象给父类用，默认安全
  public class test1{
      private String s="a";
      test1(){
        System.out.println("test1()");
      }
      test1(int i){
        System.out.println(i);
        System.out.println(s);
        
      }
      static void test2(test1 i){
        System.out.println(" 向上转型");
      }
  
  }
  
  class frog extends test1{   
  
  }
   public  class Extend_exam {
  
     public static void main(String []args) {
       frog fr=new frog();
       test1.test2(fr);
       
     }
  
  ```

+ 函数调用的绑定：
     当通过对象变量调用函数的时候，调用哪个函数这件事情叫作绑定。
     静态绑定：根据变量的声明类型来决定
     动态绑定：根据变量的动态类型来决定
     在成员函数中调用其他成员函数也是通过this这个对象变量来调用的
     java中默认对象的绑定是动态绑定

  ```java
  @Override 
  //子类和父类中存在名称和参数完全相同的函数，这一对函数构成覆盖，子类覆盖父类 ，需要注意的是只有父类中非private的方法才可以被覆盖，在导出类中，对于基类中的private方法，最好采用不同的名字。
  
  public class PrivateOverride {
  private void f() {
    System.out.println("private f()");
  }
    
  public static void main(String []args) {
    PrivateOverride a=new Derived();
    a.f();
    Derived b=new Derived();
    b.f();
    b.f1();
    //a.f1(); 这样的操作也是不被允许的
  }
  }
  class Derived extends PrivateOverride{
    public void f() {
      System.out.println("public f()");
    }
    public void f1() {
      System.out.println("这是导出类中的f1()方法");
    }
  }
  //output:
  private f()
  public f()
  这是导出类中的f1()方法
  ```

+ String 字符串分割：split() 方法用于把一个字符串分割成字符串数组。

+ 抽象：

  + 抽象函数：{表达概念而无法实现具体代码的函数

  + 抽象类：表达概念而无法构造出实体的函数

  + 区别

    有抽象函数的类一定是抽象类
    抽象类不能制造对象但是可以定义变量，任何继承了抽象类的非抽象类的的对象可以赋给这个变量
    抽象类除了不能实例化对象之外，类的其它功能依然存在，成员变量、成员方法和构造方法的访问方式和普通类一样。
    由于抽象类不能实例化对象，所以抽象类必须被继承，才能被使用。
    构造方法，类方法（用static修饰的方法）不能声明为抽象方法。
    抽象类的子类必须给出抽象类中的抽象方法的具体实现，除非该子类也是抽象类。
    抽象方法必须为public或者protected（因为如果为private，则不能被子类继承，子类便无法实现该方法），缺省情况下默认为public。
    在其他方面，抽象类和普通的类并没有区别。

+ interface接口：

  1. 抽象类可以提供成员方法的实现细节，而接口中只能存在public abstract 方法；
  2. 抽象类中的成员变量可以是各种类型的，而接口中的成员变量只能是public static final类型的；
  3. 接口中不能含有静态代码块以及静态方法，而抽象类可以有静态代码块和静态方法；
  4. 一个类只能继承一个抽象类，而一个类却可以实现多个接口  

  例子： Door的open() 、close()和alarm()根本就属于两个不同范畴内的行为，open()和close()属于门本身固有的行为特性，而alarm()属于延伸的附加行为。因此最好的解决办法是单独将报警设计为一个接口，包含alarm()行为,Door设计为单独的一个抽象类，包含open和close两种行为。再设计一个报警门继承Door类和实现Alarm接口。

  ```java
  interface Alram {
      void alarm();
  }
  
  abstract class Door {
      void open();
      void close();
  }
  
  class AlarmDoor extends Door implements Alarm {
      void oepn() {
        //....
      }
      void close() {
        //....
      }
      void alarm() {
        //....
      }
  }
  ```


+ 在文件流上建立文本处理：

  ```java
  PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("abc.txt"))));
  ```


+ 显式的静态初始化

  ```java
  public class Spoon{
    static int i;
    static{
    i=47;
    }
  }
  ```


+ int与integer的对比：

  Integer是int的包装类；int是基本数据类型
  integer变量必须实例化后才能使用；int变量不需要
  intege实际是对象的引用，指向此new的integer对象，int是直接存储数据值
  integet的默认值是null，int的默认值是0；

  ```java
     {
     Integer i= new Integer(100);
     Integet j=new Integer(100);
     System.out.println(i==j);//false
     }
     {
     Integet i=new Integer(100);
     int j=100;
     System.out.println(i==j);//true 
     }
     {
     Integer i=new Integet(100);
     Ingeter j=100;
     System.out.println(i==j);//false 非new生成的Integer变量指向的是java常量池中的对象，new Integer（）生成的变量指向堆中新建的对象，两者在内存中的地址不同
     }
  ```


+ 组合，继承，代理：

  + 组合：在新类中new 另外一个类的对象，以添加该对象的特性。

  + 继承：从基类继承得到子类，获得基类的特性。当继承发生时，编译器会从先对基类进行初始化。

  + 代理：在代理类中创建某功能的类，调用类的一些方法以获得该类的部分特性。

  + 使用场合：

    组合：各部件之间没什么关系，只需要组合即可。like组装电脑，需要new CPU(),new RAM(),new Disk()……
    继承：子类需要具有父类的功能。基类无法调用子类方法。
    代理：飞机控制类，我不想暴露太多飞机控制的功能，只需部分前进左右转的控制（而不需要暴露发射导弹功能）。通过在代理类中new一个飞机控制对象，然后在方法中添加飞机控制类的各个需要暴露的功能。

+ public private protected default的区别：
  + public：可以被所有其他类所访问
  + private：只能被自己访问和修改
  + protected：自身、子类及同一个包中类可以访问
  + default：同一包中的类可以访问，声明时没有加修饰符，认为是friendly。

+ final:

  1. 修饰基础数据成员的final
     这是final的主要用途，其含义相当于C或者C++的const，即该成员被修饰为常量，意味着不可修改。如java.lang.Math类中的PI和E是final成员，其值为3.141592653589793和2.718281828459045。

  2. 修饰类或对象的引用的final
     在Java中，我们无法让对象被修饰为final，而只能修饰对象的引用，这意味着即使你写public final A a = new A(); 事实上a指向的对象的数据依然可以被修改，不能修改的是a本身的引用值，即你不能再对a进行重赋值。同样的情况出现在数组中，比如public final int[] a = {1, 2, 3, 4, 5}，事实上a中的数值是可修改的，即可以写a[0] = 3。据目前了解，java中数组内的数据是无法修饰为不可修改的，而C/C++可以。

  3. 修饰方法的final
     修饰方法的final和C/C++中修饰成员对象的const大不相同。首先，修饰方法的final含义不是“不可修改”，而是指该方法不可被继承成员重新定义。（注意，这里所说的不能被重新定义，并不是指子类一定不能定义同名方法，如果父类的方法是私有类型，子类是允许定义该方法的，这里指的不能重新定义是指不能通过改写方法来使得方法重写的多态性得以实现，如不希望A a = new B(); a.f();这样的重写方法情况出现）
     示例：

     ```java
     
     public class A {
         // final方法f
         public final void f() {
            System.out.println("类A中的final方法f被调用了");
         }
     }
     public class B extends A {
         // 编译错误！父类的f方法是final类型，不可重写！
         //! public void f() {
         //!     System.out.println("类B中的方法f被调用了");
         //! }
     }
     ```



     + 此外，当一个方法被修饰为final方法时，意味着编译器可能将该方法用内联(inline)方式载入，所谓内联方式，是指编译器不用像平常调用函数那样的方式来调用方法，而是直接将方法内的代码通过一定的修改后copy到原代码中。这样可以让代码执行的更快（因为省略了调用函数的开销），比如在int[] arr = new int[3]调用arr.length()等。
       另一方面，私有方法也被编译器隐式修饰为final，这意味着private final void f()和private void f()并无区别。
  4. 修饰类的final:
       当一个类被修饰为final时，它的含义很明确，就是不允许该类被继承，也就是说，该类“绝后”了，任何继承它的操作都会以编译错误告终。这也凸显出Java用final而不用const作为标识符的理由。
        示例：

       ```java
        public final class A { 
          }
          // 编译错误！A是final类型，不可被继承！
          //!public class B extends A{
          //!}
       ```


+ final和static final的区别：

  ```java
   {
    class SelfCounter{   
       private static int counter;  
       private int id = counter ++; //下一次实例化该类时才会id的值才等于这一次的counter+1；         
       public String toString(){   
         return "SelfCounter :" + id;       
       }}
    class WithFinalFields{       
       static final SelfCounter wffs = new SelfCounter();      
       final SelfCounter wff = new SelfCounter();           
       public String toString(){        
        return "wff = " + wff + "\n wffs = " + wffs;  
        }
  }
  public class E18_StaticFinal {       
     public static void main(String[] args) {            
       System.out.println("First Object :"); 
       System.out.println(new WithFinalFields());      
       System.out.println("Second Object: ");                   
       System.out.println(new WithFinalFields());         
       }}，
      运行结果是
      First Object :wff = SelfCounter :1   wffs = SelfCounter :0
      Second Object: wff = SelfCounter :2  wffs = SelfCounter :0 
  ```

  结论：
  static的常量在类加载的时候被初始化，而实例常量在实例化的时候被初始化。其实上面的过程很简单。第一次实例化WithFinalFields的时候，虚拟机发现该类没有被加载，于是先加载类，加载类的同时需要初始化类的所有static无论是变量、常量还是块，于是wffs需要实例化一个SelfCounter对象，这个时候虚拟机发现SelfCounter类也没有被加载，于是加载SelfCounter类，同时初始化static变量counter为0，加载SelfCounter类完毕，开始实例化SelfCounter对象，初始化id为0（此时counter为0），同时counter变为1，这时SelfCounter对象的实例化完毕，并被赋值给WithFinalFields类的wffs常量，加载WithFinalFields类的过程完毕，开始正式实例化WithFinalFields对象，初始化wff常量，又需要实例化一个SelfCounter对象，这时虚拟机发现SelfCounter类已经被加载，于直接开始实例化SelfCounter对象，初始化id为1（此时counter为1），同时counter变为2，实例化WithFinalFields对象完毕，此时wffs的id为0，wff的id为1。第二次实例化WithFinalFields的时候，虚拟机发现该类已经被加载，直接实例化，不会初始化static无论是变量、常量还是块，于是直接初始化wff常量，需要实例化SelfCounter对象，该类也已经被加载，于是也直接实例化，初始化id为2（此时counter为2），同时counter变为3，实例化SelfCounter对象完毕，同时实例化WithFinalFields对象完毕，此时wffs的id仍然为0，wff的id为2。重点是静态的东西只会被初始化一次，发生在类加载的时候。

  ## ！！！counter++ 与++counter 的区别要注意 ##

+ 内部类对象的创建

  ```java
  class MNA{
      class A{
          class B{
  
          }
      }
  }
  
  public class MultiNestingAccess {
      public static void main(String []args){
          MNA mna=new MNA();
          MNA.A a=mna.new A();
          MNA.A.B b=a.new B();
  
      }
  }
  ```

+ 内部类的继承/实现

  ```java
  //每个内部类都能独立地继承一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有任何影响。带有参数构造器，切具有外围类的内部类的继承：
  class A {
      class innerA {
         innerA(int a){
         }
      }
  }
  class B{
      class innerB extends A.innerA{
  
          innerB(A a,int c){
              a.super(c);
          }
      }
  }
  ```

+ 队列Queue中offer与add的区别

  + 一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝。
    这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked 异常，而只是得到由 offer() 返回的 false。  
    poll，remove区别：
    remove() 和 poll() 方法都是从队列中删除第一个元素。remove() 的行为与 Collection 接口的版本相似，
    但是新的 poll() 方法在用空集合调用时不是抛出异常，只是返回 null。因此新的方法更适合容易出现异常条件的情况。 
    peek，element区别：
    element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 peek() 返回 null

+ break与continua的区别
  + 1. break
       用break语句可以使流程跳出switch语句体，也可以用break语句在循环结构终止本层循环体，从而提前结束本层循环。
       使用说明：
       （1）只能在循环体内和switch语句体内使用break；
       （2）当break出现在循环体中的switch语句体内时，起作用只是跳出该switch语句体，并不能终止循环体的执行。若想强行终止循环体的执行，可以在循环体中，但并不在switch语句中设置break语句，满足某种条件则跳出本层循环体。
    2. continue
       continue语句的作用是跳过本次循环体中余下尚未执行的语句，立即进行下一次的循环条件判定，可以理解为仅结束本次循环。
       注意：continue语句并没有使整个循环终止。

+ 创建新对象时New 与newinstance的区别
  + 1. 类的加载方式不同
           在执行Class.forName("a.class.Name")时，JVM会在classapth中去找对应的类并加载，这时JVM会执行该类的静态代码段。在使用newInstance()方法的时候，必须保证这个类已经加载并且已经连接了，而这可以通过Class的静态方法forName()来完成的。
           使用关键字new创建一个类的时候，这个类可以没有被加载，一般也不需要该类在classpath中设定，但可能需要通过classlaoder来加载。
    2. 所调用的构造方法不尽相同
           new关键字能调用任何构造方法。
           newInstance()只能调用无参构造方法。
    3. 执行效率不同
           new关键字是强类型的，效率相对较高。
           newInstance()是弱类型的，效率相对较低。

+ isAssignableFrom()方法
  + 1. class2是不是class1的子类或者子接口
    2. Object是所有类的父类
       instanceof 保持了类型的概念，它指的是“你是这个类吗，或者你是这个类的派生类吗？”而如果用==比较实际的Class对象，就没有考虑继承--它是这个确切的类型，或者不是 ，equals与==相等。

+ java反射:

  + 1. 直接获取某一个对象的class，这种方法实际在告诉我们任何一个类都有一个隐含的静态成员变量class。
       Class c1 = Foo.class;//其中Foo是类名。 编译器并不会初始化FOO类

    2. 调用某个对象的getClass()方法，已经知道该类的对象通过getClass方法。
       Class c2 = foo1.getClass();

    3. 使用Class类的forName静态方法:

       ```java
       Class c3 = null;
           try {
             c3 = Class.forName("com.om.reflect.Foo");
           } catch (ClassNotFoundException e) {
             e.printStackTrace();
           }   //编译器会对FOO类初始化
       ```

        不管c1  or c2 or c3都代表了Foo类的类类型，一个类只可能是Class类的一个实例对象。因此c1=c2=c3

+ java动态代理：

  ```java
  import java.lang.reflect.Method;
  
  public class TEEEE3 {
  public int add(int param1, int param2) {
      return param1 + param2;
  }
  
  public String echo(String mesg) {
      return "echo " + mesg;
  }
  
  public static void main(String[] args) throws Exception {
      Class classType = TEEEE3.class;
      Object invokertester = classType.newInstance();
  
      Method addMethod = classType.getMethod("add",  int.class,
              int.class );
      //Method类的invoke(Object obj,Object args[])方法接收的参数必须为对象，
      //如果参数为基本类型数据，必须转换为相应的包装类型的对象。invoke()方法的返回值总是对象，
      //如果实际被调用的方法的返回类型是基本类型数据，那么invoke()方法会把它转换为相应的包装类型的对象，
      //再将其返回
      Object result = addMethod.invoke(invokertester, new Object[] {
              new Integer(100), new Integer(200) });
      //在jdk5.0中有了装箱 拆箱机制 new Integer(100)可以用100来代替，系统会自动在int 和integer之间转换
      System.out.println(result);
  
      Method echoMethod = classType.getMethod("echo",
               String.class );
      result = echoMethod.invoke(invokertester,  "hello" );
      System.out.println(result);
  }
      }
  ```

  动态代理实现

  1. 定义一个接口

     ```java
     public interface Tinteface {
      public int add(int param1, int param2);
      public String echo(String mesg);
     }
     
     ```

  2. 实现该接口

     ```java
     public class TEEEE3 implements Tinteface {
         public int add(int param1, int param2) {
             return param1 + param2;
         }
     
         public String echo(String mesg) {
             return "echo " + mesg;
         }
     }
     ```

  3. 实现动态代理处理器

     ```java
     public class DynamicProxy implements InvocationHandler {
     
         private Tinteface tinteface;
     
         DynamicProxy(Tinteface tinteface) {
             this.tinteface = tinteface;
         }
     
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
             //若在invoke()函数中使用proxy调用方法会陷入死循环
             System.out.println("动态代理开始");
             //invoke所需参数为需要代理的目标类，目标类方法所需参数
             return method.invoke(tinteface, args);
         }
     }
     ```

  4. 动态代理实现

     ```java
     public class Test{
         public static void main(String[] args) throws IOException {
             TEEEE3 teeee3 = new TEEEE3();
             DynamicProxy dynamicProxy = new DynamicProxy(teeee3);
             Tinteface tinteface = (Tinteface) Proxy.newProxyInstance(dynamicProxy.getClass().getClassLoader(), new Class[]{Tinteface.class},dynamicProxy );
             String s = tinteface.echo("张三");
             System.out.println(s);
         }
     }
     ```

     

  

  Proxy.newProxyInstance()方法有三个参数：

  1. 类加载器(Class Loader) :一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载

  2. 需要实现的接口 数组:  一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了

  3. InvocationHandler接口。所有动态代理类的方法调用，都会交由InvocationHandler接口实现类里的invoke()方法去处理。这是动态代理的关键所在

  java动态代理详解：http://www.cnblogs.com/xiaoluo501395377/p/3383130.html

  

+ public TypeVariable<Class<T>>[] getTypeParameters() 

  + :该方法返回一个代表该泛型声明中声明的类型变量TypeVariable对象的数组。

+ Java中字符串中子串的查找:
  + 1. int indexOf(String str) ：返回第一次出现的指定子字符串在此字符串中的索引。 
    2. int indexOf(String str, int startIndex)：从指定的索引处开始，返回第一次出现的指定子字符串在此字符串中的索引。 
    3. int lastIndexOf(String str) ：返回在此字符串中最右边出现的指定子字符串的索引。 
    4. int lastIndexOf(String str, int startIndex) ：从指定的索引处开始向后搜索，返回在此字符串中最后一次出现的指定子字符串的索引。

+ 协变<? extends T> 逆变<? super T>

  + 对<? extends T>修饰的容器不能执行写操作，只能执行取操作。
    对<? super T>修饰的容器执行写入操作时, 只能写入 T 类以及子类对象.
    参考：https://www.cnblogs.com/drizzlewithwind/p/6100164.html
    https://blog.csdn.net/dou_yuan/article/details/77528107
    PECS原则
    最后看一下什么是PECS（Producer Extends Consumer Super）原则，已经很好理解了：
    P: 生产者使用 E: extends 
    如果你需要一个列表提供 T 类型的元素（即你想从列表中读取 T 类型的元素），你需要把这个列表声明成<? extends T>，比如List<? extends Integer>，因此你不能往该列表中添加任何元素。

    C: 消费者使用 S: super 
    如果需要一个列表使用 T 类型的元素（即你想把 T 类型的元素加入到列表中），你需要把这个列表声明成<? super T>，比如List<? super Integer>，因此你不能保证从中读取到的元素的类型。

    即是生产者，也是消费者 
    如果一个列表即要生产，又要消费，你不能使用泛型通配符声明列表，比如List<Integer>。

    频繁往外读取内容的，适合用上界Extends。
    经常往里插入的，适合用下界Super。 

+ inputStream OutputStream 的区别:

  + inputStream,OutputStream  前者为字节输入流，后者为字节输出流。
    Reader   Writer  前者为字符输入流，后者为字符输出流。
    四个均为抽象类。
    fileInputStream 是InputStream 的实现类  
    fileReader 是Reader 的实现类
    字节流读取单位为一个字节，字符流读取单位为一个字符  所以读取汉字的时候，如果用字节流就会导致读出来乱码。这是最常用的地方  其他基本用法差不多。。

    BufferedInputStream是套在某个其他的InputStream外，起着缓存的功能，用来改善里面那个InputStream的性能（如果可能的话），它自己不能脱离里面那个单独存在。FileInputStream是读取一个文件来作InputStream。所以你可以把BufferedInputStream套在FileInputStream外，来改善FileInputStream的性能。   

    ava.io下面有两个抽象类：InputStream和Reader
    InputStream是表示字节输入流的所有类的超类
    Reader是用于读取字符流的抽象类
    InputStream提供的是字节流的读取，而非文本读取，这是和Reader类的根本区别。
    即用Reader读取出来的是char数组或者String ，使用InputStream读取出来的是byte数组。
    弄清了两个超类的根本区别，再来看他们底下子类的使用，这里只对最常用的几个说明

    InputStream 
       | __FileInputStream 


    FileInputStream 从文件系统中的某个文件中获得输入字节。
    构造方法摘要  
    FileInputStream (File  file) 
              通过打开一个到实际文件的连接来创建一个 FileInputStream ，该文件通过文件系统中的 File 对象 file 指定。 
    FileInputStream (FileDescriptor  fdObj) 
              通过使用文件描述符 fdObj 创建一个 FileInputStream ，该文件描述符表示到文件系统中某个实际文件的现有连接。 
    FileInputStream (String  name) 
              通过打开一个到实际文件的连接来创建一个 FileInputStream ，该文件通过文件系统中的路径名 name 指定。 


    Reader
    
       |——BufferedReader 
       |___InputStreamReader 
             |__FileReader 
    
    ```java
    BufferedReader : 从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
    
    构造方法摘要  
    BufferedReader (Reader  in) 
              创建一个使用默认大小输入缓冲区的缓冲字符输入流。 
    BufferedReader (Reader  in, int sz) 
              创建一个使用指定大小输入缓冲区的缓冲字符输入流。 
    ```
    
    BufferedReader (Java Platform SE 6) 
    BufferedReader的最大特点就是缓冲区的设置。通常Reader 所作的每个读取请求都会导致对底层字符或字节流进行相应的读取请求，如果没有缓冲，则每次调用 read() 或 readLine() 都会导致从文件中读取字节，并将其转换为字符后返回，而这是极其低效的。 
    使用BufferedReader可以指定缓冲区的大小，或者可使用默认的大小。大多数情况下，默认值就足够大了。 
    因此，建议用 BufferedReader 包装所有其 read() 操作可能开销很高的 Reader（如 FileReader 和InputStreamReader）。例如， 
     BufferedReader in
       = new BufferedReader(new FileReader("foo.in"));
     将缓冲指定文件的输入。 
    InputStreamReader (Java Platform SE 6) 
    InputStreamReader 是字节流通向字符流的桥梁：它使用指定的 charset 读取字节并将其解码为字符。它使用的字符集可以由名称指定或显式给定，或者可以接受平台默认的字符集。 
    
    构造方法摘要  
    
    ```java
    InputStreamReader (InputStream  in) 
              创建一个使用默认字符集的 InputStreamReader。 
    InputStreamReader (InputStream  in, Charset  cs) 
              创建使用给定字符集的 InputStreamReader。 
    InputStreamReader (InputStream  in, CharsetDecoder  dec) 
              创建使用给定字符集解码器的 InputStreamReader。 
    InputStreamReader (InputStream  in, String  charsetName) 
              创建使用指定字符集的 InputStreamReader。 
    ```


​    

    每次调用 InputStreamReader 中的一个 read() 方法都会导致从底层输入流读取一个或多个字节。要启用从字节到字符的有效转换，可以提前从底层流读取更多的字节，使其超过满足当前读取操作所需的字节。 
    为了达到最高效率，可要考虑在 BufferedReader 内包装 InputStreamReader。例如： 
     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    InputStreamReader最大的特点是可以指转换的定编码格式
    ，这是其他类所不能的，从构造方法就可看出，
    这一点在读取中文字符时非常有用
    
    FileReader
    1）FileReader类介绍：
    InputStreamReader类的子类，所有方法（read（）等）都从父类InputStreamReader中继承而来；
    2）与InputStreamReader类的区别：
    构造方法摘要  
    
    ```java
    FileReader (File  file) 
              在给定从中读取数据的 File 的情况下创建一个新 FileReader 。 
    FileReader (FileDescriptor  fd) 
              在给定从中读取数据的 FileDescriptor 的情况下创建一个新 FileReader 。 
    FileReader (String  fileName) 
              在给定从中读取数据的文件名的情况下创建一个新 FileReader  
    ```


​    

    该类与它的父类InputStreamReader的主要不同在于构造函数，主要区别也就在于构造函数！
    从InputStreamReader的构造函数中看到，参数为InputStream和编码方式，可以看出，
    当要指定编码方式时，必须使用InputStreamReader
    类；而FileReader构造函数的参数与FileInputStream同，为File对象或表示path的String，可以看出，当要根据File对象或者String读取一个文件时，用FileReader；
    我想FileReader子类的作用也就在于这个小分工吧。该类与它的父类InputStreamReader
    的主要不同在于构造函数，主要区别也就在于构造函数！
    从InputStreamReader
    的构造函数中看到，参数为InputStream和编码方式，可以看出，
    当要指定编码方式时，必须使用InputStreamReader
    类；而FileReader构造函数的参数与FileInputStream
    同，为File对象或表示path的String，可以看出，当要根据File对象或者String读取一个文件时，用FileReader；
    我想FileReader子类的作用也就在于这个小分工吧。
    二 联系与区别 
    （1）字符与字节： 
    FileInputStream 类以二进制输入/输出，I/O速度快且效率搞，但是它的read（）方法读到的是一个字节（二进制数据），很不利于人们阅读，而且无法直接对文件中的字符进行操作，比如替换，查找（必须以字节形式操作）；
    而Reader类弥补了这个缺陷，可以以文本格式输入/输出，非常方便；比如可以使用while((ch = filereader.read())!=-1 )循环来读取文件；可以使用BufferedReader的readLine()方法一行一行的读取文本。
    （2）编码
    InputStreamReader ，它是字节转换为字符的桥梁。 你可以在构造器重指定编码的方式，如果不指定的话将采用底层操作系统的默认编码方式，例如GBK等。 
    FileReader与InputStreamReader 涉及编码转换(指定编码方式或者采用os默认编码)，可能在不同的平台上出现乱码现象！而FileInputStream 以二进制方式处理，不会出现乱码现象. 
    因此要指定编码方式时，必须使用InputStreamReader 类，所以说它是字节转换为字符的桥梁；
    (3) 缓存区
        BufferReader类用来包装所有其 read() 操作可能开销很高的 Reader（如 FileReader 和InputStreamReader）。
    （4）规范用法
    总结以上内容，得出比较好的规范用法： 
    
    ```java
    1） File file = new File ("hello.txt"); 
    FileInputStream in=new FileInputStream (file); 
    2） File file = new File ("hello.txt"); 
    FileInputStream in=new FileInputStream (file); 
    InputStreamReader inReader=new InputStreamReader (in,"UTF-8"); 
    BufferedReader bufReader=new BufferedReader(inReader); 
    3） File file = new File ("hello.txt"); 
    FileReader fileReader=new FileReader(file); 
    BufferedReader bufReader=new BufferedReader(fileReader);
    ```


​    


    Arrays.deep.toString用于多维数组
    
    public void method() throws Exception{
    
    }//此方法将异常传递给调用者