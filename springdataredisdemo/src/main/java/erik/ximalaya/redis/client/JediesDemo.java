package erik.ximalaya.redis.client;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;

public class JediesDemo {


    public static void main(String[] args) {

        Jedis jedisClient = new Jedis("localhost");

        jedisClient.select(1);

//        test_scan(jedisClient,3);
//
//        test_scan(jedisClient, "key_dream_1*", "key_dream_2*");

//        test_add_data(jedisClient);

//        test_hmget(jedisClient);


        System.out.println(test_info(jedisClient));
    }


    public static void test_add_data(Jedis jedis) {

        for (int i = 0; i < 1000; i++) {

            String key = String.format("key_dream_%03d", i);

            String value = String.format("value_dream_%03d", i);

            jedis.set(key, value);

        }

        Set<String> strings = jedis.keys("key_*");

        // TODO: 2018/2/9 当multi操作时，如何对应key和value
        List keysList = new ArrayList<>(strings);

        keysList.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o1).compareTo((String) o2);     //这恶心的代码，明天就把你改掉。
            }
        });

        keysList.forEach(System.out::println);

    }


    public static void test_scan(Jedis jedis, int count) {

        ScanParams scanParams = new ScanParams();

        byte[] matchPattern = "key_dream_0*".getBytes();

        scanParams.count(count);
        scanParams.match(matchPattern);

        String cursor = "0";

        int counter_key = 0;

        int no = 0;

        do {
            ScanResult<String> scanResult = jedis.scan(cursor, scanParams);

            cursor = scanResult.getStringCursor();

            List<String> valuesList = scanResult.getResult();

            counter_key += valuesList.size();

            System.out.printf("No. %d  counter_key=%d cursor: %s values: %s \n", no++, counter_key, cursor, valuesList);

        } while (cursor.compareTo("0") != 0);

    }


    public static void test_scan(Jedis jedis, String pattern1, String pattern2) {

        ScanParams scanParams1 = new ScanParams();

        scanParams1.count(100);
        scanParams1.match(pattern1);

        ScanParams scanParams2 = new ScanParams();

        scanParams2.count(100);
        scanParams2.match(pattern2);

        int no = 0;

        boolean scanOver1 = false;
        boolean scanOver2 = false;

        String cursor1 = "0";
        String cursor2 = "0";

        int counter_key1 = 0;
        int counter_key2 = 0;

        do {

            if (!scanOver1) {
                ScanResult<String> result1 = jedis.scan(cursor1, scanParams1);

                cursor1 = result1.getStringCursor();

                if ("0".equals(cursor1)) {
                    scanOver1 = true;
                }

                List<String> valuesList = result1.getResult();
                counter_key1 += valuesList.size();

                System.out.printf("No. %d  counter_key1=%d cursor: %s values: %s \n", no++, counter_key1, cursor1, valuesList);
            }

            if (!scanOver2) {
                ScanResult<String> result2 = jedis.scan(cursor2, scanParams2);

                cursor2 = result2.getStringCursor();

                if ("0".equals(cursor2)) {
                    scanOver2 = true;
                }

                List<String> valuesList = result2.getResult();
                counter_key2 += valuesList.size();

                System.out.printf("No. %d  counter_key2=%d cursor: %s values: %s \n", no++, counter_key2, cursor2, valuesList);
            }


        } while (!scanOver1 || !scanOver2);

    }


    public static void test_add_hash_data(Jedis jedis) {


        for (int i = 0; i < 500; i++) {

            String key = String.format("key_h_%03d", i);

            Map<String, String> fieldValueMap = new HashMap<>();

            for (int j = 0; j < 10; j++) {

                String field = String.format("field_%03d_%03d", i, j);
                String value = String.format("value_%03d_%03d", i, j);

                fieldValueMap.put(field, value);
            }

            String response = jedis.hmset(key, fieldValueMap);

            System.out.printf("hmset %s over with response=%s\n", key, response);

        }

    }

    public static void test_hmget(Jedis jedis) {


        Set<String> keySet = jedis.keys("key_h_4*");

        keySet.forEach(key -> {

            System.out.printf("%s:\n", key);

            Map<String, String> fieldAndValueMap = jedis.hgetAll(key);

            fieldAndValueMap.forEach((field, value) -> System.out.printf("\t\t[%s=%s]\n", field, value));

        });

    }

    public static boolean test_info(Jedis jedis) {

        if (jedis == null) {
            System.out.println("jedis is null,");
            return false;
        }

        String serverInfo = jedis.info("Server");

        int versionIndex = serverInfo.indexOf("redis_version");

        String infoWithVersionAhead = serverInfo.substring(versionIndex);

        int versionOverIndex = infoWithVersionAhead.indexOf("\r");

        String serverVersion = infoWithVersionAhead.substring(0, versionOverIndex);

        String leastVersionForScan = "redis_version:2.8";

        if (serverVersion != null && !serverVersion.isEmpty()) {

            System.out.printf("redis server:%s\n", serverVersion);

            return serverVersion.compareTo(leastVersionForScan) >= 0;
        } else {
            return false;
        }

    }


}
