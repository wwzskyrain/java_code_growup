package erik.little.util.guava.local.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.graph.Graph;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author erik.wang
 * @date 2019/03/07
 **/
public class Main {

    static Integer counter = 0;

    public static void main(String[] args) {

        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {

                        System.out.printf("load value for key:%s \n", key);
                        counter++;
                        return String.format("%s-%d", key, counter);

                    }

                    @Override
                    public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {

                        StringBuilder stringBuilder = new StringBuilder();
                        keys.forEach(stringBuilder::append);
                        System.out.printf("loadAll value for keys:%s \n", stringBuilder.toString());

                        Map<String, String> keyValueMap = new HashMap<>();
                        keys.forEach(key -> keyValueMap.put(key, String.format("%s-%d", key, counter)));

                        return keyValueMap;
                    }
                });

        for (int i = 0; i < 100; i++) {
            try {
                String value = graphs.get(String.valueOf(i / 10));
                System.out.printf("get value=%s for key:%d\n", value, i);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        List<String> keys = IntStream.range(1, 20).mapToObj(String::valueOf).collect(Collectors.toList());

        try {
            ImmutableMap<String, String> keyValueMap = graphs.getAll(keys);
            keyValueMap.forEach((k, v) -> System.out.printf("%s:%s\n", k, v));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
