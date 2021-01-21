import SingleElementInASortedArray540.singleNonDuplicate

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.
 *
 * Follow up: Your solution should run in O(log n) time and O(1) space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
object SingleElementInASortedArray540 {

    fun singleNonDuplicate(nums: IntArray): Int {
        var r = 0;
        for (element in nums) {
            r = r xor element
        }
        return r
    }

}

fun main() {
    val array = arrayOf(
        2 to intArrayOf(1,1,2,3,3,4,4,8,8),
        10 to intArrayOf(3,3,7,7,10,11,11),
        5 to intArrayOf(2,2,3,3,4,4,5,6,6,7,7,8,8,9,9),
        1 to intArrayOf(1),
        2 to intArrayOf(1,1,2),
        1 to intArrayOf(1,2,2))
    for (ints in array) {
        val r = singleNonDuplicate(ints.second)
        println( ints.second to r)
        assert(r == ints.first)
    }
}