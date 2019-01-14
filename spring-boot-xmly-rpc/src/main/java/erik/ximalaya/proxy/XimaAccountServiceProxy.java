package erik.ximalaya.proxy;

import com.ximalaya.xima.accounting.account.query.api.SubAccountQueryService;
import com.ximalaya.xima.accounting.account.query.api.dto.SubAccountViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XimaAccountServiceProxy {

    @Autowired
    private SubAccountQueryService subAccountQueryService;

    public SubAccountViewDto querySubAccountByUserId(int accountId, long userId, int subAccountTypeId) {
        SubAccountViewDto subAccountViewDto = subAccountQueryService.findByUserIdAndSubAccountTypeId(accountId, userId, subAccountTypeId);

        return subAccountViewDto;
    }

}
