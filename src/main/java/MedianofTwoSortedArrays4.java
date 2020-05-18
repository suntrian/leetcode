public class MedianofTwoSortedArrays4 {

    /**
     *
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     *
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     *
     * You may assume nums1 and nums2 cannot be both empty.
     *
     *
     *
     * Example 1:
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * The median is 2.0
     * Example 2:
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * The median is (2 + 3)/2 = 2.5
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length==0){
            return (nums2[(nums2.length-1)/2] + nums2[(nums2.length)/2])/2D;
        }
        if (nums2.length == 0) {
            return (nums1[(nums1.length-1)/2] + nums1[(nums1.length)/2])/2D;
        }
        int count = 0;
        int left = 0, right = 0;
        int x = 0, y = 0, m = nums1.length-1, n = nums2.length-1;
        while (count++ < (nums1.length + nums2.length+1) /2 ) {
            if ( x >= nums1.length) {
                left = nums2[y++];
            } else if ( y >= nums2.length) {
                left = nums1[x++];
            } else if ( nums1[x]>=nums2[y] ) {
                left = nums2[y++];
            } else {
                left = nums1[x++];
            }

            if (n < 0) {
                right = nums1[m--];
            } else if (m < 0) {
                right = nums2[n--];
            } else if (nums1[m] >= nums2[n]) {
                right = nums1[m--];
            } else {
                right = nums2[n--];
            }
        }

        return (left + right)/2D;
    }

    public static void main(String[] args) {
//        double median = findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
//        System.out.println(median);
//        assert median == 2.5;


//        double median2 = findMedianSortedArrays(new int[]{1, 4}, new int[]{2});
//        System.out.println(median2);
//        assert median2 == 2;

        double median3 = findMedianSortedArrays( new int[]{3}, new int[]{-2, -1});
        System.out.println(median3);
        assert median3 == -1;
    }
}
