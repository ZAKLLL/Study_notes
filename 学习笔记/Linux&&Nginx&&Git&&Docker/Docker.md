# DOCKER

+ Docker的基本概念
  + 镜像image:
    + Image包含了一个基本的OS（比如Ubuntu）和应用需要的运行环境（即文件系统），这些文件系统中的文件可能是存在于多个layer的，最终呈现结果是这些layer的叠加结果。
       通过Dockerfile可以构建一个Image，Dockerfile定义了使用的base操作系统，和一系列操作，比如RUN执行命令，或者COPY把 Dockerfile同一级目录的其他文件拷贝到Image中。每个命令的执行都会产生一个新的Layer。
       底层的Layer实际上是无法被修改的，所以不要期待后面的删除命令可以让Image的size变小。要减小size，只能是执行命令让size变大后的同一个RUN命令中，删除不需要的垃圾，避免垃圾留在某一个layer中。
       Image运行时，除了Image中包含的若干Layer，还在最上面有一个可写的Layer。运行环境中的操作，都体现在可写的Layer上。如果用docker commit提交，可以产生一个新的镜像。
  + 仓库Rigistry：
    + Docker用Rigistry来保存用户构建的镜像，Docker公司运营的Rigistry叫Docker Hub。用户可以在Docker Hub上注册账号，从而发布自己构建的镜像。而且，还可以在github上建立一个git仓，放入Dockerfile，然后在Docker Hub上创建一个自动构建项目，关联到上述github仓，则可以在git仓有更新时，自动触发构建。这种方式，可以非常有效的规避网络不稳定带来的本地构建镜像的问题，比如apt-get安装程序失败。
  + 容器Container:
    + 容器提供了程序的运行环境，把Image运行起来，就是一个容器。runtime时环境
+ Docker的安装

![1558494038894](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558494038894.png)

+ Docker速度快的原因:
  

||Docker|虚拟机VM|
|:---|:---:|:---:|
| 操作系统   |与宿主机共享OS|宿主机OS上运行虚拟机OS|
|存储大小|镜像小，便于存储与运输|镜像庞大(vmdk,vdi等)|
|运行性能|几乎无额外性能损失|操作系统额外的cpu,内存损耗|
|移植性|轻便，灵活，适应于LInux|笨重，与虚拟化技术耦合度高|
|硬件亲和性|面向软件开发者|面向硬件运维者|
|部署熟读|快速|很慢|

## Docker 命令

+ docker  search   [options]  <imagename> ： 查询镜像
  + options:  
    + -s+num  ：表示只列出点赞数超过num值的镜像
    + --no-trunc : 表示获取所有的简介信息，不省略
    + --automated : 表示只列出automated build类型的镜像
+ docker pull <imagename> ： 拉取镜像默认等于 `docker pull <imagename>:latest`
+ docker commit -a"作者" -m"提交信息" <ContainerId>  <自定义镜像名>:版本号 ：将自定义后的容器提交为自定义镜像
+ 查询：
  + docker images: 查询本地所有镜像
  + docker  images -q : 查询本地所有镜像的id
  + docker images  -q --no-trunc ：查询本地所有镜像的完整id
+ 删除：
  + docker rmi <imagename> :  默认删除最新版本的镜像    :latest
  + docker rmi -f <imagename> : 强制删除
  + docker rmi  -f  $(docker images -q) 
+ 启动：
  + 交互式启动：docker run -it <ImageId>  :启动一个伪终端进入到对应启动的docker容器中（一般是系统镜像）
  + 后台运行容器： docker run -d <ImageId> ：返回一个运行的ContainerID
  + 添加别名启动： docker run -it --name  <name> <ImageId> 
  + 重启容器： docker restart <containerID>
  + 查看所有的启动的docker 进程： docker ps
  + 查看上一个容器实例：docker ps -l
  + 查看当前运行的容器实例以及之前所有运行过的容器：docker ps -a
  + 查看正在运行的容器的id: docker ps -q
  + 查看所有的容器id: docker ps -aq
  + 关闭所有容器：docker stop $(docker ps -aq)
+ 带端口映射的启动：docker run -it -p 8888:8080 <ImageId> ： 将docker 对外暴露的端口为8888，对应容器的端口 8080
+ 带端口映射的启动:   docker run -it -P  <imageId> : 随机映射端口

![1558576073943](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558576073943.png)

+ 退出容器：
  + exit ：退出容器并且停止容器
  + crtl+P+Q ：将容器挂载至Docker后台运行，不停止容器
  + docker attach <ContainerId> ：重新进入被挂载到后台的容器的伪终端
  + docker  exec  -t <containerId>  <command>：在容器中打开新的终端，执行命令返回值，并且可以启动新的进程
  + docker stop <containerId> 停止容器
  + docker kill <containerID> 强制关闭容器
+ docker rm  <ContainerId> ：删除已经停止的容器
+ 重要：
  + docker logs [options] <ContainerID>  ：查看容器日志
    + -t  添加时间戳
    + -f  不停的追加查看
    + -tail  只看最新的某几行
    + 查看容器细节： docker **inspect** <ContainerId> ：返回json数据串
    + docker attach <ContainerId> ：重新进入被挂载到后台的容器的伪终端
    + docker  exec  -t <containerId>  <command>：在容器中打开新的终端，执行命令返回值，并且可以启动新的进程
    + docker cp <containerId>:/../...(docker容器里的文件)  /.../(宿主机文件目录)  ： 将docker容器中的文件拷贝到宿主机指定的目录下  
+ docker run it -v /宿主机绝对路径目录 :/容器内目录  <ImageName> :容器和宿主机数据互通:
  + 可以添加--privileged=true 来增加权限
  + ![1558603374582](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558603374582.png)
  + docker run it -v /宿主机绝对路径目录 :/容器内目录 :ro <ImageName> ： 容器内不可写入数据

## DockerFile:

+ 编写一个Dockerfile :

  + ```
    # volume test
    FROM centos
    VOLUME ["/dataVolumeContainer1","/dataVolumeContainer2"] #指定在生成的镜像文件中挂载两个目录
    CMD echo "finished,---------success1"
    CMD /bin/bash
    ```

  + `docker build -f /mydocker/Dockerfile -t zkl/centos .`:将dockerfile编译成images,其中-f表明指定文件，-t 表明将容器别名为zkl/centos 最后的 .表明在当前目录

  + 容器的继承： `docker run -it --name dc02 --volumes-from dc01 zkl/centos `  表明继承容器名为dc01 镜像为zkl/centos的容器实例，别名为dc01

+ DockerFile保留字指令
  + FROM:  基础镜像，当前镜像是基于哪个镜像。scratch是所有的镜像的基础镜像。类似于java中的Object
  + MAINTAINER： 镜像维护者的命令和邮箱地址
  + RUN ：容器构建时需要运行的命令
  + EXPOSE： 当前容器对外暴露出的端口
  + WORKDIR ： 指定在创建容器后，终端默认登录进来的工作目录
  + ENV： 用来构建镜像环境过程中设置环境变量
  + ADD ：将宿主机目录下的文件拷贝进镜像且ADD命令会自动处理URL和解压tar压缩包
  + COPY ： 类似ADD,拷贝文件目录到镜像中  COPY src  dest
  + VOLUME:容器数据卷，用于保存数据和持久化工作
  + CMD：指定一个容器启动时要允许的命令，Dockerfile中可以有多个CMD命令，但只有最右一个生效，CMD会被docker run 之后的参数替换。
  + ENTRYPOINT：指定一个容器启动时要运行的命令，新追加命令不会覆盖原有命令
  + ONBUILD ：当构建一个被继承的Dockerfile时运行命令，父镜像在被子继承后父镜像 的onbuild被触发

+ 自定义tomcat dockerfile 编写

  + ```dockerfile
    FROM centos 
    MAINTAINER zakl<zjk19971225@gmail.com>
    #把宿主机当前上下文的c.txt拷贝到容器/usr/local/ 路径下
    COPY c.txt /usr/local/container.txt
    #把java与Tomcat添加到容器中
    ADD jdk-8u211-linux-x64.tar.gz /usr/local/
    ADD apache-tomcat-8.5.41-deployer.tar.gz /usr/local
    
    #安装Vim
    RUN yum install -y vim
    
    #设置工作访问时候的WORKDIR ，登录落脚点
    
    ENV MYPATH /usr/local
    WORKDIR $MYPATH
    
    #配置JAVA与TOMCAT环境变量
    ENV JAVA_HOME /usr/local/jdk1.8.0_211
    ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
    ENV CATALINA_HOME /usr/local/apache-tomcat-8.5.41
    ENV CATALINA_BASE /usr/local/apache-tomcat-8.5.41
    ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin
    
    #容器运行时端口
    EXPOSE 8080
    
    #启动时候运行tomcat
    CMD /usr/local/apache-tomcat-8.5.41/bin/startup.sh && tail -F /usr/local/apache-tomcat-8.5.41/bins/logs/catalina.out
    
    ```

    

  + 文件传输：
    
    + ``` docker cp host_path containerID:container_path```



+ Docker 运行mysql镜像并且与宿主机共享数据：

  + ![1558772897885](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558772897885.png)

  + 与mysql 进行交互：

  + ![1558773108647](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\1558773108647.png)

    

