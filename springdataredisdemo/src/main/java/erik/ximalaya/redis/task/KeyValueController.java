package erik.ximalaya.redis.task;

import erik.ximalaya.redis.model.KeyValueEntry;
import erik.ximalaya.redis.util.JedisCallback;
import erik.ximalaya.redis.util.RedisHelper;
import redis.clients.jedis.*;

import java.util.Collections;
import java.util.List;

public class KeyValueController {

    JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


    public void getKeyValue(String pattern, final int pageNum, final int pageSize) {

        RedisHelper.execute(jedisPool, new JedisCallback<List<KeyValueEntry>>() {

            @Override
            public List<KeyValueEntry> doInJedis(Jedis jedis) {

                ScanParams scanParams = new ScanParams();
                scanParams.match(pattern.getBytes());

                scanParams.count(100);

                ScanResult<String> scanResult = new ScanResult<String>("0", Collections.EMPTY_LIST);

                if (pageNum == 1) {
                    scanResult = jedis.scan("0", scanParams);

                } else {
                    scanResult = jedis.scan(scanResult.getStringCursor(), scanParams);
                }

                List<String> keysList = scanResult.getResult();


                RedisHelper.execute(jedisPool, new JedisCallback<List<KeyValueEntry>>() {

                    @Override
                    public List<KeyValueEntry> doInJedis(Jedis jedis) {

                        Pipeline pipeline = jedis.pipelined();

//                        pipeline.hgetAll()
                        return null;
                    }

                });

                return null;
            }

        });

    }


    public static void main(String[] args) {

        JedisPool jedisPool = new JedisPool("localhost");

        Jedis jedis = jedisPool.getResource();


        List<String> values = jedis.mget(new String[]{"kev_dream_001", "kev_dream_001", "kev_dream_001"});


    }


}
