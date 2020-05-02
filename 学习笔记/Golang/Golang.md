# Golang 基础

+ Go的指针:

```go
package main
import "fmt"

func main(){
    a:=10
    var b *int //声明指针变量
    b=&a //将变量对应的内存地址传递给b

    fmt.Println("&a变量的地址为",&a)

    fmt.Println("*&的值为",*&a)

    fmt.Println("指针存储的地址b-->:",b)

    fmt.Println("*b的变量值-->:",*b)
}
//output：
// &a变量的地址为---> 0xc0000160a8
// *&的值为 -----> 10
// 指针存储的地址b-->: 0xc0000160a8
// *b的变量值-->: 10
```



+ 使用copy/append进行元素删除

  + **删除切片元素**

    根据要删除元素的位置有三种情况：从开头位置删除，从中间位置删除，从尾部删除。其中删除切片尾部的元素最快：

    ```go
    a = []int{1, 2, 3}
    a = a[:len(a)-1]   // 删除尾部1个元素
    a = a[:len(a)-N]   // 删除尾部N个元素
    ```

    删除开头的元素可以直接移动数据指针：

    ```go
    a = []int{1, 2, 3}
    a = a[1:] // 删除开头1个元素
    a = a[N:] // 删除开头N个元素
    ```

    删除开头的元素也可以不移动数据指针，但是将后面的数据向开头移动。可以用`append`原地完成（所谓原地完成是指在原有的切片数据对应的内存区间内完成，不会导致内存空间结构的变化）：

    ```go
    a = []int{1, 2, 3}
    a = append(a[:0], a[1:]...) // 删除开头1个元素
    a = append(a[:0], a[N:]...) // 删除开头N个元素
    ```

    也可以用`copy`完成删除开头的元素：

    ```go
    a = []int{1, 2, 3}
    a = a[:copy(a, a[1:])] // 删除开头1个元素
    a = a[:copy(a, a[N:])] // 删除开头N个元素
    ```

    对于删除中间的元素，需要对剩余的元素进行一次整体挪动，同样可以用`append`或`copy`原地完成：

    ```go
    a = []int{1, 2, 3, ...}
    
    a = append(a[:i], a[i+1:]...) // 删除中间1个元素
    a = append(a[:i], a[i+N:]...) // 删除中间N个元素
    
    a = a[:i+copy(a[i:], a[i+1:])]  // 删除中间1个元素
    a = a[:i+copy(a[i:], a[i+N:])]  // 删除中间N个元素
    ```

    删除开头的元素和删除尾部的元素都可以认为是删除中间元素操作的特殊情况。

+  **...** 解包关键词(将array进行解包操作)：

  + ```go
    func mySum(args ...int) int {
    	sum := 0
    	for i := range args {
    		sum += i
    	}
    	return sum
    }
    func main() {
    	a := []int{1, 2, 3, 4, 5, 6, 7}
    	fmt.Print(mySum(a...))
    }
    ```

+ 参数传递方式:Golang和java一样都是只有值传递，函数接受的指针类型参数也是原指针地址的拷贝。

+ **defer** 关键词：
  + 当defer被声明时，其参数会被实时解析：
    + defer 表达式中的参数值是在defer表达式被定义时候的参数值，而不是被执行时候的参数值。

+ **go**关键词：

  + go关键字用来创建goroutine(协程),是实现并发的关键

  + ```go
    //go 关键字放在方法调用前新建一个 goroutine 并让他执行方法体
    go GetThingDone(param1, param2);
    
    //上例的变种，新建一个匿名方法并执行
    go func(param1 type, param2 type) { //(param1和param2是函数定义时的参数声明)
    }(val1, val2) //val1 val2 为传入的实际传入的参数
    
    //直接新建一个 goroutine 并在 goroutine 中执行代码块
    go {
        //do someting...
    }
    ```
  
+ **init()**函数:

  + 用来初始化信息。先于main（）函数执行
  + 当包被调用时，首先调用包内所有的init()函数，init()函数可以重复多次定义，但无参数无返回值，一个go文件中可以有多个init函数，调用顺序由编写顺序决定，一个package中可以有多个init()函数，调用顺序由该包下的go文件名顺序决定。

+ 空白标志符_ : 
  
+ **_**  这个符号可以 用来丢弃返回结果，或者用于仅调用导入包中的init（）函数。
  
+ 时间格式化：

  + ```go
    now := time.Now()
    s1 := now.Format("2006年1月2日 15:04:05") //时间转换模板，必须是这个时间
    										//也可以写成2006/01/02  2006-1-2
    ```

+ 查看对象数据类型：

  + fmt.Println("%T\n",target)
  + fmt.Println(reflect.typeOf(target))
