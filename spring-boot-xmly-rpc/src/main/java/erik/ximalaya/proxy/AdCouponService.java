package erik.ximalaya.proxy;

import com.ximalaya.ad.coupon.rpc.api.CouponActivityRpcThriftService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Service
public class AdCouponService {


    @Autowired
    private CouponActivityRpcThriftService.Iface couponService;

    private ExecutorService executor = Executors.newCachedThreadPool();

    private Long activityId1 = 3369l;
    private Long couponId1 = 17931l;

    private Long activityId2 = 3368l;
    private Long couponId2 = 17930l;
    private Long userId = 204717l;
    private List<Long> userIds = Arrays.asList(10717l, 11717l, 12717l, 13717l, 14717l, 15717l, 16717l,
            17717l, 18717l, 19717l, 20717l, 21717l, 22717l, 23717l, 24717l, 25717l, 26717l, 27717l,
            28717l, 29717l, 30717l, 31717l, 32717l, 33717l, 34717l, 35717l, 36717l, 37717l, 38717l,
            39717l, 40717l, 41717l, 42717l, 43717l, 44717l, 45717l, 46717l, 47717l, 48717l, 10747l,
            11747l, 12747l, 13747l, 14747l, 15747l, 16747l, 17747l, 18747l, 19747l, 20747l, 21747l, 22747l,
            23747l, 24747l, 25747l, 26747l, 27747l, 28747l, 29747l, 30747l, 31747l, 32747l, 33747l, 34747l,
            35747l, 36747l, 37747l, 38747l, 39747l, 40747l, 41747l, 42747l, 43747l, 44747l, 45747l, 46747l,
            47747l, 48747l, 49747l, 202747l, 1002747l, 10368747l, 11123747l, 11124747l, 11125747l, 11126747l
            , 11127747l, 11128747l, 11129747l, 11130747l, 11131747l, 11132747l, 11848747l);


    public void test_alloc_coupon_after_share_activity() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < userIds.size(); j++) {
                final long userId = userIds.get(j);

                executor.submit(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {

                        couponService.allocCouponAfterShareActivity(userId, activityId1, couponId1);
                        System.out.printf("by allocCouponAfterShareActivity success allocate coupon %d to user %d\n", couponId1, userId);
                        return null;
                    }
                });

                executor.submit(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {

                        couponService.allocCouponAfterShareActivity(userId, activityId2, couponId2);
                        System.out.printf("by allocCouponAfterShareActivity success allocate coupon %d to user %d\n", couponId1, userId);
                        return null;
                    }
                });
            }

            executor.shutdown();
        }
    }

    public void test_allocCouponAfterShareActivity() {
        try {
            couponService.allocCouponAfterShareActivity(userId, activityId1, couponId1);
            System.out.printf("by allocCouponAfterShareActivity success allocate coupon %d to user %d\n", couponId1, userId);
        } catch (TException e) {
            e.printStackTrace();
        }
    }


}
