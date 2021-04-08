package hundred0;

public class _66_PlusOne {

    public int[] plusOne(int[] digits) {
        int carry = 0;
        int[] result = new int[digits.length];
        for (int i = digits.length-1; i>=0; i--) {
            int sum = digits[i] + carry + (i==digits.length-1?1:0);
            result[i] = sum %10;
            carry = sum/10;
        }
        if (carry == 1) {
            int[] result2 = new int[digits.length+1];
            result2[0] = 1;
            for (int i = 1; i < digits.length+1; i++) {
                result2[i] = result[i-1];
            }
            return result2;
        }
        return result;
    }

    public int[] plusOne2(int[] digits) {
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }
}
