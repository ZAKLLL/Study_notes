# WorkQueue

```java
public class producer {

    private final static String QUEUE_NAME = "workQueue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            for (int i = 0; i < 1000; i++) {
                String t = "";
                for (int j = 0; j <= i % 10; j++) {
                    t += ".";
                }
                String msg = "msg" + i + t;
                send(msg, channel);
            }
        }

    }

    public static void send(String msg, Channel channel) throws IOException {
        //开启持久化
        boolean durable = true;
        //无法对原有队列开启持久化,只能对新队列开启持久化
        //将消息标记为持久性并不能完全保证消息不会丢失
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        //                                       文字持久化策略
        //channel.basicPublish("", "task_queue", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
        System.out.println(" [x] Sent '" + msg + "'");
    }
}

```

+ 三类确认机制:
  + basic.ack用于肯定性确认
  + basic.nack用于负确认（注意：这是AMQP 0-9-1的RabbitMQ扩展）。
  + basic.reject用于否定的确认，但与basic.nack相比，有一个局限性
+ 分发消息数量:
  + basic.basicQos(n)

```java
public class consumerAck {
    private final static String TASK_QUEUE_NAME = "workQueue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //在向mq返回ack之前,不会再收到mq的消息
        channel.basicQos(1);
		//channel.basicQos(100);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);

            System.out.println(" [x] Received '" + message + "'");
            try {
                doWork(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" [x] Done");
                //手动确认ack,multiple是否为多消息ack确认
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                //如果channel.basicQos(100)
                //channel.basicAck(delivery.getEnvelope().getDeliveryTag(),true);

                //表明拒绝该消息,并且丢弃该信息
                //channel.basicReject(delivery.getEnvelope().getDeliveryTag(),false);
                //表明拒绝该信息,但是将该信息插入队列重排(按字母顺序排列)
                //channel.basicReject(delivery.getEnvelope().getDeliveryTag(),true);

                //本次被拒绝消费,但不意味着丢弃消息,且将消息放回队列首(无需重排,多一个参数为mutiple)
                //channel.basicNack(delivery.getEnvelope().getDeliveryTag(),true,true);

            }
        };
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {
            System.out.println("consumerTag->"+consumerTag);
        });

    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }
}
```



## basic.qos

+ basic.qos(n,bool):
  + **false**:分别应用于渠道上的每个新消费者
  + **true**: 渠道上所有消费者共享

+ 一次最多接收10条未确认的消息

  ```java
  Channel channel = ...;
  Consumer consumer = ...;
  channel.basicQos(10); // Per consumer limit
  channel.basicConsume("my-queue", false, consumer);
  ```

+ 这个例子在同一个channel上启动了两个消费者，每个消费者将独立地同时接收最多10条未被承认的消息。

  ```java
  Channel channel = ...;
  Consumer consumer1 = ...;
  Consumer consumer2 = ...;
  channel.basicQos(10); // Per consumer limit
  channel.basicConsume("my-queue1", false, consumer1);
  channel.basicConsume("my-queue2", false, consumer2);
  ```

+ 两个预取限制应相互独立地执行；消费者只有在未确认消息的两个限制都未达到时才会接收新消息。这将比上面的例子要慢，因为在通道和队列之间协调以执行全局限制的额外开销。

  ```java
  Channel channel = ...;
  Consumer consumer1 = ...;
  Consumer consumer2 = ...;
  channel.basicQos(10, false); // Per consumer limit
  channel.basicQos(15, true);  // Per channel limit
  channel.basicConsume("my-queue1", false, consumer1);
  channel.basicConsume("my-queue2", false, consumer2);
  ```

  