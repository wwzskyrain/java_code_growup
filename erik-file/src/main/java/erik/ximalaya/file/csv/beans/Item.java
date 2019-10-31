package erik.ximalaya.file.csv.beans;

import com.opencsv.bean.CsvBindByName;

/**
 * @Date 2019-09-09
 * @Created by erik
 */
public class Item {

    //    ITEM_ID,PRODUCT_ID,SKU_ID,SKU_PROPERTIES,SCOPE_ID,PRICE_TYPE_ID,UNIT_PRICE,DISCOUNT_RATE,CREATE_TIME,LAST_UPDATE_TIME,VERSION
    @CsvBindByName(column = "ITEM_ID")
    private String itemId;

    @CsvBindByName(column = "PRODUCT_ID")
    private String productId;

    @CsvBindByName(column = "DISCOUNT_RATE")
    private String discountRate;

    @CsvBindByName(column = "UNIT_PRICE")
    private String unitPrice;

    @CsvBindByName(column = "SCOPE_ID")
    private String scope;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getScope() {
        return Integer.valueOf(scope);
    }
}
