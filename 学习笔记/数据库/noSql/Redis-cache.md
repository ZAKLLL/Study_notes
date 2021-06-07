### Redis Maxmenory config

+ maxmemory 配置指令用于配置 Redis，使其为数据集使用指定数量的内存。可以使用 redis.conf 文件来设置该配置指令，或者以后在运行时使用 CONFIG SET 命令。

+ eg: 配置100M字节的内存限制，可以在redis.conf文件中使用以下指令

  + `maxmemory 100mb`

  + 将maxmemory设置为0会不限制redis的内存使用。这是64位系统的默认行为，而32位系统使用3GB的隐含内存限制。

+ 当redis使用内存到达**maxmemory**时,redis-server 将根据**maxmemory-policy** 策略来执行具体的行为。

  + maxmemory-policy:
    1. **noeviction**: 不做内存置换,当client执行命令使得server端超过了maxmemory 时，返回错误信息
    2. **allkeys-lru**：通过Lru算法(latest frequently used) 清除key
    3. **allkeys-lfu**: 通过Lfu算法(latest frequently used) 清除key
    4. **volatile-lru**: 通过Lru算法(latest recently used) 清除key(需要key过期 key expire)
    5. **volatile-lfu**: 通过Lfu算法(latest recently used) 清除key(需要key过期 key expire)
    6. **allkeys-random**: 随机清除key
    7. **volatile-random**:随机清除key(需要key过期 key expire)
    8. **volatile-ttl**: 随机清除key(需要key过期 key expire),并且尝试清除TTL(time to live) 较短的keys
  + 其中当满足条件的keys不存在的时候, **volatile-lru**, **volatile-random** 和 **volatile-ttl** 策略对应的行为会和 **noeviction** 一样

+ 如何选择一个合适的cache策略:

  + **allkeys-lru**:当请求主要呈幂律分布时,即某些key会被大量访问时,选择此策略
  + **allkeys-random**: 如果访问请求是平均的,即每个key被访问到的概率相近,选择此策略
  + **volatile-ttl**: 通过在创建缓存对象时使用不同的TTL值来向Redis提示哪些keys可以被淘汰

+ cache工作模式:

  + 客户端运行一个新的命令以添加数据到redis-server.
  + Redis检查内存的使用情况，如果它大于maxmemory的限制，它就会根据策略来驱逐键。
  + 一个新的命令被执行，以此类推。

## LRU_cache(最近最少使用)

+ Approximated LRU algorithm

  + Redis的LRU算法不是一个精确的实现。这意味着Redis不能选择最佳的驱逐对象，也就是在过去被访问最多的访问。相反，它将尝试运行LRU算法的近似值，通过对少量的键进行抽样，并驱逐被抽样的键中最好的一个（具有最古老的访问时间）。然而，自Redis 3.0以来，该算法被改进为也采取一个良好的候选池进行驱逐。这提高了算法的性能，使其能够更接近于真正的LRU算法的行为。

    Redis LRU算法的重要之处在于，你能够通过改变每次驱逐所检查的样本数来调整算法的精度。这个参数是由以下配置指令控制的。

    `maxmemory-samples 5`

  + Redis不使用真正的LRU实现的原因是它花费更多的内存。然而，对于使用Redis的应用程序来说，近似值几乎是等同的。下面是Redis使用的LRU近似算法与真正的LRU相比:

    + 亮色的部分是lru中应该被淘汰的内存区块
    + 灰色部分是尚未被lru算法选中淘汰的算法
    + 绿色部分是需要新添加到redis中的内存区块

    ![image-20210607113302273](image-20210607113302273.png)



## LFU_cache(最不经常使用)

+ Approximated LFU mode: 它使用一个概率计数器，称为 [Morris counter](https://en.wikipedia.org/wiki/Approximate_counting_algorithm),对每个对象使用几个bit 来进行访问的计数，结合一个衰减期配置，使计数器随着时间的推移而减少,因为在某些时候，我们不再想把钥匙视为频繁访问，即使它们在过去是频繁访问的，这样算法就能适应访问模式的转变。
+ LFU有一些可调整的参数：例如，如果一个原本被频繁的访问的key不再被访问，它应该以多快的速度降低排名(排名越低,被淘汰的概率越高)？也可以调整Morris计数器的范围，以便更好地使算法适应特定的使用情况。
  + 默认LFU配置参数
    + `lfu-log-factor 10`: 计数器最多支持到100万次访问计数(此配置与访问命中率的关系如下表) 
      + LFU计数器每个键只有8位，它的最大值是255，所以Redis使用了一种对数行为的概率递增法。当一个键被访问时，计数器会以这种方式递增:
        1. A random number R between 0 and 1 is extracted.
        2. A probability P is calculated as 1/(old_value*lfu_log_factor+1).
        3. The counter is incremented only if R < P.
    + `lfu-decay-time 1`:  每分钟衰减一次计数器.

+ 计数器的对数因子**lfu-log-factor**配置了需要多少次访问才能使计数器达到饱和，它只是在0-255范围内。系数越高，需要更多的访问量才能达到最大值。根据下表，系数越低，计数器对低访问量的分辨率越好。

```
+--------+------------+------------+------------+------------+------------+
| factor | 100 hits   | 1000 hits  | 100K hits  | 1M hits    | 10M hits   |
+--------+------------+------------+------------+------------+------------+
| 0      | 104        | 255        | 255        | 255        | 255        |
+--------+------------+------------+------------+------------+------------+
| 1      | 18         | 49         | 255        | 255        | 255        |
+--------+------------+------------+------------+------------+------------+
| 10     | 10         | 18         | 142        | 255        | 255        |
+--------+------------+------------+------------+------------+------------+
| 100    | 8          | 11         | 49         | 143        | 255        |
+--------+------------+------------+------------+------------+------------+
```