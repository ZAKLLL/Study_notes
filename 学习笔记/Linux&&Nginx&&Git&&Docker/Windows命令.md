

+ 根据端口查询进程: `netstat -ano|findstr '8080'`

+ 强杀进程: ` taskkill  /pid -f  'pid'`

+ 强杀某端口进程脚本:

  ```bat
  chcp 65001
  @echo off
  :begin
  echo ====指定southgnssweb运行端口(默认8090)=========
  echo 0 退出
  
  set /p port=请输入启动端口
  if "%port%"=="0" exit
  if "%port%"=="" set port=8090
  
  for /f "tokens=1-5" %%i in ('netstat -ano^|findstr ":%port%"') do (
      echo kill the process %%m who use the port
      taskkill /pid %%m -t -f
      goto :CICD
  )
  ```

+ 根据启动信息查询某进程

  ```bat
  > wmic process get processid,caption,commandline | find "java.exe" | find "server-1.properties"
  
  java.exe    java  -Xmx1G -Xms1G -server -XX:+UseG1GC ... build\libs\kafka_2.11-0.11.0.2.jar"  kafka.Kafka config\server-1.properties    644
  ```

  

