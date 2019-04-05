package erik.ximalaya;

import com.alibaba.fastjson.JSON;
import com.ximalaya.business.product.query.api.ProductItemQueryService;
import com.ximalaya.xima.accounting.account.query.api.dto.SubAccountViewDto;
import erik.ximalaya.proxy.AdCouponService;
import erik.ximalaya.proxy.TradeService;
import erik.ximalaya.proxy.XimaAccountServiceProxy;
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

    @Autowired
    private XimaAccountServiceProxy ximaAccountServiceProxy;


    @Override
    public void run(String... args) throws Exception {




    }
}
