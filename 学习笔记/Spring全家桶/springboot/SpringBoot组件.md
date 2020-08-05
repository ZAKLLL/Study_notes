启动流程图：

![image-20200502142321553](../images/image-20200502142321553.png)



## ApplicationContext中央化组件作用：

+ 用于为应用程序提供配置的中央接口。当应用程序运行时，这个接口是只读的，但如果对接口实现的话，可以重新加载。
  一个 ApplicationContext 提供了：
  + 用于访问应用程序组件的Bean工厂方法。继承自ListableBeanFactory。
  + 以通用方式加载文件资源的能力。继承自org.springframework.core.io.io.ResourceLoader接口。
  + 向注册的监听器发布事件的能力。继承自 ApplicationEventPublisher 接口。
  + 解析消息的能力，支持国际化。继承自MessageSource接口。
  + 从父上下文继承。子类将始终具有优先权。这意味着，例如，一个父上下文可以被整个Web应用程序使用，而每个servlet都有自己的子上下文，它独立于任何其他servlet的子上下文。

## ConfigurableApplicationContext

+ SPI接口，大多数（如果不是所有的应用上下文都能实现。除了ApplicationContext接口中的应用上下文客户端方法之外，还提供了配置应用上下文的设施。
  配置和生命周期方法在这里被封装起来，以避免对ApplicationContext客户端代码造成明显的影响。目前的方法只应该由启动和关闭代码使用。

## SpringBootExceptionReporter

+ ```java
  	/**
  	 * Report a startup failure to the user.
  	 * @param failure the source failure
  	 * @return {@code true} if the failure was reported or {@code false} if default
  	 * reporting should occur.
  	 */
  	boolean reportException(Throwable failure);
  ```

+ 回调接口用于支持自定义的SpringApplication启动错误的报告。

## SpringApplicationListener

+ 用于SpringApplication运行方法的监听器。SpringApplicationRunListeners通过SpringFactoriesLoader加载，并且应该声明一个公共构造函数，该构造函数接受一个SpringApplication实例和一个String[]参数。每次运行时都会创建一个新的SpringApplicationRunListener实例。