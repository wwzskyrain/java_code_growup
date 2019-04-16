package xmly.util.coupon;

import org.apache.poi.ss.usermodel.*;
import xmly.util.coupon.format.StringFormat;

import java.io.*;
import java.util.*;

import static xmly.util.coupon.CouponConstants.*;

public class ReadCouponExcel {


    public static void insertCoupon(String couponXlsFile,
                                    Integer activityId,
                                    String activityName) {

        File xlsFile = new File(couponXlsFile);

        Calendar calendar = Calendar.getInstance();

        StringBuilder dateBuilder = new StringBuilder()
                .append(calendar.get(Calendar.YEAR)).append("-")
                .append(calendar.get(Calendar.MONTH) + 1).append("-")
                .append(calendar.get(Calendar.DAY_OF_MONTH));

        String sqlFileName = String.format("%s-%d-%s.sql", dateBuilder.toString(), activityId, activityName);
        System.out.printf("insertCouponSql will be write to file:%s\n", sqlFileName);
        // 获得工作簿
        Workbook workbook = null;
        try {
            PrintWriter writer = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter("/Users/nali/work_file/coupon/" + sqlFileName)));

            workbook = WorkbookFactory.create(xlsFile);

            //只处理第一个sheet
            Sheet sheet = workbook.getSheetAt(0);

            for (int rowNo = 1; rowNo <= sheet.getLastRowNum(); rowNo++) {

                Row row = sheet.getRow(rowNo);

                if (row == null) {
                    continue;
                }

                CouponMetaData couponMetaData = CouponMetaData.excelRowDataParser(row);

                if (couponMetaData == null) {
                    continue;
                }

                Map<String, String> keyValues = new HashMap<>();
                keyValues.put(KEY_NAME_ALBUM_IDS, couponMetaData.getAlbumIds());
                keyValues.put(KEY_NAME_COUPON_NAME, couponMetaData.getCouponName().replace('\'',' '));
                keyValues.put(KEY_NAME_START_TIME, couponMetaData.getStartTime());
                keyValues.put(KEY_NAME_END_TIME, couponMetaData.getEndTime());
                keyValues.put(KEY_NAME_PLUS_RATE, couponMetaData.getPlusRate());
                keyValues.put(KEY_NAME_BROAD_CASTER_RATE, couponMetaData.getBroadCasterRate());
                keyValues.put(KEY_NAME_ACTIVITY_ID, String.valueOf(activityId));
                keyValues.put(KEY_NAME_COUPON_VALUE, couponMetaData.getPlusRate());

                String insertCouponSql = StringFormat.format(CouponConstants.INSERT_DISCOUNT_COUPON_PATTERN, keyValues);

                writer.println(insertCouponSql);
                System.out.printf("No.%d generate insert sql for albumId:%s couponName:%s\n",
                        rowNo, couponMetaData.getAlbumIds(), couponMetaData.getCouponName());
                writer.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {


        String couponExcelFilePathPattern = "/Users/nali/work_file/coupon/423-%d.xlsx";

        List<Integer> activityIds = Arrays.asList(7545,7546,7547);

        for (int i = 0; i < activityIds.size(); i++) {
            Integer activity = activityIds.get(i);
            String couponExcelFilePath = String.format(couponExcelFilePathPattern,activity);
            String activityName = activity+"name";
            insertCoupon(couponExcelFilePath,activity,activityName);
        }

    }


}
