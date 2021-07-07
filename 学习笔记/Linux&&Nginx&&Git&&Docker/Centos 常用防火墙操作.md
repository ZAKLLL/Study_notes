# Centos 常用防火墙操作

+ ### centos7 

  + firewalld的基本使用

  + 启动： systemctl start firewalld

  + 关闭： systemctl stop firewalld

  + 查看状态： systemctl status firewalld 

  + 开机禁用 ： systemctl disable firewalld

  + 开机启用 ： systemctl enable firewalld

    --------------------

  + 开放端口： firewall-cmd --zone=public --add-port=5672/tcp --permanent  （开放5672端口，tcp协议

  + 关闭端口： firewall-cmd --zone=public --remove-port=5672/tcp --permanent （关闭5672端口

  + 刷新配置： firewall-cmd --reload 

  + 查看防火墙所有开放的端口 firewall-cmd --zone=public --list-ports

  + 防火墙状态: firewall-cmd --state

  


### 抓包

+ UDP端口抓包: tcpdump -i eth0 -s 0 port 1814

+ UDP发包测试：nc -vuz 120.78.203.41 5060



## 端口相关进程操作

+ 查看端口号的进程:  
  +  (查看808*的端口号信息)   
  + ss -lntpd | grep :808* 
  + netstat -tnlp | grep :808* 
+ 根据进程pid查端口：       lsof -i | grep pid     
+ 根据端口port查进程：     lsof  -i:port          
+ 根据进程pid查端口：       netstat -nap | grep pid     
+ 根据端口port查进程         netstat -nap | grep port

### 终止进程：

+ **终止进程(慎用)**: kill -9[pid]    直接终止进程
+ **退出进程**： kill  [pid]   让进程正常退出，相当于 kill -15  [pid]



## java

+ 查看java相关的后台: jps
+ 后台运行    nohup java -jar xxx.jar &
+ 后台运行并且重定向日志  nohup <command> >>my.log 2>&1  & 
  + 其中2>&1 表示将标准错误重定向到标准输出，当command指令出现问题的时候,运行错误内容会输出到my.log中
+ tail -f  用于监视file文件的增长