package erik.spring.boot.xmly.promotion;

import com.ximalaya.business.promotion.groupon.query.api.PromotionGrouponQueryService;
import com.ximalaya.business.promotion.groupon.query.api.dto.GrouponingCountDto;
import com.ximalaya.business.promotion.plan.query.api.PromotionPlanQueryService;
import com.ximalaya.business.promotion.plan.query.api.dto.PromotionGrouponViewDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Date 2019-08-27
 * @Created by erik
 */

public class Application {

    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private PromotionPlanQueryService promotionPlanQueryService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    public void run(String... args) throws Exception {

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
            logger.info("[{}={}]", entry.getKey(), entry.getValue());
        }
    }
}
