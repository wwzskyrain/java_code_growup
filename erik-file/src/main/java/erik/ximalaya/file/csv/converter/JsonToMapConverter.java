package erik.ximalaya.file.csv.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.util.Map;

/**
 * @Date 2019-09-04
 * @Created by erik
 */
public class JsonToMapConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

        Map<String, String> map = JSON.parseObject(value, new TypeReference<Map<String, String>>() {
        });

        return map;
    }

    @Override
    public String convertToWrite(Object value) throws CsvDataTypeMismatchException {
        return JSON.toJSONString(value);
    }
}
