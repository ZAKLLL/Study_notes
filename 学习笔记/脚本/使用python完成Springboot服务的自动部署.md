---
title: 使用python完成Springboot服务的自动部署
date: 2019-11-27 19:01:14
tags: python Springboot Linux
---

#  Python自动化SpringBoot项目部署

+ 最近学校开始了校内实训，说是实训，就是拿着各种框架做些小项目，毕竟快毕业了总得学点吃饭的家伙，因为我是负责我们小组的后端开发，所以经常要推jar包上服务器进行测试。每次都得：

  + 编写/修改代码。
  + 本地测试
  + build jar
  + 通过**FileZilla**(这个玩意儿很好用,极其友好的sftp软件)推送到服务器
  + 然后xshell到服务器
  + 关闭应用
  + 清空日志(测试日志,就不做长久记录了)
  + 启动应用

+ 就是这一系列的流程，在搞了两天实在是受不了这个效率了，想要将流程全部自动化，理想目标是直接使用powershell写个脚本一键 build ,推送，重启应用，但是由于才疏学浅，不会写ps脚本，而且idea的build命令也不透明，所以选择退而求其次--**Python**

+ 最开始在度娘和G驴找了一圈，发现都没啥现成的解决方案，于是决定自己写一个,流程如下：

  + **Build**：直接使用IDEA build好，比maven还要方便。

  + **Push to Server**:

    + 这里使用的是python的os模块，使用popen()调用本地PS/CMD命令：

    + ```python
      a = os.popen('scp -r /filepath userName@host:/userName/...')
      print(a) 
      #这里因为本机的ssh已经被服务端信任,所以不需要输入密码(可以百度如何在Linux服务器中添加ssh_key)
      #请确保登录用户具有对应服务器文件路径的操作权限
      ```

    + 由于**os.popen**()这个函数是阻塞的，所以看到print输出证明推送完成。

  + **Restart Project**&&**clean log**

    + 最开始是写了一个关闭应用脚本和一个启动应用的脚本，但是为了全自动化，就同一改写到**Restart.sh**脚本中，该脚本包含了安全关闭应用，清除log日志，启动应用。

    + ```shell
      # 这里的ApplicationName.jar 就是SpringBoot 项目的启动类的Jar包名。
      PID=$(ps -ef | grep ApplicationName.jar | grep -v grep | awk '{ print $2 }')
      if [ -z "$PID" ]
      then
      echo Application is already stopped
      else
      echo kill $PID
      kill $PID
      fi
      #休眠两秒钟使得应用安全退出,当然也可以使用 kill -9 $PID 强制退出
      sleep 2
      
      # 清理项目控制台输出
      echo "" > ./nohup.out
      
      echo the Application is starting
      
      #重启项目,并且重定向项目控制台输出
      #后面的Spring.profiles.active 指定项目配置文件
      nohup java -jar /path/ApplicationName.jar --spring.profiles.active=dev  > ./nohup.out  &
      
      ```

    + 以上就是服务端的重启应用的操作，接下来就是如何在Python中一键完成自动部署的流程了。

  + **Do Restart**:

    + 一开始本来也想使用**os.popen()**的方式进行ssh_shell操作,但是发现这个API是不提供交互式操作的功能，万能的G告诉了我有**paramiko**这个模块，这个模块提供了两种ssh命令操作:**exec_command**()和**invoke_shell()**两种操作,其中invoke_shell()提供了交互式Shell操作，所以这里不讲**exec_command()**的使用。

    + ```shell
      #先安装paramiko moudle
      #根据我大清国情可以切换到清华源
      pip install paramiko
      ```

    + ```python
      import time
      import paramiko
      try:
          trans = paramiko.Transport(('127.0.0.1', 22))#这里填入自己服务器的IP HOST
          trans.connect(username='userName', password='******')#对应的账号密码
          ssh = paramiko.SSHClient()
          ssh._transport = trans
      
          client = ssh.invoke_shell()
      
      	#send()方法就是相当于进行Shell交互,记得命令最后加入换行表示在操作中按下ENTER
          client.send('cd /filePath\n')
      	#sleep保证上一条shell命令成功执行(是的,操作是有延迟的,保险一点留了一秒)
          time.sleep(1)
          client.send('./restart.sh\n')
          
          #sleep保证重启项目的完整进行
          time.sleep(4)
          output = client.recv(4048).decode('utf-8')
          print(output)
          print('success')
          trans.close()
      except paramiko.SSHException:
          print("fail")
      
      print("done")
      
      ```

+ 到这里一个全自动的项目部署就完成了,虽然仅仅是可用阶段,但是对于我来说还是省去了许多机械重复的步骤,毕竟代码存在的意义就是如此。

+ 最后放上完整的py文件：

  + ```python
    import os
    import time
    import paramiko
    
    print("start pushing")
    
    a = os.popen('scp -r /filepath userName@host:/userName/...')
    
    print(a,"successfully pushed") 
    
    try:
        trans = paramiko.Transport(('127.0.0.1', 22))
        trans.connect(username='userName', password='******')
        ssh = paramiko.SSHClient()
        ssh._transport = trans
    
        client = ssh.invoke_shell()
    
        client.send('cd /filePath\n')
        time.sleep(1)
        client.send('./restart.sh\n')
        
        time.sleep(4)
        output = client.recv(4048).decode('utf-8')
        print(output)
        print('success')
        trans.close()
    except paramiko.SSHException:
        print("fail")
    
    print("done")
    
    ```

+ **THINK MORE...**