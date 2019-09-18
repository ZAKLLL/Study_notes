+ demo gradle文件

  + ```groovy
    buildscript {
        repositories {
            mavenCentral()
        }
        dependencies {
            classpath 'com.google.protobuf:protobuf-gradle-plugin:0.8.8'
        }
    }
    plugins {
        id 'java'
    }
    apply plugin: 'com.google.protobuf'
    
    
    group 'com.zakl.netty'
    version '1.0-SNAPSHOT'
    
    sourceCompatibility = 1.8
    targetCompatibility = 1.8
    
    
    repositories {
        mavenCentral()  //远程访问
    }
    
    dependencies {
        compile(
                "io.netty:netty-all:4.1.10.Final",
                'com.google.protobuf:protobuf-java:3.7.0',
                'com.google.protobuf:protobuf-java-util:3.7.0',
                'io.grpc:grpc-netty-shaded:1.19.0',
                'io.grpc:grpc-protobuf:1.19.0',
                'io.grpc:grpc-stub:1.19.0',
                'log4j:log4j:1.2.17'
        )
    
    }
    
    protobuf {
        protoc {
            artifact = "com.google.protobuf:protoc:3.6.1"
        }
        plugins {
            grpc {
                artifact = 'io.grpc:protoc-gen-grpc-java:1.19.0'
            }
        }
        generateProtoTasks.generatedFilesBaseDir = 'src' // <- that line
        generateProtoTasks {
            all()*.plugins {
                grpc {}
            }
        }
    }
    ```

  + 

+ proto文件(文件位置默认放置在src/main/proto  可以通过sourceset更改默认文件地址)

  + ```protobuf
    syntax = "proto3";
    
    package com.zakl.protobuf;
    
    option optimize_for = SPEED;
    
    option java_package = "com.zakl.proto";
    option java_outer_classname = "StudentProto";
    option java_multiple_files = true;
    
    service StudentService {
    	
    	//实例调用，实例返回
        rpc GetRealNameByUserName (MyRequest) returns (MyResponse) {}
    		
    	//实例调用，流式返回
        rpc GetStudentsBuAge(StudentRequest) returns(stream StudentResponse){}
    	
    	//流式调用，流式返回
        rpc GetStudentWrapperByAges(stream StudentRequest)returns(StudentResponseList){};
    	
    	//流式调用，流式返回
        rpc GetStudentWrapperByAges2(stream StudentRequest)returns(stream StudentResponse){}
    
    
    }
    
    
    message MyRequest {
        string username = 1;
    }
    
    
    message MyResponse {
        string realname = 2;
    }
    
    message StudentRequest{
        int32 age=1;
    
    }
    
    
    message StudentResponse {
        string name = 1;
        string age = 2;
        string city = 3;
    }
    
    message StudentResponseList{
        repeated StudentResponse studentResponse=1;
    }
    
    ```

  + 生成代码命令

    ```shell
    gladle clean generateProto
    ```

+ 服务端代码

  + ```java
    package com.zakl.netty.grpc;
    
    import io.grpc.Server;
    import io.grpc.ServerBuilder;
    
    import java.io.IOException;
    
    /**
     * @program: netty_lecture
     * @description:
     * @author: ZakL
     * @create: 2019-04-03 19:33
     **/
    public class GrpcServer {
        private Server server;
    
        private void start() throws IOException {
    
    		//添加服务的实现
            this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();
            System.out.println("Server Started!");
    
            //关闭当前线程的jvm
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                    System.err.println("*** shutting down gRPC server since JVM is shutting down");
                    GrpcServer.this.stop();
                    System.err.println("*** server shut down");
                }
            });
            System.out.println("执行到这里");
        }
    
        private void stop() {
            if (this.server != null) {
                this.server.shutdown();
            }
        }
    
        private void awaitTermination() throws InterruptedException {
            if (this.server != null) {
                this.server.awaitTermination();
            }
        }
    
        public static void main(String[] args) throws IOException, InterruptedException {
    
            GrpcServer grpcServer = new GrpcServer();
            grpcServer.start();
            grpcServer.awaitTermination();
    
        }
    
    }
    ```

+ 服务实现代码：

  + ```java
    
    package com.zakl.netty.grpc;
    
    import com.zakl.proto.*;
    import io.grpc.stub.StreamObserver;
    import org.apache.log4j.Level;
    import org.apache.log4j.Logger;
    
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.stream.Stream;
    
    /**
     * @program: netty_lecture
     * @description:
     * @author: ZakL
     * @create: 2019-04-03 19:24
     **/
    public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    
        Logger logger = Logger.getLogger(this.getClass());
    
    
        //实例调用，实例返回实现
        @Override
        public void getRealNameByUserName(MyRequest request, StreamObserver<MyResponse> responseObserver) {
            System.out.println("接收到客户端信息： " + request.getUsername());
            responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
            responseObserver.onCompleted();
        }
    
        //实例调用，流式返回
        @Override
        public void getStudentsBuAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
            StudentResponse response1 = StudentResponse.newBuilder().setAge("17").setCity("珠海").setName("二狗").build();
            StudentResponse response2 = StudentResponse.newBuilder().setAge("17").setCity("背景").setName("网三").build();
            List<StudentResponse> list = new ArrayList<>();
            list.add(response1);
            list.add(response2);
    
            Stream<StudentResponse> stream = list.stream().filter(x -> Integer.parseInt(x.getAge()) >= request.getAge());
    
            stream.forEach(x -> responseObserver.onNext(x));
            responseObserver.onCompleted();
        }
    
        //流式请求,单例返回,使用回调函数
        @Override
        public StreamObserver<StudentRequest> getStudentWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
            return new StreamObserver<StudentRequest>() {
    
    
                @Override
                public void onNext(StudentRequest studentRequest) {
                    System.out.println(studentRequest.getAge());
                }
    
                @Override
                public void onError(Throwable t) {
                    logger.info(Level.WARN, t);
                }
                //当客户端完成流式请求的时候调用此方法
                @Override
                public void onCompleted() {
                    StudentResponse studentResponse1 = StudentResponse.newBuilder().setName("张三").setAge("17").setCity("珠海").build();
                    StudentResponse studentResponse2 = StudentResponse.newBuilder().setName("王二").setAge("18").setCity("上海").build();
                    StudentResponse studentResponse3 = StudentResponse.newBuilder().setName("leave").setAge("19").setCity("北京").build();
                    StudentResponse studentResponse4 = StudentResponse.newBuilder().setName("黑天").setAge("17").setCity("广州").build();
                    List<StudentResponse> list = Arrays.asList(
                            studentResponse1,
                            studentResponse2,
                            studentResponse3,
                            studentResponse4
                    );
    
                    StudentResponseList studentResponseList = StudentResponseList.newBuilder()
                            .addStudentResponse(studentResponse3).addStudentResponse(studentResponse4)
                            .addStudentResponse(studentResponse1).addStudentResponse(studentResponse2).build();
    
                    responseObserver.onNext(studentResponseList);
    
                    //当返回完毕后，此方法调用客户端的onCompleted();
                    responseObserver.onCompleted();
                }
            };
        }
    
        //流式请求，流式返回
        @Override
        public StreamObserver<StudentRequest> getStudentWrapperByAges2(StreamObserver<StudentResponse> responseObserver) {
            return new StreamObserver<StudentRequest>() {
                @Override
                public void onNext(StudentRequest value) {
                    System.out.println(value.getAge());
                    responseObserver.onNext(StudentResponse.newBuilder().setAge(String.valueOf(value.getAge())).setName("张二").setCity("北京").build());
                }
    
                @Override
                public void onError(Throwable t) {
                    t.printStackTrace();
                }
    
                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };
        }
    }
    
    ```

  + 

+ 客户端

  + ```java
    public class GrpcClient {
        public static void main(String[] args) throws InterruptedException {
            ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext(true).build();
    
            //同步调用，非流式调用
            StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
    
            //异步调用
            StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);
        }
    }
    ```

    + 四种调用方式

      1. ```java
         //实例参数调用，实例返回
                 MyResponse realNameByUserName = blockingStub.getRealNameByUserName(MyRequest.newBuilder().setUsername("张三张三你是谁").build());
                 System.out.println(realNameByUserName.getRealname());
         ```

      2. ```java
         /实例调用，流返回
                 Iterator<StudentResponse> studentsBuAge = blockingStub.getStudentsBuAge(StudentRequest.newBuilder().setAge(17).build());
                 while (studentsBuAge.hasNext()) {
                     System.out.println(studentsBuAge.next().toString());
                 }
         ```

      3. ```java
         //流式调用，实例返回 ,需要异步调用
                 StreamObserver<StudentResponseList> responseListStreamObserver = new StreamObserver<StudentResponseList>() {
         
                     @Override
                     public void onNext(StudentResponseList value) {
                         value.getStudentResponseList().forEach(studentResponse -> {
                             System.out.println(studentResponse.getAge());
                             System.out.println(studentResponse.getCity());
                             System.out.println(studentResponse.getName());
                         });
                     }
         
                     @Override
                     public void onError(Throwable t) {
                         t.printStackTrace();
                     }
         
                     @Override
                     public void onCompleted() {
                         System.out.println("onCompleted");
                     }
                 };
         
                 StreamObserver<StudentRequest> studentRequestStreamObserver = stub.getStudentWrapperByAges(responseListStreamObserver);
         
                 studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(10).build());
                 studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
                 studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
                 studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
         
                 //当发送流结束后，回调服务端的onCompleted
                 studentRequestStreamObserver.onCompleted();
         		//异步调用，使用线程休眠的方式来等待服务端返回数据，否者jvm直接关闭了
                 Thread.sleep(50000);
         ```

      4. ```java
         //双向流传输
                 StreamObserver<StudentRequest> requestStreamObserver = stub.getStudentWrapperByAges2(new StreamObserver<StudentResponse>() {
                     @Override
                     public void onNext(StudentResponse value) {
                         System.out.println(value.getName() + value.getCity() + value.getAge());
                     }
         
                     @Override
                     public void onError(Throwable t) {
                         t.printStackTrace();
                     }
         
                     @Override
                     public void onCompleted() {
                         System.out.println("onCompleted");
                     }
                 });
         
                 for (int i = 0; i < 10; i++) {
                     requestStreamObserver.onNext(StudentRequest.newBuilder().setAge(10 + i).build());
                     Thread.sleep(1000);
                 }
                 requestStreamObserver.onCompleted();
                 Thread.sleep(5000);
         ```







+ Node.js 生成静态代码(在static_codegen文件下，从proto/Srudent.proto文件中获取生成信息)

  + ```shel
    grpc_tools_node_protoc --js_out=import_style=commonjs,binary:static_codegen/ --grpc_out=static_codegen --plugin=protoc-gen-grpc=/c/Users/HP/AppData/Roaming/npm/grpc_tools_node_protoc_plugin.cmd  proto/Srudent.proto
    ```

  + 