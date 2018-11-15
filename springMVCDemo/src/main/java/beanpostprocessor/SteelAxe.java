package beanpostprocessor;

public class SteelAxe
        implements Axe {

    private Double weight;

    public SteelAxe() {
        System.out.println("SteelAxe() 构造函数");
    }


    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        System.out.println("Spring 调用setWeight() 方法 with weight：" + weight);
        this.weight = weight;
    }

    public String chop() {
        return "钢斧砍柴真快";
    }
}