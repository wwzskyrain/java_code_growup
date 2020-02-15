package erik.study.spring.xmly.m2.promotion;

import com.ximalaya.business.common.api.exceptions.DomainException;
import com.ximalaya.business.promotion.coupon.command.api.CouponCommandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author erik.wang
 * @Date 2019-11-25
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mainstay-context.xml"})
public class PromotionCouponCommandServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(PromotionCouponCommandServiceTest.class);

    @Autowired
    private CouponCommandService couponCommandService;

    @Test
    public void test_allocate_coupon_and_throw_exception() {

        Long userId = 1234567890L;
        Long couponId = 1625459L;
        try {
            couponCommandService.allocateCoupon(1, userId, couponId, "erik-test");
        } catch (DomainException e) {
            logger.info("allocate error.", e);
        }

    }

}
