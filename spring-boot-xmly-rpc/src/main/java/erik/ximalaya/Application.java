package erik.ximalaya;

import erik.ximalaya.proxy.AdCouponService;
import erik.ximalaya.proxy.TradeService;
import erik.ximalaya.proxy.XimaAccountServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private AdCouponService adCouponService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private XimaAccountServiceProxy ximaAccountServiceProxy;


    @Override
    public void run(String... args) throws Exception {


        tradeService.test_place_trade_order_and_make_direct_payment();

    }
}
