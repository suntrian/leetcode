public class ReverseWordsinaStringIII557 {

  /**
   * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
   *
   * Example 1:
   * Input: "Let's take LeetCode contest"
   * Output: "s'teL ekat edoCteeL tsetnoc"
   * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
   *
   * @param s
   * @return
   */
  public static String reverseWords(String s) {
    char[] chars = s.toCharArray();
    StringBuilder builder = new StringBuilder();
    int l = -1;
    for (int i = 0; i <= chars.length; i++){
      if (i == chars.length || chars[i] == ' '){
        for (int j = i-1; j >l; j--){
          builder.append(chars[j]);
        }
        if (i!=chars.length){
          builder.append(chars[i]);
        }
        l = i;
      }
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    //System.out.println(reverseWords("Let's take LeetCode contest"));
    System.out.println(reverseWords("each   word i  s   separated by single space and there will not be any extra space in the string"));
  }

}
