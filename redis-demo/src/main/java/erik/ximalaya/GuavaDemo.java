package erik.ximalaya;

import com.google.common.collect.HashMultimap;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author erik.wang
 * @date 2019/04/20
 **/
public class GuavaDemo {

    public static void main(String[] args) {

        HashMultimap<Integer, byte[]> multiMap = HashMultimap.create();

        multiMap.put(1, "one".getBytes());
        multiMap.put(2, "two".getBytes());
        multiMap.put(3, "three".getBytes());
        multiMap.put(1, "one-copy".getBytes());

        for (Integer integer : new HashSet<>(multiMap.keys())) {

            Set<byte[]> valueSet = multiMap.get(integer);
            byte[][] valueArray = valueSet.toArray(new byte[0][]);

            List<String> values = new ArrayList<>();
            for (byte[] bytes : valueArray) {

                try {
                    values.add(new String(bytes, Charset.defaultCharset().name()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(values);

        }

    }

}
