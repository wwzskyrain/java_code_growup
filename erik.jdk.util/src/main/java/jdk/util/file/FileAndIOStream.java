package jdk.util.file;

import java.io.*;

/**
 * Created by nali on 2017/10/28.
 */
public class FileAndIOStream {


    public static void main(String[] args) throws IOException {
        String filePath = "/Users/nali/study/word-track/data";
        String unifiedOrderNos = new FileAndIOStream().getUnifiedOrderNo(filePath);
        System.out.println(unifiedOrderNos);
    }



    public  String getUnifiedOrderNo(String filePath) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));

        StringBuffer stringBuffer = new StringBuffer();
        String data = null;
        while((data = br.readLine())!=null)
        {
            stringBuffer.append(data).append(",");
        }

        return stringBuffer.toString();

    }

}
