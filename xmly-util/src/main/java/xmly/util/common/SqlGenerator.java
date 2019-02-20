package xmly.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SqlGenerator {

    private final static Logger logger = LoggerFactory.getLogger(SqlGenerator.class);

    public void generateInsertSql(String completeCreateTableSql,
                                  String tableNameWithoutNumberSuffix,
                                  int suffixBeginNumber,
                                  int suffixEndNumber) {

        logger.info("call method generateInsertSql with parameter completeCreateTableSql:{}, tableNameWithoutNumberSuffix:{}, suffixBeginNumber:{}, suffixEndNumber:{}",
                completeCreateTableSql, tableNameWithoutNumberSuffix, suffixBeginNumber, suffixEndNumber);

        if (suffixBeginNumber < 0 || suffixBeginNumber > suffixEndNumber) {
            logger.error("invalid suffixBeginNumber:{} or suffixEndNumber:{}", suffixBeginNumber, suffixEndNumber);
        }

        List<String> createSqlList = new ArrayList<>();

        int bitNum = ((int) Math.log10(suffixEndNumber)) + 1;

        for (int i = suffixBeginNumber; i <= suffixEndNumber; i++) {
            createSqlList.add(completeCreateTableSql.replace(tableNameWithoutNumberSuffix,
                    String.format("%s_%0" + bitNum + "d", tableNameWithoutNumberSuffix, i)));
        }

        try {
            String fileName = new StringBuffer()
                    .append("create-table-")
                    .append(tableNameWithoutNumberSuffix)
                    .append(".sql")
                    .toString();
            FileWriter fileWriter = new FileWriter(fileName);

            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(fileWriter));

                createSqlList.forEach(out::println);

                out.flush();
            } finally {
                fileWriter.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
