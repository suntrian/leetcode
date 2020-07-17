/**
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * Example 1:
 *
 * Input: "42"
 * Output: 42
 * Example 2:
 *
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 *              Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 *
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 *
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 *              digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 *
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 *              Thefore INT_MIN (−231) is returned.
 */

public class StringToInteger_atoi8 {

    public static int myAtoi(String str) {
        final int INIT = -1, SIGN = 1, NUM = 2;
        long ret = 0, status = -1;
        boolean neg = false;
        for (char c : str.toCharArray()) {
            if (' ' == c || '\t' == c) {
                if (INIT != status) {
                    break;
                }
            } else if ('+' == c || '-' == c) {
                if (INIT != status) {
                    break;
                }
                neg = '-'==c;
                status = SIGN;
            } else if (c >= '0' && c <= '9') {
                ret = ret*10 + (c - '0');
                if (ret > Integer.MAX_VALUE) {
                    break;
                }
                status = NUM;
            } else {
                break;
            }
        }
        if (neg) {
            return (int)Math.max(Integer.MIN_VALUE, -ret);
        } else {
            return (int)Math.min(Integer.MAX_VALUE, ret);
        }
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi(String.valueOf(Integer.MAX_VALUE)+"+215353"));
        System.out.println(myAtoi(Integer.MIN_VALUE+"-123536"));
    }
}
