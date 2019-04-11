import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupAnagrams49 {
  /**
   * Given an array of strings, group anagrams together.
   *
   * Example:
   *
   * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
   * Output:
   * [
   *   ["ate","eat","tea"],
   *   ["nat","tan"],
   *   ["bat"]
   * ]
   * Note:
   *
   * All inputs will be in lowercase.
   * The order of your output does not matter.
   *
   * @param strs
   * @return
   */
  public static List<List<String>> groupAnagrams(String[] strs) {
     return new ArrayList<>(Stream.of(strs)
             .collect(Collectors
                     .groupingBy(i-> {
                              char[] cs = i.toCharArray();
                              Arrays.sort(cs);
                              return new String(cs);},
                             Collectors.toList())
             )
             .values()
     );
  }

  public static List<List<String>> groupAnagrams2(String[] strs) {
    int[] prime = {2,3,5,7,11,13,17,19,23,29, 31,37,41,43,47,53,59,61,67,71, 73,79,83,89,97,101,103,	107,109,113, 127,131,137,139,149,151,157,163,167,173, 179,181,191,193,197,199,211,223,227,229, 233,239,241,251,257,263};
    List<List<String>> result = new ArrayList<>();
    Map<Integer, Integer> pos = new HashMap<>();
    for (String str: strs){
      int key = 1;
      for (char c: str.toCharArray()){
        key *= prime[c-'a'];
      }
      if (pos.containsKey(key)){
        result.get(pos.get(key)).add(str);
      } else {
        List<String> list = new ArrayList<>();
        list.add(str);
        result.add(list);
        pos.put(key, result.size()-1);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    List<List<String>> r = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    System.out.println(r);
  }
}
