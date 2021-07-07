## JVM 参数使用

+ -XX:+\<option> 表示开启option选项

+ -XX:-\<option> 表示关闭option选项

+ -XX:\<option>=\<value> 表示设定某个选项的值

+ -XX:+TraceClassLoading :表示监控类的加载





### 常用gc 参数(general gc && ZGC)

+ **heapSize设置指南**:	堆可以容纳你的应用程序的实时占用内存，且堆中有足够的余量，允许在GC运行时为分配进行服务。需要多少净空在很大程度上取决于应用程序的分配率和实时集的大小。一般来说，你给ZGC的内存越多越好。但同时，浪费内存也是不可取的，所以关键是要在内存使用和GC需要运行的频率之间找到一个平衡。

  + -XX:MinHeapSize, -Xms
  + -XX:InitialHeapSize, -Xms
  + -XX:MaxHeapSize, -Xmx
  + -XX:SoftMaxHeapSize

+ **并发GC线程数**: 这个选项本质上决定了应该给GC多少CPU时间。给它太多，GC会从应用中窃取过多的CPU时间。给得太少，应用程序分配垃圾的速度可能比GC收集垃圾的速度快。

  注意！一般来说，如果低延迟（即低应用响应时间）对你的应用很重要，那么永远不要过度配置你的系统。理想情况下，你的系统的CPU利用率不应超过70%。

  + -XX:ConcGCThreads
  + -XX:ParallelGCThreads

+ other:

  + -XX:UseLargePages
  + \-
  + -XX:UseTransparentHugePages
  + -XX:UseNUMA
  + \-
  + -XX:SoftRefLRUPolicyMSPerMB
  + -XX:AllocateHeapAt




## other

+ ZGC 文档: [Main - Main - OpenJDK Wiki (java.net)](https://wiki.openjdk.java.net/display/zgc/Main#Main-SupportedPlatforms)

  

## EG

![1568274341196](1568274341196.png)

**JVM参数设置**(FOR JDK8):

+ java -Xmx3550m -Xms3550m -Xmn 10 m -Xss128k -XX:SurvivorRatio=6
  + -Xmx3550m:设置JVM最大可用内存为3550M. 
  + -Xms3550m:设置JVM促使内存为3550m.此值可以设置与-Xmx相同,以避免每次垃圾回收完成后JVM重新分配内存. 
  + -Xmn 2g:设置年轻代大小为2G.
  + -Xss128k:设置每个线程的堆栈大小. 
  + -XX:SurvivorRatio=6:设置年轻代中Eden区与一个Survivor区的大小比值.设置为6,则总Survivor:Eden=2:6;一个Survivor区占整个年轻代的1/8=1.25;Eden区占3/4=7.5；
+ JVM 启动默认参数：-Xmx为物理内存的1/4，-Xms为物理内存的1/64，
+ jvm 调优文档:[Tuning Java Virtual Machines (JVMs) (oracle.com)](https://docs.oracle.com/cd/E21764_01/web.1111/e13814/jvm_tuning.htm#PERFM167)