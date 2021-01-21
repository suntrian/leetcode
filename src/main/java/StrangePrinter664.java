public class StrangePrinter664 {

    /**
     * There is a strange printer with the following two special requirements:
     *
     * The printer can only print a sequence of the same character each time.
     * At each turn, the printer can print new characters starting from and ending at any places, and will cover the
     * original existing characters.
     * Given a string consists of lower English letters only, your job is to count the minimum number of turns the
     * printer needed in order to print it.
     *
     * Example 1:
     * Input: "aaabbb"
     * Output: 2
     * Explanation: Print "aaa" first and then print "bbb".
     * Example 2:
     * Input: "aba"
     * Output: 2
     * Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the
     * existing character 'a'.
     * Hint: Length of the given string will not exceed 100.
     */
    public int strangePrinter(String s) {
        if (s.isEmpty()) return 0;
       int result = 0;
       char f = '\u0000';
       for (int i = 0 ; i < s.length(); i++) {
            char x = s.charAt(i);
            if (f != x) {
                f = x;
                result++;
            }
       }
       return result/2+1;
    }

    public static void main(String[] args) {
        StrangePrinter664 printer = new StrangePrinter664();
        int r2 = printer.strangePrinter("aaabb");       //2
        int r3 = printer.strangePrinter("aba");         //2
        int r4 = printer.strangePrinter("abab");        //3
        int r5 = printer.strangePrinter("ababa");       //3
        int r6 = printer.strangePrinter("ababab");      //4
        int r7 = printer.strangePrinter("abababa");     //4
        int r8 = printer.strangePrinter("abababab");    //5
        int r9 = printer.strangePrinter("ababababa");   //5
        int r10 = printer.strangePrinter("ababababab");  //6

        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
        System.out.println(r5);
        System.out.println(r6);
        System.out.println(r7);
        System.out.println(r8);
        System.out.println(r9);
        System.out.println(r10);
    }
}
