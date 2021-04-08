package hundred7;

import java.util.HashMap;

public class _788_RotatedDigits {

    /**
     * X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is
     * different from X.  Each digit must be rotated - we cannot choose to leave it alone.
     *
     * A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5
     * rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other
     * number and become invalid.
     *
     * Now given a positive number N, how many numbers X from 1 to N are good?
     *
     * Example:
     * Input: 10
     * Output: 4
     * Explanation:
     * There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
     * Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
     * Note:
     *
     * N  will be in range [1, 10000].
     */
    public static int rotatedDigits(int N) {
        int result = 0;
        for (Integer i = 1; i <= N; i++) {
            String c = i.toString();
            boolean breaked = false;
            char[] newNum = new char[c.length()];
            CHAR: for (int j = 0; j < c.length(); j++) {
                switch (c.charAt(j)) {
                    case '3':
                    case '4':
                    case '7':
                        breaked = true;
                        break CHAR;
                    case '0':
                    case '1':
                    case '8':
                        newNum[j] = c.charAt(j) ;
                        break ;
                    case '2':
                        newNum[j] = '5';
                        break ;
                    case '5':
                        newNum[j] = '2';
                        break ;
                    case '6':
                        newNum[j] = '9';
                        break ;
                    case '9':
                        newNum[j] = '6';
                        break ;
                    default:
                            break ;
                }
            }
            if (!breaked && !c.equals(new String(newNum))){
                result ++;
            }
        }
        return result;
    }

    public static int rotatedDigits2(int N) {
        int result = 0;
        for (Integer i = 1; i <= N; i++) {
            String c = i.toString();
            boolean counted = false;
            CHAR: for (int j = 0; j < c.length(); j++) {
                switch (c.charAt(j)) {
                    case '3':
                    case '4':
                    case '7':
                        counted = false;
                        break CHAR;
                    case '2':
                    case '5':
                    case '6':
                    case '9':
                        counted = true;
                        continue ;
                    default:
                        break ;
                }
            }
            if (counted) result++;
        }
        return result;
    }

    public static void main(String[] args) {
        int target = 100;
        System.out.println(rotatedDigits(target));
        System.out.println(rotatedDigits2(target));
    }

}
