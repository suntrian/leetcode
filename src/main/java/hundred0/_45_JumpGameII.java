package hundred0;

import org.junit.Assert;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 105
 */
public class _45_JumpGameII {

    public static int jump1(int[] nums) {
        //minimum jumps from i to j;
        boolean[][] dp = new boolean[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (i + nums[i]>=j) {
                    dp[i][j] = true;
                } else break;
            }
        }
        return dp(dp, nums.length-1);
    }

    private static int dp(boolean[][] dp, int target) {
        if (target==0) return 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < target; i++) {
            if (dp[i][target]) {
                min = Math.min(min, dp(dp, i)+1);
            }
        }
        return min;
    }

    public static int jump2(int[] nums) {
        return jumpHelp(nums, nums.length-1);
    }

    private static int jumpHelp(int[] nums, int target) {
        if (target == 0) return 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < target; i++) {
            if (i+nums[i]>=target) {
                min = Math.min(min, jumpHelp(nums, i));
            }
        }
        return min+1;
    }

    public static int jump3(int[] nums) {
        if (nums.length==1) return 0;
        int jump = 0, maxReach = 0, cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i+nums[i]>=nums.length-1) {
                return jump+1;
            }

            if (i+nums[i]>maxReach) {
                maxReach = i+nums[i];
            }

            if (i==cur) {
                jump++;
                cur = maxReach;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(jump1(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}));
        long one = System.nanoTime();
        System.out.printf("COST:%d\n", one-start);
        System.out.println(jump2(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}));
        long two = System.nanoTime();
        System.out.printf("COST:%d\n", two-one);
        System.out.println(jump3(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}));
        long three = System.nanoTime();
        System.out.printf("COST:%d\n", three-two);
        //Assert.assertEquals(5, jump2(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}));
        Assert.assertEquals(2, jump2(new int[]{2, 3, 1, 1, 4}));
        Assert.assertEquals(2, jump2(new int[]{2, 3, 0, 1, 4}));
    }

}
