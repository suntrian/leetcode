package hundred0;

import org.junit.Assert;

/**
 * 7. Reverse Integer
 * Easy
 *
 * 4554
 *
 * 6979
 *
 * Add to List
 *
 * Share
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 *
 *
 * Example 1:
 *
 * Input: x = 123
 * Output: 321
 * Example 2:
 *
 * Input: x = -123
 * Output: -321
 * Example 3:
 *
 * Input: x = 120
 * Output: 21
 * Example 4:
 *
 * Input: x = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * -231 <= x <= 231 - 1
 */
public class _7_ReverseInteger {

    public int reverse(int x) {
        char[] chars = Integer.toString(x).toCharArray();
        boolean neg = '-' == chars[0];
        long r = 0;
        for (int i = chars.length-1; i >= (neg?1:0); i--) {
            r=r*10+chars[i]-'0';
        }
        r = neg?-r:r;
        return r>Integer.MAX_VALUE?0:r<Integer.MIN_VALUE?0:(int)r;
    }

    public static void main(String[] args) {
        _7_ReverseInteger r = new _7_ReverseInteger();
        Assert.assertEquals(21, r.reverse(120));
        Assert.assertEquals(-321, r.reverse(-123));
        Assert.assertEquals( 321, r.reverse(123));
        Assert.assertEquals(0, r.reverse(Integer.MAX_VALUE));
        Assert.assertEquals(0, r.reverse(Integer.MIN_VALUE));

    }

}
