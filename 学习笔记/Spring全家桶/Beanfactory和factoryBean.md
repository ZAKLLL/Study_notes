BeanFactory： 以Factory结尾，表示它是一个工厂类，是用于管理Bean的一个工厂FactoryBean：以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，根据该Bean的Id从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身， 如果要获取FactoryBean对象，可以在id前面加一个&符号来获取.

如下面代码所示，bean的产生实际上是从getObject()中来进行获取，而不是获取**NettyRpcReference** 这个factoryBean本身，尽管他本身也是一个Bean被注入spring容器中。

```java
public class NettyRpcReference implements FactoryBean, DisposableBean {


    private String remoteInterfaceName;
    private String ipAddr;
    private int port;
    private RpcSerializeProtocol protocol;
    private EventBus eventBus = new EventBus();


    @Override
    public void destroy() {
        eventBus.post(new ClientStopEvent(0));
    }


    @PostConstruct
    public void init() {
        MessageSendExecutor.getInstance().setRpcServerLoader(ipAddr, port, protocol);
        ClientStopEventListener listener = new ClientStopEventListener();
        eventBus.register(listener);
    }


    @Override
    public Object getObject() {
        return MessageSendExecutor.getInstance().execute(getObjectType());
    }

    @Override
    public Class<?> getObjectType() {
        if (remoteInterfaceName == null) {
            //该bean参数尚未注入,不适合提前加载。
            return null;
        }
        try {
            return this.getClass().getClassLoader().loadClass(remoteInterfaceName);
        } catch (ClassNotFoundException e) {
            System.err.println("spring analyze fail!");
        }
        return null;
    }
}
```

