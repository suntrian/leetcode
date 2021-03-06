package hundred0;

public class _9_PalindromeNumber {

    /**
     * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as
     * forward.
     *
     * Example 1:
     *
     * Input: 121
     * Output: true
     * Example 2:
     *
     * Input: -121
     * Output: false
     * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a
     * palindrome.
     * Example 3:
     *
     * Input: 10
     * Output: false
     * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
     * Follow up:
     *
     * Coud you solve it without converting the integer to a string?
     */

    public static void main(String[] args) {
        System.out.println(isPalindrome(13131));
        System.out.println(isPalindrome2(1331331));
    }

    public static boolean isPalindrome(int x) {
        if (x<0) return false;
        String str = String.valueOf(x);
        for (int i = 0; i < str.length()/2; i++) {
            if (str.charAt(i) != str.charAt(str.length()-i-1)) return false;
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        if (x<0 || (x%10==0 && x>0)) return false;
        int y = x;
        int rev = 0;
        do {
            rev = rev*10+y%10;
        }
        while ((y=y/10) != 0);
        if (rev == x) return true;
        return false;
    }
}
