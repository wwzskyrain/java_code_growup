package jdk.util.type;


public class ReturnNullPointException {

    public static void main(String[] args) {

        returnOriginLong(2);
        returnOriginLong(3);    //抛出NullPointerException

    }

    public static long returnOriginLong(int inputNumber) {
//      当子函数返回null时，就会抛出空指针异常。
        return returnWrapperLong(inputNumber);
    }

    public static Long returnWrapperLong(int inputNumber) {

        if (inputNumber % 2 == 0) {
            return Long.valueOf(inputNumber);
        } else {
            return null;
        }

    }

}
