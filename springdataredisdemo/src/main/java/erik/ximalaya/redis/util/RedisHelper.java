package erik.ximalaya.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class RedisHelper {

    public static <T> T execute(JedisPool jedisPool, JedisCallback<T> callback) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return callback.doInJedis(jedis);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
