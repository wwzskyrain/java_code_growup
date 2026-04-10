package erik.ximalaya;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.concurrent.*;

public class KafkaTest {

    public static void main(String[] args) throws InterruptedException {

        String applicationContextPath = "file:/Users/nali/project_erik/java_code_growup/spring.kafka/src/main/java/resources/application-context.xml";

        ApplicationContext applicationContext = new FileSystemXmlApplicationContext(applicationContextPath);

        KafkaProducer kafkaProducer = applicationContext.getBean(KafkaProducer.class);

        String topicName = "adCoupon.allocated";

        ArrayList<Future<RecordMetadata>> futures = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            String message = String.format("message to send kafka %d 1107-5", i);
            kafkaProducer.send(new ProducerRecord<String, String>("adCoupon.allocated", message));
            System.out.println(message);
        }

//        for (Future<RecordMetadata> future : futures) {
//            try {
//                RecordMetadata recordMetadata = future.get();
//                System.out.println(JSON.toJSONString(recordMetadata));
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }

        TimeUnit.SECONDS.sleep(1);


    }
}
