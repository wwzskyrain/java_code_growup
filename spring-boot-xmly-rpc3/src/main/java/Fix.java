import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Date 2019-09-04
 * @Created by erik
 */
public class Fix {

    public static void main(String[] args) throws IOException {
        String pattern = "INSERT INTO XIMA_PMP.PMP_PROMOTION_ITEM ( PROMOTION_ID, ITEM_ID, PROMOTION_PRICE, CREATE_TIME, LAST_UPDATE_TIME) VALUES ( %s, %s, NULL, '%s', '%s');";
        String fileName = "/Users/nali/Downloads/fix-coupon-item.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line = null;

        int lineNo = -1;
        while ((line = bufferedReader.readLine()) != null) {
            lineNo++;
            String[] lineStringArray = line.split(",");

            String promotionId = lineStringArray[1];
            if (lineNo == 0) {
                continue;
            }
            String itemId = lineStringArray[2];

            String presentItemId = new StringBuilder().append(itemId.substring(0, 7))
                    .append('4')
                    .append(itemId.substring(8))
                    .toString();

            String createTime = lineStringArray[4];
            String lastUpdateTime = lineStringArray[4];
            System.out.println(String.format(pattern, promotionId, presentItemId, createTime, lastUpdateTime));
        }

    }

}
