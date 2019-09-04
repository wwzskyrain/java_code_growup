package erik.ximalaya.file.csv.beans;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import erik.ximalaya.file.csv.converter.JsonToMapConverter;

import java.util.Map;

/**
 * @Date 2019-09-04
 * @Created by erik
 */
public class XiDianBean {

    //    PRODUCT_ID,DOMAIN,CATEGORY_ID,SPU_ID,PRODUCT_TYPE_ID,STATUS,NAME,DESCRIPTION,COVER_URL,PROPERTIES,OWNER_ID,DETAILS,CREATE_TIME,LAST_UPDATE_TIME,VERSION
    @CsvBindByName(column = "PRODUCT_ID")
    private String productId;

    @CsvBindByName(column = "DESCRIPTION")
    private String description;

    @CsvCustomBindByName(column = "PROPERTIES", converter = JsonToMapConverter.class)
    private Map<String, String> properties;

    @CsvBindByName(column = "CREATE_TIME")
    private String createTime;

    @CsvBindByName(column = "VERSION")
    private String version;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "XiDianBean{" +
                "productId='" + productId + '\'' +
                ", description='" + description + '\'' +
                ", properties='" + properties + '\'' +
                ", createTime='" + createTime + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
