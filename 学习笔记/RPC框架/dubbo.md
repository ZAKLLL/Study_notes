# Dubbo学习

+ 设计架构图:

  ![image-20210105103813701](image-20210105103813701.png)

## 服务

```xml
<dubbo:service group="feedback" interface="com.xxx.IndexService" />
<dubbo:service group="member" interface="com.xxx.IndexService" />
```

## 引用

```xml
<dubbo:reference id="feedbackIndexService" group="feedback" interface="com.xxx.IndexService" />
<dubbo:reference id="memberIndexService" group="member" interface="com.xxx.IndexService" />
```

任意组：

```xml
<dubbo:reference id="barService" interface="com.foo.BarService" group="*" />
```
+ SPI,Adaptive,Wrapper,Activate
