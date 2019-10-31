package erik.ximalaya.file.csv.util;

import erik.ximalaya.file.csv.beans.AppleProductXiCoin;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2019-09-21 -- 生成苹果充值产品
 * @Created by erik
 */
public class AppleProductXiCoinTest {

    private String xiCoinProductIdPrefix = "101600";
    private String xiCoinItemIdPrefix = "1016000";
    private String propertyIdPrefix = "20160921";

    private Long attributeIdAppleProductId = 10010541L;
    private Long attributeIdXiCoinAmount = 100160001L;
    private Long attributeIdTemplateId = 300000159L;


    private String filePath = "/Users/nali/work_file/xi-coin-product2.csv";
    private String SQL_PATTERN_INSERT_PRODUCT = "INSERT INTO XIMA_PRD2.PRD_PRODUCT (PRODUCT_ID, DOMAIN, CATEGORY_ID, SPU_ID, PRODUCT_TYPE_ID, STATUS, NAME, DESCRIPTION, COVER_URL, PROPERTIES, OWNER_ID, DETAILS, CREATE_TIME, LAST_UPDATE_TIME, VERSION)" +
            " VALUES (%s, 1, 160, '%s', 0, 1, '%s', '%s', 'http://fdfs.xmcdn.com/group65/M0B/19/8B/wKgMdF1yE0agnZFyAAA_OO60nvE487.jpg', null, -3, '%s', NOW(), NOW(), 1)" +
            " ON DUPLICATE KEY UPDATE" +
            " LAST_UPDATE_TIME = NOW(), VERSION = VERSION + 1;";

    private String SQL_PATTERN_INSERT_ITEM = "INSERT INTO XIMA_PRD2.PRD_ITEM (ITEM_ID, PRODUCT_ID, SKU_ID, SKU_PROPERTIES, SCOPE_ID, PRICE_TYPE_ID, UNIT_PRICE, DISCOUNT_RATE, CREATE_TIME, LAST_UPDATE_TIME, VERSION)" +
            " VALUES (%s, %s, null, null, 1, 1, %s, null, NOW(), NOW(), 1) ON DUPLICATE KEY UPDATE LAST_UPDATE_TIME = NOW(), VERSION = VERSION + 1;";

    private String SQL_PATTERN_INSER_PROPERTY = "INSERT INTO XIMA_PRD2.PRD_PRODUCT_PROPERTY (PRODUCT_PROPERTY_ID, PRODUCT_ID, PRODUCT_ATTRIBUTE_ID, VALUE, CREATE_TIME, LAST_UPDATE_TIME, VERSION) " +
            " VALUES (%s, %s, %s, '%s', NOW(), NOW(), 1)" +
            " on duplicate key update LAST_UPDATE_TIME = now(), VERSION = VERSION + 1;";

    private CsvUtil csvUtil;


    @Before
    public void init() {
        csvUtil = new CsvUtil();
    }


    @Test
    public void test_apple_product_xi_coin() {
        List<AppleProductXiCoin> appleProductXiCoins = csvUtil.getCsvData(filePath, AppleProductXiCoin.class);
        buildSql(appleProductXiCoins);
    }


    public void buildSql(List<AppleProductXiCoin> productXiCoins) {
        List<String> productIds = new ArrayList<>();
        List<String> itemIds = new ArrayList<>();
        for (AppleProductXiCoin productXiCoin : productXiCoins) {
            if (!"approved".equals(productXiCoin.getStatus())) {
                continue;
            }
            String spuId = getSpuId(productXiCoin);
            String productId = getProductId(xiCoinProductIdPrefix, spuId);
            String itemId = getItemId(xiCoinItemIdPrefix, spuId);
            System.out.println(String.format(SQL_PATTERN_INSERT_PRODUCT, productId, spuId, productXiCoin.getDescription(), productXiCoin.getDescription(), productXiCoin.getDescription()));
            System.out.println(String.format(SQL_PATTERN_INSERT_ITEM, itemId, productId, productXiCoin.getPrice()));

            String propertyId1 = propertyIdPrefix + spuId.substring(1) + "1";
            System.out.println(String.format(SQL_PATTERN_INSER_PROPERTY, propertyId1, productId, attributeIdXiCoinAmount, productXiCoin.getPrice()));

            String propertyId2 = propertyIdPrefix + spuId.substring(1) + "2";
            System.out.println(String.format(SQL_PATTERN_INSER_PROPERTY, propertyId2, productId, attributeIdAppleProductId, productXiCoin.getAppleProductId()));

            String propertyId3 = propertyIdPrefix + spuId.substring(1) + "3";
            System.out.println(String.format(SQL_PATTERN_INSER_PROPERTY, propertyId3, productId, attributeIdTemplateId, "20190921"));

            System.out.println();
            productIds.add(productId);
            itemIds.add(itemId);
        }
        System.out.println();
        System.out.println(productIds);
        System.out.println(itemIds);
    }

    private String getSpuId(AppleProductXiCoin xiCoin) {
        return "12" + "1" + "00" + StringUtils.leftPad(xiCoin.getPrice(), 6, '0');
    }

    /**
     * 顶空app的两套喜点充值产品
     */
    @Test
    public void buildSql() {
        int[] xiAmounts = {6, 18, 30, 45, 60, 118, 188, 268};
        String businessPlatform = "13";
        int osType = 2;

        List<String> productInsertSqls = new ArrayList<>();
        List<String> itemInsertSqls = new ArrayList<>();
        for (int i = 0; i < xiAmounts.length; i++) {

            String spuId = getSpuId(businessPlatform, String.valueOf(osType), xiAmounts[i]);
            String productId = getProductId(xiCoinProductIdPrefix, spuId);
            String itemId = getItemId(xiCoinItemIdPrefix, spuId);
            String productName = getProductName(osType, xiAmounts[i]);
            productInsertSqls.add(generatorProductInsertSql(productId, spuId, productName, productName, productName));
            itemInsertSqls.add(generatorItemInsertSql(itemId, productId, String.valueOf(xiAmounts[i])));
        }
        for (String productInsertSql : productInsertSqls) {
            System.out.println(productInsertSql);
        }
        System.out.println("-------------------");
        for (String itemInsertSql : itemInsertSqls) {
            System.out.println(itemInsertSql);
        }
    }

    public String generatorProductInsertSql(String productId, String spuId, String description, String name, String detail) {
        return String.format(SQL_PATTERN_INSERT_PRODUCT, productId, spuId, description, name, description, description);
    }

    public String generatorItemInsertSql(String itemId, String productId, String price) {
        return String.format(SQL_PATTERN_INSERT_ITEM, itemId, productId, price);
    }

    public String getProductName(int osType, int xiAmount) {
        if (osType == 1) {
            return String.format("苹果充值%d喜点", xiAmount);
        } else if (osType == 2) {
            return String.format("安卓充值%d喜点", xiAmount);
        } else {
            return String.format("喜点充值%d喜点", xiAmount);
        }
    }

    private String getSpuId(String businessPlatform, String osType, int xiCoinAmount) {
        return businessPlatform + osType + "00" + StringUtils.leftPad(String.valueOf(xiCoinAmount), 6, '0');

    }

    private String getProductId(String productIdPrefix, String spuId) {
        return productIdPrefix + spuId;
    }

    private String getItemId(String itemIdPrefix, String spuId) {
        return itemIdPrefix + spuId;
    }


}
