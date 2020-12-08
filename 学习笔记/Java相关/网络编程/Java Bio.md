#  i/O流程结构:核心概念---Stream(信息载体)

![1554425840523](../../images/1554425840523.png)



## InputStream : 写入流，从外部往程序中读取数据都可以看作为inputStream

+ ![1554425549112](../../images/1554425549112.png)

```java
//写入流，添加缓冲装饰器
BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("path"));

//Dateinpustream也是处理流，装饰器，可以直接从steam中读取java原生数据类型
```





## OutpuitStream : 读出流，从程序往外部写出数据都可以看作为OutputStream

+ ![1554425697147](../../images/1554425697147.png)

  + ![1554425656463](../../images/1554425656463.png)
  + ![1554425672527](../../images/1554425672527.png)

  ```java
  //读出流，添加读出缓冲器
  BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("path"));
  ```

  

  

  ## Reader : 文本写入流

+ ![1554426040959](../../images/1554426040959.png)

  

  ```java
  BufferedReader fileReader =new BufferedReader(new FileReader("NioTestIn_13.txt")) ;//默认使用ISO-8859-1编码
  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("filepath"),"UTF-8"));
  ```

  

  ##  Writer ：文本读出流

+ ![1554426052064](../../images/1554426052064.png)

+ 写入读出都是相对程序本身而言的.

```java
BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("OOOOOutput.txt")));
        System.out.println("PrintStream:"+PrintStream.class.getSuperclass().getName());

        while (true) {
            InputStream printStream = System.in;
            InputStreamReader inputStreamReader = new InputStreamReader(printStream);
            BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader);

            String str = bufferedReader1.readLine();
            if (str.equals("bye")) {
                break;
            }
            bufferedWriter.write(str);
        }
        bufferedWriter.close();
```

