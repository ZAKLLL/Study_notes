```bash
# 安装bbr
wget --no-check-certificate https://github.com/teddysun/across/raw/master/bbr.sh && chmod +x bbr.sh && ./bbr.sh
 # 重启系统
 reboot
 
 # 查看bbr状态
 sysctl net.ipv4.tcp_available_congestion_control
 
 #如果返回值如下则开启成功
 net.ipv4.tcp_available_congestion_control = reno cubic bbr
```



