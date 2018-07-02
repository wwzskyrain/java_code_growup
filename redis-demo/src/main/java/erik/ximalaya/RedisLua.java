package erik.ximalaya;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Set;

public class RedisLua {

    public static String HSET_KEY2_IF_KKEY1_EXISTS = "if redis.call(\"exists\",KEYS[1])==1 then\n" +
            "\treturn redis.call(\"hset\",KEYS[2],KEYS[3],ARGV[1])\n" +
            "else\n" +
            "\treturn 0\n" +
            "end";

    public static String SET_KEY2_IF_KEY1_EXISTS = "if redis.call(\"exists\",KEYS[1])==1 then\n" +
            "\treturn redis.call(\"set\",KEYS[2],ARGV[1])\n" +
            "else\n" +
            "\treturn 0\n" +
            "end";

    public static void main(String[] args) {

        test_hset_key2_if_key1_exists();
    }

    public static void test_hset_key2_if_key1_exists() {
        Jedis jedis = new Jedis("localhost", 6379);

        String key1 = "new-key-1";
        String key2 = "new-key-2";
        String field1 = "field1";

        Object result = jedis.eval(HSET_KEY2_IF_KKEY1_EXISTS.getBytes(), 3, key1.getBytes(), key2.getBytes(),
                field1.getBytes(), String.valueOf(2).getBytes());

        if (result instanceof Long) {
            if (((Long) result).compareTo(1l) == 0) {
                System.out.println("new key or new file ");
            } else if (((Long) result).compareTo(0l) == 0) {
                System.out.println("over set old field ");
            }
            System.out.println("result instanceof Long");
        }

        Set<String> allKeys = jedis.keys("*");

        ArrayList<String> allKeysList = new ArrayList<String>(allKeys);

        allKeysList.sort(String::compareToIgnoreCase);
        allKeysList.forEach(key -> System.out.println(key));
    }

}
