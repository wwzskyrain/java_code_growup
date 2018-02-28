import beans.CompensateBean;
import com.alibaba.fastjson.JSON;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVDemo {

    public static void main(String[] args) {

        OpenCSVDemo demo = new OpenCSVDemo();

        FileReader fileReader = null;
        try {

            fileReader = new FileReader("/Users/nali/Desktop/compensate-xi-dian.csv");

            demo.testReadCSVFile(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void testReadCSVFile(FileReader fileReader) {

        List<CompensateBean> compensateBeans = new ArrayList<>();
        try {
            compensateBeans = new CsvToBeanBuilder<CompensateBean>(fileReader).withType(CompensateBean.class).withThrowExceptions(false).build().parse();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        compensateBeans.forEach(compensateBean -> System.out.println(JSON.toJSONString(compensateBean)));
    }


    public void testReadCSVFileWithCSVReader() {
        File file = new File("/Users/nali/Desktop/compensate-xi-dian.csv");
        try {
            List<String[]> data = readCSVFile(file, 0, ",");

            for (String[] array : data) {
                for (int i = 0; i < array.length; i++) {
                    System.out.println(array[i].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<String[]> readCSVFile(File file, int startRow, String characters) throws IOException {
        List<String[]> strArrayList = new ArrayList<String[]>();

        CSVReader reader = null;
        if (",".equalsIgnoreCase(characters)) {
            reader = new CSVReader(new FileReader(file));
        } else {
            //带分隔符
            reader = new CSVReader(new FileReader(file), characters.toCharArray()[0]);
        }

        String[] nextLine;
        int i = 1;
        while ((nextLine = reader.readNext()) != null) {
//          System.out.println("Name: [" + nextLine[0] + "]\nAddress: [" + nextLine[1] + "]\nEmail: [" + nextLine[2] + "]");

            if (i >= startRow)
                strArrayList.add(nextLine);

            i++;
        }

        return strArrayList;
    }

}
