# Spring注解

+ @Service用于标注业务层组件

+ @Controller用于标注控制层组件（如struts中的action）

+ @Repository用于标注数据访问组件，即DAO组件

+ @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。

+ @Resource和@Autowired 的区别：
  + @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了。@Resource有两个属性是比较重要的，分是name和type，Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。 

+ @Value("${jwt.auth.path}") :读取application.yml中的配置文件

+ @DatetimeFormat：是将String转换成Date，一般前台给后台传值时用

+ @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")  将Date转换成String  一般后台传值给前台时
+ Qualifier: **@Qualifier** 注释和 **@Autowired** 注释通过指定哪一个真正的 bean 将会被装配来消除混乱
  + @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService; 
+ **系统缓存常用注解**:
  + @CacheConfig：主要用于配置该类中会用到的一些共用的缓存配置
  + @Cacheable：主要方法的返回值将被加入缓存。在查询时，会先从缓存中获取，若不存在才再发起对数据库的访问。
  + @CachePut：主要用于数据新增和修改操作
  + @CacheEvict：配置于函数上，通常用在删除方法上，用来从缓存中移除相应数据。
+ @Slf4j: 配合lombok可以直接调用log函数，无需再声明日志类：
  + private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

+ @Async ：异步调用注解。

+ @JsonCreater(这个是java序列化中的注解)：json在反序列化时，默认选择类的无参构造函数创建类对象，当没有无参构造函数时会报错，@JsonCreator作用就是指定反序列化时用的无参构造函数。构造方法的参数前面需要加上@JsonProperty,否则会报错

  + ```java
     @JsonCreator
        public Person(@JsonProperty("id") String id) {
            this.id = id;
        }
    ```