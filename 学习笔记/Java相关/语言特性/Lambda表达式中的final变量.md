+ lambda表达式访问实例变量(可正常编译)

  ```java
      int a = 0;
      public void test1() {
          Consumer<Integer> consumer = i -> a++;
      }
  ```

+ lambda表达式访问局部变量(**Variable used in lambda expression should be final or effectively final**)

  ```java
      public void test1() {
          int a = 0;
          Consumer<Integer> consumer = i -> a++;
      }
  ```





+ 解释:

  The fundamental difference between a field and a local variable is that the local variable is copied when JVM creates a lambda instance. On the other hand, fields can be changed freely, because the changes to them are propagated to the outside class instance as well (their scope is the whole outside class, as Boris pointed out below).

  The easiest way of thinking about anonymous classes, closures and labmdas is from the variable scope perspective; imagine a copy constructor added for all local variables you pass to a closure.



+ #### Variable Capture

  + 变量捕获使lambdas可以使用在lambda本身之外声明的变量。

    有三种非常相似的变量捕获类型。

    局部变量捕获:  local variable capture

    实例变量捕获: instance variable capture
    静态变量捕获: static variable capture

    

  + 只有当一个局部变量是有效的final时，你才能访问它，这意味着它在赋值后不会改变其值。它不一定要明确声明为final，但建议这样做以避免混淆。如果你在一个lambda函数中使用它，然后改变它的值，编译器就会开始抱怨。

    你不能这样做的原因是lambda不能可靠地引用局部变量，因为它可能在你执行lambda之前被销毁。正因为如此，它做了一个深度拷贝。改变局部变量可能会导致一些混乱的行为，因为程序员可能期望λ内的值发生变化，所以为了避免混乱，明确禁止这样做。

    当涉及到实例变量时，如果你的lambda与你要访问的变量在同一个类中，你可以简单地使用this.field来访问该类中的一个字段。而且，这个字段不一定是最终的，可以在以后的程序过程中改变。

    这是因为如果一个lambda被定义在一个类中，它就会和该类一起被实例化，并与该类实例绑定，因此可以很容易地引用它所需要的字段的值。

    静态变量和实例变量一样被捕获，只是你不会用这个来引用它们。它们可以被改变，并且由于同样的原因不需要是最终的。

  + 参考来源 [Lambda Expressions in Java (stackabuse.com)](https://stackabuse.com/lambda-expressions-in-java/)