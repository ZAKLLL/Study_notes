APP=property-manage-service
APP_NAME=${APP}".jar"

log_dir=/home/program/jar_logs/
log_file=/home/program/jar_logs/"${APP}".log

command=$1

# 启动
function start(){
    # 日志文件夹不存在，则创建
    if [ ! -d "${log_dir}" ];then
        mkdir "${log_dir}"
    fi

    rm -f tpid
    nohup java -Xdebug -Xrunjdwp:transport=dt_socket,suspend=n,server=y,address=38082 -jar ${APP_NAME} --spring.profiles.active=test 1>/dev/null 2>"${log_file}" &
    echo $! > tpid
    check
}

# 停止
function stop(){
    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${tpid} ]; then
        echo 'stop process...'
        kill -15 $tpid
    fi

    sleep 5

    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${tpid} ]; then
        echo 'Kill Process!'
        kill -9 $tpid
    else
        echo 'Stop Success!'
    fi
}

# 检查
function check(){
    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${tpid} ]; then
        echo '$APP is running.'
    else
        echo '$App is NOT running.'
    fi

}

# 强制kill进程
function forcekill(){
    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`

    if [ ${tpid} ]; then
        echo 'Kill Process!'
        kill -9 $tpid

    fi

}

# 输出进程号
function showtpid(){
    tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
    if [ ${tpid} ]; then
        echo 'process '$APP_NAME' tpid is '$tpid
    else
        echo 'process '$APP_NAME' is not running.'
    fi
}

if [ "${command}" ==  "start" ]; then
    start

elif [ "${command}" ==  "stop" ]; then
     stop

elif [ "${command}" ==  "check" ]; then
     check

elif [ "${command}" ==  "kill" ]; then
     forcekill

elif [ "${command}" == "tpid" ];then
     showtpid

else
    echo "Unknow argument...."
fi