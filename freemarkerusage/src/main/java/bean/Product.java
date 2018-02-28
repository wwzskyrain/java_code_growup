package bean;

public class Product {

    private String url;
    private String name;

    // As per the JavaBeans spec., this defines the "url" bean property
    public String getUrl() {
        return url;
    }

    // As per the JavaBean spec., this defines the "name" bean property
    public String getName() {
        return name;
    }

    public Product() {
    }

    public Product(String url, String name) {
        this.url = url;
        this.name = name;
    }
}
