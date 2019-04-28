package erik.ximalaya.proxy;

import com.alibaba.fastjson.JSON;
import com.ximalaya.business.common.bean.enums.ChannelType;
import com.ximalaya.business.common.bean.enums.Domain;
import com.ximalaya.business.common.lib.dto.CommonResponse;
import com.ximalaya.business.trade.command.api.TradeCommandService;
import com.ximalaya.business.trade.command.api.dto.*;
import com.ximalaya.business.trade.query.api.TradeQueryService;
import org.apache.http.conn.util.DomainType;
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

    private final static Long UNIT_XI_COIN_ITEM_ID = 1016000110004700000L;

    public void test_place_trade_order_and_make_direct_payment() {

        PlaceTradeOrderDto placeTradeOrderDto = new PlaceTradeOrderDto();

        Map<String, Object> context = new HashMap<>();
        context.put("rate", "0.25");

        int businessTypeId = 100;
        int domainId = 1;
        Long buyerId = 2019011801l;

        TradePaymentDto tradePaymentDto = new TradePaymentDto();

        tradePaymentDto.setPayerId(buyerId);
        tradePaymentDto.setDomain(domainId);
        tradePaymentDto.setPaymentItems(
                Arrays.asList(new TradePaymentItemDto(ChannelType.ANDROID_REDEEM.getId(), 0l, 0l, null)));

        TradeOrderItemDto tradeOrderItemDto = new TradeOrderItemDto();

        tradeOrderItemDto.setQuantity(80000);
        tradeOrderItemDto.setItemId(UNIT_XI_COIN_ITEM_ID);


        placeTradeOrderDto.setContext(context);
        placeTradeOrderDto.setDomain(Domain.XIMA_USER.getId());
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
