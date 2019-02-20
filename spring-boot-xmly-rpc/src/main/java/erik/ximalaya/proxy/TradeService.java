package erik.ximalaya.proxy;

import com.alibaba.fastjson.JSON;
import com.ximalaya.business.common.lib.dto.CommonResponse;
import com.ximalaya.business.trade.command.api.TradeCommandService;
import com.ximalaya.business.trade.command.api.dto.*;
import com.ximalaya.business.trade.query.api.TradeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class TradeService {

    @Autowired
    private TradeCommandService tradeCommandService;


    public void test_place_trade_order_and_make_direct_payment() {

        PlaceTradeOrderDto placeTradeOrderDto = new PlaceTradeOrderDto();

        Map<String, Object> context = new HashMap<>();
        context.put("tag", "压测使用");

        int businessTypeId = 1271;
        int domainId = 1;
        Long buyerId = 2019011801l;

        TradePaymentDto tradePaymentDto = new TradePaymentDto();

        tradePaymentDto.setPayerId(buyerId);
        tradePaymentDto.setDomain(domainId);
        tradePaymentDto.setPaymentItems(
                Arrays.asList(new TradePaymentItemDto(5, 0l, 0l, null)));

        TradeOrderItemDto tradeOrderItemDto = new TradeOrderItemDto();

        tradeOrderItemDto.setQuantity(1);
        tradeOrderItemDto.setItemId(1012200100000222524l);


        placeTradeOrderDto.setContext(context);
        placeTradeOrderDto.setDomain(1);
        placeTradeOrderDto.setBusinessTypeId(businessTypeId);
        placeTradeOrderDto.setBuyerId(buyerId);
        placeTradeOrderDto.setTradePaymentDto(tradePaymentDto);
        placeTradeOrderDto.setTradeOrderItemDtos(Arrays.asList(tradeOrderItemDto));

        System.out.println(JSON.toJSONString(placeTradeOrderDto));

        CommonResponse<PlaceTradeResultDto> placeTradeResultDtoCommonResponse = tradeCommandService
                .placeTradeOrderAndMakeDirectPayment(placeTradeOrderDto);
        System.out.println(JSON.toJSONString(placeTradeResultDtoCommonResponse));
    }

}
