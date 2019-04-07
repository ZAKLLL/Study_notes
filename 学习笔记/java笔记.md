# java笔记
+ File.separator： 保证各类系统下分隔符不出错

+ 创建一个文件夹

  ```java
  private final static String uploadfilepath="E:"+File.separator;
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