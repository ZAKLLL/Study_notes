



### 使用@ConfigurationProperties

编写application.yml/application.properties文件(更改默认设置例如tomcat端口)

application.yml(冒号后一定要有空格，使用空格缩进，不要使用tab)

```yml
person:
    username: 周振昭
    age: 12
    birth: 2019/11/11
    map: {k1: jiji,k2: didi}
    list:
        - list1
        - list2
    dog:
     dogname: zhouzhou
     age: 12

```



```java
@Component
@ConfigurationProperties(prefix = "person")//对该javabean类进行注入
public class Person {
    private String username;
    private Integer age;
    private Date birth;
    private Map<String, String> map;
    private List<String> list;
    private Dog dog;

    
    class Dog{
        private String dogname;
        private Integer age;
    }
```

 ### @value注解(使用${spel}表达式)

```java
public class Person {
    @Value("${person.username}")
    private String username;
    private Integer age;
    private Date birth;
    private Map<String, String> map;
    private List<String> list;
    private Dog dog;

    
    class Dog{
        private String dogname;
        private Integer age;
    }
    
}
//--------或者如下

  @Value("${person.username}")
    private String name;
    @Test
    public void Val() {
        System.out.println(name);
    }   
```

### @**Configuration**(用来代替编写sprin.xml配置文件，SpringBoot推荐写法)

```java
@Configuration //声明该类为配置类，不用编写spring.xml配置文件了
public class MyConfig {

    @Bean   //类比为<beans>中<bean>的角色,bean中id即为方法名userService
    public UserService userService() {
        return new UserService();
    }
}

//测试---
@Autowired
    ApplicationContext ioc;

    @Test
    public void TestConfg() {
        System.out.println(ioc.containsBean("userService"));
    }
//output :true 证明该UserService已经以userService的id名放入了Spring容器
```

如果采用传统spring配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

 <bean id="helloService" class="com.atguigu.springboot.service.HelloService"></bean>
</beans>
```

并且要在一个配置类(主配置类)上添加@ImportResource注解

```java
@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class HelloworldApplication {
	  public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }

}
```

