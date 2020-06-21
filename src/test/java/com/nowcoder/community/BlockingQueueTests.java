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
