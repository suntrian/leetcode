import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
 *
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

public class ProductOfArrayExceptSelf238 {

    /**
     * O(n^2)
     * @param nums
     * @return
     */
    public static int[] productExceptSelf1(int[] nums) {
        int [] result = new int[nums.length];
        Arrays.fill(result, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                result[j] *= i==j?1:nums[i];
            }
        }
        return result;
    }

    /**
     * use division
     * @param nums
     * @return
     */
    public static int[] productExceptSelf2(int[] nums) {
        int[] result = new int[nums.length];
        int product = Arrays.stream(nums).reduce(1, (x,y)->x*y);
        for (int i = 0; i < nums.length; i++) {
            result[i] = product/nums[i];
        }
        return result;
    }

    /**
     * no divide and O(n)
     * @param nums
     * @return
     */
    public static int[] productExceptSelf3(int[] nums) {
        int[] leftProd = new int[nums.length];
        int x = 1;
        for (int i =0; i < nums.length; i++) {
            x *= nums[i];
            leftProd[i] = x;
        }
        int[] rightProd = new int[nums.length];
        int y = 1;
        for (int i = nums.length-1; i>=0; i--) {
            y *= nums[i];
            rightProd[i] = y;
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] =(i<=0?1:leftProd[i-1]) * (i >= nums.length-1?1:rightProd[i+1]);
        }
        return result;

    }

    public static void main(String[] args) {
       int[] array =  productExceptSelf3(new int[]{1,2,3,4});
        System.out.println(Arrays.equals(array, new int[]{24, 12, 8, 6}));
    }
}
