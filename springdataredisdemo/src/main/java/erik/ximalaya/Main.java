package erik.ximalaya;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-context.xml");

        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);

        new Main().test_set_operation(redisTemplate.opsForSet());
//        redisTemplate.opsForSet().add("set","12");

    }

    public void test_set_operation(SetOperations setOperations){

        if(setOperations==null){
            return;
        }

        String setKey;
        for(int i = 0; i<4; i++){

            setKey=String.format("set_key_%d",i);
            List<String> valueList = new ArrayList<>();
            for(int j=0;j<4;j++){
                valueList.add(setKey+String.format(":value_%d",j));
            }
            setOperations.add(setKey,valueList.toArray(new String[0]));
            System.out.println(String.format(setKey+valueList));

        }

        for(int i = 0; i<4; i++) {

            setKey = String.format("set_key_%d", i);
            Set value = setOperations.members(setKey);
            System.out.println(String.format("key=%s ,value=%s",setKey,value));
        }

    }
}
