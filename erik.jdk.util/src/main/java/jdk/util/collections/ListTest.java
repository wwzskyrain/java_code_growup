package jdk.util.collections;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2019/03/07
 **/
public class ListTest {



    public static void main(String[] args) {

        List<Long> couponIds = new ArrayList<>();
        couponIds.add(1L);
        couponIds.add(2L);

        List<Long> couponIds1 = new ArrayList<>();
        couponIds1.add(2L);
        couponIds1.add(3L);

        couponIds.retainAll(couponIds1);

        System.out.println(JSON.toJSONString(couponIds));
        System.out.println(JSON.toJSONString(couponIds1));

    }

}
