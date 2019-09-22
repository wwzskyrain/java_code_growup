package erik.ximalaya.file.csv.beans;

import com.opencsv.bean.CsvBindByName;

/**
 * @Date 2019-09-09
 * @Created by erik
 */
public class Product {
//    PRODUCT_ID,DOMAIN,CATEGORY_ID,SPU_ID,PRODUCT_TYPE_ID,STATUS,NAME,DESCRIPTION,COVER_URL,PROPERTIES,OWNER_ID,DETAILS,CREATE_TIME,LAST_UPDATE_TIME,VERSION

    @CsvBindByName(column = "PRODUCT_ID")
    private String productId;

    @CsvBindByName(column = "NAME")
    private String name;

    @CsvBindByName(column = "DESCRIPTION")
    private String description;

    @CsvBindByName(column = "COVER_URL")
    private String coverUrl;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
