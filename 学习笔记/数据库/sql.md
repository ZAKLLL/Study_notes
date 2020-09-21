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

+ 获取随机时间:

  + ```sql
    select timestamp '2020-09-13 00:00:00' + random() * (timestamp '2020-09-30 20:00:00' - timestamp '2020-09-13 00:00:00')
    ```

+ 循环更新随机时间

  + ```sql
    create or replace function replacetime(a1 bigint, a2 bigint)
        returns
            void
    AS
    $$
    declare
        ii integer;
    begin
        ii := 1;
        FOR ii IN a1..a2
            LOOP
                RAISE NOTICE 'value %',ii; ##打印调试
                UPDATE t_monitor_ditch
                SET data_time =(select timestamp '2020-09-13 00:00:00' +
                                       random() * (timestamp '2020-09-30 20:00:00' - timestamp '2020-09-13 00:00:00'))
                WHERE id = ii;
            end loop;
    end
    $$ LANGUAGE plpgsql;
    
    select replacetime(245136, 246236)
```
    
    