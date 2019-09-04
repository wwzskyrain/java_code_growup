package erik.ximalaya.file.csv.beans;

import com.alibaba.fastjson.JSON;
import com.opencsv.bean.CsvBindByName;

/**
 * @Date 2019-09-04
 * @Created by erik
 */
public class CouponItemBean {
    //    PROMOTION_ITEM_ID,PROMOTION_ID,ITEM_ID,PROMOTION_PRICE,CREATE_TIME,LAST_UPDATE_TIME
    @CsvBindByName(column = "PROMOTION_ITEM_ID")
    private String promotionItemId;

    @CsvBindByName(column = "PROMOTION_ID")
    private String promotionId;

    @CsvBindByName(column = "ITEM_ID")
    private String itemId;

    @CsvBindByName(column = "PROMOTION_PRICE")
    private String promotionPrice;

    @CsvBindByName(column = "CREATE_TIME")
    private String createTime;

    @CsvBindByName(column = "LAST_UPDATE_TIME")
    private String lastUpdateTime;


    public String getPromotionItemId() {
        return promotionItemId;
    }

    public void setPromotionItemId(String promotionItemId) {
        this.promotionItemId = promotionItemId;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
