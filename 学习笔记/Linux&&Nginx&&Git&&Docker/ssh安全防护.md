1.修改root用户名
	vi /etc/passwd
	修改第1行第1个root为新的用户名
	vi /etc/shadow
	修改第1行第1个root为新的用户名

2.修改ssh登录端口
	vi /etc/ssh/sshd_config
	Port 22 #将注释打开，并且将默认的端口22修改为需要的端口号
	修改认证次数：
	MaxAuthTries 3
	#AddressFamily any
	#ListenAddress 0.0.0.0
	#ListenAddress ::

	重启ssh服务
	systemctl restart sshd

3.查看ssh登录失败前100信息：
	lastb | awk '{print $3}' | sort | uniq -c | sort -nr | head -n 100

4.禁止某个ip进行ssh访问：
	vim /etc/hosts.deny
	格式  sshd:$IP:deny
	例如：sshd:192.168.1.147:deny

	这是允许的 /etc/hosts.allow 
	sshd:19.16.18.1:allow
	sshd:19.16.18.2:allow

5.设置多次失败登录即封掉IP,防止暴力破解的脚本
	vim /usr/local/bin/secure_ssh.sh
	touch /usr/local/bin/black.txt #添加登录失败次数
	脚本内容（5次失败禁止）：
	#! /bin/bash
	cat /var/log/secure|awk '/Failed/{print $(NF-3)}'|sort|uniq -c|awk '{print $2"="$1;}' > /usr/local/bin/black.txt
	for i in `cat  /usr/local/bin/black.txt`
	do
	  IP=`echo $i |awk -F= '{print $1}'`
	  NUM=`echo $i|awk -F= '{print $2}'`
	   if [ $NUM -gt 5 ];then
		  grep $IP /etc/hosts.deny > /dev/null
		if [ $? -gt 0 ];then
		  echo "sshd:$IP:deny" >> /etc/hosts.deny
		fi
	  fi
	done

	修改权限：
	chmod 777 /usr/local/bin/black.txt /usr/local/bin/secure_ssh.sh
	
	添加定时任务：
	crontab -e
	*/5 * * * *  sh /usr/local/bin/secure_ssh.sh