package erik.ximalaya.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Solutions {

    //    26. Remove Duplicates from Sorted Array
    public int removeDuplicatesI(int[] nums) {

        if (nums.length < 2) {
            return 1;
        }
        int index = 1;  //index是待添加的位置；相当于一个新的数组的，其实。
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[index - 1]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    //    80. Remove Duplicates from Sorted Array II
    public int removeDuplicatesII(int[] nums) {
//      通过，但是不能扩展，比如最多出现3次，4次，5次

        if (nums.length < 3) {
            return nums.length;
        }
        int index = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] == nums[index - 1] && nums[i] == nums[index - 2]) {
//              这个判断条件是可以手动扩展的，当然会越写越长。
//              然而结合原数组是排好序的，所以，如果num[i]==num[index-occur]，
//              那么num[i]就等于从index-occur到index的所有值；所以只需要判断
//              num[i]==num[index-occur]即可，从而简化代码质量，并且赋予了该算法
//              以扩展性：occur表示最大出现次数。编码实现见removeDuplicatesII2
                continue;
            } else {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }


    //    80. Remove Duplicates from Sorted Array II
    public int removeDuplicatesII2(int[] nums) {
//      通过，可扩展版：最多出现occur>1次

        int occur = 2;

        if (nums.length <= occur) {
            return nums.length;
        }
        int index = occur;
        for (int i = occur; i < nums.length; i++) {
            if (nums[i] == nums[index - occur]) {
                continue;
            } else {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    //    33. Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
//  关键是找到target所在的那一半，而且这个查住不能大于O(logn)
        if (nums.length == 0) {
            return -1;
        }
        int maxElementIndex = 0;

        while (maxElementIndex < nums.length - 1) {
            if (nums[maxElementIndex] > nums[maxElementIndex + 1]) {
                break;
            }
            maxElementIndex++;
        }

        int low = 0, high = nums.length - 1;

        if (target < nums[0]) {
            low = maxElementIndex + 1;
        } else {
            high = maxElementIndex;
        }

        int middle = (low + high) / 2;
        while (low <= high) {
            middle = (low + high) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }

        }

        return -1;

    }

    //    81. Search in Rotated Sorted Array II
    public boolean searchII(int[] nums, int target) {
//  哈哈，I的解法也完全可使用于II，通过
        if (nums.length == 0) {
            return false;
        }
        int maxElementIndex = 0;

        while (maxElementIndex < nums.length - 1) {
            if (nums[maxElementIndex] > nums[maxElementIndex + 1]) {
                break;
            }
            maxElementIndex++;
        }

        int low = 0, high = nums.length - 1;

        if (target < nums[0]) {
            low = maxElementIndex + 1;
        } else {
            high = maxElementIndex;
        }

        int middle = (low + high) / 2;
        while (low <= high) {
            middle = (low + high) / 2;
            if (nums[middle] == target) {
                return true;
            } else if (nums[middle] > target) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }

        }

        return false;

    }

//    4. Median of Two Sorted Arrays

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//       基础解法
        int totalLength = nums1.length + nums2.length;

        int counter = 0;
        double sum = 0;
        for (int i = 0, j = 0; i < nums1.length || j < nums2.length; ) {
            int min = 0;
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    min = nums1[i];
                    i++;
                } else {
                    min = nums2[j];
                    j++;
                }
            } else if (i < nums1.length) {
                min = nums1[i];
                i++;
            } else {
                min = nums2[j];
                j++;
            }
            counter++;

//          这一段代码是找出第counter个最小值

            if (counter == totalLength / 2) {
                sum += min;
            }
            if (counter == totalLength / 2 + 1) {

                if (totalLength % 2 == 1) {
                    return min;
                }
                sum += min;
                break;
            }
        }

        return sum / 2;

    }

    /**
     * 找出最长的连续(整数值的连续，非元素位置连续)子序列；
     * <br/>    1   不能在ArrayList的遍历中调用它的remove方法，但是可以在它的iterator的遍历中调用iterator的remove方法，但是每个next只能调用一次；
     * <br/>    2   若想不按照iterator的next顺序来"删除"元素，可以用List的数据镜像——数组or其他形式，来遍历它并能删除元素。
     * <br/>    3   这个题目虽然用了双层循环，但是内循环会删除元素，所以整体来讲还是O(n)的。
     * <br/>    4   还有，不要以为for比while慢，for的代码形式上更简洁呢
     * <br/>    5   不要以为三木运算符比Math.max快，其实只是少了层函数调用，还不如后者代码简洁
     * <br/>    6   leetcode的百分比不一定准的。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        Set<Integer> hs = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            hs.add(nums[i]);
        }
        int max = 0;

        for (int index = 0; index < nums.length; index++) {

            if (hs.contains(nums[index])) {
                int count = 1;
                hs.remove(nums[index]);

                int low = nums[index] - 1;
                while (hs.contains(low)) {
                    count++;
                    hs.remove(low);
                    low--;
                }
                int high = nums[index] + 1;
                while (hs.contains(high)) {
                    count++;
                    hs.remove(high);
                    high++;
                }
                max = Math.max(count, max);
            }

        }
        return max;

    }

    @Test
    public void test_longestConsecutive() {

        int[] nums = new int[]{100, 4, 200, 1, 3, 2};

        int result = longestConsecutive(nums);

        int expected = 4;
        Assertions.assertEquals(expected, result);

    }

    @Test
    public void test_twice_remove_iterator() {

        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

        Iterator<Integer> iterator = set.iterator();

        Integer first = iterator.next();
        System.out.println(set);
        iterator.remove();
        System.out.println(set);
        iterator.remove();
        System.out.println(set);

    }

//    Two Sum

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if(map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]),i};
            }else {
                //为呼应后来着。
                map.put(target-nums[i],i);
            }
        }
        return null;    //因为题设一定会有且只有一个解，所以这里直接返回null；

    }

    @Test
    public void test_two_sum() {
        int[] nums = {3,2,4};
        int target = 6;
        int[] except = {1, 2};
        Assertions.assertArrayEquals(except, twoSum(nums, target));
    }


}