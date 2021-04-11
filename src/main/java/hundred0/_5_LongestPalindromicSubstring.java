package hundred0;

import org.junit.Assert;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 * Example 3:
 *
 * Input: s = "a"
 * Output: "a"
 * Example 4:
 *
 * Input: s = "ac"
 * Output: "a"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters (lower-case and/or upper-case),
 */
public class _5_LongestPalindromicSubstring {

    //穷举
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        if (chars.length==1) return s;
        int ml = 0, mr = 0;
        for (int r =0; r < chars.length; r++) {
            for (int i = 0; i < r; i++) {
                //仅当相同字符时，检查是否回环
                boolean isPalindromic = true;
                for (int j = 0; j <= (r-i)/2; j++) {
                    if (chars[i+j] != chars[r-j]) {
                        isPalindromic = false;
                        break;
                    }
                }
                if (isPalindromic) {
                    if (r-i > mr-ml) {
                        ml = i;
                        mr = r;
                    }
                    break;
                }
            }
        }
        return s.substring(ml, mr+1);
    }

    //动态规划
    public static String longestPalindrome2(String s) {
        char[] chars = s.toCharArray();
        if (chars.length==1) return s;
        boolean[][] dp = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
            if (i+1 < chars.length && chars[i] == chars[i+1]) dp[i][i+1] = true;
            for (int j = 0; j <= Math.min(i, chars.length-i); j++) {
                if (i+j+1 >= chars.length) break;
                boolean isPalindrome = false;
                if (i-j-1>=0 && chars[i - j - 1] == chars[i + j + 1] && dp[i - j][i + j]){
                    dp[i-j-1][i+j+1] = true;
                    isPalindrome = true;
                }
                if (chars[i-j] == chars[i+j+1] && ( i-j+1>i || dp[i-j+1][i+j])) {
                    dp[i-j][i+j+1] = true;
                    isPalindrome = true;
                }
                if (!isPalindrome) break;
            }
        }
        int maxLen = 0, minL = 0, minR = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j][i] && i - j > maxLen) {
                    maxLen = i-j;
                    minL = j;
                    minR = i;
                    break;
                }
            }
        }
        return s.substring(minL, minR+1);
    }

    public static void main(String[] args) {
        Assert.assertEquals("dddddddd", longestPalindrome2("dddddddd"));
        Assert.assertEquals("aaaaaaa", longestPalindrome2("aaaaaaa"));
        Assert.assertEquals("aca", longestPalindrome2("aacabdkacaa"));
        Assert.assertEquals("bab", longestPalindrome2("babad"));
        Assert.assertEquals("bb", longestPalindrome2("cbbd"));
        Assert.assertEquals("cbbccbbc", longestPalindrome2("cbbccbbc"));
        Assert.assertEquals("cbbcbbc", longestPalindrome2("cbbcbbc"));
    }

}
