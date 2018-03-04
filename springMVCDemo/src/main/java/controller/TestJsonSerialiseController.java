package controller;

import model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@RequestMapping(value = "/json")
@Controller
public class TestJsonSerialiseController {


    @RequestMapping(value = "/student")
    @ResponseBody
    public List<Student> getStudent() {

        System.out.println("request /json/student");

        Student erik = new Student("erik.wang", 172, 10000l);

        Student song = new Student();
        song.setName("Song.Jiang");

        return Arrays.asList(erik, song);

    }

}
