package jdk.util.collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {

    public static void main(String[] args) {

        List<BigDecimal> bigDecimalList = new ArrayList<>();

        bigDecimalList.add(new BigDecimal("4"));
        bigDecimalList.add(new BigDecimal("1"));
        bigDecimalList.add(new BigDecimal("13"));
        bigDecimalList.add(new BigDecimal("33"));
        bigDecimalList.add(new BigDecimal("22"));
        bigDecimalList.add(new BigDecimal("12"));


        System.out.println(bigDecimalList);

//        Collections.sort(bigDecimalList);

        System.out.println(bigDecimalList);

        Collections.sort(bigDecimalList, new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal o1, BigDecimal o2) {
                if ((o1.intValue() > 10 && o1.intValue() < 20) &&
                        (o2.intValue() > 10 && o2.intValue() < 20)) {
                    return o1.compareTo(o2);
                }
                if ((o1.intValue() > 10 && o1.intValue() < 20)) {
                    return -1;
                }
                if ((o2.intValue() > 10 && o2.intValue() < 20)) {
                    return 1;
                }
                return o1.compareTo(o2);

//                return o1.intValue()-o2.intValue();
            }
        });

        System.out.println(bigDecimalList);
    }

}
