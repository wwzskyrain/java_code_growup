package javacsv;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class JavaCSVDemo {

    public static void main(String[] args) {

        String filePath = "/Users/nali/Desktop/compensate-xi-dian.csv";
        try {
            CsvReader csvReader = new CsvReader(filePath, ',');

            while (csvReader.readRecord()) {

                for (int i = 0; i < csvReader.getColumnCount(); i++) {
                    System.out.print(csvReader.get(i) + ",");
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
