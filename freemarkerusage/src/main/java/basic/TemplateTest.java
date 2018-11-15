package basic;

import bean.Product;
import directive.UpperDirective;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import mymethod.IndexOfMethod;
import vo.TradeOrderVo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TemplateTest {

    public static void main(String[] args) throws IOException, TemplateException {

//        System.out.println(System.getProperty("user.dir"));

        new TemplateTest().test();

    }

    public void test() throws IOException, TemplateException {

        Configuration configuration = TemplateFactory.getConfiguration();

        Map<String, Object> root = new HashMap<>();

        Product product = new Product("product/green/mouse.html", "green mouse");

        product.setHotProduct(true);
        product.setUsable(false);

        root.put("product", product);

        root.put("indexOf", new IndexOfMethod());

        root.put("upper", new UpperDirective());

        Template template = null;

        try {
            template = configuration.getTemplate("test.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

        root.put("testBoolean", null);

        TradeOrderVo tradeOrderVo = new TradeOrderVo();
        tradeOrderVo.setCompensatableForAlbum(null);
        root.put("tradeOrderVo", tradeOrderVo);
        boolean booleanV = false;
        root.put("booleanV", booleanV);

        Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

        template.process(root, out);
    }


}
