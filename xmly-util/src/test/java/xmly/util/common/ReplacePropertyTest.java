package xmly.util.common;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;


public class ReplacePropertyTest {

    ReplaceProperty replaceProperty ;

    @Before
    public void init(){
        replaceProperty = new ReplaceProperty();
    }

    @Test
    public void test(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String filePath = "/Users/nali/project_erik/java_code_growup/xmly-util/mainstay.properties";
        String targetPostfix = ".i18n";

        new ReplaceProperty().doReplace(filePath, targetPostfix);
    }



}
