###      ls file 

+ 查看目录：ls   
+ 查看所有信息: ls -r
+ 查看文件基本类型（.后缀名）：file filenam

### cd
+ 进入退出文件夹：cd
+ 复制： cp old  new //递归辅助 cp -R old new 
+ 远程文件拷贝： scp   -r root@<ip地址>:<文件路径>   <本地路径>
+ 拷贝文件到远程：scp -r  <本地文件路径>   root@<ip地址>:<目标文件路径>
+ 剪切/移动 ： mv old new(rename)
+ 创建文件夹：mkdir
+ 查看当前工作目录：pwd

### rm

+ 删除空文件夹：rmdir
+ 删除带有文件的文件夹 : rm -r floder / rm -rf floder
+ 删除文件带有提示： rm -i file
+ 编辑文件：vim

### cat

+  查看文件 ：cat
+ 将文件内容移动 ：cat resource > target
+ 将文件内容打包后移动 : cat resource1 resource2 >targetfile
+ 将文件内容移动到另一个文件： cat  resource >> targerfile(exit)

### 权限

+ 显示权限: ls -l
  drwxr-xr-x 2 root root 4096 Jan  7 10:39 floder1
  + 当前用户所持有权限drwxr: d代表文件夹/r可读/w可写/x可执行
  + group所持有权限xr:可执行/可读取 
  + others所持有权限x:可执行   


+ 更改权限： chmod u+r(用户user添加可读权限)  file

  + ​	     chmod u-w(用户user删除可写权限)  file

+ 切换到root用户: sudo su

### find查询
+ 语法：find [PATH] [option] [action]



### 进程ps
+ 查看当前进程：ps -ef

+ 查看所有状态:  ps -aux

+ -e：显示所有进程。

  -f：全格式。

  -h：不显示标题。

  -l：长格式。

  -w：宽输出。

  -a：显示终端上的所有进程，包括其他用户的进程。

  -r：只显示正在运行的进程。

  -x：显示没有控制终端的进程。

### 终止进程：
+ 终止进程: kill -9[pid]    直接终止进程
+ 退出进程： kill  [pid]   让进程正常退出，相当于 kill -15  [pid]

### 正则表达式：grep
+ 语法：grep [-acinv] [--color=auto] '查找字符串' filename
+ 查看进程为java的状态: ps -aux|grep java


### 系统任务相关：
+ 将命令放置在后台执行：&（放置在命令之后） vim f1.txt &
+ 当前命令的终止:crtl+c
+ 将正在执行的命令放置在后台并且暂停执行::crtl+z
+ 将后台暂停的命令变成继续执行
+ 查看后台运行的命令：jobs
+ 将后台命令调至前台继续执行：fg

### 压缩
+ 压缩：tar -jcv -f filename.tar.bz2 要被处理的文件或目录名称
+ 查询：tar -jtv -f filename.tar.bz2
+ 解压：tar -jxv -f filename.tar.bz2 -C 欲解压缩的目录

## others
+ 切换用户： su -username
+ 切换至root: sudo su

## java

+ 查看java相关的后台 jps

+ 后台运行    nohup java -jar superboot.jar &

+ tail -f  用于监视file文件的增长

  
## 管道符 |

+ ps -ef | grep <args> : 将查询到的所有的进程传递到grep命令中，进行匹配查询

+ Echo {1..100} |tr ' ' '+'|bc  ： 将输入的1到100中的空格替换为+号，将表达将表达式传递给bc，bc进行计算输入1加到100的值

  

## tail

+ tail -f  <filename> ：持续查看文件的增长情况

+ tail -f -n <num> <filename> :持续查看文件最新num行的增长情况

  