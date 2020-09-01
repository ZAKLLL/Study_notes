import paramiko
import os
import time

try:
    trans=paramiko.Transport(('120.76.62.44',22))
    trans.connect(username='root',password='ZWZlove520')
    ssh=paramiko.SSHClient()
    ssh._transport=trans
    channel=ssh.invoke_shell()

    channel.send('cd /root/aigongjiao\n')    
    time.sleep(1)
    channel.send('./restart.sh\n')    
    time.sleep(4)
    output=channel.recv(4048).decode('utf-8')
    print(output)
    print('success')
    trans.close()
except paramiko.SSHException:
    print("fail")