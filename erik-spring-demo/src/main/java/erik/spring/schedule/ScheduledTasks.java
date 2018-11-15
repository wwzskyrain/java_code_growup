package erik.spring.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws InterruptedException {

        log.info("start job at：{}",dateFormat.format(new Date()));

        TimeUnit.SECONDS.sleep(8);

        log.info("end job at:{} in thread:{}", dateFormat.format(new Date()), Thread.currentThread().getName());
    }
}