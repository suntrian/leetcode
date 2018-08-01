import java.util.Arrays;

public class ScrambleString87 {

    /**
     * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings
     * recursively.
     *
     * Below is one possible representation of s1 = "great":
     *
     *     great
     *    /    \
     *   gr    eat
     *  / \    /  \
     * g   r  e   at
     *            / \
     *           a   t
     * To scramble the string, we may choose any non-leaf node and swap its two children.
     *
     * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
     *
     *     rgeat
     *    /    \
     *   rg    eat
     *  / \    /  \
     * r   g  e   at
     *            / \
     *           a   t
     * We say that "rgeat" is a scrambled string of "great".
     *
     * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
     *
     *     rgtae
     *    /    \
     *   rg    tae
     *  / \    /  \
     * r   g  ta  e
     *        / \
     *       t   a
     * We say that "rgtae" is a scrambled string of "great".
     *
     * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
     *
     * Example 1:
     *
     * Input: s1 = "great", s2 = "rgeat"
     * Output: true
     * Example 2:
     *
     * Input: s1 = "abcde", s2 = "caebd"
     * Output: false
     */

    public static boolean isScramble(String s1, String s2) {
        if (s1==null||s2==null||s1.length()!=s2.length()) return false;
        if (s1.equals(s2)) return true;
        char[] c1 = s1.toCharArray();
        Arrays.sort(c1);
        char[] c2= s2.toCharArray();
        Arrays.sort(c2);
        if (!new String(c1).equals(new String(c2))) return false;
        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i))){
                return true;
            }
            if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i)) && isScramble(s1.substring(i), s2.substring(0, s2.length()-i))){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgtae";
        //System.out.println(isScramble(s1, s2));
        String S1 = "abc";
        String S2 = "bca";
        //System.out.println(isScramble(S1, S2));
        s1 = "abcdefghijklmnopq";
        s2 = "efghijklmnopqcadb";
        System.out.println(isScramble(s1, s2));
    }
}
