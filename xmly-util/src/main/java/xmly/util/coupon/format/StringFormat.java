package xmly.util.coupon.format;


import java.util.Map;

public class StringFormat {

    /**
     * @param pattern     ex: albumId:{albumId},albumName:{albumName},version.
     * @param keyValueMap <key,value>: key will be replace in pattern with value
     * @return the replaced pattern,don't affective pattern.
     */
    public static String format(String pattern, Map<String, String> keyValueMap) {

        if (pattern == null || keyValueMap == null || keyValueMap.size() == 0) {
            return pattern;
        }

        String result = pattern;

        for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
            result = result.replace(String.format("{%s}", entry.getKey()), entry.getValue());
        }

        return result;
    }
}
