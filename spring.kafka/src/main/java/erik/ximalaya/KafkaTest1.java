package erik.ximalaya;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KafkaTest1 {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.3.70:9092");//服务器ip:端口号，集群用逗号分隔
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer producer = new KafkaProducer<>(props);
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String message = String.format("message to send kafka %d 1107-1", i);
            producer.send(new ProducerRecord<String, String>("adCoupon.allocated", message));
            System.out.println(message);
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over!");


    }
}
