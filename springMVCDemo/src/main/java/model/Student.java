package model;

public class Student {

    private String name;
    private Integer height;
    private Long id;

    public Student() {
    }

    public Student(String name, Integer height, Long id) {
        this.name = name;
        this.height = height;
        this.id = id;
    }

    public String getName() {
        return "[" + name + "]";
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

}
