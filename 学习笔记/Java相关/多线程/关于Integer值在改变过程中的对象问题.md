---
title: 关于Integer值在改变过程中的对象问题
date: 2019-11-14 15:12:57
tags: 多线程 Integer
---

发现了这样一段代码，没有按照预定想法进行输出：

```java
public class Demo implements Runnable {

    private Integer a = 0;
    @Override
    public void run() {
        for (int j = 0; j < 1_000_000; j++) {
            synchronized (a) {
                a++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(demo.a);
    }
}
//output
1145098
```

一开始想是不是因为**synchronized**住了对象a本身,然后又对a进行了修改，这样的操作会导致原子性丢失。于是尝试了将a放入一个单独的对象。代码如下：

```java
public class Demo implements Runnable {

    //    private Integer a = 0;
    private A a = new A();

    static class A {
        public int value = 0;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1_000_000; j++) {
            synchronized (a) {
                a.value++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(demo.a.value);
    }
}

//output
200000
```

神奇的是代码好像正常运行了，那么问题是出现在哪儿呢？

```java
String a=new String("Hello World");
```

上面这段代码到底创建了几个对象？这是一个很经典的问题？

+ 当JVM常量池中不存在“Hello world”常量时，该语句创建了两个对象
  + 在常量池创建“Hello world”对象
  + 在堆中创建new String()对象，并从常量池取值到new String中，完成赋值。
+ 当常量池中已经存在“Hello world”时：
  + 在堆中创建new String()对象，并从常量池取值到new String中，完成对象创建。

那这段代码创建的一个对象或者两个对象跟这次遇到的多线程问题有关吗？

+ 在JVM中所有的基本数据类型的包装类型都拥有自己的常量池( Byte,Short,Integer,Character,Long ),该常量池仅缓存**-128-127**之间的值，也就是说，当创建的值是在该范围中，并且修改的操作不超过该范围，则对象依然是同一个对象，只是对象的值发生了改变。

+ 在这个情景中：

  + Integer 的对象自增（or 其他运算）后还是同一个对象吗？
    当声明一个Integer类型的变量时，例如Integer a = 200;：

    当对 a 进行运算时，如果其结果值总是在 [-128, 127] 之间时，这个值会直接从一个缓存数组中取出，这时取出来的都是同一个对象，而不会重新创建一个新的对象。

    如果运算后的结果超出了这个范围，就会每次重新创建一个对象出来，新创建的两个对象也肯定是不一样的。

  + 所以虽然对象a被**synchronized**住了，但是a的对象本身发生了改变，简而言之，两个线程在进行不同的操作时锁定的不是同一个对象，就会发生原子性问题。

