package erik.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by nali on 2017/9/24.
 */
public class LambdaExpression {


    public static void main(String[] args) {


        lambda();

    }



    public static  void  lambda(){

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names,(String a,String b)->{
            return a.compareTo(b);
        });

        System.out.println(names);

    }

}
