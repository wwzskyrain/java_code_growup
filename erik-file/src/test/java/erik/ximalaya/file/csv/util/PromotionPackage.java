package erik.ximalaya.file.csv.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import erik.ximalaya.file.csv.beans.Item;
import erik.ximalaya.file.csv.beans.Product;
import erik.ximalaya.file.csv.beans.ProductRelation;
import erik.ximalaya.file.csv.builder.PromotionBuilder;
import erik.ximalaya.file.csv.vo.PromotionItem;
import erik.ximalaya.file.csv.vo.PromotionRule;
import javafx.util.Pair;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Date 2019-09-09
 * @Created by erik
 */
public class PromotionPackage {

    private String packageProductFilePath = "/Users/nali/Downloads/package-product.csv";
    private String packageItemFilePath = "/Users/nali/Downloads/package-item.csv";
    private String packageProductRelationFilePath = "/Users/nali/Downloads/package-product-relation.csv";

    private Integer contentPackageBasedPromotionId = 102000;
    private Integer getContentActivityId = 1234567;

    @Test
    public void test_product() {
        String productFilePath = "/Users/nali/Downloads/package-product.csv";
//        String productFilePath = "/Users/nali/Downloads/package-product-test.csv";
        CsvUtil csvUtil = new CsvUtil();
        List<Product> products = csvUtil.getCsvData(productFilePath, Product.class);
        int count = 0;
        for (Product product : products) {
            System.out.println("No:" + (++count) + " " + JSON.toJSONString(product));
        }
    }

    @Test
    public void test_item() {
        String itemFilePath = "/Users/nali/Downloads/promotion-item.csv";
        List<Item> items = new CsvUtil().getCsvData(itemFilePath, Item.class);
        int count = 0;
        for (Item item : items) {
            System.out.println("No:" + (++count) + " " + JSON.toJSONString(item));
        }
    }

    @Test
    public void test_relation() {
        String relationFilePath = "/Users/nali/Downloads/promotion-relation.csv";
        List<ProductRelation> productRelations = new CsvUtil().getCsvData(relationFilePath, ProductRelation.class);
        int count = 0;
        for (ProductRelation productRelation : productRelations) {
            System.out.println("No:" + (++count) + " " + JSON.toJSONString(productRelation));
        }
    }

    /**
     * 内容包数据迁移
     */
    @Test
    public void test_generate_insert_rule_sql_for_content_package() {

        CsvUtil csvUtil = new CsvUtil();
        String contentProductFilePath = "/Users/nali/Downloads/content-product.csv";
        List<Product> products = csvUtil.getCsvData(contentProductFilePath, Product.class);

        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(product -> product.getProductId().toString(),
                        Function.identity(),
                        (a, b) -> a));

        String compositeItemFilePath = "/Users/nali/Downloads/content-composite-item.csv";
        List<Item> compositeItems = csvUtil.getCsvData(compositeItemFilePath, Item.class);

        String contentItemFilePath = "/Users/nali/Downloads/content-content-item.csv";
        List<Item> contentItems = csvUtil.getCsvData(contentItemFilePath, Item.class);

        String contentRelationFilePath = "/Users/nali/Downloads/content-relation.csv";
        List<ProductRelation> productRelations = csvUtil.getCsvData(contentRelationFilePath, ProductRelation.class);
        Multimap<String, String> relationMap = ArrayListMultimap.create();
        for (ProductRelation relation : productRelations) {
            relationMap.put(relation.getParentProductId(), relation.getChildProductId());
        }

        List<String> promotionRuleInsertSqls = new ArrayList<>();
        List<String> promotionItemInsertSqls = new ArrayList<>();

        for (Item compositeItem : compositeItems) {
            Product product = productMap.get(compositeItem.getProductId());
            if (product == null) {
                continue;
            }

            Collection<String> childProductIds = relationMap.get(product.getProductId());
            if (CollectionUtils.isEmpty(childProductIds)) {
                continue;
            }

            PromotionBuilder promotionBuilder = new PromotionBuilder();
            Integer promotionId = contentPackageBasedPromotionId++;
            List<PromotionItem> promotionItems = new ArrayList<>();
            for (String childProductId : childProductIds) {
                Item commonItem = null;
                Item contentItem = null;
                for (Item item : contentItems) {
                    if (item.getProductId().equals(childProductId) &&
                            item.getScope() == 1) {
                        commonItem = item;
                    }
                    if (item.getProductId().equals(childProductId) &&
                            item.getScope() == 15) {
                        contentItem = item;
                    }
                }
                if (commonItem != null && contentItem != null) {
                    Pair<Item, Item> pair = new Pair<>(commonItem, contentItem);
                    PromotionItem promotionItem = promotionBuilder.buildPromotionItem(compositeItem, pair, promotionId.toString());
                    promotionItems.add(promotionItem);
                    promotionItemInsertSqls.add(promotionBuilder.getPromotionItemInsertSql(promotionItem));
                }
            }
            PromotionRule promotionRule = promotionBuilder.buildPromotionRule(getContentActivityId.toString(), promotionId.toString(), product, compositeItem, promotionItems);
            promotionRuleInsertSqls.add(promotionBuilder.getPromotionRuleInsertSql(promotionRule));
        }

        for (String promotionRuleInsertSql : promotionRuleInsertSqls) {
            System.out.println(promotionRuleInsertSql);
        }

        System.out.println("-----------------");

        for (String promotionItemInsertSql : promotionItemInsertSqls) {
            System.out.println(promotionItemInsertSql);
        }

    }

    /**
     * 打包购买的数据迁移
     */
    @Test
    public void test_generate_inser_rule_sql() {

        CsvUtil csvUtil = new CsvUtil();
        List<Product> products = csvUtil.getCsvData(packageProductFilePath, Product.class);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(product -> product.getProductId().toString(),
                        Function.identity(),
                        (a, b) -> a));

        List<Item> items = csvUtil.getCsvData(packageItemFilePath, Item.class);

        Map<String, Item> productId2ItemMap = items.stream().collect(Collectors.toMap(
                item -> item.getProductId().toString(),
                Function.identity(),
                (a, b) -> a));

        List<ProductRelation> productRelations = csvUtil.getCsvData(packageProductRelationFilePath, ProductRelation.class);

        Multimap<String, String> parentId2ChildIdMap = ArrayListMultimap.create();
        for (ProductRelation productRelation : productRelations) {
            parentId2ChildIdMap.put(productRelation.getParentProductId(), productRelation.getChildProductId());
        }

        List<String> promotionRuleInsertSqls = new ArrayList<>();
        List<String> promotionItemInsertSqls = new ArrayList<>();

        PromotionBuilder promotionBuilder = new PromotionBuilder();

        List<PromotionRule> promotionRules = new ArrayList<>();

        for (String parentProductId : parentId2ChildIdMap.keySet()) {
            Product parentProduct = productMap.get(parentProductId);
            if (parentProduct == null) {
                System.out.println("not found product" + parentProductId);
            }
            Item parentItem = productId2ItemMap.get(parentProductId);

            Collection<String> childProductIds = parentId2ChildIdMap.get(parentProductId);

            PromotionRule promotionRule = promotionBuilder.buildPromotionRule(parentProduct, parentItem);
            List<PromotionItem> promotionItems = new ArrayList<>();

            for (String childProductId : childProductIds) {
                Item childItem = productId2ItemMap.get(childProductId);
                PromotionItem promotionItem = promotionBuilder.buildPromotionItem(parentItem, childItem, promotionRule.promotionId);
                promotionItemInsertSqls.add(promotionBuilder.getPromotionItemInsertSql(promotionItem));
                promotionItems.add(promotionItem);
            }
            promotionRule.promotionItemList = promotionItems;
            promotionRuleInsertSqls.add(promotionBuilder.getPromotionRuleInsertSql(promotionRule));

            promotionRules.add(promotionRule);
        }


//        for (String promotionRuleInsertSql : promotionRuleInsertSqls) {
//            System.out.println(promotionRuleInsertSql);
//        }
//
//        for (String promotionItemInsertSql : promotionItemInsertSqls) {
//            System.out.println(promotionItemInsertSql);
//        }

        StringBuilder stringBuilder = new StringBuilder();
        for (PromotionRule promotionRule : promotionRules) {
            Map<String, String> context = JSON.parseObject(promotionRule.context, new TypeReference<Map<String, String>>() {
            });
            stringBuilder.append(context.get("compositeItemId"))
                    .append(":")
                    .append(promotionRule.promotionId)
                    .append(",");
        }

        System.out.println(stringBuilder.toString());
    }


}
