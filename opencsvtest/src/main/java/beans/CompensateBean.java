package beans;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import converter.ConverterStringToChannelTypeId;

import java.math.BigDecimal;

public class CompensateBean {

    @CsvBindByPosition(required = true, position = 0)
    private Long userId;

    @CsvBindByPosition(required = true, position = 1)
    private BigDecimal amount;

    @CsvCustomBindByPosition(required = true, position = 2, converter = ConverterStringToChannelTypeId.class)
    private Integer channelTypeId;


    private Integer testField=100;

    public CompensateBean() {
    }

    public CompensateBean(Long userId, BigDecimal amount, Integer channelTypeId) {
        this.userId = userId;
        this.amount = amount;
        this.channelTypeId = channelTypeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(Integer channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public Integer getTestField() {
        return testField;
    }

    public void setTestField(Integer testField) {
        this.testField = testField;
    }
}
