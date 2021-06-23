#### Locking Reads

在InnoDB中,有两种带锁安全访问数据的方式:

1. **SELECT ... FOR SHARE(S锁)**:

   + 乐观锁,并发读锁,被锁定的记录允许被其他事务并发读取,可共享的S锁,但是不可被其他事务的DML变更,需要先获取X (FOR UPDATE)锁

   + Note:

     + SELECT ... FOR SHARE是SELECT ... LOCK IN SHARE MODE的替代品，但是为了向后兼容，LOCK IN SHARE MODE仍然可用。这些语句是等价的。然而，FOR SHARE支持OF table_name、NOWAIT和SKIP LOCKED选项。

   + 死锁问题:
     + 使用此查询，并执行对应数据的update可能出现死锁的情况,eg:

       1. 首先，客户端A创建了一个包含一条记录的表，然后开始一个事务。在该事务中，A通过在共享模式下选择该行获得了一个S锁。

          ```sql
          mysql> CREATE TABLE t (i INT) ENGINE = InnoDB;
          Query OK, 0 rows affected (1.07 sec)
          
          mysql> INSERT INTO t (i) VALUES(1);
          Query OK, 1 row affected (0.09 sec)
          
          mysql> START TRANSACTION;
          Query OK, 0 rows affected (0.00 sec)
          
          mysql> SELECT * FROM t WHERE i = 1 FOR SHARE;
          +------+
          | i    |
          +------+
          |    1 |
          +------+
          ```

          

       2. 接下来，客户B开始一个事务，并试图从表中删除该行。

          ```sql
          mysql> START TRANSACTION;
          Query OK, 0 rows affected (0.00 sec)
          
          mysql> DELETE FROM t WHERE i = 1;
          ```

       3. 删除操作需要一个X锁。这个锁不能被授予，因为它与客户端A持有的S锁不兼容，所以这个请求被放在该行的锁请求队列中，客户端B被阻止。最后，客户端A也试图从表中删除该行

          ```sql
          mysql> DELETE FROM t WHERE i = 1;
          ERROR 1213 (40001): Deadlock found when trying to get lock;
          try restarting transaction
          ```

       4. 这里发生了死锁，因为客户端A需要一个X锁来删除该行。然而，这个锁的请求不能被批准，因为客户端B已经有一个X锁的请求，并且正在等待客户端A释放其S锁。也不能将A持有的S锁升级为X锁，因为之前B有一个X锁的请求。结果，InnoDB为其中一个客户端生成了一个错误，并释放了它的锁。客户端返回这个错误。

          ```sql
          ERROR 1213 (40001): Deadlock found when trying to get lock;
          try restarting transaction
          ```

          在这个时候，其他客户的锁请求可以被批准，它从表中删除了该行。

     + 

   

     

2. **SELECT ... FOR UPDATE**(X锁):
   
   + 悲观写锁,独占式获取此锁,阻止其他事务对被锁定的数据访问.或者阻止在某些事务隔离级别中读取数据。一致性读取忽略了在读取视图中存在的记录上设置的任何锁。(记录的旧版本不能被锁定；它们是通过在记录的内存副本上应用撤销日志来重建的)。



### 跳过并发数据

##### Locking Read Concurrency with NOWAIT and SKIP LOCKED

如果一条记录被一个事务锁定，那么请求相同锁定记录的SELECT ... FOR UPDATE或者SELECT ... FOR SHARE事务必须等待，直到阻塞的事务释放该记录的锁。这种行为防止事务更新或删除被其他事务查询更新的行。然而，如果你希望查询在请求的行被锁定时立即返回，或者从结果集中排除锁定的行是可以接受的，那么等待行锁被释放就没有必要。

为了避免等待其他事务释放行锁，NOWAIT和SKIP LOCKED选项可以与SELECT ... FOR UPDATE或SELECT ... FOR SHARE锁定读取语句一起使用.

- `NOWAIT`

  使用 **NOWAIT **的锁定读取不会等待获得一个行锁。查询立即执行，如果请求的行被锁定，则会出现错误。

- `SKIP LOCKED`

  使用 **SKIP LOCKED **的锁定读取不会等待获得一个行锁。查询立即执行，从结果集中删除锁定的行。