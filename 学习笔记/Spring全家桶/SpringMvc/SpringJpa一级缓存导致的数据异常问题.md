# SpringJpa 一级缓存导致的数据异常问题

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

+ 解决方案:

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

    

