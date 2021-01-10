# Server.xml配置详解

```xml
<?xml version="1.0" encoding="UTF-8"?>
 <!-- 表示整个servlet容器,tomcat运行环境中只有唯一一个Server实例 -->
<Server port="8005" shutdown="SHUTDOWN">
  <Listener className="org.apache.catalina.startup.VersionLoggerListener" />
  <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" />
  <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" />
  <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" />
  <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener" />


  <GlobalNamingResources>
    <Resource name="UserDatabase" auth="Container"
              type="org.apache.catalina.UserDatabase"
              description="User database that can be updated and saved"
              factory="org.apache.catalina.users.MemoryUserDatabaseFactory"
              pathname="conf/tomcat-users.xml" />
  </GlobalNamingResources>

   <!-- 一个或多个Connector的集合,这些connector共享同一个Container 来处理其请求.在同一个Tomcat实例内可以包含任意多个Service实例,他们互相独立 -->
  <Service name="Catalina">
      
      
   	<!--Connector 可以共享的线程池,可以定定义一个或者自定义多个-->
    <!--
    <Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
        maxThreads="150" minSpareThreads="4"/>
    -->

    <!-- tomcat连接器，用以监听并转换Socket请求,同时将读取的Socket请求交由Container处理，支持不同协议及不同的I/O方式 -->
    <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />

    <!-- 表示整个Servlet容器,在tomcat中，Engine为最高层级的容器对象.尽管Engine不是直接处理请求的容器，却是获取目标容器的入口 -->
    <Engine name="Catalina" defaultHost="localhost">
      <Realm className="org.apache.catalina.realm.LockOutRealm">
        <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
               resourceName="UserDatabase"/>
      </Realm>
     
     <!-- Host 作为一类容器,用于表示ServletContex,在Servlet规范中，一个ServletContext即表示一个独立的Web应用 -->
      <Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">

        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log" suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />
      </Host>
    </Engine>
  </Service>
</Server>

```

| 组件      | 作用                                                         |
| --------- | ------------------------------------------------------------ |
| Server    | 表示整个servlet容器,tomcat运行环境中只有唯一一个Server实例   |
| Service   | 一个或多个Connector的集合,这些connector共享同一个Container 来处理其请求.在同一个Tomcat实例内可以包含任意多个Service实例,他们互相独立 |
| Connector | tomcat连接器，用以监听并转换Socket请求,同时将读取的Socket请求交由Container处理，支持不同协议及不同的I/O方式 |
| Container | Container表示能够执行客户端请求并返回响应的一类对象,在Tomcat中存在不同级别的容器:Engine，host,Context,Wrapper |
| Engine    | 表示整个Servlet容器,在tomcat中，Engine为最高层级的容器对象.尽管Engine不是直接处理请求的容器，却是获取目标容器的入口 |
| Host      | Host 作为一类容器,表示Servlet引擎(Engine)中的虚拟机，与一个服务器的网络名有关，如域名等等,客户端可以使用这个网络名连接服务器,这个名称需要在dns服务器上注册。 |
| Context   | Context 作为一类容器,用于表示ServletContex,在Servlet规范中，一个ServletContext即表示一个独立的Web应用 |
| Wrapper   | Wrapper作为一类容器,用于表示Web应用中定义的Servlet           |
| Exexutor  | 表示Tomcat组件间可以共享的线程池                             |

