import java.util.*;
import java.util.stream.Stream;

public class FindCommonCharacters1002 {

  /**
   * Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
   *
   * You may return the answer in any order.
   *
   *
   *
   * Example 1:
   *
   * Input: ["bella","label","roller"]
   * Output: ["e","l","l"]
   * Example 2:
   *
   * Input: ["cool","lock","cook"]
   * Output: ["c","o"]
   *
   *
   * Note:
   *
   * 1 <= A.length <= 100
   * 1 <= A[i].length <= 100
   * A[i][j] is a lowercase letter
   */
  public static List<String> commonChars(String... A) {
    int[][] count = new int[A.length][26];
    for (int i = 0; i < A.length; i++){
      for (char c: A[i].toCharArray()){
        count[i][c-'a']++;
      }
    }
    List<String> result = new ArrayList<>();
    OUT: for (int i = 0; i < 26; i++){
      int min = 0;
      for (int j = 0; j < A.length; j ++){
        if (count[j][i] == 0){
          continue OUT;
        }
        if (min == 0 || count[j][i] < min ){
          min = count[j][i];
        }
      }
      for (int j = 0; j < min; j++){
        result.add(String.valueOf((char)(i+'a')));
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(commonChars("bella","label","roller"));
    System.out.println(commonChars("cool","lock","cook"));
    System.out.println(commonChars("coool","loock","cook"));
    System.out.println(commonChars("asdfasdfwefe","dqegqefafeafee","afweofadlfjwoafle","fwoeifjalsdfjwef"));
  }

}
