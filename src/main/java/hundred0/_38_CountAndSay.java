package hundred0;

public class _38_CountAndSay {

    /**
     * The count-and-say sequence is the sequence of integers with the first five terms as following:
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 is read off as "one 1" or 11.
     * 11 is read off as "two 1s" or 21.
     * 21 is read off as "one 2, then one 1" or 1211.
     * Given an integer n, generate the nth term of the count-and-say sequence.
     *
     * Note: Each term of the sequence of integers will be represented as a string.
     *
     * Example 1:
     *
     * Input: 1
     * Output: "1"
     * Example 2:
     *
     * Input: 4
     * Output: "1211"
     */

    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }

    public static String countAndSay(int n) {
        if (n==1) return "1";
        String result = "1";
        for (int i = 1; i < n; i++) {
            result = helper(result);
        }
        return result;
    }

    public static String helper(String in) {
        char t = in.charAt(0);
        StringBuilder builder = new StringBuilder();
        if (in.length() == 1) return builder.append(1).append(t).toString();
        int count = 1;
        for (int i = 1; i < in.length(); i++) {
            if (in.charAt(i)==t && i != in.length()-1) {
                count++;
                continue;
            }
            if (i == in.length()-1) {
                if (in.charAt(i)==t){
                    builder.append(count+1).append(t);
                } else {
                    builder.append(count).append(t).append(1).append(in.charAt(i));
                }
                break;
            }
            builder.append(count).append(t);
            t = in.charAt(i);
            count = 1;
        }
        return builder.toString();
    }
}
