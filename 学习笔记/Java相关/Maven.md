+ <option>:<optional>true</optional>表示两个项目之间依赖不传递；不设置optional或者optional是false，表示传递依赖。

+ <scope>:

  + ## scope的分类

    ### compile（编译范围）

    **默认就是compile**，什么都不配置也就是意味着compile。compile表示被依赖项目需要参与当前项目的编译，当然后续的[测试](http://lib.csdn.net/base/softwaretest)，

    运行周期也参与其中，是一个比较强的依赖。打包的时候通常需要包含进去。

    ### test**（测试范围）**

    scope为test表示依赖项目仅仅参与测试相关的工作，包括测试代码的编译，执行。比较典型的如junit。

    PS: test表示只能在src下的test文件夹下面才可以使用，你如果在a项目中引入了这个依赖，在b项目引入了a项目作为依赖，在b项目中这个注解不会生效，因为scope为test时无法传递依赖。

    ### runntime**（运行时范围）**

    runntime表示被依赖项目无需参与项目的编译，不过后期的测试和运行周期需要其参与。与compile相比，**跳过编译**而已，

    说实话在终端的项目（非开源，企业内部系统）中，和compile区别不是很大。比较常见的如JSR×××的实现，对应的API jar是compile的，

    具体实现是runtime的，compile只需要知道接口就足够了。[Oracle](http://lib.csdn.net/base/oracle) jdbc驱动架包就是一个很好的例子，一般scope为runntime。

    另外runntime的依赖通常和optional搭配使用，optional为true。我可以用A实现，也可以用B实现。

    ### provided**（已提供范围）**

    provided意味着打包的时候可以不用包进去，别的设施(Web [Container](http://lib.csdn.net/base/docker))会提供。事实上该依赖理论上可以参与编译，测试，运行等周期。相当于compile，但是在打包阶段做了exclude的动作。

    例如， 如果你开发了一个web 应用，你可能在编译 classpath 中需要可用的Servlet API 来编译一个servlet，但是你不会想要在打包好的WAR 中包含这个Servlet API；

    这个Servlet API JAR 由你的应用服务器或者servlet 容器提供。已提供范围的依赖在编译classpath （不是运行时）可用。它们不是传递性的，也不会被打包。

    ### system**（系统范围）**

    system范围依赖与provided 类似，但是你必须显式的提供一个对于本地系统中JAR 文件的路径。这么做是为了允许基于本地对象编译，

    而这些对象是系统类库的一部分。这样的构件应该是一直可用的，Maven 也不会在仓库中去寻找它。如果你将一个依赖范围设置成系统范围，

    你必须同时提供一个 systemPath 元素。注意该范围是不推荐使用的（你应该一直尽量去从公共或定制的 Maven 仓库中引用依赖）。

    ## scope的依赖传递

    A–>B–>C。当前项目为A，A依赖于B，B依赖于C。知道B在A项目中的scope，那么怎么知道C在A中的scope呢？答案是： 
    当C是test或者provided时，C直接被丢弃，A不依赖C； 
    否则A依赖C，C的scope继承于B的scope。