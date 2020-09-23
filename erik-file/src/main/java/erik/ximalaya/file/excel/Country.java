package erik.ximalaya.file.excel;

/**
 * @author erik.wang
 * @date 2020-07-20 14:39
 */
public class Country {

    private String name;
    private String shortCode;

    public Country(String name, String shortCode) {
        this.name = name;
        this.shortCode = shortCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
}
