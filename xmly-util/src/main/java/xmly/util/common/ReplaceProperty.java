package xmly.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * 1.替换配置文件中，某些key的value；假设配置文件中，同时存在key和"key+targetPostfix"，则key的value将被"key+targetPostfix"的值所替换<br/>
 * 2.保留配置文件中的其他属性、注释、空格。
 */
public class ReplaceProperty {

    private static final Logger logger = LoggerFactory.getLogger(ReplaceProperty.class);

    public static class DataLine {

        public String content;
        public Property property;

        public DataLine() {
        }

        public DataLine(int number, String content) {
            this.content = content;
            property = buildProperty(this);
        }

    }

    public static class Property {

        public String key;
        public String value;

        public Property(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public Property setValue(String value) {
            this.value = value;
            return this;
        }
    }


    public static void main(String[] args) {



    }

    public void doReplace(String filePath, String targetPostfix) {
        List<DataLine> dataLineData = read(filePath, targetPostfix);
        write(filePath, dataLineData);
    }


    private static void write(String filePath, List<DataLine> dataLines) {

        try {
            FileWriter fileWriter = new FileWriter(filePath);

            try {
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                PrintWriter printWriter = new PrintWriter(bufferedWriter);

                for (DataLine dataLine : dataLines) {
                    String toWrite = null;
                    if (dataLine.property == null) {
                        toWrite = dataLine.content;
                    } else {
                        toWrite = dataLine.property.key + "=" + dataLine.property.value;
                    }

                    printWriter.println(toWrite);
                }
                printWriter.flush();

            } finally {
                fileWriter.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<DataLine> read(String filePath, String targetPostfix) {

        List<DataLine> dataLines = new ArrayList<DataLine>();
        try {

            FileReader fileReader = new FileReader(filePath);

            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String dataLine = null;
                int counter = 1;
                while ((dataLine = bufferedReader.readLine()) != null) {
                    dataLines.add(new DataLine(counter++, dataLine));
                }

                Map<String, String> targetDataUsedToReplace = new HashMap<String, String>();

                for (DataLine lineData : dataLines) {
                    if (lineData.property != null && lineData.property.key.endsWith(targetPostfix)) {
                        String key = lineData.property.key;
                        String targetKeyToBeReplaced = key.substring(0, key.length() - targetPostfix.length());
                        targetDataUsedToReplace.put(targetKeyToBeReplaced, lineData.property.value);

                    }
                }
                logger.info("those key will be replaced");

                for (Map.Entry<String, String> entry : targetDataUsedToReplace.entrySet()) {
                    logger.info("{} = {}", entry.getKey(), entry.getValue());
                }

                Set<String> targetKeySetToBeReplaced = targetDataUsedToReplace.keySet();
                for (DataLine lineData : dataLines) {
                    if (lineData.property != null && targetKeySetToBeReplaced.contains(lineData.property.key)) {
                        String value = targetDataUsedToReplace.get(lineData.property.key);
                        lineData.property.setValue(value);
                    }
                }

                return dataLines;

            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.info("file not found {} ", filePath);
        }
        return dataLines;
    }


    public static ReplaceProperty.Property buildProperty(ReplaceProperty.DataLine DataLine) {

        if (DataLine == null || DataLine.content == null | DataLine.content.length() == 0) {
            return null;
        }

        String content = DataLine.content;

        String[] contentSplitByEqualCharacter = content.split("=");
        if (contentSplitByEqualCharacter.length != 2) {
            return null;
        }

        String key = contentSplitByEqualCharacter[0].trim();
        String value = contentSplitByEqualCharacter[1].trim();

        return new ReplaceProperty.Property(key, value);
    }
}
