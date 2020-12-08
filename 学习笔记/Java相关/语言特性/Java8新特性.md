### Lambda表达式



示例

```java
static List<Employee> employees = Arrays.asList(
            new Employee("张三", 12, 5000),
            new Employee("李四", 20, 6000),
            new Employee("王二", 37, 4000),
            new Employee("周五", 19, 3000),
            new Employee("今天", 80, 9000)
    );

public interface Mypredict<T> {
    boolean test(T t);
}
```

```java
@Test
public void test(){
     List<Employee> eps = dodo(employees, (e) -> e.getWage() > 500);
     eps.forEach(System.out::println);
}
public static List<Employee> dodo(List<Employee> list, Mypredict<Employee> me) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (me.test(employee)) {
                employees.add(employee);
            }
        }
        return employees;
 }
```



+ 语法格式

  + 1.无参数无返回值，Lambda箭头操作符' -> '，左边表示参数列表，右边为函数式结构的实现@FunctionalInterface的实现，类比匿名内部类

  ```java
  @FunctionalInterface
  public interface LamTest {
      public void T();
  }
  
  @Test
  public void test(){
  LamTest lamTest=()->System.out.println("lambda的无参数无返回格式");
          lamTest.T();
  }
  ```

  + 2.一个参数，无返回值

  ```java
  @FunctionalInterface
  public interface LamTest {
      public void T(String str);
  }
  
  @Test
  public void test(){
   LamTest lamTest=(x)->System.out.println(x);
          lamTest.T("hello");
  }
  ```

  + 3.多个参数，带有返回值

  ```java
  @FunctionalInterface
  public interface LamTest {
      public StringBuilder T(String str,String str2);
  }
  @Test
  public void test(){
  LamTest lamTest=(x,y)->{
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(x).append(y);
              return stringBuilder;
          };//有多条语句是使用大括号括起来，使用return返回目标值
  LamTest lamTest1 = (x, y) -> new StringBuilder().append(x).append(y);//单条语句时，可以省略return
          System.out.println(lamTest.T("zhou","真"));
          System.out.println(lamTest1.T("zhou","假"));
  }
  ```

  + 注意事项：
    + lambda表达式与匿名内部类一样，可以调用，但是不可以对局部变量进行改变，虽没有显式声明，但是java8中默认将表达式中使用的局部变量设置为了final。
    + @FunctionalInterface是用来检验是否为函数式接口(只存在一个抽象方法的接口)
    + lambda表达式不需要声明参数类型，jvm使用"类型推断"可自动判断参数类型，如果声明，则所有参数需要声明类型
    + 当参数列表只有一个参数传入时候，可以省略括号，但不建议这样做

  lambda表达式与匿名内部类示例

  ​	函数式接口

  ```java
  @FunctionalInterface
  public interface Mypredict<T> {
      boolean test(T t);
  }
  ```

  

  ```java
  public void test() {
          //单条语句带返回值
          dodo(employees, (e) -> e.getWage() > 5000).stream().forEach(System.out::println);
          //多条语句带返回值
          dodo(employees, (e) -> {
              boolean b = e.getWage() > 5000;
              return b;
          }).stream().forEach(System.out::println);
          //匿名内部类
          dodo(employees, new Mypredict<Employee>() {
              @Override
              public boolean test(Employee employee) {
                  return employee.getWage()>5000;
              }
          }).stream().forEach(System.out::println);
      }
  	//具体方法
      public static List<Employee> dodo(List<Employee> list, Mypredict<Employee> me) {
          List<Employee> employees = new ArrayList<>();
          for (Employee employee : list) {
              if (me.test(employee)) {
                  employees.add(employee);
              }
          }
          return employees;
      }
  ```

  

  #### Java8提供的四个核心函数式接口：

  1. Comsumer<T> :	消费型接口

     ​	void accept(T t);

  2. Supplier<T>:  供给型接口

     ​	T get();

  3. Functional<T,R> : 函数型接口

     ​     	R apply(T t);

  4. BiPredicate<T>: 断言型接口

     ​	boolean test(T t);

#### lambda表达式方法引用

 如果lambda表达式的内容已经有现有方法实现，可以使用"方法引用"，调用现有的方法

 语法格式：

 1. 对象::实例方法名

    ```java
      		//before
            Consumer com=x->System.out.println(x);
            com.accept("hello");
            //对象::实例方法名
            PrintStream ps = System.out;
            Consumer con = ps::println;//System.out::println
            con.accept("hello");
    ```

    

2. 类::静态方法名

   ```java
   		 //before
           Comparator<Integer> comparator = (x, y) -> Integer.compare(x,y);
           System.out.println(comparator.compare(1, 3));
           //类::静态方法名
           Comparator<Integer> comparator1 = Integer::compareTo;
           comparator1.compare(1, 3);
   ```

3. 类::实例方法名(要求参数列表中的第一个参数需要是方法的调用者，另一个参数为被调用的方法所需的参数传入)

   ```java
   		//before
           BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
           System.out.println(biPredicate.test("heel", "sdad"));
           //after
           BiPredicate<String, String> biPredicate1 = String::equals;
           System.out.println(biPredicate.test("heel", "sdad"));
   ```

4. 构造器引用(构造器的参数列表需要与函数式接口中的抽象方法列表一致)

   无参时：

   ```java
    Supplier<Employee> supplier = Employee::new;
     System.out.println(supplier.get().getClass().getName());
   ```

   有参时(需要目标类提供带参构造器)：

   ```java
   		//before
           Function<String,Employee> employeeFunction=(x)->new Employee(x);
           System.out.println(employeeFunction.apply("name"));
           //构造引用
           Function<String, Employee> employeeFunction1 = Employee::new;
           System.out.println(employeeFunction1.apply("TTTT"));
   ```

   

5. 数组引用：

   ```java
     //before
           Function<Integer, String[]> function = (x) -> new String[x];
           System.out.println(function.apply(6).length);
           //数组引用
           Function<Integer, String[]> function1 = String[]::new;
           ps.println(function1.apply(10).length);
   ```

   

### Stream()API

示例

```java
static List<Employee> employees = Arrays.asList(
            new Employee("张三", 12, 5000),
            new Employee("李四", 20, 6000),
            new Employee("王二", 37, 4000),
            new Employee("周五", 19, 3000),
            new Employee("今天", 80, 9000)
    );
@Test
public void test(){
	employees.stream().filter((e) -> e.getWage() > 7000).forEach(System.out::println);
}
```



+ Stream流的创建：

1. 通过Collection系列的集合创建:

   ```java
    List<String> a = new ArrayList<String>();
           a.add("aa");
           a.add("bb");
           a.add("cc");
           Stream stream =a.stream();
   ```

   

2. 通过Arrays中的静态方法stream()获取数组流

   ```java
   Stream<String> stream1 = Arrays.stream(new String[10]);
   ```

3. 通过Stream中的静态方法of:

   ```java
   Stream<String> stream1 = Arrays.stream(new String[10]);
   ```

4. 创建无限流

   1. 迭代

   ```java
   Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2);
   ```

   2. 生成

   ```java
    Stream<Double> stream4 = Stream.generate(Math::random);//方法引用
    Stream<Double> stream5 = Stream.generate(() -> Math.random());//方法调用
   ```

   

+ Stream流的中间操作(多个中间操作可称为流水线操作，只有当终止操作存在时才会触发中间操作，“惰性求值

  ”)，生成新的流

  + filter(Predicate p)
  + distinct()筛选，去除掉重复元素(需要在源元素中重写hashcode()和equals()操作)
  + limit(n)取流的前n个元素
  + skip(n)与limit()互补，丢弃流的前n个元素，当流中元素不足n时，返回空流。





+ Stream流中的map映射（将流中的每一个元素变成一个新的流，再将这些流放入一个流Stream<Stream<T>>）：

  ```java
  @Test
  public void T{
  List<String> a = new ArrayList<String>();
          a.add("aaa");
          a.add("bbb");
          a.add("ccc");
        Stream<Stream<Character>> streamStream= a.stream().map(Test::d);
          streamStream.forEach(e -> {
              e.forEach(System.out::println);
          });
  }
  public static Stream<Character> d(String string) {
          List<Character> characters = new ArrayList<>();
          for (Character c : string.toCharArray()) {
              characters.add(c);
          }
          return characters.stream();
      }
  ```

+ Stream流中的flatmap映射(将所有的元素按照需求变更后重新放置再一个流中Stream<T>):

  ```java
  
  @Test
  public void T{
  List<String> a = new ArrayList<String>();
          a.add("aaa");
          a.add("bbb");
          a.add("ccc");
      
  Stream<Character> characterStream=a.stream().flatMap(e->{
             List<Character> characters = new ArrayList<>();
             for (Character character : e.toCharArray()) {
                 characters.add(character);
             }
             return characters.stream();
         });
         characterStream.forEach(x->System.out.println(x));
  }
  ```



+ Stream的查找与匹配
  1. allMatch--检查是否匹配所有元素
  2. anyMatch--检查是否至少匹配一个元素
  3. noneMatch--检查是否没有匹配所有元素
  4. findFirst--返回第一个元素
  5. count--返回流中的元素的总个数
  6. max--返回流中的最大值
  7. min--返回流中最小值

+ Strem规约(通过将流中的元素反复结合返回一个T/Optional<T>)

  + reduce(T iden,BinaryOperator b)

  ```java
  //计算平方和
  List<Integer> nums = new ArrayList<>();
          nums.add(2);
          nums.add(4);
          nums.add(3);
          nums.add(7);
          Integer reduce = nums.stream().reduce(0, (x, y) -> x + y*y);//0相当于初始值x
          System.out.println(reduce);
  ```

  + reduce(BinaryOperator b)

  ```java
  //计算总和 
  Optional<Integer> reduce1 = nums.stream().reduce(Integer::sum);
          System.out.println(reduce1);
  ```

  

+ Stream的收集collect(将流转换成其他形式，接受一个Collector接口的实现（一般使用Collectors工具类及其静态方法)用于给Stream中元素做汇总)

+ Stream的分组，通过collect(Collectors.groupingBy())来进行分组，返回一个Map类型



## 奇淫巧计

+ 排序输出

```java
IntStream.of(nums).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

```



