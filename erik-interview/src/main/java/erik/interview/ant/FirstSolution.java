package erik.interview.ant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date 2019-08-12
 * @Created by erik
 */
public class FirstSolution {

    public static void main(String[] args) {

        String filePath = "/Users/nali/erik/grep_demo_unsort";
        String grepPattern = "Exception";
        int n = 10;
        findMostCommonLineWithCount(filePath, grepPattern, n);
    }

    public static void findMostCommonLineWithCount(String filePath, String grepPattern, int n) {

        if (filePath == null || filePath.length() < 1 || grepPattern == null || grepPattern.length() < 1 || n < 1) {
            log("invalid parameter with filePath:%s grepPattern:%s n:%d\n", filePath, grepPattern, n);
            return;
        }

        List<String> allDataLines = getAllDataLines(filePath);
        allDataLines = allDataLines.stream().filter(data -> data.contains(grepPattern)).collect(Collectors.toList());
        if (allDataLines.size() == 0) {
            return;
        }

        HashMap<String, Integer> lineContent2CountMap = new HashMap<>();
        for (String dataLine : allDataLines) {
            Integer count = lineContent2CountMap.get(dataLine);
            if (count == null) {
                lineContent2CountMap.put(dataLine, 1);
            } else {
                lineContent2CountMap.put(dataLine, ++count);
            }
        }

        List<Map.Entry<String, Integer>> line2CountMapEntries = new ArrayList<>();

        for (Map.Entry<String, Integer> stringIntegerEntry : lineContent2CountMap.entrySet()) {
            line2CountMapEntries.add(stringIntegerEntry);
        }

        line2CountMapEntries.sort((entry1, entry2) -> {
            int result = entry1.getValue().compareTo(entry2.getValue());
            if (result != 0) {
                return -1 * result;
            }
            return -1 * entry1.getKey().compareTo(entry2.getKey());
        });

        Integer maxCount = line2CountMapEntries.get(0).getValue();
        int maxCountLength = maxCount.toString().length();
        String pattern = "%" + maxCountLength + "d %s\n";
        for (Map.Entry<String, Integer> stringIntegerEntry : line2CountMapEntries) {
            if (n > 0) {
                // 输出最终结果
                log(pattern, stringIntegerEntry.getValue(), stringIntegerEntry.getKey());
                n--;
            } else {
                break;
            }
        }
    }

    private static List<String> getAllDataLines(String filePath) {

        List<String> dataLines = new LinkedList<>();

        try (FileReader fileReader = new FileReader(filePath);) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            if (!bufferedReader.ready()) {
                log("file %s is not ready to read.\n");
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                dataLines.add(line);
            }
        } catch (IOException ioException) {
            log("file %s open error.\n", filePath);
        }
        return dataLines;
    }

    private static void log(String pattern, Object... parameter) {
        System.out.printf(pattern, parameter);
    }

}
