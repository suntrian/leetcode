public class ValidPalindrome125 {

    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * Note: For the purpose of this problem, we define empty string as valid palindrome.
     *
     * Example 1:
     *
     * Input: "A man, a plan, a canal: Panama"
     * Output: true
     * Example 2:
     *
     * Input: "race a car"
     * Output: false
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0;
        int j = s.length()-1;
        while (i<j) {
            char l = s.charAt(i);
            char r = s.charAt(j);
            if (l<48 || l>122 || (l>57 && l<97)){
                i++;
                continue;
            }
            if (r<48 || r>122 || (r>47 && r<97)){
                j--;
                continue;
            }
            if (l != r) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        //isPalindrome(s);
        String s2 = "0P";
        System.out.println(isPalindrome(s2));
    }
}
