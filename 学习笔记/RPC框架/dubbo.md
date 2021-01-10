# Dubbo用法示例

# 服务分组

使用服务分组区分服务接口的不同实现

当一个接口有多种实现时，可以用 group 区分。

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

