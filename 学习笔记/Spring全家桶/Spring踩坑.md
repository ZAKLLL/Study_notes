+ 时间慢8小时：时区设置问题

  + 在配置文件中按照如下方案解决

  + ```properties
    spring.jackson.time-zone=GMT+8
    ```





+ 关于**@PostConstruct**与**ApplicationContextAware**的冲突问题

+ 现在有这样一个业务代码

  ```java
  @Component
  public class SpringContextUtil implements ApplicationContextAware {
  
      private static ApplicationContext context;
  
      public void setApplicationContext(ApplicationContext applicationContext)
              throws BeansException {
          this.context = applicationContext;
      }
  
      /**
       * 根据bean的name来获取bean
       *
       * @param beanName
       * @return
       */
      public static Object getBean(String beanName) {
          return context.getBean(beanName);
      }
      public static <T> T getBean(Class<T> clazz) {
          return context.getBean(clazz);
      }
      /**
       * 获取spring上下文
       *
       * @return
       */
      public static ApplicationContext getContext() {
          return context;
      }
  }
  
  ```

  

  ```java
  @Component
  @Slf4j
  public class Demo {
  
      @PostConstruct
      public void test() {
          SpringContextUtil.getBean("JedisPool")
      }
  }
  
  ```

  当SpringBoot应用启动时，报执行到```        SpringContextUtil.getBean("JedisPoo")```报<font color='red'>NPE</font>,经过排查为SpringContextUtil中的context为null,原因是Spring容器在等待@PostConstruct所在注解类加载完毕，才会执行调用**ApplicationContextAware.setApplicationContext**注入Spring上下文，则意味着此时的**SpringContextUtil**这个bean还没有被Spring容器管理。

  解决方案：

  1. 优选于Demo类加载SpringContexUtil

     ```java
     @Component
     @Slf4j
     @DependsOn("springContextUtil")
     public class Demo {
         @PostConstruct
         public void test() {
             SpringContextUtil.getBean("JedisPool")
         }
     }
     
     ```

  2. 不要在@PostConstruct方法中执行这种bean调用涉及到加载顺序且较为复杂的方式

     ```java
     @Component
     @Slf4j
     public class Demo {
         @PostConstruct
         public void test() {
             //SpringContextUtil.getBean("JedisPool")
         }
     }
     
     ```




+ 本地开发可以访问resources下的静态资源，打包之后无法正常访问:

  ```java
  //本地开发可以访问,是因为本地使用文件类型进行静态资源访问.
  File file=new ClassPathResource("template/xxx.xlsx").getFile();
  
  //打包成jar之后需要以下形式访问,因为目标文件是被压缩在jar包之中，不能通过访问文件的方式进行访问
  InputStream inputStream = new ClassPathResource("template/xxx.xlsx").getInputStream();
  
  ```

  

