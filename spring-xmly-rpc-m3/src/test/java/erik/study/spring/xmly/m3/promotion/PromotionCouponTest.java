package erik.study.spring.xmly.m3.promotion;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.ximalaya.business.promotion.coupon.common.api.dto.CouponDto;
import com.ximalaya.business.promotion.coupon.query.api.CouponQueryService;
import groovy.json.JsonBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author erik.wang
 * @Date 2019-10-31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:thrift-service-promotion-coupon.xml")
public class PromotionCouponTest {

    @Autowired
    private CouponQueryService couponQueryService;

    @Test
    public void test_query_by_activity_id() {
        Long activityId = 10101070L;
        List<CouponDto> couponDtos = couponQueryService.queryCouponsByActivityId(activityId, 1, 10);
        JSON.toJSONString(couponDtos);
        Assert.assertNotEquals(0, couponDtos.size());
    }

    @Test
    public void test_is_allocated() {
        int domain = 1;
        long userId = 12339L;
        long realCouponId = 1625200L;
        long fakeCouponId = 1111111L;
        Set<Long> couponIds = Sets.newHashSet(realCouponId, fakeCouponId);

        Map<Long, Boolean> allocatedCouponIds = couponQueryService.isAllocated(domain, userId, couponIds);

        Assert.assertTrue(allocatedCouponIds.get(realCouponId));
        Assert.assertNull(allocatedCouponIds.get(fakeCouponId));
    }

    @Test
    public void test_query_item_coupon(){

    }

}
