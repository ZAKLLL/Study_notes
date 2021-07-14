# Redis

+  开启服务：

  + ```CMD
    redis-server.exe redis.windows.conf
    ```
  
  + redis-cli  :进入redis客户端
  + auth + pwd 进行登录

+ redis数据备份：

  + ```
    redis 127.0.0.1:6379> SAVE
    该命令将在 redis 安装目录中创建dump.rdb文件  
    ```

+  config get maxclients: 查看最大客户端连接数

+  config set maxclients: 设置最大客户端连接数

+  info clients: 当前客户端连接情况

+  config get timeout: 查看连接超时

+ config get timeout: 设置连接超时

+  CLIENT LIST: 查看客户端信息





### Redis Persistence:

+ 参考文档[Redis Persistence – Redis](https://redis.io/topics/persistence)

+ **RDB** (Redis Database): 

  + RDB以指定的时间间隔对你的数据集进行时间点快照。

  + RDB Snapshotting:

    + 默认情况下，Redis 将数据集的快照保存在磁盘上，在一个叫做 dump.rdb 的二进制文件中。你可以配置Redis，让它每隔N秒保存一次数据集，如果数据集中至少有M个变化，或者你可以手动调用SAVE(Blocking)或BGSAVE(Non-Blocking)命令。

    + eg:如果至少有1000个键发生变化，这种配置将使Redis每60秒自动将数据集转储到磁盘。

      `save 60 1000`

    + 工作原理:

      + Redis Fork 一个子线程
      + 子线程将当前Redis中的dataset 写入一个临时的RDB file
      + 当子线程工作完毕,将使用临时创建的dump.rdb作为新的rdb snashotting 替换旧的 dump.rdb

+ **AOF** (Append Only File):

  + AOP以日志形式追加服务器收到的每一个写操作记录作为持久化数据.因为是以追加的形式进行,所有会比RDB更加耗费空间,可以使用rewrite的方式复写已存在的AOF文件用以节省存储空间。
  + fully-durable strategy (更加耐用策略):
    + 在RDB snashotting 策略中,需要组合触发条件才能执行Redis 持久化策略,此时如果使用Kill -9 关闭引用,或者服务断电,最后一次RDB 操作之后的所有数据都将丢失,对于某些应用可能是不可接受的.
    + 配置与策略:
      + 在redis.*.conf中配置`appendonly yes`
      + **appendfsync always**:每当有新的命令被追加到AOF中时，就进行fsync 操作,此配置**十分十分慢**,但是是最安全的,最差情况也只会丢失最后一条操作记录(被截断 truncated )
      + **appendfsync everysec**: fsync每秒钟一次。足够快了（在2.4版本以上中可能和RDB一样快），如果发生宕机/断电,最多损失最后一秒数据(**suggested**)
      + **appendfsync no**:redis不进行fsync操作,只是把你的数据权限放在操作系统的手中。这是一种更快但是更不安全的方法。通常情况下，Linux在这种配置下会每30秒刷新一次数据，但这取决于内核的精确调校。
    + 截断(truncated )修复:
      + 如果由于某种原因（磁盘满了或其他原因），日志以一条写了一半的命令结束，redis-check-aof工具能够轻易地修复它。

+ **RDB + AOF**:

  + 可以在同一个实例中结合AOF和RDB。注意，在这种情况下，当Redis重新启动时，AOF文件将被用来重建原始数据集，因为它被保证是最完整的。

+ **No persistence**: 

  + 不配置任何持久化策略

