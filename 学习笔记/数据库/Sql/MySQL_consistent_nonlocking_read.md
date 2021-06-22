# InnoDB 中的无锁读取模式

+ Consistent  nonLocking read 是 InnoDB 使用MVCC的方式进行快照读,此模式是InnoDB处理READ COMMITTED和REPEATABLE READ隔离级别中SELECT语句的默认模式。一致性读取不会在其访问的表上设置任何锁，因此在表上执行一致性读取的同时，其他会话可以自由修改这些表。
+ 快照读:
  + 像不加锁的select操作就是快照读，即不加锁的非阻塞读；快照读的前提是隔离级别不是串行级别，串行级别下的快照读会退化成当前读；之所以出现快照读的情况，是基于提高并发性能的考虑，快照读的实现是基于多版本并发控制，即MVCC,可以认为MVCC是行锁的一个变种，但它在很多情况下，避免了加锁操作，降低了开销；既然是基于多版本，即快照读可能读到的并不一定是数据的最新版本，而有可能是之前的历史版本
+ 当前读
  +  像select lock in share mode(共享锁), select for update ; update, insert ,delete(排他锁)这些操作都是一种当前读，为什么叫当前读？就是它读取的是记录的最新版本，读取时还要保证其他并发事务不能修改当前记录，会对读取的记录进行加锁。



### 不同隔离级别下的读取模式

+ REPEATABLE READ（默认级别):

  + 同一事务中的所有一致读取都会读取该事务中第一次读取所建立的快照。即对于同一条**DQL** **无锁 select sql ** ,在同一个事务中,无论读取执行多少次,都是一样的数据返回,如果想要读取到其他事务并发执行的**DML**(insert/update/delete)操作,需要等待当前事务提交完毕后,再次查询。

    ```sql
                 Session A              Session B
    -- 关闭自动提交
               SET autocommit=0;      SET autocommit=0;
    time
    |          SELECT * FROM t;
    |          empty set
    |                                 INSERT INTO t VALUES (1, 2);
    |
    v          SELECT * FROM t;
               empty set
                                      COMMIT;
    
               SELECT * FROM t;
               empty set
    
               COMMIT;
    
               SELECT * FROM t;
               ---------------------
               |    1    |    2    |
               ---------------------
    ```

    

  + **Notes**

    + 快照适用于**DQL(SELECT)** 语句,但不一定适用于**DML**语句,eg:

      1. 如果你开启了一个REPEATABLE READ事务A,并进行了一次**快照读查询**
      2. 此时另一个REPEATABLE READ事务B开启,并插入或修改了一些行，然后提交了B事务.
      3. 事务A发出的DELETE或UPDATE语句可能会影响B提交的行,即使事务A不能查询B的DML操作。

    + 如果一个事务确实更新或删除了由不同事务提交的行，这些变化对当前的事务是可见的,例如，你可能会遇到下面这种情况。

      ```sql
      -- A 事务:
      SELECT COUNT(c1) FROM t1 WHERE c1 = 'xyz';
      -- Returns 0: no rows match.
      
      -- B事务:
      insert INTO  t1 (c1) value('xyz');(loop for serval time) 
      -- B txn insert several rows  committed
      
      --A 事务:
      DELETE FROM t1 WHERE c1 = 'xyz';
      -- Deletes several rows recently committed by A txn.
      ```

      ```sql
      -- A事务 DQL
      SELECT COUNT(c2) FROM t1 WHERE c2 = 'abc';
      -- Returns 0: no rows match.
      
      -- B事务 DML commited
      UPDATE t1 SET c2 = 'cba' WHERE c2 = 'abc';
      -- Affects 10 rows: another txn just committed 10 rows with 'abc' values.
      
      -- A事务 DQL 可以查询到A事务(commited)的DML变更
      SELECT COUNT(c2) FROM t1 WHERE c2 = 'cba';
      -- Returns 10: this txn can now see the rows it just updated.
      ```

+ READ COMMITTED:

  + 在READ COMMITTED隔离级别下,事务中的每个一致读取都会以读取时间为pointTime,创建新的快照(此快照包含了其他事务在此时间点之前的commited)

  + 在FOR SHARE下，会发生一个锁定的读取。一个SELECT会阻塞，直到包含最新鲜记录的事务结束。

  + 此隔离等级,可以确保每次都是读到最新的数据库中被commited的数据,在RR事务中,亦可通过 加锁的方式(非快照读)

    ```sql
    SELECT * FROM t FOR SHARE;
    ```

    



### other

一致性读取在某些DDL语句上不起作用。

一致性读取在DROP TABLE上不起作用，因为MySQL不能使用一个已经被丢弃的表，InnoDB会销毁该表。

一致性读取在ALTER TABLE操作上不起作用，ALTER TABLE操作是对原始表进行临时拷贝，并在临时拷贝建立后删除原始表。当你在一个事务中重新发出一致读取时，新表中的行是不可见的，因为当事务的快照被拍摄时，这些行并不存在。在这种情况下，该事务会返回一个错误。ER_TABLE_DEF_CHANGED, "表定义已经改变，请重试交易"。

对于INSERT INTO ...这样的子句中的选择，读取的类型是不同的。选择，更新... (SELECT)，和CREATE TABLE ... SELECT，这些条款没有指定FOR UPDATE或FOR SHARE。

默认情况下，InnoDB对这些语句使用更强的锁，并且SELECT部分的行为就像READ COMMITTED，其中每个一致的读取，甚至在同一事务中，设置和读取自己的新鲜快照。

要在这种情况下执行无锁读取，请将事务的隔离级别设置为READ UNCOMMITTED或READ COMMITTED，以避免在从选定表读取的行上设置锁。



参考文档:[MySQL :: MySQL 8.0 Reference Manual :: 15.7.2.3 Consistent Nonlocking Reads](https://dev.mysql.com/doc/refman/8.0/en/innodb-consistent-read.html)