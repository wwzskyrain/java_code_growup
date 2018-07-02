package jdk.util.datastructure.tree;

import com.alibaba.fastjson.JSON;
import jdk.util.datastructure.tree.vo.Category;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SequencingTree {


    public static Integer autoIncreaseCategoryId = 300;

    public static String INSERT_SQL_PATTERN = "INSERT INTO XIMA_PRD2.PRD_PRODUCT_CATEGORY VALUES (%d,\"%s\",1,%d,NOW(),NOW(),1);";

    public static void main(String[] args) {


        StringBuilder stringBuilder = getData();

        Category rootCategory = JSON.parseObject(stringBuilder.toString(), Category.class);

//        rootCategory = sequencingCategoryId(rootCategory);

        System.out.println(JSON.toJSONString(rootCategory));

        List<Category> categories = linearizingCategoryTree(rootCategory);

        categories.stream()
                .sorted(Comparator.comparing(Category::getCategoryId))
                .forEach(SequencingTree::createInsertSql);


    }

    public static void createInsertSql(Category category){

        System.out.printf(INSERT_SQL_PATTERN+"\n",
                category.getCategoryId(),category.getName(),category.getParentId());

    }

    public static Category sequencingCategoryId(Category category) {
        Queue<Category> queue = new LinkedList<>();
        queue.addAll(category.getChildren());
        while (!queue.isEmpty()) {
            Category removedCategory = queue.remove();
            List<Category> children = removedCategory.getChildren();

            if (children != null && children.size() > 0) {
                for (Category child : children) {
                    child.setParentId(autoIncreaseCategoryId);
                }
            }
            removedCategory.setCategoryId(autoIncreaseCategoryId);
            autoIncreaseCategoryId++;
            queue.addAll(children);

        }
        return category;
    }

    public static List<Category> linearizingCategoryTree(Category category) {

        if (category == null) {
            return null;
        }

        Queue<Category> queue = new LinkedList<>();
        List<Category> categories = new ArrayList<>();
        queue.add(category);

        while (!queue.isEmpty()) {
            Category categoryRemoved = queue.remove();
            categories.add(categoryRemoved);
            List<Category> children = categoryRemoved.getChildren();
            if (children != null && children.size() > 0) {
                queue.addAll(children);
            }
        }

        return categories;

    }

    public static StringBuilder getData() {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(new FileReader("/Users/nali/study/work-track/erp_sql_insert_category_test.json"));

            String data = null;
            while ((data = in.readLine()) != null) {
                stringBuilder.append(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder;

    }

}
