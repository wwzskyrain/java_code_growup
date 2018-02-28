package erik.ximalaya.redis.util;

import redis.clients.jedis.Jedis;

public interface JedisCallback<T> {

    public T doInJedis(Jedis jedis);
}
