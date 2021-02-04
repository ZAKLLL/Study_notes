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