package hundred1;

import java.util.Collections;
import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * Example 2:
 *
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class _140_WorkBreakII {

    public List<String> wordBreak(String s, List<String> wordDict) {
        int[][] trie = new int[10][26];
        for (int i = 0; i < wordDict.size(); i++) {
            String w = wordDict.get(i);
            for (int j = 0; j < w.length(); j++) {
                char c = w.charAt(j);
                trie[j][c-'a'] = i+1;
            }
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j =0; j <= 10; j++) {
                for (int t : trie[j]) {
                    if (t > 0) {
                        
                    }
                }
            }
        }
        return Collections.emptyList();
    }

}
