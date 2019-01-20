package xmly.util.coupon;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import static xmly.util.coupon.CouponConstants.*;

public class CouponMetaData {

    private String albumIds;
    private String couponName;
    private String startTime;
    private String endTime;
    private String plusRate;
    private String broadCasterRate;

    public CouponMetaData() {
    }

    public static CouponMetaData excelRowDataParser(Row row) {

        CouponMetaData couponMetaData = new CouponMetaData();

        Cell albumIdsCell = row.getCell(KEY_COLUMN_NO_ALBUM_IDS);
        switch (albumIdsCell.getCellType()) {
            case NUMERIC:
                couponMetaData.setAlbumIds(getIntegralNum(albumIdsCell));
                break;
            case STRING:
                couponMetaData.setAlbumIds(albumIdsCell.getStringCellValue());
            default:
                throw new RuntimeException("invalid value type for albumIdCell.");
        }

        couponMetaData.setCouponName(row.getCell(KEY_COLUMN_NO_COUPON_NAME).getStringCellValue());

        couponMetaData.setStartTime(getMilliseconds(row.getCell(KEY_COLUMN_NO_START_TIME)).toString());
        couponMetaData.setEndTime(getMilliseconds(row.getCell(KEY_COLUMN_NO_END_TIME)).toString());
        couponMetaData.setPlusRate(getIntegralNum(row.getCell(KEY_COLUMN_NO_PLUS_RATE)));
        couponMetaData.setBroadCasterRate(getIntegralNum(row.getCell(KEY_COLUMN_NO_BROAD_CASTER_RATE)));

        return couponMetaData;
    }

    public static Long getMilliseconds(Cell cell) {
        Date date = cell.getDateCellValue();
        return date.getTime();
    }

    public static String getIntegralNum(Cell cell) {
        return new BigDecimal(cell.getNumericCellValue()).toString();
    }

    public String getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(String albumIds) {
        this.albumIds = albumIds;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPlusRate() {
        return plusRate;
    }

    public void setPlusRate(String plusRate) {
        this.plusRate = plusRate;
    }

    public String getBroadCasterRate() {
        return broadCasterRate;
    }

    public void setBroadCasterRate(String broadCasterRate) {
        this.broadCasterRate = broadCasterRate;
    }

}
