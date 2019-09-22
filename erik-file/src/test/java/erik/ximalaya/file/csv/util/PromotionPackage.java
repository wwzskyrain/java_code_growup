package erik.ximalaya.file.csv.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import erik.ximalaya.file.csv.beans.Item;
import erik.ximalaya.file.csv.beans.Product;
import erik.ximalaya.file.csv.beans.ProductRelation;
import erik.ximalaya.file.csv.builder.PromotionBuilder;
import erik.ximalaya.file.csv.vo.PromotionItem;
import erik.ximalaya.file.csv.vo.PromotionRule;
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

    @Test
    public void test_generate_inser_rule_sql() {

//        String productFilePath = "/Users/nali/Downloads/package-product.csv";
        String productFilePath = "/Users/nali/work_file/package-test-product.csv";
        CsvUtil csvUtil = new CsvUtil();
        List<Product> products = csvUtil.getCsvData(productFilePath, Product.class);
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(product -> product.getProductId().toString(),
                        Function.identity(),
                        (a, b) -> a));

//        String itemFilePath = "/Users/nali/Downloads/promotion-item.csv";
        String itemFilePath = "/Users/nali/work_file/package-test-item.csv";
        List<Item> items = csvUtil.getCsvData(itemFilePath, Item.class);

        Map<String, Item> productId2ItemMap = items.stream().collect(Collectors.toMap(
                item -> item.getProductId().toString(),
                Function.identity(),
                (a, b) -> a));

//        String relationFilePath = "/Users/nali/Downloads/promotion-relation.csv";
        String relationFilePath = "/Users/nali/work_file/package-test-product-relation.csv";
        List<ProductRelation> productRelations = csvUtil.getCsvData(relationFilePath, ProductRelation.class);

        Multimap<String, String> parentId2ChildIdMap = ArrayListMultimap.create();
        for (ProductRelation productRelation : productRelations) {
            parentId2ChildIdMap.put(productRelation.getParentProductId(), productRelation.getChildProductId());
        }

        List<String> promotionRuleInsertSqls = new ArrayList<>();
        List<String> promotionItemInsertSqls = new ArrayList<>();

        PromotionBuilder promotionBuilder = new PromotionBuilder();

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
                PromotionItem promotionItem = promotionBuilder.buildPromotionItem(parentItem, childItem, promotionRule);
                promotionItemInsertSqls.add(promotionBuilder.getPromotionItemInsertSql(promotionItem));
                promotionItems.add(promotionItem);
            }
            promotionRule.promotionItemList = promotionItems;
            promotionRuleInsertSqls.add(promotionBuilder.getPromotionRuleInsertSql(promotionRule));


        }


        for (String promotionRuleInsertSql : promotionRuleInsertSqls) {
            System.out.println(promotionRuleInsertSql);
        }

        for (String promotionItemInsertSql : promotionItemInsertSqls) {
            System.out.println(promotionItemInsertSql);
        }
    }


}
