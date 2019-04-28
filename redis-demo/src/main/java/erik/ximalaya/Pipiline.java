package erik.ximalaya;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @date 2019/04/20
 **/
public class Pipiline {


    public static void main(String[] args) {

//        new Pipiline().test_lock();
//        new Pipiline().test_z_count();

        new Pipiline().test_pipeline();

    }

    /**
     * 使用redis的pipeline和获取返回值
     */
    public void use_pipeline_and_get_response() {

        Jedis jedis = new Jedis("127.0.0.1", 6381);
        jedis.auth("erik.wang");

        jedis.select(15);
        jedis.flushDB();
        Pipeline pipeline = jedis.pipelined();

        Response<Long> setKey1 = pipeline.setnx("key1", "key1");
        Response<Long> setKey2 = pipeline.setnx("key2", "key2");
        Response<Long> setKey3 = pipeline.setnx("key1", "key1-after");

        //
        List<Object> returnAll = pipeline.syncAndReturnAll();
        System.out.println(setKey1.get());
        System.out.println(setKey2.get());
        System.out.println(setKey3.get());

    }

    public static Jedis getJedis() {
        Jedis jedis = new Jedis("127.0.0.1", 6381);
        jedis.auth("erik.wang");
        return jedis;
    }

    /**
     * 这是个redis实现的分布式锁，什么叫做分布式锁就不用解释了，实现原理：。。。
     * 等等，可惜这个不是分布式错；
     * 看点：pipeline执行的命令是多个类型时，其返回结果也是不同类型的。所以以一下方式可以试下指定接收pipeline中某些命令的执行结果。
     */
    public void test_lock() {

        Jedis jedis = getJedis();

        List<Response<Long>> responses = new ArrayList<>();

        Pipeline pipeline = jedis.pipelined();
        List<String> keys = Arrays.asList("1", "2", "3", "4");

        for (String key : keys) {
            responses.add(pipeline.setnx(key, key));
        }

        for (String key : keys) {
            pipeline.expire(key, 10);
        }

        pipeline.sync();
        System.out.println(responses.stream().anyMatch(response -> response.get() > 0));
    }

    /**
     * pipeline中不同该命令返回的结果类型也不一样的
     */
    public void test_pipeline() {

        Jedis jedis = getJedis();

        List<Object> response = new ArrayList<>();

        Pipeline pipeline = jedis.pipelined();
        response.add(pipeline.set("fool", "bar"));
        response.add(pipeline.zadd("foo", 1, "barowitch"));
        response.add(pipeline.zadd("foo", 0, "barinsky"));
        response.add(pipeline.zadd("foo", 0, "barikoviev"));
        response.add(pipeline.get("fool"));
        response.add(pipeline.zrange("foo", 0, -1));
        pipeline.sync();

        System.out.println(response);

    }

    /**
     * 解析zcount命令的返回结果。
     */
    public void test_z_count() {
        Jedis jedis = getJedis();
        jedis.select(15);

        System.out.println(jedis.zcount("erik", 400, 400));
        Set<Tuple> tuples = jedis.zrangeWithScores("erik", 0, -1);

        for (Tuple tuple : tuples) {
            System.out.printf("%s,%s\n", tuple.getElement(), Double.valueOf(tuple.getScore()).intValue());
        }

        Map<Long, Integer> couponId2TimeMap = new HashMap<>();

        for (Tuple tuple : tuples) {
            long couponId = Double.valueOf(tuple.getScore()).longValue();
            Integer time = couponId2TimeMap.get(couponId);

            time = time == null ? 1 : time + 1;
            couponId2TimeMap.put(couponId, time);
        }
        System.out.println(couponId2TimeMap);

    }


}
