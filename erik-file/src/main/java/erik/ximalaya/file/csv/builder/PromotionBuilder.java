package erik.ximalaya.file.csv.builder;

import com.alibaba.fastjson.JSON;
import erik.ximalaya.file.csv.beans.Item;
import erik.ximalaya.file.csv.beans.Product;
import erik.ximalaya.file.csv.vo.PromotionItem;
import erik.ximalaya.file.csv.vo.PromotionRule;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2019-09-10
 * @Created by erik
 */
public class PromotionBuilder {

    String PROMOTION_RULE_INERT_SQL = "INSERT INTO XIMA_PMP.PMP_PROMOTION_RULE (PROMOTION_ID, ACTIVITY_ID, PROMOTION_NAME, PROMOTION_LABEL, PROMOTION_TYPE, DESCRIPTION,DISCOUNT_TYPE, DISCOUNT_RATE, DISCOUNT_AMOUNT, CONTEXT, CREATE_TIME,LAST_UPDATE_TIME, VERSION, HAS_PROMOTION_INVENTORY, HAS_PERSONAL_INVENTORY) VALUES ({PROMOTION_ID}, {ACTIVITY_ID}, {PROMOTION_NAME}, {PROMOTION_LABEL}, {PROMOTION_TYPE}, {DESCRIPTION}, {DISCOUNT_TYPE},{DISCOUNT_RATE}, {DISCOUNT_AMOUNT}, {CONTEXT}, NOW(), NOW(), 1, 0, 1) ON DUPLICATE KEY UPDATE LAST_UPDATE_TIME = NOW(), VERSION = VERSION + 1;";
    String PROMOTION_ITEM_INSERT_SQL = "INSERT INTO XIMA_PMP.PMP_PROMOTION_ITEM (PROMOTION_ID, ITEM_ID, PROMOTION_PRICE, CREATE_TIME, LAST_UPDATE_TIME) VALUES ({PROMOTION_ID}, {ITEM_ID}, {PROMOTION_PRICE}, now(), now()) ON DUPLICATE KEY UPDATE LAST_UPDATE_TIME = NOW();";
    Integer basePromotionId = 101000;

    public PromotionItem buildPromotionItem(Item parentItem, Item childItem, PromotionRule promotionRule) {
        PromotionItem promotionItem = new PromotionItem();
        promotionItem.itemId = childItem.getItemId();
        promotionItem.promotionId = promotionRule.promotionId;
        promotionItem.promotionPrice = new BigDecimal(childItem.getUnitPrice())
                .multiply(new BigDecimal(parentItem.getDiscountRate()))
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString();
        return promotionItem;
    }

    public PromotionRule buildPromotionRule(Product product, Item item) {

        PromotionRule promotionRule = new PromotionRule();

        String coverUrlPrefix = "http://fdfs.xmcdn.com";

        promotionRule.promotionId = (basePromotionId++).toString();
        promotionRule.activityId = "10101142";  //test:10101142
//        promotionRule.activityId = "212229";
        Map<String, Object> context = new HashMap<>();
        context.put("coverUrl", coverUrlPrefix + product.getCoverUrl());
        context.put("compositeItemId", item.getItemId());
        promotionRule.context = JSON.toJSONString(context);
        promotionRule.description = product.getDescription();
        promotionRule.discountType = "1";
        promotionRule.discountAmount = item.getUnitPrice();
        promotionRule.discountRate = "";
        promotionRule.label = "";
        promotionRule.name = product.getName();
        promotionRule.type = "1";

        return promotionRule;
    }

    public String getPromotionItemInsertSql(PromotionItem promotionItem) {
        SqlBuilder sqlBuilder = new SqlBuilder(PROMOTION_ITEM_INSERT_SQL);
        sqlBuilder.replace("{PROMOTION_ID}", promotionItem.promotionId)
                .replace("{ITEM_ID}", promotionItem.itemId)
                .replace("{PROMOTION_PRICE}", promotionItem.promotionPrice);
        return sqlBuilder.builder();
    }


    public String getPromotionRuleInsertSql(PromotionRule promotionRule) {

//    'ACTIVITY_ID', 'PROMOTION_NAME', 'PROMOTION_LABEL', 'PROMOTION_TYPE', 'DESCRIPTION', 'DISCOUNT_TYPE','DISCOUNT_RATE', 'DISCOUNT_AMOUNT', 'CONTEXT', 'CREATE_TIME', 'LAST_UPDATE_TIME', 1, 0, 1);";
        SqlBuilder sqlBuilder = new SqlBuilder(PROMOTION_RULE_INERT_SQL);

        sqlBuilder.replace("{PROMOTION_ID}", promotionRule.promotionId)
                .replace("{ACTIVITY_ID}", promotionRule.activityId)
                .replace("{PROMOTION_NAME}", promotionRule.name)
                .replace("{PROMOTION_LABEL}", promotionRule.label)
                .replace("{PROMOTION_TYPE}", promotionRule.type)
                .replace("{DESCRIPTION}", promotionRule.description)
                .replace("{DISCOUNT_TYPE}", promotionRule.discountType)
                .replace("{DISCOUNT_RATE}", promotionRule.discountRate)
                .replace("{DISCOUNT_AMOUNT}", promotionRule.getDiscountAmount())
                .replace("{CONTEXT}", promotionRule.context);

        return sqlBuilder.builder();
    }

    class SqlBuilder {
        private String sqlPattern;

        SqlBuilder(String sqlPattern) {
            this.sqlPattern = new String(sqlPattern);
        }

        public SqlBuilder replace(String target, String value) {

            if (value != null && value.length() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                sqlPattern = sqlPattern.replace(target,
                        stringBuilder.append("\'")
                                .append(value.toString())
                                .append("\'"));
            } else {
                sqlPattern = sqlPattern.replace(target, "NULL");
            }
            return this;
        }

        public String builder() {
            return sqlPattern;
        }
    }

}
