# 拦截器

+ 日志log4j.properties

  ```properties
  log4j.rootCategory=INFO, stdout
  log4j.appender.stdout=org.apache.log4j.ConsoleAppender
  log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
  log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} %5p %t %c{2}:%L - %m%n
  log4j.category.org.springframework.beans.factory=DEBUG
  ```

+ 自定义拦截器

```java
public class MethodInterceptor implements HandlerInterceptor {
 private static final Logger logger=Logger.getLogger(MethodInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long start=System.currentTimeMillis();
        request.setAttribute("start",start);
        System.out.println("拦截器开始工作");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long end=System.currentTimeMillis();
        Long start = (Long) request.getAttribute("start");
        Long Usetime=end-start;
        System.out.println("用时"+Usetime);
        logger.info("方法耗时："+ Usetime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
```

+ 配置拦截器

```xml
<mvc:interceptors>
    <mvc:interceptor>
        <!-- 这里path是拦截uri请求 -->
        <mvc:mapping path="/**/*"/>
        <bean class="com.zakl.Interceptors.MethodInterceptor"></bean>
    </mvc:interceptor>
</mvc:interceptors>
```

​	注意mvc.xml配置顺序：

```xml
<mvc:default-servlet-handler></mvc:default-servlet-handler>
<mvc:annotation-driven></mvc:annotation-driven>
```

## 会话拦截器

拦截器配置：

```xml
<mvc:interceptor>
    <mvc:mapping path="/User/**/*"/>
    <!--不拦截uri为/User/login的访问-->
    <mvc:exclude-mapping path="/User/login" />
    <bean class="com.zakl.Interceptors.SessionInteceptor"/>
</mvc:interceptor>
```

拦截器实现：

```java
public class SessionInteceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger(SessionInteceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user =request.getSession().getAttribute("Session_user");
        if (user!=null){
            if (user instanceof User) {
                User u= (User) user;
                u.setPassword(null);
                request.getSession().setAttribute("Session_user",u);
                logger.info("用户名为:"+u.getUsername()+"已经登录，可以正常执行操作");
                return true;
            }else {
                logger.warn("狗粉丝，滚出克");
            }
        }
        return false;
    }
```

