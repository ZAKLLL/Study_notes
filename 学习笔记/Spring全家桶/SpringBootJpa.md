+ yml配置文件

  ```yml
  spring:
      datasource:
        url: jdbc:mysql://localhost:3306/users?serverTimezone=UTC
        username: root
        password: root
        driver-class-name: com.mysql.cj.jdbc.Driver
      jpa:
        properties:
          hibernate:
            hbm2ddl:
              auto: update
            dialect: org.hibernate.dialect.MySQLInnoDBDialect
        show-sql: true
  ```

+ 使用方法：

  ```java
  public interface UserDao extends CrudRepository<user,Integer> {
  } //直接继承接口，接口类声明实体类,以及主键数据类型，这里是user和Integer.调用时直接调用接口提供的方法或者自定义方法
  ```

  