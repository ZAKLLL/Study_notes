# Hibernate

+ @MappedSuperclass:

  + 1.@MappedSuperclass注解使用在父类上面，是用来标识父类。

    2.@MappedSuperclass标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够隐射在其子类对用的数据库表中。

    3.@MappedSuperclass标识的类不能再有@Entity或@Table注解。

+ Criteria查询工具类：

  + 多条件联合查询
  
  + ```java
    String comId = param.getCompany().getId();
                List<TDepartment> depts = param.getDepartments();
                String name = param.getName() + "";
                String phoneNum = param.getPhoneNum() + "";
                Criteria criteria = getIBaseDAO().getCurrentSession().createCriteria(TCompanyDirectory.class);
      
                if (depts != null) {
                    Disjunction dis = Restrictions.disjunction();//多条件查询准备
                    for (TDepartment dept : depts) { //查询所有外键部门Id符合条件的员工
                        dis.add(Restrictions.eq("department.id", "" + dept.getId()));
                    }
                    criteria.add(dis);
                }
            
                criteria.add(Restrictions.eq("company.id", comId));
                criteria.add(Restrictions.like("name", "%" + name + "%"));
                criteria.add(Restrictions.like("mobilePhone", "%" + phoneNum + "%"));
                criteria.addOrder(Order.asc("name"));
    ```
  
  + 分页查询:
  
  + ```java
    criteria.setProjection(null);
            SearchCompanyDirectoryResult searchResult = new SearchCompanyDirectoryResult();
            searchResult.setTotalCount(criteria.list().size());
            criteria.setFirstResult(beginIndex);
            criteria.setMaxResults(everyPage);
            searchResult.setCds(criteria.list());
            searchResult.setListDepts(param.getCompany().getDepartments());
            return searchResult;
    ```
  
  + 常用条件查询API:
  
  + ```java
    Restrictions.eq() equal，=
    Restrictions.allEq() 参数为Map对象，使用key/value进行多个等于的对比，相当于多个Restrictions.eq()的效果
    Restrictions.gt() greater-than, >
    Restrictions.lt() less-than, <
    Restrictions.le() less-equal, <=
    Restrictions.between() 对应SQL的between子句
    Restrictions.like() 对应SQL的like子句
    Restrictions.in() 对应SQL的in子句
    Restrictions.and() and关系
    Restrictions.or() or关系
    Restrictions.isNull() 判断属性是否为空，为空返回true，否则返回false
    Restrictions.isNotNull() 与Restrictions.isNull()相反
    Order.asc() 根据传入的字段进行升序排序
    Order.desc() 根据传入的字段进行降序排序
    MatchMode.EXACT 字符串精确匹配，相当于“like 'value'”
    MatchMode.ANYWHERE 字符串在中间位置，相当于“like '%value%'”
    MatchMode.START 字符串在最前面的位置，相当于“like 'value%'”
    MatchMode.END 字符串在最后面的位置，相当于“like '%value'”
    ```
  
  + 排序的两种实现:
  
  + ```java
    List cats = sess.createCriteria(Cat.class)
        .add( Property.forName("name").like("F%") )
        .addOrder( Property.forName("name").asc() )
        .addOrder( Property.forName("age").desc() )
        .setMaxResults(50)
        .list();
    //------------------------
    List cats = sess.createCriteria(Cat.class)
        .add( Restrictions.like("name", "F%")
        .addOrder( Order.asc("name") )
        .addOrder( Order.desc("age") )
        .setMaxResults(50)
        .list();
    ```
  
  + 
