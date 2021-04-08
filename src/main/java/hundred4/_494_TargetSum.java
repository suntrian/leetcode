package hundred4;

import util.Printer;

import java.util.Arrays;
import java.util.Stack;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -.
 * For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class _494_TargetSum {

    static Integer[] cout ;

    public static int findTargetSumWays(int[] nums, int S) {
        Arrays.sort(nums);
        cout = new Integer[nums.length];
        return findTargetSumWaysHelper(nums, S);
    }

    public static int findTargetSumWaysHelper(int[] nums, int S) {
        if (S > nums[nums.length-1]*nums.length) return 0;
        if (nums.length == 1){
            int c = 0;
            if (nums[0] == S) {
                cout[0] = nums[0];
                Printer.print(cout);
                c++;
            }
            if (nums[0] == -S){
                cout[0] = -nums[0];
                c++;
            }
            return c;
        }
        int[] newNums = Arrays.copyOf(nums, nums.length-1);
        cout[newNums.length] = -nums[newNums.length];
        int m = findTargetSumWaysHelper(newNums, S+nums[newNums.length]);
        cout[newNums.length] = nums[newNums.length];
        int n = findTargetSumWaysHelper(newNums, S-nums[newNums.length]);
        return m+n;
    }


    public static int findTargetSumWays2(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }

    public static void main(String[] args) {
        int[] nums = {1,0};
        int c = findTargetSumWays(nums, 1);
        System.out.println(c);
    }
}
