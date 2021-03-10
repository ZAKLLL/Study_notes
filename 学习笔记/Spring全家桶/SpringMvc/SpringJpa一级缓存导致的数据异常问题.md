# SpringJpa 一级缓存导致的数据异常问题解决方案

+ 背景: 存在如下一段代码逻辑

  ```java
  @Data
  @Table(name = "t_a")
  @Entity
  public class A  {
  
      
      @Id
      @Column(name = "id")
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;
  	
  	@Column(name="b_id")    
      private Integer bId;
  	
  	//b_id为b的主键,作为t_a的外键(逻辑外键,不做数据库关联)
      @ManyToOne(targetEntity = SysUserInfo.class)
      @JoinColumn(name = "b_id", referencedColumnName = "id", insertable = false, updatable = false)
      private B b;
  }
  ```

  ````java
  @Data
  @Table(name = "t_b")
  @Entity
  public class B  {
  
      
      @Id
      @Column(name = "id")
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;
      
  	private String info;
  }
  ````

  整理情况是 B->A为一对多关联关系,通过**@ManyToOne**注解可以在查询A的时候关联查询a关联的B信息。

  现在有一段代码逻辑如下(B表中存在id为1的记录):

  ````java
  
  @Autowired
  private ADao adao;
  
  public List<A> saveAndFind(){
      A a=new A();
      a.setBid(1);
      aDao.save(a);
  	List<A> aList = aDao.findAll();
  }
  
  ````

  代码逻辑为先插入一条A,然后查询所有的A信息:

  + 期望结果:查询到所有的A,并且查询到A关联的所有

    ````json
    [
      {
        "id": 1,
        "b": {
          "id": 1,
          "info": "b_info"
        }
      }
    ]
    ````

  + 实际结果:

    ````json
    [
      {
        "id": 1,
        "b": null
      }
    ]
    ````

+ 原因:

  + 在执行插入后马上查询的操作时,新插入的entity会被缓存到Jpa(hiberinate)的一级缓存**session**中,当执行findAll的时候,会优先查询以及缓存中的entity,此时的entity为刚刚插入实体,并没有进行实际关联B表信息,b 仍然为null,不符合期望.

+ 解决方案(1):

  + 既然问题是由于Jpa一级缓存造成的,则对症下药,在进行查询前,清除Session中的缓存,令Jpa完整执行一次从数据库中查询的动作,则顺带完成了对于B表的关联查询操作

  + 解决实现:

    ````java
    @Autowired
    private ADao adao;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<A> saveAndFind(){
        A a=new A();
        a.setBid(1);
        aDao.save(a);
        
        //清除jpa一级缓存
        entityManager.clear()
    	List<A> aList = aDao.findAll();
    }
    ````

  + 输出结果

    ````json
    [
      {
        "id": 2,
        "b": {
          "id": 1,
          "info": "b_info"
        }
      }
    ]
    ````

  + 存在其他问题:

    **entityManager** 不是一个线程安全的操作,直接执行**entityManager.clear()**操作可能导致其他需要但是仍未进行持久化操作并且处于session中的entity被全部清除掉,可能会引发,业务异常.

    **entityManager.clear()** 

    ```
    Clear the persistence context, causing all managed entities to become detached. Changes made to entities that have not been flushed to the database will not be persisted
    
    清除持久化上下文，使所有被管理的实体脱离。对未被刷新到数据库的实体所做的更改将不会被持久化。
    ```

+ 解决方案(2):

  + 使用**entityManager.detach()** 函数,对需要保存到的新A,进行save并flush到数据库中,然后在entityManager中清除每一个新插入的A entity:

    该函数javaDoc文档:

    ```
    Remove the given entity from the persistence context, causing a managed entity to become detached. Unflushed changes made to the entity if any (including removal of the entity), will not be synchronized to the database. Entities which previously referenced the detached entity will continue to reference it.
    
    从持久化上下文中删除给定的实体，使被管理的实体脱离。如果对实体有任何未刷新的更改（包括删除实体），将不会同步到数据库。之前引用被分离实体的实体将继续引用它。
    ```

  + 解决实现:

    ````java
    @Autowired
    private ADao adao;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<A> saveAndFind(){
        A a=new A();
        a.setBid(1);
        aDao.saveAndFlush(a);
        
    	//在session中清除被缓存的a对象
        entityManager.detach(a);
        
    	List<A> aList = aDao.findAll();
    }
    ````

  + 输出结果

    ````json
    [
      {
        "id": 3,
        "b": {
          "id": 1,
          "info": "b_info"
        }
      }
    ]
    ````

​    

​    

