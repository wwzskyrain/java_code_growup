package erik.ximalaya.model;

import java.util.List;

public class Student {

    private Integer age;
    private String name;
    private List<Long> bwh;

    public Student() {
    }

    public Student(Integer age, String name, List<Long> bwh) {
        this.age = age;
        this.name = name;
        this.bwh = bwh;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getBwh() {
        return bwh;
    }

    public void setBwh(List<Long> bwh) {
        this.bwh = bwh;
    }
}
