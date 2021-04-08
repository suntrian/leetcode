package hundred6;

public class _650_TwoKeysKeyboard {

    public int minSteps(int n) {
        if (n == 1) return 0;
        if (n <= 5) return n;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n%i == 0) {
                return minSteps(n/i) + i;
            }
        }
        return n;
    }
}
