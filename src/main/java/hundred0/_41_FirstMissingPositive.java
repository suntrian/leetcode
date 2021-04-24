package hundred0;

import org.junit.Assert;

/**
 * Given an unsorted integer array nums, find the smallest missing positive integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 300
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space?
 */
public class _41_FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {
        boolean[] bucket = new boolean[301];
        for (int num : nums) {
            if (num>0 && num<=300) {
                bucket[num-1] = true;
            }
        }
        for (int i = 0; i < bucket.length; i++) {
            if (!bucket[i]) {
                return i+1;
            }
        }
        return nums.length+1; // equals 301;
    }

    public static int firstMissingPositive2(int[] nums) {
        if (nums == null) {
            return -1;
        }

        // (1) mark
        int marker = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i] = marker;
            }
        }
        // System.out.println(Arrays.toString(nums));

        // (2) check
        for (int i = 0; i < nums.length; i++) {
            // IMPORTANT for in-place
            int value = Math.abs(nums[i]);
            if (value > 0 && value <= nums.length && nums[value - 1] > 0) {
                nums[value - 1] = -nums[value - 1];
            }
        }
        // System.out.println(Arrays.toString(nums));

        // (3) find
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        // System.out.println(Arrays.toString(nums));
        return nums.length + 1;
    }

    public static void main(String[] args) {
        Assert.assertEquals(3, firstMissingPositive(new int[]{1, 2, 0}));
        Assert.assertEquals(2, firstMissingPositive(new int[]{3, 4, -1, 1}));
        Assert.assertEquals(1, firstMissingPositive(new int[]{7,8,9,11,12}));
        Assert.assertEquals(1, firstMissingPositive(new int[]{-1, -2, -3, 0}));
        Assert.assertEquals(10, firstMissingPositive(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        Assert.assertEquals(3, firstMissingPositive(new int[]{1, 2, 4, 5, 6, 7, 8, 9}));
    }

}
