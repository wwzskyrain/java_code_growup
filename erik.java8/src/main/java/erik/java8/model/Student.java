package erik.java8.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nali on 2017/10/18.
 */
public class Student {

    private Long id;
    private String name;
    private Float heigth;

    public Student() {
    }

    public Student(Long id, String name, Float heigth) {
        this.id = id;
        this.name = name;
        this.heigth = heigth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getHeigth() {
        return heigth;
    }

    public void setHeigth(Float heigth) {
        this.heigth = heigth;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", heigth=" + heigth +
                '}';
    }

    public static List<Student> students=new ArrayList<>();
    static {
        students.add(new Student(1l,"1name",1.01f));
        students.add(new Student(2l,"2name",2.01f));
        students.add(new Student(3l,"3name",3.01f));
        students.add(new Student(4l,"4name",4.01f));
        students.add(new Student(5l,"5name",5.01f));
    }
}
