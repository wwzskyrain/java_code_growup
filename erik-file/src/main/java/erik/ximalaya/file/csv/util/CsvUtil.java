package erik.ximalaya.file.csv.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public class CsvUtil {
    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvUtil.class);

    /**
     * 解析csv文件并转成bean
     * @param fileName  csv文件路径
     * @param clazz 类
     * @param <T>   泛型
     * @return 泛型bean集合
     */
    public <T> List<T> getCsvData(String fileName, Class<T> clazz) {

        try {
            Reader fileReader = new FileReader(fileName);
            try {
                HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
                strategy.setType(clazz);

                CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(fileReader)
                        .withSeparator(',')
                        .withQuoteChar('\"')
                        .withMappingStrategy(strategy)
                        .build();
                return csvToBean.parse();
            } finally {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
