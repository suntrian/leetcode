package hundred0;

import util.Stack;

import java.util.*;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */

public class _42_TrappingRainWater {

    public static int trap(int[] height) {
        if (height.length<=2) {
            return 0;
        }
        int[][] heightIndexed = new int[height.length][2];
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            heightIndexed[i] = new int[]{h, i};
        }
        Arrays.sort(heightIndexed, Comparator.comparing(x->x[0]));
        int[] max = heightIndexed[height.length-1];
        int[] secd = heightIndexed[height.length-2];
        int rangeX = Math.min(max[1], secd[1]);
        int rangeY = Math.max(max[1], secd[1]);
        List<Integer[]> regions = new ArrayList<>();
        regions.add(new Integer[]{rangeX, rangeY});
        for (int i = height.length-3; i>=0; i--) {
            int[] cur = heightIndexed[i];
            if (cur[1]>rangeY) {
                regions.add(new Integer[]{rangeY, cur[1]});
                rangeY = cur[1];
            } else if (cur[1]<rangeX) {
                regions.add(new Integer[]{cur[1], rangeX});
                rangeX = cur[1];
            }
        }
        int trap = 0;
        for (Integer[] region : regions) {
            int l = height[region[0]], r = height[region[1]];
            int h = Math.min(l, r);
            for (int i = region[0]+1; i< region[1]; i++) {
                trap += h - height[i];
            }

        }
        return trap;

    }


    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = trap(nums);
        System.out.println(trap);
    }
}
