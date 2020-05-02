+ Gradle:

  + ```groovy
    compile(
                'com.google.protobuf:protobuf-java:3.7.0',
                'com.google.protobuf:protobuf-java-util:3.7.0'
        )
    ```

+ google Protocol: 

  + ```properties
    syntax = "proto2";  //语法
     
    package tutorial;    //包名
    
    option java_package = "com.example.tutorial";   //在Java中的包名
    option java_outer_classname = "AddressBookProtos";  //生成类的类名
    
    message Person {    
      required string name = 1;
      required int32 id = 2;
      optional string email = 3;
    
      enum PhoneType {
        MOBILE = 0;
        HOME = 1;
        WORK = 2;
      }
    
      message PhoneNumber {
        required string number = 1;
        optional PhoneType type = 2 [default = HOME];
      }
    
      repeated PhoneNumber phones = 4;
    }
    
    message AddressBook {
      repeated Person people = 1;
    }
    ```

  + ``` shell
    protoc --java_out=src/main/java  src/protobuf/Student.proto #前面为输出位置，后面为源文件proto文件位置
    ```

  + java代码中生成对象实例  

    + ```java
      MyDateInfo.Student strdent = MyDateInfo.student.newBuilder().setName("张三").setAge(19).setAddress("天安门").build();
      
      ```

+ 传送不同的数据对象:

  + 最外层的包为Mymessage ，必须为该DateType指定一种类型，oneof限制只能出现一种类

  + ```protobuf
    syntax = "proto2";
    
    package com.zakl.protobuf;
    
    option optimize_for = SPEED;
    
    option java_package = "com.zakl.netty.sixthexample";
    option java_outer_classname = "MyDateInfo";
    
    
    message MyMessage {
        enum DateType {
            PersonType = 1;
            DogType = 2;
            CatType = 3;
        }
        //要求DateType为必须设置的字段
        required DateType date_type = 1;
    
        //三种类型同一时刻只能出现一个
        oneof dateBody {
            Person person = 2;
            Dog dog = 3;
            Cat cat = 4;
        }
    
    }
    
    message Person {
        optional string name = 1;
        optional int32 age = 2;
        optional string address = 3;
    }
    
    message Dog {
        optional string name = 1;
        optional int32 age = 2;
    }
    
    message Cat {
        optional string name = 1;
        optional string city = 2;
    
    }
    ```

+ git submodule: git 仓库里面的一个仓库
