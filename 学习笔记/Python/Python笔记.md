# Python



### 基础

+ ```python
  f = open("test.txt"，'wb') #open 是打开创建/函数 'wb'是以二进制方式写入
  ```

## Python&Mongodb

+ 连接数据库

  + ```python
    myclient = pymongo.MongoClient("mongodb://127.0.0.1:27017/?gssapiServiceName=mongodb")
        mydb = myclient["runoobdb"] #使用runoobdb库
        mycol = mydb["sites"]  #使用runnoobdb库中的sites集合
    ```

+ 增：

  + ```python
    mylist = [
            {"name": "Taobao", "alexa": "100", "url": "https://www.taobao.com"},
            {"name": "QQ", "alexa": "100", "url": "https://www.qq.com"},
            {"name": "Facebook", "alexa": "10", "url": "https://www.facebook.com"},
            {"name": "知乎", "alexa": "103", "url": "https://www.zhihu.com"},
            {"name": "Github", "alexa": "109", "url": "https://www.github.com"}
        ]
        x = mycol.insert_many(mylist) #使用insert或insert_one 插入一条数据
    ```

+ 删：

  + ```python
    mycol.delete_one({"name":"QQ"}) #可以使用delete_many删除更多匹配的数据
    ```

+ 改：

  + ```python
    mycol.update({"name":"QQ"},{'$set':{'alexa':'6666'}}) #更新数据
    ```

+ 查：

  + ```python
    rs = mycol.find({},{"name":1,"_id":0,"url":1,'alexa':1}).sort("alexa",-1).limit(3)
    #find方法第一个参数为查询条件，第二个参数为需要查询的结果
    #除了 _id 你不能在一个对象中同时指定 0 和 1，如果你设置了一个字段为 0，则其他都为 1，反之亦然。
    #sort()为排序方式,第一个参数指明排序字段，第二个参数指明排序方式，默认为1升序，-1降序
    #limit()表示限制条数返回
    ```

+ 模糊查询:

  +  ```python
    rs = mycol.find({"name": {"$regex": "^F"}}) #需要使用$regex表明是正则表达式
    ```

    
    

