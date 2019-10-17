+ 开启主节点：

  + ```bash
    hadoop jar /usr/local/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.2.jar pi 10 10
    ```

+ 开启从节点：

  + ```bash
    docker run -itd --net=hadoop --name hadoop-slave1 --hostname hadoop-slave1 kiwenlau/hadoop:1.0 
    ```

    

+ WordCount:

  + ```bash
    #!/bin/bash
    
    # test the hadoop cluster by running wordcount
    
    # create input files 
    mkdir input
    echo "Hello Docker" >input/file2.txt
    echo "Hello Hadoop" >input/file1.txt
    
    # create input directory on HDFS
    hadoop fs -mkdir -p input
    
    # put input files to HDFS
    hdfs dfs -put ./input/* input
    
    # run wordcount 
    hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.7.2-sources.jar org.apache.hadoop.examples.WordCount input output
    
    # print the input files
    echo -e "\ninput file1.txt:"
    hdfs dfs -cat input/file1.txt
    
    echo -e "\ninput file2.txt:"
    hdfs dfs -cat input/file2.txt
    
    # print the output of wordcount
    echo -e "\nwordcount output:"
    hdfs dfs -cat output/part-r-00000
    
    ```

  + 