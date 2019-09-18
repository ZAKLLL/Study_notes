# Kotlin作用域函数

作用域函数，Kotlin在语法层面支持拓展函数，作用域函数，作用域函数是指对数据做一些变换的函数，与集合的操作符很相似，但集合的操作符只能作用域集合对象，而作用域函数可以操作任何对象。

Kotlin在语法层面为我们提供了: `let`, `run`, `with`, `apply`, and `also`几个作用域函数

展示一个作用域函数let：

```java
data class Person(var name: String, var age: Int, var city: String) {
    fun moveTo(newCity: String) { city = newCity }
    fun incrementAge() { age++ }
}

fun main() {
    Person("Alice", 20, "Amsterdam").let {
        println(it)
        it.moveTo("London")
        it.incrementAge()
        println(it)
    }
}
```

如上所示我们向let方法中传入一个lambda表达式，值得注意的是我们在这里使用了一个it关键字，我们先暂时放下这个it关键字来试试假如不试用let方法想要获取一样的结果的方式

```kotlin
data class Person(var name: String, var age: Int, var city: String) {
    fun moveTo(newCity: String) { city = newCity }
    fun incrementAge() { age++ }
}

fun main() {
    val alice = Person("Alice", 20, "Amsterdam")
    println(alice)
    alice.moveTo("London")
    alice.incrementAge()
    println(alice)
}
```

可以看到在后者中我们每当对对象进行一次操作时，都需要传入对象的声明（对象名称)，而在前面的代码中我们使用it替代了调用者Person()对象本身。当然也可以将对象声明为x或者任何你想要的名字

```kotlin
 Person("Alice", 20, "Amsterdam").let { x->
        println(x)
        x.moveTo("London")
        x.incrementAge()
        println(x)
    }
这样看起来就跟java中的lambda表达式十分相似了。
```

那么问题来了,如何区分或者该在什么时候调用这些作用域函数呢，毕竟他们看起来都差不多

### Kotlin官方文档为我们提供了两种区分方式：

+ 引用上下文的方式(**it**还是**this**)
+ 函数的返回类型



###  先来看看it和this的区别:

```kotlin
fun main() {
    val str = "Hello"
    // this
    str.run {
        println("The receiver string length: $length")
        //println("The receiver string length: ${this.length}") // does the same
        //在这段代码中this指的是调用者本身(即lambda函数接受者)
    }

    // it
    str.let {
        println("The receiver string's length is ${it.length}")
        //这里的it值的是将调用者作为lambda函数所需参数传入，也可以写成
        println(x->"The receiver string's length is ${x.length}")
        
    }
}
```

通过上面的代码可以得知

* 不同：
     *      let有闭包参数，run没有闭包参数
     *      let的闭包参数就是调用者本身，参数名为it
     *      run没有闭包参数，可以使用this指代调用者
     *      注：在let中，不可以使用this指代调用者

这里列出分别使用This作为调用者以及it作为闭包参数的方法

+ this
  + run
  + with<T>
  + apply
  + 值得注意的是，大多数时候this可以被省略，但如果这样的做法影响了代码可读性的话，建议使用this.
+ it
  + let
  + also

### 通过返回类型

+ 返回 上下文本身(指调用者)
  + apply
  + also
+ 返回lambda函数的执行结果
  + run
  + let
  + with

+ demo1:返回上下文对象

```kotlin
fun main() {
    val numberList = mutableListOf<Double>()
    numberList.also { println("Populating the list") }
            .apply {
                this.add(2.71)
                add(3.14)  //省略this
                add(1.0)
            }
            .also { println("Sorting the list") }
            .sort()
    println(numberList)
}
//查看一下also与apply的源代码
public inline fun <T> T.also(block: (T) -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block(this)
    return this
}
public inline fun <T> T.also(block: (T) -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block(this)
    return this
}
```

显而易见，使用apply或者also都可以返回调用者本身，使用这样的作用域函数可以非常方便的进行链式编码

+ dem02：返回lambda表达式结果

```kotlin
val numbers = mutableListOf("one", "two", "three")
    val countEndsWithE = numbers.run { 
        add("four")
        add("five")
        count { it.endsWith("e") }
    }
    println("There are $countEndsWithE elements that end with e.")
//看代码大家都可以知道输出结果为3
//即countEndsWithE的数据类型为Int，这是因为返回了lambda表达式中count的返回类型
public inline fun <T, R> T.run(block: T.() -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}
```

当然也可以选择无返回值(并不是没有执行结果)

```kotlin
fun main() {
    val numbers = mutableListOf("one", "two", "three")
    with(numbers) {
        val firstItem = first()
        val lastItem = last()        
        println("First item: $firstItem, last item: $lastItem")
    }
}
```

### 作用域函数的选择

参考kotolin官方文档：

| 函数    | 引用方式 | Return value   | 是否为拓展函数                               |
| :------ | :------- | :------------- | :------------------------------------------- |
| `let`   | `it`     | Lambda result  | Yes                                          |
| `run`   | `this`   | Lambda result  | Yes                                          |
| `run`   | -        | Lambda result  | No: called without the context object        |
| `with`  | `this`   | Lambda result  | No: takes the context object as an argument. |
| `apply` | `this`   | Context object | Yes                                          |
| `also`  | `it`     | Context object | Yes                                          |

官方文档也建议你选择：

- Executing a lambda on non-null objects(当非空对象需要执行闭包时): `let`
- Introducing an expression as a variable in local scope(将表达式作为局部范围中的变量引入): `let`
- Object configuration(对对象进行参数配置时): `apply`
- Object configuration and computing the result(对对象进行配置并且需要得到执行结果): `run`
- Running statements where an expression is required: non-extension(代码运行时需要非拓展性的表达式) `run`
- Additional effects(添加附加效果): `also`
- Grouping function calls on an object(对函数进行分组调用时): `with`



***注意:***虽然作用域函数可以使我们的代码变得更简洁，并且使得链式编程变得更加简单，但是建议不要在代码中过度使用作用域函数，这可能会导致代码的可读性降低，注意尽量不要使用嵌套函数，并且在使用链式编程的时候要小心混淆**this**和**it**，它们是十分容易被混淆的，而这可能会带来十分多的问题。