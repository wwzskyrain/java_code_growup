package jdk.util.io;

import java.io.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertiesReadWriteDemo {


    public static void main(String[] args) {

//        String propertiesFilePath = "erik.jdk.util/mainstay.properties";
//        String mainstayPropertiesFileContent = getFileContent(propertiesFilePath);
//        replace(mainstayPropertiesFileContent);

        replace("zk.connection=1111111");

    }

    public static void test_1(){
        Properties properties = new Properties();


        String propertiesFilePath = "erik.jdk.util/mainstay.properties";

        try {
            FileWriter mainstayPropertiesFileWriter = new FileWriter(propertiesFilePath, true);
            properties.load(new FileReader(propertiesFilePath));
            properties.forEach((k, v) -> System.out.printf("%s=%s\n", k, v));

            String zkConnectionKey = "zk.connection";
            String zkConnectionI18nKey = "zk.connection.i18n";


            String zkConnectionI18nValue = properties.getProperty(zkConnectionI18nKey);
            if (zkConnectionI18nValue == null) {
                System.err.println(String.format("key:%s not found in config file:%s", zkConnectionI18nKey, propertiesFilePath));
            }


            properties.setProperty(zkConnectionKey, zkConnectionI18nValue);
            String comments = zkConnectionKey + " is overridden to " + zkConnectionI18nValue;
            properties.store(mainstayPropertiesFileWriter, comments);
            mainstayPropertiesFileWriter.flush();
            System.out.println("overriding " + zkConnectionKey + "success");
            System.out.println("over!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileContent(String path){
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line).append('\n');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static String replace(String sourceString){

        Pattern pattern = Pattern.compile("zk.connection=");

        Matcher matcher = pattern.matcher(sourceString);

        if(matcher.matches()){

            System.out.println(matcher.replaceAll("zk.connection=zk.connection.i18n.value"));
        }
        System.out.println("after replaceAll");
        System.out.println(sourceString);
        return sourceString;

    }

}
