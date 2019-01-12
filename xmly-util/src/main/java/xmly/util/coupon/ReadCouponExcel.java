package xmly.util.coupon;

import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.DecimalFormat;

public class ReadCouponExcel {

    private static String INSERT_COUPON_PATTERN = "INSERT INTO ads_coupon.coupon ( name, description, activity_id, generate_rule, type, using_rule, valid_date, create_time, update_time, status, seq, batch_no, is_plus_coupon) VALUES ( '%s', null, %d, '{\"generateType\":null,\"quantity\":0}', 'PAYED_SUBSCRIBE', '{\"usingLimit\":{\"usingLimitType\":\"UNLIMIT\",\"minLimitValue\":0},\"discount\":{\"discountType\":\"RATE\",\"couponValue\":null,\"plusRate\":66,\"broadCasterRate\":100},\"timeRanges\":null,\"amount\":0,\"recvTimes\":0,\"isShowOnAlbum\":0,\"weight\":0,\"manualWeight\":0,\"labelName\":null,\"usingScope\":\"APPOINTED\",\"albums\":[%s]}', '{\"type\":\"FIXED_TIME_PERIOD\",\"startTime\":1546185600000,\"endTime\":1546531199000,\"validDays\":0}', now(), now(), 'CREATED', 1, 0, 0);";


    public static void insertCouponFor6370() {
        File xlsFile = new File("/Users/nali/work_file/think_span_year_12_26_6370.xlsx");

        int activityId = 6370;
        // 获得工作簿
        Workbook workbook = null;
        try {
            PrintWriter writer = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter("/Users/nali/work_file/add_five_album_coupon_12_26_6370.sql")));

            workbook = WorkbookFactory.create(xlsFile);

            // 获得工作表个数
            int sheetCount = workbook.getNumberOfSheets();
            // 遍历工作表

            DecimalFormat decimalFormat = new DecimalFormat("#");
            for (int i = 0; i < 1; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                // 获得行数
                int rowTotalNumber = sheet.getLastRowNum() + 1;

                System.out.println("rowTotalNumber:" + rowTotalNumber);

                for (int rowNo = 1; rowNo < rowTotalNumber; rowNo++) {
                    Row row = sheet.getRow(rowNo);
                    Cell couponIdCell = row.getCell(0);
                    Cell couponNameCell = row.getCell(1);

                    String albumId = decimalFormat.format(Double.valueOf(couponIdCell.getNumericCellValue()));
                    String couponName = couponNameCell.getStringCellValue();
                    System.out.printf("No line:%d\n", rowNo);
                    writer.println(String.format(INSERT_COUPON_PATTERN, couponName, activityId, albumId));
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void insertCouponFor6384() {

        File xlsFile = new File("/Users/nali/work_file/think_span_year_12_16_6384.xlsx");

        int activityId = 6384;
        // 获得工作簿
        Workbook workbook = null;
        try {
            PrintWriter writer = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter("/Users/nali/work_file/add_five_album_coupon_12_26_6384.sql")));

            workbook = WorkbookFactory.create(xlsFile);

            // 获得工作表个数
            int sheetCount = workbook.getNumberOfSheets();
            // 遍历工作表

            DecimalFormat decimalFormat = new DecimalFormat("#");
            for (int i = 0; i < 1; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                // 获得行数
                int rowTotalNumber = sheet.getLastRowNum() + 1;
                System.out.println("rowTotalNumber:" + rowTotalNumber);
                for (int rowNo = 1; rowNo < rowTotalNumber; rowNo++) {
                    Row row = sheet.getRow(rowNo);
                    Cell couponIdCell = row.getCell(0);
                    Cell couponNameCell = row.getCell(1);

                    String albumId = decimalFormat.format(Double.valueOf(couponIdCell.getNumericCellValue()));
                    String couponName = couponNameCell.getStringCellValue();

                    System.out.printf("No line:%d\n", rowNo);
                    writer.println(String.format(INSERT_COUPON_PATTERN, couponName, activityId, albumId));
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

//        String inputFilePath = "/Users/nali/work_file/coupon/coupon_6370_to_update.xlsx";
//        String outFilePath = "/Users/nali/work_file/coupon/sql_update_coupon_name_6370.sql";
//        updateCouponName(inputFilePath, outFilePath);

        String inputFilePath = "/Users/nali/work_file/coupon/coupon_6384_to_update.xlsx";
        String outFilePath = "/Users/nali/work_file/coupon/sql_update_coupon_name_6384.sql";
        updateCouponName(inputFilePath, outFilePath);


    }

    public static void updateCouponName(String inputFilePath, String outputFilePath) {

        String updateCouponNameSqlPattern = "update ads_coupon.coupon set name = \"%s\" ,update_time = now() where id = %s;";

        File xlsFile = new File(inputFilePath);

        // 获得工作簿
        Workbook workbook = null;
        try {
            PrintWriter writer = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(outputFilePath)));

            workbook = WorkbookFactory.create(xlsFile);

            // 获得工作表个数
            int sheetCount = workbook.getNumberOfSheets();
            // 遍历工作表

            DecimalFormat decimalFormat = new DecimalFormat("#");
            for (int i = 0; i < 1; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                // 获得行数
                int rowTotalNumber = sheet.getLastRowNum() + 1;
                System.out.println("rowTotalNumber:" + rowTotalNumber);

                for (int rowNo = 1; rowNo < rowTotalNumber; rowNo++) {
                    Row row = sheet.getRow(rowNo);
                    Cell couponIdCell = row.getCell(0);
                    Cell couponNameCell = row.getCell(1);

                    String couponId = decimalFormat.format(Double.valueOf(couponIdCell.getNumericCellValue()));
                    String couponName = couponNameCell.getStringCellValue();

                    String updateCouponNameSql = String.format(updateCouponNameSqlPattern, couponName, couponId);
                    System.out.printf("No line:%d \t %s\n", rowNo, updateCouponNameSql);
                    writer.println(updateCouponNameSql);
                    writer.flush();
                }
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
