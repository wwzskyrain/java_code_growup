package xmly.util.coupon;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author erik.wang
 * @date 2019/06/04
 **/
public class UatCoupon {

    public static void main(String[] args) {

        String pattern = "{\"serverIp\": \"172.29.0.16\", \"serverPort\": \"9187\", \"namespace\": \"com.ximalaya.ad.coupon.rpc.api\", \"serviceName\": \"CouponActivityRpcThriftService\", \"methodName\": \"batchAllocCouponByCouponIds\", \"isMultiplex\": \"false\", \"paramStr\": \"[{\\\"class\\\": \\\"userId\\\", \\\"parentClass\\\": \\\"\\\", \\\"type\\\": \\\"i64\\\", \\\"value\\\": \\\"%d\\\"}, {\\\"class\\\": \\\"couponIds\\\", \\\"parentClass\\\": \\\"\\\", \\\"type\\\": \\\"list<i64>\\\"}, {\\\"class\\\": \\\"couponIds-0\\\", \\\"parentClass\\\": \\\"couponIds\\\", \\\"type\\\": \\\"i64\\\", \\\"value\\\": \\\"200003\\\"}]\"}";

        try {
            FileWriter fileWriter = new FileWriter("uat-test");
            int baseId = 1000000;
            for (int i = 0; i < 4000; i++) {
                fileWriter.write(String.format(pattern + "\n", i + baseId));
            }
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
