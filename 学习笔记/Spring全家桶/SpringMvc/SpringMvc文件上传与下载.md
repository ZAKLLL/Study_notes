#  文件上传与下载

### 文件上传

1.  jar包

   ```xml
   <!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
   <dependency>
       <groupId>commons-fileupload</groupId>
       <artifactId>commons-fileupload</artifactId>
       <version>1.3.3</version>
   </dependency>
   ```

2. 文件上传解析器mvc.xml中注册 

   ```xml
   <!-- 文件解析器 id必须如下multipartResolver-->
   <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
       <property name="maxUploadSize" value="102400000"></property>
       <property name="maxUploadSizePerFile" value="2000000"></property>
       <property name="defaultEncoding" value="UTF-8"></property>
   </bean>
   ```

3. 文件提交页面

   + 注意enctype="multipart/form-data"

   ```html
   <form action="${webpath}/test/p9" method="post" enctype="multipart/form-data">
       文件：<input type="file" name="file"><br>
               <input type="submit" value="上传">
   </form>
   ```

   + 多文件

     ```html
     <form action="${webpath}/test/p10" method="post" enctype="multipart/form-data">
         文件：<input type="file" name="file"><br>
         文件：<input type="file" name="file"><br>
         <input type="submit" value="上传">
     </form>
     ```

4.  Controller

   + 单文件上传

   ```java
   //File.separator保证了在各类系统下分隔符不出错
   private final static String uploadfilepath="E:"+File.separator;
   @RequestMapping("/p9")
   public String upload(@RequestParam("file") MultipartFile multipartFile, Model model) throws IOException {
       if (multipartFile!=null && !multipartFile.isEmpty()){
           //获取源文件名
           String originalfilename = multipartFile.getOriginalFilename();
           //取得文件名前缀
           String fileNamePrefix = originalfilename.substring(0, originalfilename.lastIndexOf("."));
           //格式化获取时间
           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
           //创建新文件名
           String newfilename = fileNamePrefix + sdf.format(new Date())+originalfilename.substring(originalfilename.lastIndexOf("."));
           File file = new File(uploadfilepath + newfilename);
           //执行上传操作
           multipartFile.transferTo(file);
           System.out.println(file.getAbsolutePath());
           model.addAttribute("filename", newfilename);
       }
       return "uploadsuccess";
   }
   ```

   + 多文件上传

     + ```java
       @RequestMapping("/p10")
       public String uploadfiles(@RequestParam("file") MultipartFile[] multipartFiles, Model model) throws IOException {
           List<String> filenames = new ArrayList<>();
           if (multipartFiles.length>0){
               for (MultipartFile multipartFile:multipartFiles){
                   if (multipartFile!=null && !multipartFile.isEmpty()){
                       //获取源文件名
                       String originalfilename = multipartFile.getOriginalFilename();
                       //取得文件名前缀
                       String fileNamePrefix = originalfilename.substring(0, originalfilename.lastIndexOf("."));
                       //格式化获取时间
                       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                       //创建新文件名
                       String newfilename = fileNamePrefix + sdf.format(new Date())+originalfilename.substring(originalfilename.lastIndexOf("."));
                       File file = new File(uploadfilepath + newfilename);
                       //执行上传操作
                       multipartFile.transferTo(file);
                       System.out.println(file.getAbsolutePath());
                       filenames.add(newfilename);
                   }
               }
           }
           model.addAttribute("filenames", filenames);
           return "uploadsuccess";
       }
       ```

       创建一个文件夹

       ```java
       private final static String uploadfilepath="E:"+File.separator;
       static File files = new File(uploadfilepath+"file1");
       static {
           if (!files.exists()) {
               files.mkdirs();
           }
           System.out.println(files.getPath());
       }
       ```

### 文件下载

+ 注意设置contentType 注意添加头部信息 ，注意字符编码

```java
 private final static String downloadpath="E:"+File.separator;
    @RequestMapping("/download")
    public String download(HttpServletResponse response){
        //定义该文件名
        String filename="gakki.jpg";
        //获取该文件的路径
        Path path = Paths.get(downloadpath, filename);
        if (Files.exists(path)){
            //获取文件类型名,从'.'后面一位获得
            String filesuffix=filename.substring(filename.lastIndexOf(".")+1);
            //设置contenttype,只有这样才能下载
            response.setContentType("application/"+filesuffix);
            //添加头部信息,注意字符编码
            try {
                response.addHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes("UTF-8"),"ISO8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //通过path进行下载,outputStream使用response.getOutputStream
            try {
                Files.copy(path, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
```

