package com.nowcoder.community;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTests {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}

class Producer implements Runnable {

    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(20);
                queue.put(i);
                System.out.println(Thread.currentThread().getName() + "生产:" + queue.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {

    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(new Random().nextInt(1000));
                queue.take();
                System.out.println(Thread.currentThread().getName() + "消费:" + queue.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/* Note：Kafka在Win10下简单使用:
   1. Dowload:http://kafka.apache.org/downloads
   2. 解压后配置:
      afka_2.12-2.2.0\config\zookeeper.properties: dataDir=D:/work/data/zookeeper
      kafka_2.12-2.2.0\config\server.properties  : log.dirs=D:/work/data/kafka-logs
   3. 启动:
 new cmd   cd kafka_2.12-2.2.0 dir
          >bin\windows\zookeeper-server-start.bat config\zookeeper.properties
 new cmd: >bin\windows\kafka-server-start.bat config\server.properties
          观察上面配置的目录是被创建并有新文件产生
 new cmd: cd kafka_2.12-2.2.0\bin\windows                                (默认端口)                （创建1个副本）  （1个分区） (topic名字)
          创建主题:   kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
          查看主题:   kafka-topics.bat --list --bootstrap-server localhost:9092
    4.使用:
        生产者发送消息:
        kafka-console-producer.bat --broker-list localhost:9092 --topic test
        >hello
        >world
        >
 new cmd: cd kafka_2.12-2.2.0\bin\windows
          kafka-console-consumer.bat --bootstrap-server localhost:9092 -topic test --from-beginning

 */

/* Note: ES在Win10下简单使用(elastic.co):
   1. Dowload: https://www.elastic.co/cn/downloads/ ->选ES ->考虑兼容性选past releases->下载https://www.elastic.co/cn/downloads/past-releases/elasticsearch-6-4-3
   2. 解压后配置:
            config目录elasticsearch.yml: #cluster.name: my-application->cluster.name: nowcoder
                                         #path.data: /path/to/data->path.data: d:\work\data\elasticsearch-6.4.3\data
                                         #path.logs: /path/to/logs->path.logs: d:\work\data\elasticsearch-6.4.3\logs
   3. 配置环境变量:
            PATH: elasticsearch-6.4.3\bin
   4. Download 中文分词插件:https://github.com/medcl/elasticsearch-analysis-ik
                           https://github.com/medcl/elasticsearch-analysis-ik/releases/tag/v6.4.3
   5. 在elasticsearch-6.4.3\plugins目录下新建ik目录，并将解压这个插件到这个目录下 config/IKAnalyzer.cfg.xml这个文件可用于拓展新词

   6. 下载postman:https://www.postman.com/downloads/

   7. 启动ES: 双击elasticsearch-6.4.3\bin下的elasticsearch.bat启动(初次较慢, 标志:publish_address {127.0.0.1:9200}, bound_addresses {127.0.0.1:9200}, {[::1]:9200})

   8. 查看ES健康状态: cmd -> curl -X GET "localhost:9200/_cat/health?v"
            节点数:         curl -X GET "localhost:9200/_cat/nodes?v"
            列出索引(表):   curl -X GET "localhost:9200/_cat/indices?v"
            建立索引:       curl -X PUT "localhost:9200/test"
            删除索引:       curl -X DELETE "localhost:9200/test"

   9. 用postman测试:
            列出索引:       GET  localhost:9200/_cat/indices?v
            建立索引:       PUT  localhost:9200/test
            删除索引:     DELETE localhost:9200/test
            建立文档:       PUT  localhost:9200/test/_doc/1    (若test不在则自动创建)
                           Body raw -> JSON
                                 {
                                    "title":"hello",
                                    "content":"How are you!"
                                 }
            查询文档:       GET  localhost:9200/test/_doc/1
            删除文档:     DELETE localhost:9200/test/_doc/1
            搜索:         GET    localhost:9200/test/_search
                         找出title中包含"互联网"的项:   localhost:9200/test/_search?q=title:互联网
                                                      localhost:9200/test/_search?q=content:运营实习 (会分词)
                         找出包含互联网的项("全文搜索"):            localhost:9200/test/_search
                                Body raw ->JSON
                                   {
                                        "query":{
                                            "multi_match":{
                                                "query":"互联网",
                                                "fields":["title", "content"]
                                            }
                                        }
                                    }

 */
