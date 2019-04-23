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
        new Pipiline().test_z_count();

        Map<Long, Integer> couponId2TimeMap = new HashMap<>();


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
        pipeline.syncAndReturnAll();

        System.out.println(responses.stream().map(Response::get).collect(Collectors.toList()));
        System.out.println(responses.stream().anyMatch(response -> response.get() > 0));
    }

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
