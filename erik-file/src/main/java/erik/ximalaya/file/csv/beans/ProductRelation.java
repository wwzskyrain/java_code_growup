package erik.ximalaya.file.csv.beans;

import com.opencsv.bean.CsvBindByName;

/**
 * @Date 2019-09-09
 * @Created by erik
 */
public class ProductRelation {

    @CsvBindByName(column = "CHILD_ID")
    private String childProductId;

    @CsvBindByName(column = "PARENT_ID")
    private String parentProductId;

    public String getParentProductId() {
        return parentProductId;
    }

    public void setParentProductId(String parentProductId) {
        this.parentProductId = parentProductId;
    }

    public String getChildProductId() {
        return childProductId;
    }

    public void setChildProductId(String childProductId) {
        this.childProductId = childProductId;
    }
}
