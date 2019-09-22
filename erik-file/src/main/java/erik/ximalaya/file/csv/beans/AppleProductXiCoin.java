package erik.ximalaya.file.csv.beans;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

/**
 * @Date 2019-09-21
 * @Created by erik
 */
public class AppleProductXiCoin implements Serializable {
    private static final long serialVersionUID = -2982320955773475378L;

    //    price,productId,description,status

    @CsvBindByName(column = "price")
    private String price;

    @CsvBindByName(column = "productId")
    private String appleProductId;

    @CsvBindByName(column = "description")
    private String description;

    @CsvBindByName(column = "status")
    private String status;

    public AppleProductXiCoin() {
    }

    public AppleProductXiCoin(String price, String appleProductId, String description, String status) {
        this.price = price;
        this.appleProductId = appleProductId;
        this.description = description;
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAppleProductId() {
        return appleProductId;
    }

    public void setAppleProductId(String appleProductId) {
        this.appleProductId = appleProductId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
