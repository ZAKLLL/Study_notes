#!/bin/bash
#autor: gfw-breaker

# 如果脚本安装nginx失败，手动安装nginx再运行脚本


## ss parameters
ss_port=443
ss_password="1015849735"
ss_method="aes-256-cfb"
ss_protocol="auth_sha1_v4_compatible"
ss_obfs="tls1.2_ticket_auth_compatible"

## install ss/ssr
yum install -y git epel-release nginx
git clone https://github.com/gfw-breaker/onekey-ssr.git
cd onekey-ssr
chmod +x *.sh
./install.sh

## generate ssr config file
config_file=/etc/shadowsocksr/user-config.json
mkdir /etc/shadowsocksr

cat > $config_file <<EOF
{
    "server": "0.0.0.0",
    "server_ipv6": "::",
    "server_port": $ss_port,
    "local_address": "127.0.0.1",
    "local_port": 1080,

    "password": "$ss_password",
    "method": "$ss_method",
    "protocol": "$ss_protocol",
    "protocol_param": "",
    "obfs": "$ss_obfs",
    "obfs_param": "",
    "speed_limit_per_con": 0,
    "speed_limit_per_user": 0,

    "additional_ports" : {},
    "timeout": 120,
    "udp_timeout": 60,
    "dns_ipv6": false,
    "connect_verbose_info": 0,
    "redirect": "",
    "fast_open": false
}
EOF


## restart ssr
service ssr restart

## start nginx (used for checking network)
service nginx start

## disable iptables
service iptables stop
chkconfig iptables off

## use Google DNS
echo nameserver 8.8.8.8 > /etc/resolv.conf
echo nameserver 8.8.4.4 >> /etc/resolv.conf

