# 第一个mybatis应用测试
+ 项目结构  
    ![项目结构]("C:\Users\HP\Pictures\vscode截图\mybatis_Structure.png")

+ 所需jar包，本测试使用maven管理，均使用中央仓库导入
    ```XML
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.13</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.hamcrest/hamcrest-all -->
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-all</artifactId>
            <version>1.3</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    ```
+ mybatis连接工具类：   
 ```java
 package com.zakl.com.zakl.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis.cfg.xml";
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }
}

 ```

+  Usermapper接口
 ```java
 package com.zakl.mapper;

import com.zakl.pojo.Userpojo;

public interface UserMapper {
    void insert(Userpojo userpojo);
}
 ```
+ Userpojo类
    ```java
    public class Userpojo {
    private int id;
    private String username;
    private String password;
        //省略get set方法
    ```



+  mybatis.cfg.xml数据库连接配置：
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 环境, 就是配置数据库访问环境的标签.
    default - 默认使用什么环境
    -->
    <environments default="development">
        <!-- 配置具体的某一个环境
        id - 当前环境的命名
        -->
        <environment id="development">
            <!-- 事务管理方式, 当前框架管理数据库事务使用什么技术.
            type - 使用的具体技术. JDBC, 就是Connection.commit()/rollback()
            -->
            <transactionManager type="JDBC"/>
            <!-- 数据源, 访问的数据库参数
            type - 管理方式, 管理Connection的方式,
            POOLED , 代表池化管理. 就是连接池.
            -->
            <dataSource type="UNPOOLED">
                <!-- 配置具体参数 -->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>

    <!-- 引用映射文件 -->
    <mappers>
        <!-- resource : 相对路径查询资源的属性.
        相对于当前核心配置文件的位置开始查找映射文件.
        -->
        <mapper resource="com.zakl.pojo/userpojoMapper.xml"/>
    </mappers>
</configuration>

```
这里要注意是否添加?serverTimezone=UTC，新版本的mysql需要添加这个，否者会报错。  
+ Mapper.xml映射配置   
```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="com.zakl.mapper.UserMapper">
    <insert id="insert">
        insert into users(username,password) values (#{username},#{password})
    </insert>
   </mapper>
```
注意命名空间*namespace="com.zakl.mapper.UserMapper"*应当为Mapper接口位置。


最后进行测试
+  测试类：   
```java
package com.zakl;

public class AppTest {
    @Test
    public void demo() {
        SqlSession sqlSession = MybatisUtil.getSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Userpojo userpojo = new Userpojo();
        userpojo.setUsername("培荣");
        userpojo.setPassword("123123");
        userMapper.insert(userpojo);
        sqlSession.commit();
        sqlSession.close();

    }
}
```

