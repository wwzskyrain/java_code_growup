package erik.ximalaya.list;

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
//  关键是找到target所在的那一办，而且这个查住不能大于O(logn)
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
        int total = nums1.length + nums2.length;

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

            if (counter == total / 2) {
                sum += min;
            }
            if (counter == total / 2 + 1) {

                if(total%2==1){
                    return min;
                }
                sum += min;
                break;
            }
        }

        return sum / 2;

    }

    public static void main(String[] args) {
        int[] num1 = {1, 3};
        int[] num2 = {2};
        Solutions solutions = new Solutions();
        double result = solutions.findMedianSortedArrays(num1, num2);

        System.out.println(result);

    }


}
