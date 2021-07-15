+ 服务端时区

  ```java
  2021-07-13T00:00:00.000+00:00
  表示 UTC+0,的时区对应的时间为 2021-07-13 00:00:00.000
  如果本机时区为 UTC+8,则此时间在本机上对应时间为 2021-07-13 08:00:00.000
  ```


+ 浏览器内嵌显示:

  ```java
  response.setHeader("Content-disposition", "inline;fileName=" + fileName);
  ```




+ **META-INF/resources**: 此目录下的资源文件可在打包成jar之后直接访问。