+ **@oneToMany **VS **@ManyToOne**

+ **referencedColumnName** 是Many方外键

+ **name** 是one方的被关联的键

  + ```java
    @Entity
    public class One{
        @Id
        @Column(name = "id")
        private Integer id;
    
        
        //oneToMany的情况下 name为关联表的外键，referencedColumnName为被外键关联本表的键
        @OneToMany(targetEntity = Many.class)
    	@JoinColumn(name = "one_id", referencedColumnName = "id")
    	private List<Many> ManyEntities;
    }
    
    @Entity
    public class Many{
        @Id
        @Column(name = "id")
        private Integer id;
        
    	//可以去除
        @Column(name = "one_id")
        private Integer oneId;
    
        //ManyToOne的情况下  name为被外键关联本表的键,为关联表的外键referencedColumnName
        //与 oneToMany 相反
        @ManyToOne(targetEntity = One.class)
    	@JoinColumn(name = "id", referencedColumnName = "one_id" insertable = false, updatable = false)
    	private One one;
    }
    ```



+ @DynamicInsert:
  + 对于插入,该实体是否应该使用动态sql生成,如果为true,表示忽略null值,仅插入非null字段,如果数据库存在null字段的default,使用数据库的该字段的default值
+ @DynamicUpdate:
  + 为了进行更新，此实体是否应使用动态sql生成，为true的话，仅仅更新非null字段。请注意，对于被**detached**的Entity，如果不启用select-before-update，这是不可能的
