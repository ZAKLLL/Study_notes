+ 注册队列(无交换机模式):

  + 在pom.xml中添加rabbitmq依赖：

    + ```xml
          <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-starter-amqp</artifactId>
           </dependency>
      ```

  + 在localhost:15672中进行队列注册

​	![1552527559804](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1552527559804.png)

 1. 发送消息： 

    ```java
    @Autowired
        private RabbitTemplate rabbitTemplate;
        @Test
        public void sendmsgtormq(){
            //指定向队列名为rabbitmqtest的消息队列中发送消息
            rabbitTemplate.convertAndSend("rabbitmqtest","生产消息测试");
        }
    ```

	2. 接受消息：

    ```java
    @RabbitListener(queues = "rabbitmqtest")
    @Component
    public class Getmsg {
        @RabbitHandler
        public void getmsg(String string) {
    
            System.out.println("ranbbitmqtest接收到的消息为:"+string);
        }
    }
    //可同时指定多个接受类，但是某个队列中的消息只能被接受一次，当消息被接收，队列中的该消息就被清除
    ```



+ 分裂模式fanout:

  1. 在rabbitmq后台注册交换机fanout_test：

     ![1552527910557](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1552527910557.png)

  2. 将该交换机与消息队列进行绑定，确认消息的分发：

     ![1552527994297](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1552527994297.png)

  3. 消息的发送：

     ```java
      @Test
      public void sendmsgtormq2(){
          //这里的convertAndSend方法的第一个参数指定为你将用来分发消息的交换机，第二个参数指定队列可以为空，第三个为你要发送的消息
             rabbitTemplate.convertAndSend("fanout_test","","生产消息测试");
         }
     ```

  4. 分裂模式消息的接受：

     ```java
     //如同上面的消息接收一样。只需要指定消息队列
     @RabbitListener(queues = "fanoutmq1")
     @Component
     public class Getmsg2 {
         @RabbitHandler
         public void getmsg(String string) {
     
             System.out.println("fanoutmq1接收到的消息为:"+string);
         }
     }
     ```

  5. 主题模式消息队列(需要编写匹配规则)：

     1.  #:相当于通配符，   a*:意味着a只能出现一次

     2. ![1552528703038](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1552528703038.png)

        