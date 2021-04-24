package hundred0;

import org.junit.Assert;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 *
 * Constraints:
 *
 * 1 <= num1.length, num2.length <= 200
 * num1 and num2 consist of digits only.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class _43_MultiplyStrings {

    public static String multiply(String num1, String num2) {
        char[] result = new char[num1.length()+num2.length()];
        Arrays.fill(result, '0');
        int start = num1.length()+num2.length()-1;
        for (int i = num1.length()-1; i >=0; i--) {
            for (int j = num2.length()-1; j >=0; j--) {
                int l = num1.charAt(i)-'0';
                int r = num2.charAt(j)-'0';
                int mul = l*r;
                result[i+j+1] += mul%10;
                if (mul>9) {
                    result[i+j] += mul/10;
                }
                start = mul>9?i+j:mul>0?i+j+1:start;
                if (result[i+j+1]>'9') {
                    result[i+j] += (result[i+j+1]-'0')/10;
                    result[i+j+1] = (char) ((result[i+j+1]-'0')%10 + '0');
                    start = i+j;
                }
            }
        }
        return new String(result, start, result.length-start);
    }

    public static void main(String[] args) {
        Assert.assertEquals("123116286014066400", multiply("1307544150", "94158416"));
//        Assert.assertEquals("0", multiply("0", "4"));
//        Assert.assertEquals("0", multiply("0", "9999999999"));
//        Assert.assertEquals("56088", multiply("123", "456"));
//        Assert.assertEquals(String.valueOf((long) 999999*(long) 99999999), multiply("999999", "99999999"));
//        Assert.assertEquals(String.valueOf((long) 235235*(long) 252531), multiply("235235", "252531"));
//        Assert.assertEquals("6", multiply("2", "3"));

        BigInteger i = BigInteger.ZERO, j = BigInteger.ZERO;
        while (i.compareTo(new BigInteger("9999999999999999999999999999999"))<0) {
            while (j.compareTo(new BigInteger("9999999999999999999999999999999"))<0) {
                System.out.println( i + " * " + j  + " = " + i.multiply(j));
                Assert.assertEquals(i.multiply(j).toString(), multiply(i.toString(), j.toString()));
                i = i.multiply(BigInteger.valueOf(9)).add(BigInteger.valueOf(3));
                j = j.multiply(BigInteger.valueOf(7)).add(BigInteger.valueOf(2));
            }
        }
    }

}
