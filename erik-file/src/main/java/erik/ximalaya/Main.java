package erik.ximalaya;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    private static final char PAD_CHAR = '0';

    public static void main(String[] args) throws IOException {

        String path = "/Users/nali/work_file/11.27优惠券需求.xlsx";

        String sqlScriptFilePathOfExternal = "/Users/nali/work_file/insert-coupon-external-station.sql";
        String sqlScriptFilePathOfInner = "/Users/nali/work_file/insert-coupon-inner-station.sql";


        long activityIdStationInner = 5107l;
        long activityIdStationExternal = 5108l;
//        long activityIdTest = 3375l;

        String INSERT_COUPON_PATTERN = "insert into `ads_coupon`.`coupon` ( `name`, `description`, `activity_id`, `generate_rule`, `type`, `using_rule`, `valid_date`, `create_time`, `update_time`, `status`, `seq`, `batch_no`, `is_plus_coupon`) values ( '%s', null, '%d', '{\\\"generateType\\\":null,\\\"quantity\\\":0}', 'PAYED_SUBSCRIBE', '{\\\"usingLimit\\\":{\\\"usingLimitType\\\":\\\"UNLIMIT\\\",\\\"minLimitValue\\\":0},\\\"discount\\\":{\\\"discountType\\\":\\\"RATE\\\",\\\"couponValue\\\":null,\\\"plusRate\\\":50,\\\"broadCasterRate\\\":100},\\\"timeRanges\\\":null,\\\"amount\\\":0,\\\"recvTimes\\\":0,\\\"isShowOnAlbum\\\":0,\\\"weight\\\":0,\\\"manualWeight\\\":0,\\\"labelName\\\":null,\\\"usingScope\\\":\\\"APPOINTED\\\",\\\"albums\\\":[%d]}', '{\\\"type\\\":\\\"FIXED_TIME_PERIOD\\\",\\\"startTime\\\":1543593600000,\\\"endTime\\\":1543939199000,\\\"validDays\\\":0}', NOW(), NOW(), 'CREATED', '1', '0', '0');\n";

//        FileWriter sqlScriptFileExternalWriter = new FileWriter(sqlScriptFilePathOfExternal, false);
//        FileWriter sqlScriptFileInnerFWriter = new FileWriter(sqlScriptFilePathOfInner, false);

        Workbook workbook = readFile(path);
        for (int k = 0; k < workbook.getNumberOfSheets(); k++) {
            Sheet sheet = workbook.getSheetAt(k);

            int rows = sheet.getPhysicalNumberOfRows();
            System.out.printf("Sheet %d with name %s has %d rows \n", k, workbook.getSheetName(k), rows);

            int lineNo = 1;
            for (Row row : sheet) {
                if (lineNo <= 1) {
                    lineNo++;
                    continue;
                }

                String couponName = row.getCell(0).toString();

                double couponIdDouble = row.getCell(2).getNumericCellValue();
                Long couponIdLong = new BigDecimal(couponIdDouble).longValue();

                String insertCouponOfExternal = String.format(INSERT_COUPON_PATTERN, couponName, activityIdStationExternal, couponIdLong);
                String insertCouponOfInner = String.format(INSERT_COUPON_PATTERN, couponName, activityIdStationInner, couponIdLong);

//                sqlScriptFileExternalWriter.write(insertCouponOfExternal);
//                sqlScriptFileExternalWriter.flush();
//
//                sqlScriptFileInnerFWriter.write(insertCouponOfInner);
//                sqlScriptFileInnerFWriter.flush();

                System.out.print(insertCouponOfExternal);
                System.out.print(insertCouponOfInner);

            }

        }

    }


    private static Workbook readFile(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            return new XSSFWorkbook(fis);        // NOSONAR - should not be closed here
        }
    }

    public static Long getProductId(Integer domain, Integer categoryId, Integer productTypeId, String spuId) {

        return Long.parseLong(StringUtils.rightPad(String.valueOf(domain), 2, PAD_CHAR) +
                String.valueOf(categoryId) +
                String.valueOf(productTypeId) +
                trim(spuId, 11));
    }

    private static String trim(String str, int maxLen) {
        if (str.length() > maxLen) {
            return str.substring(str.length() - maxLen);
        }
        return StringUtils.leftPad(str, maxLen, PAD_CHAR);
    }

}
