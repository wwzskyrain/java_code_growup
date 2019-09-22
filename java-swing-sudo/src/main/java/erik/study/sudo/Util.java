package erik.study.sudo;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Date 2019-09-18
 * @Created by erik
 */
public class Util {

    public static SudoData getSudoData() {
        String elementString = "5,1,2,3,8,4,6,7,9," +
                "4,9,7,2,6,5,8,1,3," +
                "3,8,6,9,1,7,2,5,4," +
                "2,7,8,6,9,3,1,4,5," +
                "6,4,3,8,5,1,7,9,2," +
                "1,5,9,4,7,2,3,8,6," +
                "7,6,1,5,2,9,4,3,8," +
                "9,2,4,7,3,8,5,6,1," +
                "8,3,5,1,4,6,9,2,7";
        List<Element> elements = new ArrayList<>();
        String[] elementStrArray = elementString.split(",");
        for (int i = 0; i < elementStrArray.length; i++) {
            Set<Integer> data = new HashSet<>(Arrays.asList(Integer.valueOf(elementStrArray[i].trim())));
//            elements.add(new Element(data));
        }
        return new SudoData(elements.toArray(new Element[0]));
    }

    public static void main(String[] args) {
        getSudoData();
    }
}
