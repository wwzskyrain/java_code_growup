package erik.study.spring.xmly.m3.promotion;

import com.alibaba.fastjson.JSON;
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

}
