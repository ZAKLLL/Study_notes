# mybits配置

1. mybatis配置   

     ```xml
     <?xml version="1.0" encoding="UTF-8" ?>
     <!DOCTYPE configuration
             PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
             "http://mybatis.org/dtd/mybatis-3-config.dtd">
     <configuration>
         <!-- 添加日志-->
         <settings>
             <setting name="logImpl" value="LOG4J"/>
         </settings>
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
             <mapper resource="com.zakl.pojo/AddressMapper.xml"/>
         </mappers>
     </configuration>
     
     ```

2. MybatisUtil.java

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




## properties

3. ​	mybatis.cfg.xml引用jdbc.properties文件	   

   ```xml
      <properties resource="jdbc.properties">
           <!-- 可以在这里定义子节点 suchas <property name="username" value="boy" >-->
      </properties>
    <environments default="development"></environments>
           <environment id="development"> 
               <dataSource type="UNPOOLED">
                   <property name="driver" value="${driver}"/>
                   <property name="url" value="${url}"/>
                   <property name="username" value="${username}"/>
                   <property name="password" value="${password}"/>
               </dataSource>
           </environment>
       </environments>
   
   ```

   ```
   #jdbc.properties
   rl=jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC
   username=root
   password=root
   driver=com.mysql.cj.jdbc.Driver
   ```


## typrAliases(类型别名 ) 

```xml
<typeAliases>
    <typeAlias type="com.zakl.pojo.userpo" alias="userpo"
</typeAliases>
   <!--这样在 <select中就可以直接使用 resultType="userpo"  -->
```



## mappers(类似于具体实现类DAO的配置)

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

## 多参数传入的sql语句：

param 从1开始

aegs从0开始 

```java
List<Userpojo> querybyUP(String username,String password);
```

```xml
<Select id="querybyUP" resultType="com.zakl.pojo.Userpojo">
    select * where uaername=#{arg0} and password=#{arg1}
    <!-- 或者使用   select * where uaername=#{param1} and password=#{param2} -->
</Select>
```



通过加注解可以使用别名传参数：

```java
List<Userpojo> querybyUP(@Param("name")String username,@Param("pwd")String password);
```

```xml
<Select id="querybyUP" resultType="com.zakl.pojo.Userpojo">
    select * where uaername=#{name} and password=#{pwd}
</Select>
```


也可以通过单个javabean的方式查找(通过javabean中的get set方法查找的)

```java
Userpojo querybyjavabean(Userpojo userpojo);
```

```xml
<select id="querybyjavabean" recsultype="Userpojo类的全限定名">
    select * from girls name=#{username} and password=#{password}
</select>
```

多个javabean传参

```java 
public class A{
    private String username;
    get..set
}
public class B{
    private String flower;
    get..set
}
@interface
Userpojo querybyAB(@param("a")A a, @param("b")B b);

<select id="querybyjavabean" recsultype="Userpojo类的全限定名">
    select * from girls name=#{a.username} and flower=#{b.flower}
</select>


```

通过map传参查询

```java
@interface
USerpojo querybymap(Map<String,Object> map);

Map<String,Object> map=new HashMap<>();
map.put("username",value);
map.put("password",value);

Userpojo userpojo=MybatisUtil.getSession().getMapper(UserMapper.class).querybymap(map);

<select id="querybyjavabean" recsultype="Userpojo类的全限定名">
    select * from users name=#{username} and password=#{password}
</select>

```

+ 后面内容采用新表address测试

判断是否为空：

```java
@Test
    public void demo4(){
        SqlSession sqlSession=MybatisUtil.getSession();
        AddressMapper addressMapper=sqlSession.getMapper(AddressMapper.class);
        List<Addresspojo> addresspojoList=addressMapper.querybycs("",null);
        sqlSession.commit();
        System.out.println(addresspojoList.toString());
        sqlSession.close();
    }
```



```xml
<insert id="addAddress">
        insert into address(country,street,zip) values (#{country},#{street},#{zip})
    </insert>
    <select id="querybycs" resultType="com.zakl.pojo.Addresspojo">
        select * from address
        <where>
            <if test="param1 !=null">
                and country=#{param1}
            </if>
            <if test="param2 !=null">
                and street=#{param2}
            </if>
        </where>
    </select>
```



update

```xml
<update id="updateAddress">
        update address
        <set>
            <if test="country !=null and country !=''">
                country=#{country},
            </if>
            <if test="street !=null and street !=''">
                street=#{street},
            </if>
            <if test="zip !=null and zip !=''">
                zip=#{zip},
            </if>
        </set>
        where id=#{id}
    </update>
```



selectbychoose

```xml
 <select id="querychoose" resultType="com.zakl.pojo.Addresspojo">
        select * from address
        <where>
            <choose>
                <when test="country !=null and country !=null">
                    and country=#{country}
                </when>
                <when test="street !=null and street !=null">
                    and street=#{street}
                </when>
                <otherwise>
                    and zip=#{zip}
                </otherwise>
            </choose>
        </where>
    </select>

```

 

通过trim标签判断	

```xml
<select id="querybytrim" resultType="com.zakl.pojo.Addresspojo">
        select * from address

        <trim prefix="where" prefixOverrides="and">
            <if test="country !=null and country !=''">
                and country=#{country}
            </if>
            <if test="street !=null and street !=''">
                and street=#{street}
            </if>
            <if test="zip !=null and zip !=''">
                and zip=#{zip}
            </if>
        </trim>
    </select>
```

foreach

```xml
<!-- collection 描述集合-->
<select id="querybylist"  resultType="com.zakl.pojo.Addresspojo" >
        select * from address
        where id in
          <foreach item="integerList" collection="list" open="(" close=")" separator=",">
              #{integerList}
          </foreach>
    </select>
```

模糊查询：

```java
List<Addresspojo> querybylike(@Param("str") String str);
```



```xml
<!--通过bind绑定-->
<select id="querybylike" resultType="com.zakl.pojo.Addresspojo">
        <bind name="pattern" value="'%'+str+'%'"></bind>
        select * from address where country like #{pattern}
    </select>
<!--通过mysqlcontact拼接函数查询-->
 <select id="querybylike" resultType="com.zakl.pojo.Addresspojo">
        select * from address where country like CONCAT('%',#{str},'%')
 </select>
```

常用字段声明

```xml
<sql column="引用名">
    country,street
</sql>
```

