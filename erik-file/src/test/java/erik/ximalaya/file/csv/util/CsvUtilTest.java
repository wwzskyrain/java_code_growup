package erik.ximalaya.file.csv.util;

import com.alibaba.fastjson.JSON;
import erik.ximalaya.file.csv.beans.XiDianBean;
import erik.ximalaya.file.csv.beans.CouponItemBean;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Date 2019-09-04
 * @Created by erik
 */
public class CsvUtilTest {

    private CsvUtil csvUtil;

    @Before
    public void init() {
        csvUtil = new CsvUtil();
    }

    @Test
    public void test_coupon_item_bean() {

        String fileName = "/Users/nali/project_erik/java_code_growup/erik-file/src/file/fix-coupon-item.csv";
        List<CouponItemBean> couponItemBeans = csvUtil.getCsvData(fileName, CouponItemBean.class);
        System.out.println(JSON.toJSONString(couponItemBeans));
    }

    @Test
    public void test_xi_dian_bean(){
        String fileName = "/Users/nali/project_erik/java_code_growup/erik-file/src/file/xi-dian-product.csv";
        List<XiDianBean> xiDianBeans = csvUtil.getCsvData(fileName, XiDianBean.class);
        for (XiDianBean xiDianBean : xiDianBeans) {
            Map<String, String> properties = xiDianBean.getProperties();
            System.out.println(properties);
        }
    }

}
