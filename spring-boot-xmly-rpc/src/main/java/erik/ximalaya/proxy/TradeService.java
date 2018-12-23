package erik.ximalaya.proxy;

import com.ximalaya.business.trade.command.api.TradeCommandService;
import com.ximalaya.business.trade.query.api.TradeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TradeService {

    @Autowired
    private TradeCommandService tradeCommandService;

    @Autowired
    private TradeQueryService tradeQueryService;

    public void test_place_trade_order_and_make_direct_payment() {
        System.out.println(tradeCommandService == null);
    }

}
