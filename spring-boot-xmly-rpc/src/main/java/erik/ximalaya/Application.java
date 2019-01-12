package erik.ximalaya;

import erik.ximalaya.proxy.AdCouponService;
import erik.ximalaya.proxy.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private AdCouponService adCouponService;

    @Autowired
    private TradeService tradeService;

    @Override
    public void run(String... args) throws Exception {

        adCouponService.test_query_coupon_details();

    }
}
