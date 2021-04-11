package hundred0;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
public class _3_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0, len = 0;
        char[] chars = s.toCharArray();
        for (; r < chars.length; r++) {
            char c = chars[r];
            for (int i = l; i < r; i++) {
                if (c == chars[i]) {
                    len = Math.max(r - l, len);
                    l = i+1;
                    break;
                }
            }
        }
        return Math.max(r - l, len);
    }

    public static void main(String[] args) {
        Assert.assertEquals(7, lengthOfLongestSubstring("abcabcbbfwgafea_fwg_23ag3gadfdddaawgg03"));
        Assert.assertEquals(3, lengthOfLongestSubstring("dvdf"));
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbbbb"));
    }

}
