package com.erik.jdk;

import java.io.*;

public class RecoverDataContext {

    private final static String UPDATE_SQL = "UPDATE XIMA_XAC.XAC_TRADE_ORDER_%02d SET CONTEXT=%s WHERE TRADE_ORDER_ID=%s;\n";

    public static void main(String[] args) {


        String pathName = "/Users/nali/Downloads/context4.csv";

        int counter = 0;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(pathName));

            fileReader.readLine();
            String line = fileReader.readLine();
            while (line != null) {

                String tradeOrderId = line.substring(0, line.indexOf(","));

                line = line.substring(line.indexOf(",") + 1);

                int userId = Integer.parseInt(line.substring(0, line.indexOf(",")));
                int tableIndex = userId % 100;

                String context = line.substring(line.indexOf(",") + 1);

                System.out.printf(UPDATE_SQL, tableIndex, context, tradeOrderId);

                line = fileReader.readLine();
                counter++;
            }
            System.out.println("counter=" + counter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
