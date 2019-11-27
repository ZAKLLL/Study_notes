# java笔记
+ File.separator： 保证各类系统下分隔符不出错

+ 创建一个文件夹

  ```java
  nibiprivate final static String uploadfilepath="E:"+File.separator;
  static File files = new File(uploadfilepath+"file1");
  static {
      if (!files.exists()) {
          files.mkdirs();
      }
      System.out.println(files.getPath());
  }
  ```

+ String 字符编码：UTF-8 --> ISO8859-1

  ```java
  String ISO_String=new String(UTF_String.getBytes("UTF-8"),"ISO8859-1))")
  ```

+ url传参  

  ```xml
  <a href="${webpath}/jsp/comment.jsp?newsid=${obj.id}"  >href</a>
  ```

  另一个jsp取参数

  ```xml
  <input type="text" name="newsid" value=${param.newsid}>
  ```

+ 表单提交提示框

  ```html
  <script language="JavaScript">
      function dd() {
          var msg = '您真的确定要删除吗？';
          if (confirm(msg) == true) {
              return true;
          } else {
              return false;
          }
      }
  </script>
  <form action="${webpath}/User/delete" method="post" onsubmit="return dd()">
                      <input name="newsid" type="hidden" value="${obj.id}">
                      <input type="submit" value="删除">
   </form>
  ```

+ fmt标签(时间显示)：

  + ```xml
    <fmt:formatDate value="${obj.createdate}" pattern="yyyy-MM-dd HH:MM:SS"></fmt:formatDate>
    ```

+ 线程：详情见java并发以及多线程关键字volatile



+ math方法

  1. 算术计算

  - `Math.sqrt()` : 计算平方根
  - `Math.cbrt()` : 计算立方根
  - `Math.pow(a, b)` : 计算a的b次方
  - `Math.max( , )` : 计算最大值
  - `Math.min( , )` : 计算最小值
  - `Math.abs()` : 取绝对值

  2. 进位

  - `Math.ceil()`: 天花板的意思，就是逢余进一
  - `Math.floor()` : 地板的意思，就是逢余舍一
  - `Math.rint()`: 四舍五入，返回double值。注意.5的时候会取偶
  3. 随机数

  - `Math.random()`: 取得一个[0, 1)范围内的随机数

+ java常量池

  + CLass 文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池，用于存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放。
    运行时常量池相对于 CLass 文件常量池的另外一个重要特征是**具备动态性**，Java 语言并不要求常量一定只有编译期才能产生，也就是并非预置入 CLass 文件中常量池的内容才能进入方法区运行时常量池，运行期间也可能将新的常量放入池中，这种特性被开发人员利用比较多的就是 **String 类的 intern()** 方法。
    
  + 运行时常量池：
    
    + ```
      运行时常量池大小受栈区大小的影响
      运行时常量池大小受方法区大小的影响
      存放了编译时期生成的各种字面量
      存放编译时期生成的符号引用
      ```

+  String.intern()： 
  
  + 存在于.class文件中的常量池，在运行期被JVM装载，并且可以扩充。String的intern()方法就是扩充常量池的一个方法；当一个String实例str调用intern()方法时，Java查找常量池中是否有相同Unicode的字符串常量，如果有，则返回其的引用，如果没有，则在常量池中增加一个Unicode等于str的字符串并返回它的引用  

+ 是否可以被GC:

  + 在java语言中，判断一块内存空间是否符合垃圾收集器收集标准的标准只有两个：

    1.给对象赋值为null，以下没有调用过。

    2.给对象赋了新的值，重新分配了内存空间。
