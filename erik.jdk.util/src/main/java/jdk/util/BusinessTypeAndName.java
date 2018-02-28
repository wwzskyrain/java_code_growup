package jdk.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class BusinessTypeAndName {

    public static String data = "1228:喜钻充值;1222:发票;1223:新年分享活动;1232:地网门票;202:购买声音;1225:买赠;1243:代金券;1241:拼团;201:购买专辑;1226:巅峰会员;400:直播送礼;500:直播门票;600:录播送礼;601:主播送礼;1235:付费群;1240:直播问答;1227:知识宝;1233:游戏;1236:广告充值";

    public static void main(String[] args) {

        String[] businessTypeIdAndName = data.split(";");

        Map<String, String> businessTypeIdAndNameMap = new HashMap<>();

        for (int i = 0; i < businessTypeIdAndName.length; i++) {

            String[] tuple = businessTypeIdAndName[i].split(":");

            businessTypeIdAndNameMap.put(tuple[0],tuple[1]);

        }
        System.out.println(businessTypeIdAndNameMap);

        System.out.println(JSON.toJSONString(businessTypeIdAndNameMap));

    }

}
