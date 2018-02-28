package erik.ximalaya;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RedisTemplateTest extends AbstractTest{

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test_set_operation(){

        SetOperations setOperations = redisTemplate.opsForSet();
        test_set_operation(setOperations);

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
