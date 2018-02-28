package controller;


import dto.PageDto;
import model.FieldAndValueEntry;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.util.JedisCallback;
import redis.util.RedisHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class RedisController {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping(value = "/hash/mget")
    @ResponseBody
    public List<FieldAndValueEntry> showFieldAndValue(@RequestParam(value = "pageNum") Integer pageNum,
                                                      @RequestParam(value = "pageSize") Integer pageSize) {

        jedisPool.getResource();

        return RedisHelper.execute(jedisPool, new JedisCallback<List<FieldAndValueEntry>>() {

            @Override
            public List<FieldAndValueEntry> doInJedis(Jedis jedis) {

                ScanParams scanParams = new ScanParams();
                scanParams.match("key_h_1*".getBytes());

                scanParams.count(100);

                ScanResult<String> scanResult = new ScanResult<String>("0", Collections.EMPTY_LIST);

                if (pageNum == 1) {
                    scanResult = jedis.scan("0", scanParams);

                } else {
                    scanResult = jedis.scan(scanResult.getStringCursor(), scanParams);
                }

                List<String> keysList = scanResult.getResult();


                return RedisHelper.execute(jedisPool, new JedisCallback<List<FieldAndValueEntry>>() {

                    @Override
                    public List<FieldAndValueEntry> doInJedis(Jedis jedis) {

                        List<FieldAndValueEntry> fieldAndValueEntries = new ArrayList<>();

                        for (String key : keysList) {

                            Map<String, String> fieldAndValueMap = jedis.hgetAll(key);

                            fieldAndValueMap.forEach((field, value) -> {
                                fieldAndValueEntries.add(new FieldAndValueEntry(field, value));
                            });
                        }

                        return fieldAndValueEntries;

                    }

                });

            }

        });
    }

    @RequestMapping(value = "/hash/scan")
    @ResponseBody
    public PageDto showFieldAndValue(@RequestParam(value = "pageNum") Integer pageNum,
                                  @RequestParam(value = "pageSize") Integer pageSize,
                                  @RequestParam(value = "pattern") String pattern) {

//      用scan实现分页，性能最差时退化到keys命令。
        PageDto<Map<String, String>> pageDto = new PageDto<>();

        pageDto.setPageNum(pageNum);
        pageDto.setPageSize(pageSize);

        Integer totalCount = 0;
        Jedis jedis = jedisPool.getResource();

        int start = (pageNum - 1) * pageSize;
        int end = pageNum * pageSize;

        List<String> allKeys = new ArrayList<>();

        String cursor = "0";
        do {
            ScanResult<String> scanResult = jedis.scan(cursor, new ScanParams().match(pattern).count(30));

            allKeys.addAll(scanResult.getResult());

            cursor = scanResult.getStringCursor();
        } while (!(cursor.equals("0") || allKeys.size() >= end));

        totalCount = allKeys.size();
        if (!cursor.equals("0")) {
            totalCount++;
        }

        pageDto.setTotalCount(totalCount);

        if (allKeys.size() < start) {
            pageDto.setData(new ArrayList<>());
            return pageDto;
        }

        if (end > allKeys.size()) {
            end = allKeys.size();
        }

        final List<String> keys = allKeys.subList(start, end);

        List<Map<String, String>> transactionVos = RedisHelper.execute(jedisPool, new JedisCallback<List<Map<String, String>>>() {

            @Override
            public List<Map<String, String>> doInJedis(Jedis jedis) {

                ArrayList<Map<String, String>> data = new ArrayList<>();

                if (CollectionUtils.isEmpty(keys)) {
                    return data;
                }

                for (String key : keys) {

                    Map<String, String> fieldAndValueMap = jedis.hgetAll(key);

                    data.add(fieldAndValueMap);
                }

                return data;
            }
        });


        pageDto.setData(transactionVos);

        return pageDto;
    }


}
