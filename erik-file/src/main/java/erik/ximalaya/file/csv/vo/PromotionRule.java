package erik.ximalaya.file.csv.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Date 2019-09-09
 * @Created by erik
 */
public class PromotionRule {

    //    'ACTIVITY_ID', 'PROMOTION_NAME', 'PROMOTION_LABEL', 'PROMOTION_TYPE', 'DESCRIPTION', 'DISCOUNT_TYPE','DISCOUNT_RATE', 'DISCOUNT_AMOUNT', 'CONTEXT', 'CREATE_TIME', 'LAST_UPDATE_TIME', 1, 0, 1);";
    public String promotionId;
    public String activityId = "";
    public String name;
    public String label;
    public String type = "1";
    public String description;
    public String discountType = "2";
    public String discountAmount;
    public String discountRate;
    public String context;

    public List<PromotionItem> promotionItemList;

    public String getDiscountAmount() {
        if (promotionItemList != null && promotionItemList.size() > 0) {
            BigDecimal discountAmount = BigDecimal.ZERO;
            for (PromotionItem promotionItem : promotionItemList) {
                discountAmount = discountAmount.add(new BigDecimal(promotionItem.promotionPrice));
            }
            return discountAmount.toPlainString();
        }
        return discountAmount;
    }
}
