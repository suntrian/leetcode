import java.util.Arrays;

public class ValidTriangleNumber611 {

    /**
     Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
     Example 1:
     Input: [2,2,3,4]
     Output: 3
     Explanation:
     Valid combinations are:
     2,3,4 (using the first 2)
     2,3,4 (using the second 2)
     2,2,3
     Note:
        The length of the given array won't exceed 1000.
        The integers in the given array are in the range of [0, 1000].
     *
     * @param nums
     * @return
     */
    public static int triangleNumber(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length-2; i++) {
            for (int j = i+1; j < nums.length-1; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if ((nums[i] + nums[j]) > nums[k] && (nums[i] + nums[k]) > nums[j] && (nums[j] + nums[k]) > nums[i]) {
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    public static int triangleNumber2(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0 ; i < nums.length-2; i++) {
            for (int j = i + 1; j < nums.length-1; j++) {
                for (int k = j+1; k < nums.length && nums[i]+nums[j]>nums[k];k++){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {24,3,82,22,35,84,19};
        //triangleNumber(nums);
        int[] nums2 = {2,2,3,4};
        triangleNumber2(nums2);
    }
}
