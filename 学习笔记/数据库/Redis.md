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
    ```

    该命令将在 redis 安装目录中创建dump.rdb文件

    