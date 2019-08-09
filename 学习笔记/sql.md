# SQL

+ 查询某段时间内的出现的重复字段：

  + ```sql
    select * from z_operaterecord where operatetime between '2019-06-28' and '2019-06-29' group  by userid having count(*)>1;
    ```

+ 删除重复数据中的最新一条:

  + **一定要注意时间**

  + ```sql
     delete from z_operaterecord where id in 
     ( select t.tid from 
        ( select max(ID) as tid from z_operaterecord where operatetime between '2019-06-28' and '2019-06-29' group by userid having count(*) >1 ) t);
    ```

    

