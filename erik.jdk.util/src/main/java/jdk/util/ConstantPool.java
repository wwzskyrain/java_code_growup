package jdk.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author erik.wang
 * @date 2019/05/07
 **/
public class ConstantPool {

    public static void main(String[] args) {


    }

    @Test
    public void test_swap() {
        try {
            Integer a = 1;
            Integer b = 2;
            swap(a, b);
            System.out.printf("a=%d,b=%d\n", a, b);
            Integer c = 1;
            System.out.printf("c=%d", c);  //常量池中原来1的位子中的value已经被改变成2了。

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 这是有问题的 swap，这种思路就有问题的
     * @param num1
     * @param num2
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void swap(Integer num1, Integer num2) throws NoSuchFieldException, IllegalAccessException {

        Integer temp = num1;
        Field valueField = Integer.class.getDeclaredField("value");
        // 这里要用getDeclaredField而不能用getField，后者不能找到非public的字段的。

        valueField.setAccessible(true);
        valueField.set(num1, num2);
        valueField.set(num2, temp);
        System.out.printf("a=%d,b=%d\n", num1, num2);
        //利用反射，改变了整数常量池中的数值的。
    }


    /**
     * ? 如何才能实现swap呢? 也是不行的；赋值操作时有去无回的。
     * @param a
     * @param b
     */
    public void correct_swap(Integer a, Integer b) {

        System.out.printf("a=%d,b=%d\n", a, b);

        a=a^b;
        b=b^a;
        a=a^b;

        System.out.printf("a=%d,b=%d\n", a, b);

    }

    @Test
    public void test_correct_swap(){
        Integer a = 1;
        Integer b = 2;
        correct_swap(a, b);
        System.out.printf("a=%d,b=%d\n", a, b);
        Integer c = 1;
        System.out.printf("c=%d", c);  //常量池中原来1的位子中的value已经被改变成2了。
    }


    @Test
    public void changeIntegerConstantPool() throws NoSuchFieldException, IllegalAccessException {

        Field valueField = Integer.class.getDeclaredField("value");
        // 这里要用getDeclaredField而不能用getField，后者不能找到非public的字段的。

        valueField.setAccessible(true);
        for (int i = -128; i < 127; i++) {
            valueField.set(i, 0);
        }

        //利用反射，改变了整数常量池中的数值的。
        //再次遍历输出时，发现都被修改了。

        for (int i = -128; i < 130; i++) {
            System.out.printf("%d=%d\n", i, i);
        }

    }
}
