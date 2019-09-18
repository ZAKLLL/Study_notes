# 实体关系问题

+  1:1  

  多表查询

  ```java
  package com.zakl.pojo;
  
  import java.io.Serializable;
  //返回的查询类
  public class Userwithdetailpojo extends User2pojo implements Serializable {
  
      private  Userdetailpojo userdetailpojo;
  	//省略get set toString
  }
  
  ```



  ```xml
   <resultMap id="userwithdetail" type="com.zakl.pojo.Userwithdetailpojo">
          <id  property="userid" column="uid" />
          <result property="phone" column="phone"></result>
          <result property="password" column="password"></result>
          <result property="createDate" column="creatdate"></result>
          <result property="status" column="status"></result>
  		<!--关联的信息 -->
     	  <!--持有Userdetialpojo的封装
  		property是Userwithdetailpojo类的属性
  		不像result简单属性
  		需要用javaType来额外指明他的java数据类型
  		官方推荐方式
  		-->
       	<!--列名有重复的时候,使用别名-->
          <association property="userdetailpojo" javaType="com.zakl.pojo.Userdetailpojo">
              <id property="udid" column="udid"></id>
              <result property="address" column="address"></result>
              <result property="cid" column="cid"></result>
              
              <!--也可以使用连缀点法 -->
              <!--
              <result property="Userdetailpojo.udid" column="udid"></result>
              <result property="Userdetailpojo.address" column="address"></result>
              <result property="Userdetailpojo.cid" column="cid"></result>
  			-->
          </association>
  
      </resultMap>
  
      <select id="querybyid" resultMap="userwithdetail">
          select t1.userid as uid, t1.phone, t1.password ,t1.creatdate, t1.status, t2.udid , t2.address, t2.cid
          from user2 as t1 ,userdetail as t2
          <where>
              t1.userid=t2.userid and t1.userid =#{id};
          </where>
  ```

  使用泛型方法进行测试

  ```java
      public static<T> void  queryautocommit(String methodname,Object[] objs) throws InvocationTargetException, IllegalAccessException {
          SqlSession sqlSession = MybatisUtil.getSession();
          User2Mapper user2Mapper = sqlSession.getMapper(User2Mapper.class);
          Method[] methods=user2Mapper.getClass().getMethods();
          Method method=null;
          for (int i=0;i<methods.length;i++){
              if (methods[i].getName().equals(methodname)){
                  method=methods[i];
                  System.out.println(methods[i].getName());
              }
          }
          List<T> userwithdetailpojos= (List<T>) method.invoke(user2Mapper,objs);
          System.out.println(userwithdetailpojos);
          sqlSession.close();
      }
  
      @Test
      public void testdemo() throws InvocationTargetException, IllegalAccessException {
          Object[] objects=new Object[]{1};
          queryautocommit("querybyid",objects);
      }
  ```

  ###  使用分步查詢

  ```xml
  	<resultMap id="basic" type="com.zakl.pojo.User2pojo">
          <id  property="userid" column="userid" />
          <result property="phone" column="phone"></result>
          <result property="password" column="password"></result>
          <result property="createDate" column="creatdate"></result>
          <result property="status" column="status"></result>
      </resultMap>
  	
   <resultMap id="resultmap2" extends="basic" type="com.zakl.pojo.Userwithdetailpojo" >		<!-- select属性是用来选择接口中的方法 -->
          <association property="userdetailpojo" select="com.zakl.mapper.UserdetailMapper.querybyUseridbystep" column="userid"></association>
      </resultMap>
  
  <select id="querybystepid" resultMap="resultmap2">
          select userid , phone,password ,creatdate, status
          from user2
          <where>
              userid =#{id};
          </where>
      </select>
  
  ```

  UserdetailMapper查詢文件

  ```xml
      <select id="querybyUseridbystep" resultType="com.zakl.pojo.Userdetailpojo">
          select * from userdetail where userid=#{userid}
      </select>
  ```



  ```java
  public interface User2Mapper {
      List<Userwithdetailpojo> querybyid(@Param("id") Integer id);
      List<Userwithdetailpojo> querybystepid(@Param("id") Integer id);
  }
  public interface UserdetailMapper {
      List<Userwithdetailpojo> querybyUseridbystep(@Param("userid") Integer userid);
  }
  
  
   @Test
      public void demo11() {
          SqlSession sqlSession = MybatisUtil.getSession();
          User2Mapper user2Mapper=sqlSession.getMapper(User2Mapper.class);
          List<Userwithdetailpojo> userwithdetailpojos = user2Mapper.querybystepid(1);
          System.out.println(userwithdetailpojos);
          sqlSession.commit();
          sqlSession.close();
      }
  
  ```



  注意每次新增Mapper.xml時都應該在mybatis.cfg.xml中進行配置

  ```xml
      <mappers>
          <!-- resource : 相对路径查询资源的属性.
          相对于当前核心配置文件的位置开始查找映射文件.
          -->
          <mapper resource="com.zakl.pojo/userpojoMapper.xml"/>
          <mapper resource="com.zakl.pojo/AddressMapper.xml"/>
          <mapper resource="com.zakl.pojo/User2pojpMapper.xml"/>
          <mapper resource="com.zakl.pojo/UserdetailMapper.xml" />
      </mappers>
  ```

+  1:n     

  ### 多表联合查询

  ​	三表结构



  ​	![1545488294531](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1545488294531.png)

  ​	三个pojo类

  ```java
  public class UserwithBlog extends User2pojo implements Serializable {
      private List<Blog> blogs;
  }
  
  public class Blog {
      private Integer id;
      private User2pojo user2pojo;
      private String title;
      private String content;
      private List<Comment> comments;
  }
  
  public class Comment {
      private Integer id;
      private String comment;
      private Blog blog;
  }
  
  //接口方法
  @interface
    UserwithBlog querblogbyid(@Param("userid")Integer userid);
  
  ```

  mapper.xml配置

  ```xml
  <!--多表联合查询 有1对多关系时使用 connection标签 使用oftype来声明类型 -->
  	<resultMap id="userwithblog" extends="basic" type="com.zakl.pojo.UserwithBlog">
          <collection property="blogs" ofType="com.zakl.pojo.Blog">
              <id property="id" column="bid"></id>
              <result property="title" column="title"></result>
              <result property="content" column="content"></result>
            <!--  <result property="user2pojo.userid" column="aid"></result>-->
              <collection property="comments" ofType="com.zakl.pojo.Comment">
                  <id property="id" column="cid"></id>
                  <result property="comment" column="comment"></result>
                 <!-- <result property="blog.id" column="blogid"></result>-->
              </collection>
          </collection>
      </resultMap>
  
  
      <select id="querblogbyid" resultMap="userwithblog">
             select t1.userid as aid ,t1.phone ,t1.password,t1.creatdate,t1.status,
                    t2.id as bid,t2.userid,t2.title,t2.content,
                    t3.id as cid, t3.comment,t3.blogid
              from user2 as t1 ,blog as t2,comment as t3
              <where>
                  t1.userid=t2.userid and
                   t2.id=t3.blogid and
                    t1.userid=#{userid}
              </where>
  
      </select>
  ```

