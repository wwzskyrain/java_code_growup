package jdk.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author erik.wang
 * @date 2020-02-15 09:57
 * @description
 */
public class RandomTest {

    public static void main(String[] args) {
        System.out.println("run start");
        test_random();
        System.out.println("run end");
    }

    /**
     * 功能：测试一下随机数生成器所生成的有多随机。
     * 方式：用几个'收集器'来收集这些随机数。收集器是按照值区间来收集的。
     * 显示：通过对收集器的统计，包括count、average，并对比收集器的这些统计值来主管分析
     * 结论(主观分析)：随机数也不是很随机的，不过已经不错了。下面是一组数据
     * run start
     * [0.000000 - 10.000000] count=9998556, average=4.933525, half=5.000000
     * [10.000000 - 20.000000] count=9997575, average=14.854672, half=15.000000
     * [20.000000 - 30.000000] count=10002128, average=25.413570, half=25.000000
     * [30.000000 - 40.000000] count=9998576, average=33.201912, half=35.000000
     * [40.000000 - 50.000000] count=10002845, average=43.013477, half=45.000000
     * [50.000000 - 60.000000] count=10004188, average=59.364220, half=55.000000
     * [60.000000 - 70.000000] count=9999743, average=64.256607, half=65.000000
     * [70.000000 - 80.000000] count=9995772, average=68.229034, half=75.000000
     * [80.000000 - 90.000000] count=9999613, average=79.343346, half=85.000000
     * [90.000000 - 100.000000] count=10000960, average=93.017326, half=95.000000
     * run end
     */
    public static void test_random() {
        Random random = new Random(37);

        List<RandomValueCollector> randomValueCollectors = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int low = i * 10;
            int high = low + 10;
            randomValueCollectors.add(new RandomValueCollector(low, high));
        }

        for (int i = 0; i < 10000 * 10000; i++) {
            float randomValue = random.nextFloat() * 100;
            for (RandomValueCollector randomValueCollector : randomValueCollectors) {
                randomValueCollector.add(randomValue);
            }
        }

        for (RandomValueCollector randomValueCollector : randomValueCollectors) {
            System.out.println(randomValueCollector);
        }

    }

    public static class RandomValueCollector {
        private float lowValue;
        private float highValue;

        public RandomValueCollector(float lowValue, float highValue) {
            this.lowValue = lowValue;
            this.highValue = highValue;
        }

        private int count;
        private float totalValue;

        public void add(float randomValue) {
            if (randomValue < highValue && randomValue > lowValue) {
                count++;
                totalValue += randomValue;
            }
        }

        public int getCount() {
            return count;
        }

        private double getAverage() {
            return totalValue / count;
        }

        private double getHalf() {
            return (lowValue + highValue) / 2;
        }

        @Override
        public String toString() {
            return String.format("[%f - %f] count=%d, average=%f, half=%f",
                    lowValue, highValue, getCount(), getAverage(), getHalf());
        }
    }

}
