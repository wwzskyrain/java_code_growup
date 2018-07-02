package com.erik.jdk;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommonMainForTemp {

    public static final String updateSQLTemplate = "UPDATE `XIMA_XAC`.`XAC_RECHARGE_ORDER` SET `STATUS_ID` = 3 WHERE `MERCHANT_ORDER_NO` = '%s';";

    public static final String merchantOrderNos = "";

    public static void main(String[] args) {

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/nali/study/word-track/merchant_order_no_to_change_recharge_order_status"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> merchantOrderNoLink = new LinkedList<>();

        if (bufferedReader != null) {
            try {
                String merchantOrderNo = null;
                do {
                    merchantOrderNo = bufferedReader.readLine();
                    merchantOrderNoLink.add(merchantOrderNo);
                } while (merchantOrderNo != null);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String[] merchantOrderNoArray = merchantOrderNoLink.toArray(new String[]{});

        List<String> updateSqlList = createUpdateSql(merchantOrderNoArray);

        writeToFile("/Users/nali/study/word-track/merchant_order_no_sql",updateSqlList);


    }

    public static List<String> createUpdateSql(String[] merchantOrderNos) {

        if (merchantOrderNos == null || merchantOrderNos.length == 0) {
            return new LinkedList<>();
        }

        List<String> updateSqlList = new LinkedList();
        for (int i = 0; i < merchantOrderNos.length; i++) {
            String updateSql = String.format(updateSQLTemplate, merchantOrderNos[i]);
            updateSqlList.add(updateSql);
        }

        return updateSqlList;
    }

    public static void writeToFile(String path,List<String> content) {

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(path)));

            if(content==null){
                return;
            }
            for(int i=0;i<content.size();i++){

                out.println(content.get(i));

                System.out.printf("%d : %s\n",i,content.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        out.flush();



    }

}
