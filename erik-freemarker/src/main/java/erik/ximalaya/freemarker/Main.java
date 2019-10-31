package erik.ximalaya.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) {

        // Where the application is initialized; in general you do this ONLY ONCE in the application life-cycle!
        Configuration configuration = new Configuration(new Version(2, 3, 21));
        try {
            configuration.setDirectoryForTemplateLoading(new File("/Users/nali/project_erik/java_code_growup/erik-freemarker/src/main/resources"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Later, whenever the application needs a template (so you may do this a lot, and from multiple threads):
        Template myTemplate = null;
        try {
            myTemplate = configuration.getTemplate("myTemplate.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Writer consoleWriter = new OutputStreamWriter(System.out);

        Map<Object, Object> dataModel = new HashMap<>();

        List<String> listData = new ArrayList<>();

        dataModel.put("list", listData);
        dataModel.put("emptyValue", "");
        dataModel.put("nullValue", null);


        try {
            myTemplate.process(dataModel, consoleWriter);

        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
