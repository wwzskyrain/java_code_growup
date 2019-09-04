package erik.study.spring.xmly.m3.promotion;

import com.ximalaya.business.promotion.plan.query.api.PromotionPlanQueryService;
import com.ximalaya.business.promotion.plan.query.api.dto.PromotionGrouponViewDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Date 2019-08-27
 * @Created by erik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appcontext-service-thrift.xml")
public class PromotionPlanTestClass {

    @Autowired
    private PromotionPlanQueryService promotionPlanQueryService;

    @Test
    public void test() {
        List<Long> list = new ArrayList<>();
        list.add(1010000100000242386L);
        list.add(1010000100000452844L);
        list.add(1010000100000262158L);
        list.add(1010000100000452577L);
        list.add(1010000100000544648L);
        list.add(1010000100000099543L);
        list.add(1010000100000452518L);

        Map<Long, PromotionGrouponViewDto> id2GrouponViewDtoMap = promotionPlanQueryService.findGrouponByItemIds(list);

        for (Map.Entry<Long, PromotionGrouponViewDto> entry : id2GrouponViewDtoMap.entrySet()) {
            System.out.println(String.format("[%s:%s]", entry.getKey(), entry.getValue()));
        }

        Assert.assertEquals(5L, id2GrouponViewDtoMap.size());
    }
}
