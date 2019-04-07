+ mongod：

  ​	mongod--dbpathdir//打开或者新建一个数据库

+ mongoimport(导入数据)：

  ​	mongoimport  --db  dbname  --collection  collectionname--drop  --file  filepath

  ​	--db导入到哪个库

  ​	一-collection导入到哪个集合

  ​	--drop加上就表示清空原有文档

  ​	--file要导入的文件

+ mongo：

  ​	use dbname//创建一个新的库

  ​	show dbs      //查看所有的苦

  ​	show collection //查看当前库所有集合  

  ----------

  + 增：

  ​	db．collectionName．insert(obj）//在名为collectionName的集合中插入一条文档如果集合不存在则新建该集合	

  + 删：

     db.dropdatabase()       //删除当前所在数据库

    db.collectionName.drop() //删除名为collectionName的集合

    db.collectionName.remove({k1:v1}) //删除所有匹配到{k1:v1}的文档

    db.collectionName.remove({k1:v1}，{justOnce:true}) //删除第一个匹配到{k1:v1}的文档

    db.collectionName.remove() //清空collectionName集合，集合本身保留

    

    

    

  + 查：

  ​	db．collectiormame．find（0）//查询所有文档

  ​	db．collectionName．find（{k：v}）//查询k的值加的文档

  ​	db．collectionName．find（{k1：v1},{k2，V2})//查询k1的值为v1且k2的值为V2的文档

  ​	db.	collectionName.	find({$or：[{k1:v1}，{k2：v2}]})//查询k1的值为v1或k2的值为v2的文档

  ​	db.	collectionName.	find({k：{$gt：v}})//查询k的值大于v的文档

  ​	db.c011ectionName.find(k：{$lt：v}})//查询k的值小于v文档

  + 改

    修改文档：

    db.collectionName.update{

    {k1:v1},

    ​      {

    ​	$set:{k2:v2},

    ​       }

    }

    替换文档:

    db.collectionName.update{

    ​	{k1:v1},

    ​	{

    ​		k2:v2

    ​	}

    }

  + 排序：

    db.collectionName.find().sort({k1:1},{k2:-2}) //将查询到的结果按照k1来排序，如果K1的值相同，则按照k2来排序，1是升序，-1是降序

  