package basic;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

public class TemplateFactory {

    private static final String FTL_FILE_DIR="freemarkerusage/src/main/resources/ftl";

    private static Configuration configuration = buildConfiguration();

    private static Configuration buildConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);

        try {
            configuration.setDirectoryForTemplateLoading(new File(FTL_FILE_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }

        configuration.setDefaultEncoding("UTF-8");

        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);

        return configuration;
    }

    public static Configuration getConfiguration(){
        return configuration;
    }


}
