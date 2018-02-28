package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nali on 2017/10/22.
 */
@Controller
public class StudentController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getHelloMessage(){

        System.out.println("hello request.");

        Map<String,String> dataModel=new HashMap<>();

        dataModel.put("songJiang","Boss");
        dataModel.put("LinChong","general");

        return dataModel;

    }

}
