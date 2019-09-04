package erik.spring.boot.xmly.groupon;

import com.ximalaya.business.promotion.groupon.query.api.PromotionGrouponQueryService;
import com.ximalaya.business.promotion.groupon.query.api.dto.GrouponingCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2019-08-27
 * @Created by erik
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private PromotionGrouponQueryService promotionGrouponQueryService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
