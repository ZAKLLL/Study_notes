+ 事务的几种传播特性：
  + PROPAGATION_REQUIRED--支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。 
  + PROPAGATION_SUPPORTS--支持当前事务，如果当前没有事务，就以非事务方式执行。 
  + PROPAGATION_MANDATORY--支持当前事务，如果当前没有事务，就抛出异常。 
  + PROPAGATION_REQUIRES_NEW--新建事务，如果当前存在事务，把当前事务挂起。 
  + PROPAGATION_NOT_SUPPORTED--以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 
  + PROPAGATION_NEVER--以非事务方式执行，如果当前存在事务，则抛出异常。 

+ Spring的两种方式的事务管理：
  + **编程式事务管理：** 通过Transaction Template手动管理事务，实际应用中很少使用，
  + **使用XML配置声明式事务：** 推荐使用（代码侵入性最小），实际是通过AOP实现

+ Spring Mvc和struts2:
  	1. 机制: spring   mvc的入口是 servlet,而struts2是filter,这样就导致了二者的机制不同。
   	2. struts2有以自己的interceptor机制，spring mvc用的是独立的AOP方式。
   	3. spring mvc的方法之间基本上独立的，独享request response数据，struts2所有Action变量是共享的。
   	4. spring mvc是基于方法的设计，而struts2是基于类的设计。
   	5. 性能：spring会稍微比struts快。 spring mvc是基于方法的设计 ， 而sturts是基于类 ， 每次发一次请求都会实例一个action，每个action都会被注入属性，而spring基于方法，粒度更细

+ Spring 的scope作用域：

  +  在没有设值注入的情况下才会根据配置文件中的构造注入，一旦有设值注入，则构造注入失效
  + ![1569398227237](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1569398227237.png)

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

      

    