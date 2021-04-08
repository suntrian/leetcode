package hundred7;

/**
 *
 * We are given two strings, A and B.
 *
 * A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example,
 * if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some
 * number of shifts on A.
 *
 * Example 1:
 * Input: A = 'abcde', B = 'cdeab'
 * Output: true
 *
 * Example 2:
 * Input: A = 'abcde', B = 'abced'
 * Output: false
 * Note:
 *
 * A and B will have length at most 100.
 */

public class _796_RotateString {

  /**
   * todo not finished
   * @param A A
   * @param B B
   * @return true or false
   */
  public static boolean rotateString(String A, String B) {
    if (A.length() != B.length()) return false;
    int size = A.length();
    if (size == 0) return true;
    int a = 0, b =0;
    while (true) {
      if (A.charAt(a) == B.charAt(b)){
        a++;b++;
      } else {
        while (A.charAt(a)!= B.charAt(b)) b++;
        if (b==a) return false;
      }
      if (b==size){
        b=0;
      }
      if (a==size-1){
        return true;
      }
    }
  }

  public static boolean rotateString2(String A, String B) {
    if (A.length() != B.length()) return false;
    int size = A.length();
    if (size == 0) return true;
    for (int i = 0; i < size; i++){
      if (B.equals(A.substring(i,size) + A.substring(0,i))){
        return true;
      }
    }
    return false;
  }

  public static boolean rotateString3(String A, String B) {
    if (A.length() != B.length()) return false;
    int size = A.length();
    if (size == 0) return true;
    if ((A+A).contains(B)) return true;
    return false;
  }

    public static void main(String[] args) {
    String A = "abcde", B = "cdeab";
    System.out.println(rotateString2("",""));
    System.out.println(rotateString2(A,B));
    System.out.println(rotateString2("abcde", "abced"));

  }
}
