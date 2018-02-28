package erik.jdbc;

import java.io.*;
import java.util.Properties;

/**
 * Created by nali on 2017/10/13.
 */
public class TestProperty {


    public static void main(String[] args) throws IOException {

        System.out.println(System.getProperty("user.dir"));
//        testPropertiesClass(testClassLoaderGetResourceAsStream("db.properties"));

        InputStream inputStream = TestProperty.class.getClassLoader().getResourceAsStream("db2.properties");

        testPropertiesClass(inputStream);

    }

    public static void testPropertiesClass(InputStream inputStream) throws IOException {

        Properties properties=new Properties();

        properties.load(inputStream);

        properties.forEach((key,value) -> System.out.println(key+" = "+value));

    }


    public static InputStream testClassLoaderGetResourceAsStream(String fileName){

        ClassLoader classLoader=ClassLoader.getSystemClassLoader();
        return classLoader.getResourceAsStream(fileName);

    }


}
