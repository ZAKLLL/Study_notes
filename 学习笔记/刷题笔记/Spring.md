+ Spring 模块：
  
  + 1. Spring Core： Core封装包是框架的最基础部分，提供IOC和依赖注入特性。这里的基础概念是BeanFactory，它提供对Factory模式的经典实现来消除对程序性单例模式的需要，并真正地允许你从程序逻辑中分离出依赖关系和配置。
  
       2.Spring Context: 构建于[Core](http://www.mianwww.com/html/2014/03/19750.html#beans-introduction)封装包基础上的 [Context](http://blog.chinaunix.net/u/9295/ch03s08.html)封装包，提供了一种框架式的对象访问方法，有些象JNDI注册器。Context封装包的特性得自于Beans封装包，并添加了对国际化（I18N）的支持（例如资源绑定），事件传播，资源装载的方式和Context的透明创建，比如说通过Servlet容器。
  
       3．Spring DAO:  [DAO](http://www.mianwww.com/html/2014/03/19750.html#dao-introduction) (Data Access Object)提供了JDBC的抽象层，它可消除冗长的JDBC编码和解析数据库厂商特有的错误代码。 并且，JDBC封装包还提供了一种比编程性更好的声明性事务管理方法，不仅仅是实现了特定接口，而且对所有的POJOs（plain old Java objects）都适用。

       4.Spring ORM: [ORM](http://www.mianwww.com/html/2014/03/19750.html#orm-introduction) 封装包提供了常用的“对象/关系”映射APIs的集成层。 其中包括[JPA](http://blog.chinaunix.net/u/9295/ch12s07.html)、[JDO](http://blog.chinaunix.net/u/9295/ch12s03.html)、[Hibernate](http://blog.chinaunix.net/u/9295/ch12s02.html) 和 [iBatis](http://blog.chinaunix.net/u/9295/ch12s06.html) 。利用ORM封装包，可以混合使用所有Spring提供的特性进行“对象/关系”映射，如前边提到的简单声明性事务管理。
  
       5.Spring AOP: Spring的 [AOP](http://www.mianwww.com/html/2014/03/19750.html#aop-introduction) 封装包提供了符合AOP Alliance规范的面向方面的编程实现，让你可以定义，例如方法拦截器（method-interceptors）和切点（pointcuts），从逻辑上讲，从而减弱代码的功能耦合，清晰的被分离开。而且，利用source-level的元数据功能，还可以将各种行为信息合并到你的代码中。
  
       6.Spring Web: Spring中的 Web 包提供了基础的针对Web开发的集成特性，例如多方文件上传，利用Servlet listeners进行IOC容器初始化和针对Web的ApplicationContext。当与WebWork或Struts一起使用Spring时，这个包使Spring可与其他框架结合。
  
       7.Spring Web MVC: Spring中的[MVC](http://www.mianwww.com/html/2014/03/19750.html#mvc-introduction)封装包提供了Web应用的Model-View-Controller（MVC）实现。Spring的MVC框架并不是仅仅提供一种传统的实现，它提供了一种清晰的分离模型，在领域模型代码和Web Form之间。并且，还可以借助Spring框架的其他特性。
  
+ Spring事务的几种传播特性：
  
  + PROPAGATION_REQUIRED: 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。 
  + PROPAGATION_SUPPORTS: 支持当前事务，如果当前没有事务，就以非事务方式执行。 
  + PROPAGATION_MANDATORY: 支持当前事务，如果当前没有事务，就抛出异常。 
  + PROPAGATION_REQUIRES_NEW: 新建事务，如果当前存在事务，把当前事务挂起。 
  + PROPAGATION_NOT_SUPPORTED: 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 
  + PROPAGATION_NEVER: 以非事务方式执行，如果当前存在事务，则抛出异常。 
  
+ Spring的两种方式的事务管理：
  + **编程式事务管理：** 通过Transaction Template手动管理事务，实际应用中很少使用，
  + **使用XML配置声明式事务：** 推荐使用（代码侵入性最小），实际是通过AOP实现

+ Spring Mvc和struts2:
  	1. 机制: spring   mvc的入口是 servlet,而struts2是filter,这样就导致了二者的机制不同。
   	2. struts2有以自己的interceptor机制，spring mvc用的是独立的AOP方式。
   	3. spring mvc的方法之间基本上独立的，独享request response数据，struts2所有Action变量是共享的。
   	4. spring mvc是基于方法的设计，而struts2是基于类的设计。
   	5. 性能：spring会稍微比struts快。 spring mvc是基于方法的设计 ， 而sturts是基于类 ， 每次发一次请求都会实例一个action，每个action都会被注入属性，而spring基于方法，粒度更细

+ IOC和AOP

   + **IOC:** 控制反转也叫依赖注入。IOC利用java反射机制，AOP利用代理模式。IOC 概念看似很抽象，但是很容易理解。说简单点就是将对象交给容器管理，你只需要在spring配置文件中配置对应的bean以及设置相关的属性，让spring容器来生成类的实例对象以及管理对象。在spring容器启动的时候，spring会把你在配置文件中配置的bean都初始化好，然后在你需要调用的时候，就把它已经初始化好的那些bean分配给你需要调用这些bean的类。
   + **AOP：** 面向切面编程。（Aspect-Oriented Programming） 。AOP可以说是对OOP的补充和完善。OOP引入封装、继承和多态性等概念来建立一种对象层次结构，用以模拟公共行为的一个集合。实现AOP的技术，主要分为两大类：一是采用动态代理技术，利用截取消息的方式，对该消息进行装饰，以取代原有对象行为的执行；二是采用静态织入的方式，引入特定的语法创建“方面”，从而使得编译器可以在编译期间织入有关“方面”的代码，属于静态代理。

   

+ Spring 的scope作用域：

  + 在没有设值注入的	情况下才会根据配置文件中的构造注入，一旦有设值注入，则构造注入失效

  + | Scope                                                        | Description                                                  |
    | :----------------------------------------------------------- | :----------------------------------------------------------- |
    | [singleton](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-singleton) | (Default) Scopes a single bean definition to a single object instance for each Spring IoC container. |
    | [prototype](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-prototype) | Scopes a single bean definition to any number of object instances. |
    | [request](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-request) | Scopes a single bean definition to the lifecycle of a single HTTP request. That is, each HTTP request has its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring `ApplicationContext`. |
    | [session](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-session) | Scopes a single bean definition to the lifecycle of an HTTP `Session`. Only valid in the context of a web-aware Spring `ApplicationContext`. |
    | [application](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-application) | Scopes a single bean definition to the lifecycle of a `ServletContext`. Only valid in the context of a web-aware Spring `ApplicationContext`. |
    | [websocket](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp-websocket-scope) | Scopes a single bean definition to the lifecycle of a `WebSocket`. Only valid in the context of a web-aware Spring `ApplicationContext`. |

  + ![1569398227237](../../images/1569398227237.png)

+ Spring DI的两种方式：

  + 设置注入：

    + ```xml
      <!-- Spring IOC注入方式: 设值注入 -->
      <bean id="injectionService" class="com.service.InjectionServiceImpl">
          <property name="injectionDAO" ref="injectionDAO"></property>
          </bean>
      <bean id="injectionDAO" class="com.dao.InjectionDAOImpl"></bean>
      ```

    + ```java
      public class InjectionServiceImpl{
          private InjectionDAO injectionDAO;
      }
      ```

  + 构造注入：

    + ```xml
      <!-- Spring IOC注入方式: 构造注入 -->
      <bean id="injectionService" class="com.service.InjectionServiceImpl">
          	<constructor-arg name="injectionDAO" ref="injectionDAO"></constructor-arg>
      </bean> 
      <bean id="injectionDAO" class="com.dao.InjectionDAOImpl"></bean>
      ```

    + ```java
      public class InjectionServiceImpl{
          private InjectionDAO injectionDAO;
          public InjectionServiceImpl(InjectionDAO injectionDAO){
              this.injectionDAO=injectionDAO;
          }
      }
      ```

  + Spring 开启cgLIb：
  
    + ```xml
      <aop:aspectj-autoproxy proxy-target-class="true"/>
      ```
