package erik.spring.schedule.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println(String.format("fixedRated now %s", dateFormat.format(new Date())));
    }


    @Scheduled(cron = "3/10 * * * * ?")
    public void reportCurrentTimeWithCron() {
        System.out.println(String.format("cron now %s", dateFormat.format(new Date())));
    }


}