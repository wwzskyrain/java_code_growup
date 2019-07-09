package jdk.util.string;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LongToHexStrig {

    public static void main(String[] args) {

        List<String> hexStrings = Arrays.asList(
                Long.toHexString(0),
                Long.toHexString(1),
                Long.toHexString(2),
                Long.toHexString(8),
                Long.toHexString(16),
                Long.toHexString(17),
                Long.toHexString(31),
                Long.toHexString(32),
                Long.toHexString(33),
                Long.toHexString(64),
                Long.toHexString(127)

        );
//  [0, 1, 2, 8, 10, 11, 1f, 20, 21, 40, 7f]
        System.out.println(hexStrings);
    }


    @Test
    public void test_string_buffer(){
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < 9; i++) {
            stringBuffer.append(i);
        }

        System.out.println(stringBuffer);

    }

}