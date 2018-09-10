import java.util.Arrays;

public class TwoSum1 {

    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * Example:
     *
     * Given nums = [2, 7, 11, 15], target = 9,
     *
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */

    public static int[] twoSum(int[] nums, int target) {
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums2);
        int l = 0;
        int r = nums2.length-1;
        while (l<r) {
            int sum = nums2[l] + nums2[r];
            if (sum == target) {
                Integer[] result = new Integer[2];
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == nums2[l] && result[0] == null) {
                        result[0] = i;
                        continue;
                    }
                    if (nums[i] == nums2[r] && result[1] == null) {
                        result[1] = i;
                        continue;
                    }
                }
                return new int[]{result[0], result[1]};
            }
            else if (sum > target) r--;
            else if (sum < target) l++;
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        nums = new int[]{3,2,4};
        nums = new int[]{3,3};
        nums = new int[]{-1,-2,-3,-4,-5};
        System.out.println(twoSum(nums, -8)[0]);
    }

}
