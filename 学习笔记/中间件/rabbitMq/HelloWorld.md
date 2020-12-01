# 简单队列

+ 生产者:

  ​		向hello队列发送消息

  ```java
  public class Send {
  	//指定队列名
      private final static String QUEUE_NAME = "hello";
  
      public static void main(String[] argv) throws Exception {
          int cnt=0;
          while (true){
              ConnectionFactory factory = new ConnectionFactory();
              factory.setHost("localhost");
              try (Connection connection = factory.newConnection();
                   Channel channel = connection.createChannel()) {
                  channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                  String message = "Hello World!"+cnt++;
                  channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                  System.out.println(" [x] Sent '" + message + "'");
              }
              TimeUnit.MICROSECONDS.sleep(100L);
          }
      }
  }
  ```

+ 消费者:

  ​	消费rabbitMq的信息:

  ```java
  public class Recv {
      private final static String QUEUE_NAME = "hello";
      public static void main(String[] argv) throws Exception {
          ConnectionFactory factory = new ConnectionFactory();
          factory.setHost("localhost");
          Connection connection = factory.newConnection();
          Channel channel = connection.createChannel();
  
          channel.queueDeclare(QUEUE_NAME, false, false, false, null);
          System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
          //异步回调
          DeliverCallback deliverCallback = (consumerTag, delivery) -> {
              String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
              System.out.println(" [x] Received '" + message + "'");
          };
          channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
          });
      }
  }
  ```

  

