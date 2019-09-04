package jdk.util.concurrent.reading;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author erik.wang
 * @date 2019/04/26
 **/
public class AQSReading {

    public final static String QUEYR_PATTERN = "{\"serverIp\":\"172.29.0.105\",\"serverPort\":49886,\"namespace\":\"com.ximalaya.business.promotion.groupon.query.thrift.api\",\"serviceName\":\"ThriftPromotionGrouponQueryService\",\"methodName\":\"queryUserActiveGroupon\",\"isMultiplex\":true,\"paramStr\":\"[{\\\"class\\\":\\\"userId\\\",\\\"parentClass\\\":\\\"\\\",\\\"type\\\":\\\"i64\\\",\\\"value\\\":\\\"%d\\\"},{\\\"class\\\":\\\"domain\\\",\\\"parentClass\\\":\\\"\\\",\\\"type\\\":\\\"i32\\\",\\\"value\\\":\\\"1\\\"},{\\\"class\\\":\\\"itemId\\\",\\\"parentClass\\\":\\\"\\\",\\\"type\\\":\\\"i64\\\",\\\"value\\\":\\\"1010000100000096048\\\"}]\"}";
    public final static String CREATE_COUPON = "{\"serverIp\":\"172.29.0.105\",\"serverPort\":49886,\"namespace\":\"com.ximalaya.business.promotion.groupon.command.thrift.api\",\"serviceName\":\"ThriftPromotionGrouponCommandService\",\"methodName\":\"createGroupon\",\"isMultiplex\":true,\"paramStr\":\"[{\\\"class\\\":\\\"groupDto\\\",\\\"parentClass\\\":\\\"\\\",\\\"type\\\":\\\"ThriftGrouponDto\\\"},{\\\"class\\\":\\\"groupDto-promotionId\\\",\\\"parentClass\\\":\\\"groupDto\\\",\\\"type\\\":\\\"i64\\\",\\\"value\\\":\\\"1600041\\\"},{\\\"class\\\":\\\"groupDto-initiatorId\\\",\\\"parentClass\\\":\\\"groupDto\\\",\\\"type\\\":\\\"i64\\\",\\\"value\\\":\\\"200000\\\"},{\\\"class\\\":\\\"groupDto-context\\\",\\\"parentClass\\\":\\\"groupDto\\\",\\\"type\\\":\\\"string\\\",\\\"value\\\":\\\"\\\"}]\"}";
    public static void main(String[] args) {

        Integer baseUserId = 200000;
        for (int i = 0; i < 100; i++) {
            System.out.println(String.format(CREATE_COUPON, baseUserId + i));
        }

    }

}

