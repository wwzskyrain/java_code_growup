package converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class ConverterStringToChannelTypeId extends AbstractBeanField<Integer> {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

        if (value == null) {
            throw new CsvConstraintViolationException("value is null");
        }
        value = value.trim().toLowerCase();
        if (value.equals("apple")||value.equals("ios")) {
            return 5;
        } else if (value.equals("android")){
            return 4;
        }else {
            throw  new CsvConstraintViolationException("invalid deviceTypeName:"+value);
        }

    }

}
