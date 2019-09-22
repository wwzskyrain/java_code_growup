package erik.study.sudo;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * @Date 2019-09-22
 * @Created by erik
 */
public class SudoDataTest {

    private SudoData sudoData;

    @Before
    public void init() {
        String filePath = "file/sudo-1";
        sudoData = new SudoData(filePath);
    }

    @Test
    public void test_file_load() {
        Element[] elements = sudoData.getElements();
        for (Element element : elements) {
            System.out.println(element);
        }
    }

    @Test
    public void test_get_elements_by_gong() {
        Set<Element> elements = sudoData.getElementByGong(1);
        Block block = new Block(elements);
        System.out.println(block);

        System.out.println(new Block(sudoData.getElementByGong(3)));
        System.out.println(new Block(sudoData.getElementByGong(7)));
        System.out.println(new Block(sudoData.getElementByGong(8)));
    }

    @Test
    public void test_convert_to_gong_no(){
        System.out.println(sudoData.convertToGongNo(1));
        System.out.println(sudoData.convertToGongNo(3));
        System.out.println(sudoData.convertToGongNo(9));
        System.out.println(sudoData.convertToGongNo(26));
        System.out.println(sudoData.convertToGongNo(27));
        System.out.println(sudoData.convertToGongNo(80));
    }

    @Test
    public void test_find_gong_values(){
        System.out.println(sudoData.findValuesByGongNo(0));
        System.out.println(sudoData.findValuesByGongNo(1));
        System.out.println(sudoData.findValuesByGongNo(5));
        System.out.println(sudoData.findValuesByGongNo(6));
        System.out.println(sudoData.findValuesByGongNo(8));
    }

    @Test
    public void test_find_column_value(){
        System.out.println(sudoData.findValuesByColumnNo(0));
        System.out.println(sudoData.findValuesByColumnNo(1));
        System.out.println(sudoData.findValuesByColumnNo(2));
        System.out.println(sudoData.findValuesByColumnNo(3));
        System.out.println(sudoData.findValuesByColumnNo(5));
        System.out.println(sudoData.findValuesByColumnNo(8));
    }

    @Test
    public void test_update_element_value(){
        Element element = sudoData.getElement(78);
        System.out.println(element);
        sudoData.updateValueOfElement(element);
        System.out.println(element);
    }


}
