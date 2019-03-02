package erik.ximalaya.proxy;

import com.alibaba.fastjson.JSON;
import com.ximalaya.business.common.bean.enums.DeviceType;
import com.ximalaya.xima.accounting.account.query.api.SubAccountQueryService;
import com.ximalaya.xima.accounting.account.query.api.dto.SubAccountViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XimaAccountServiceProxy {

    @Autowired
    private SubAccountQueryService subAccountQueryService;


    public void querySubAccountByUserId() {

        Long userId = 306381L;

        List<SubAccountViewDto> subAccountViewDtos = subAccountQueryService.findBothBalanceByUserIdAndSubAccountTypeId(userId, 100);

        System.out.println("subAccountViewDtos:{}" + JSON.toJSONString(subAccountViewDtos));

        SubAccountViewDto subAccountViewDto = subAccountQueryService.findByUserIdAndDeviceTypeId(userId, DeviceType.ANDROID.getId());

        System.out.println("subAccountViewDto:{}" + JSON.toJSONString(subAccountViewDto));
    }

}
