package erik.spring.redis.main;

import com.alibaba.fastjson.JSON;
import erik.spring.redis.config.RedisTemplateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisTemplateConfig.class})
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() {

        Map<String, Object> properties = new HashMap<>();

        properties.put("key-123", "value-123");
        properties.put("key-abc", 456);

        redisTemplate.opsForHash().putAll("hashKey", properties);

        Map<String, Object> ans = redisTemplate.opsForHash().entries("hashKey");
        System.out.println("ans:" + ans);

        for (Map.Entry<String, Object> entry : ans.entrySet()) {
            System.out.println(JSON.toJSONString(entry));
        }

        redisTemplate.opsForList();

    }

    @Test
    public void testRedisOpsForHash(){



    }

}
