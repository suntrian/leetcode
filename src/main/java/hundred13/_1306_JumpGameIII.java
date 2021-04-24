package hundred13;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 *
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 5 * 104
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 */
public class _1306_JumpGameIII {

    public static boolean canReach(int[] arr, int start) {
        return dfs(arr, start, new boolean[arr.length]);
    }

    private static boolean dfs(int[] arr, int start, boolean[] visited) {
        if (start < 0 || start >= arr.length) return false;
        if (visited[start]) return false;
        if (arr[start] == 0) return true;
        visited[start] = true;
        return dfs(arr, start + arr[start], visited) || dfs(arr, start - arr[start], visited);
    }

    public static boolean canReach2(int[] arr, int start) {
        if (start > arr.length - 1 || start < 0)
            return false;
        else if (arr[start] == -1)
            return false;
        else if (arr[start] == 0)
            return true;
        int temp = arr[start];
        arr[start] = -1;
        return canReach2(arr, start + temp) || canReach2(arr, start - temp);
    }

    public static void main(String[] args) {

        Assert.assertTrue(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));
        Assert.assertTrue(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 0));
        Assert.assertFalse(canReach(new int[]{3,0,2,1,2}, 2));

    }

}
