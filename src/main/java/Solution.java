import util.TreeNode;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by yuanxm on 1/19/17.
 */


interface Callback{
    void exec();
}

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}


//355
/*Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
        postTweet(userId, tweetId): Compose a new tweet.
        getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
        follow(followerId, followeeId): Follower follows a followee.
        unfollow(followerId, followeeId): Follower unfollows a followee.
*/
class Twitter{
    /** Initialize your data structure here. */
    private List<List<String>> twitter;
    private List<List<Integer>> follow;

    public Twitter() {
        twitter = new ArrayList<List<String>>();
        follow = new ArrayList<List<Integer>>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        this.twitter.add(Arrays.asList(String.valueOf(userId),String.valueOf(tweetId)));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        List<Integer> userfollow = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < this.follow.size(); i++){
            if(this.follow.get(i).get(0) == userId) userfollow.add(this.follow.get(i).get(1));
        }
        for (int i = this.twitter.size() - 1; i >= 0 ; i--) {
            if(Integer.valueOf(this.twitter.get(i).get(0)) == userId || userfollow.contains(Integer.valueOf(this.twitter.get(i).get(0)))) {
                result.add(Integer.valueOf(this.twitter.get(i).get(1)));
                count++;
                if(count >=10) break;
            }
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        this.follow.add(Arrays.asList(followerId, followeeId));
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        for(int i = 0; i < this.follow.size(); i++) {
            if (this.follow.get(i).get(0) == followerId && this.follow.get(i).get(1) == followeeId) {
                this.follow.remove(i);
            }
        }
    }

    /**
     * Your Twitter object will be instantiated and called as such:
     * Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId);
     * List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId);
     * obj.unfollow(followerId,followeeId);
     */
}


public class Solution implements Callback{

    public void  swap(int[] str, int a, int b){
        if(a<0 || b<0 || a >= str.length || b>=str.length) return;
        int c = str[a];
        str[a] = str[b];
        str[b] = c;
    }
    public void  swap(char[] str, int a, int b){
        if(a<0 || b<0 || a >= str.length || b>=str.length) return;
        char c = str[a];
        str[a] = str[b];
        str[b] = c;
    }


    public Solution(){

    }


    public void testTime(Callback callback){
        long start = System.nanoTime();
        callback.exec();
        long end = System.nanoTime();
        System.out.println("[USE TIME]:" + (end-start));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;
        Arrays.sort(nums);
        int i = 0;
        while (i <= (nums.length - 2)) {
            if (nums[i] > 0) break;
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                if (sum >= 0) while (nums[k] == nums[--k] && j < k) ;
                if (sum <= 0) while (nums[j] == nums[++j] && j < k) ;
            }
            while (nums[i] == nums[++i] && i < (nums.length - 2)) ;
        }
        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        //Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
        //Find all unique quadruplets in the array which gives the sum of target.
        // i,j,m,n
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) return result;
        Arrays.sort(nums);
        int i = 0, j = 1, m = 2, n = 3;
        while (i < nums.length - 3) {
            j = i + 1;
            while (j > i && j < m) {
                m = j + 1;
                while (m > j && m < n) {
                    n = m + 1;
                    while ((n <= nums.length - 1) && (nums[i] + nums[j] + nums[m] + nums[n]) <= target) {
                        if ((nums[i] + nums[j] + nums[m] + nums[n]) == target) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                        }
                        while (++n < nums.length && nums[n] == nums[n - 1]) ;
                    }
                    while (++m < nums.length - 1 && nums[m] == nums[m - 1]) ;
                }
                while (++j < nums.length - 2 && nums[j] == nums[j - 1]) ;
            }
            while (++i < nums.length - 3 && nums[i] == nums[i - 1]) ;
        }
        return result;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) return result;
        Arrays.sort(nums);
        int i, j, m, n;
        i = 0;
        while (i < nums.length - 3) {
            //if( nums[i] > target) break;
            n = nums.length - 1;
            while (n >= 3) {
                //if ( nums[n] < target) break;
                j = i + 1;
                m = n - 1;
                while (j < m) {
                    int sum = nums[i] + nums[j] + nums[m] + nums[n];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                    }
                    if (sum >= target) while (nums[m] == nums[--m] && j < m) ;
                    if (sum <= target) while (nums[j] == nums[++j] && j < m) ;
                }
                while (nums[n] == nums[--n] && n >= 3) ;
            }
            while (nums[i] == nums[++i] && i < nums.length - 3) ;

        }
        return result;
    }

    public boolean fourSum_Testcase() {
//        int[] nums = {-5,-2,-1,0,2,3,5,7,9};
//        System.out.println(fourSum(nums, 2).toString());
//        System.out.println(fourSum2(nums, 2).toString());
//        int[] nums2 = {1,0,-1,0,-2,2};
//        System.out.println(fourSum(nums2, 0 ).toString());
//        System.out.println(fourSum2(nums2, 0 ).toString());
//        int[] nums3 = {-3,-2,-1,0,0,1,2,3};
//        System.out.println(fourSum(nums3, 0).toString());
//        System.out.println(fourSum2(nums3, 0).toString());
        int[] num4 = {1, -2, -5, -4, -3, 3, 3, 5};
        System.out.println(fourSum2(num4, -11).toString());

        return true;
    }

    public boolean canJump(int[] nums) {
     /*
        Given an array of non-negative integers, you are initially positioned at the first index of the array.
        Each element in the array represents your maximum jump length at that position.
        Determine if you are able to reach the last index.
        For example:
        A = [2,3,1,1,4], return true.
        A = [3,2,1,0,4], return false.
    */
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }

    public void recoverTree(TreeNode root) {
    /*
        Two elements of a binary search tree (BST) are swapped by mistake.
        Recover the tree without changing its structure.
        Note:
        A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
    */
        Stack<TreeNode> stack = new Stack<>();
        TreeNode swap = null;
        int wrong = Integer.MIN_VALUE;
        TreeNode cursor = root;
        stack.push(cursor);
        while (!stack.isEmpty()) {
            if (cursor.left != null) {
                stack.push(cursor);
                if (cursor.left.val > cursor.val) {
                    if (swap == null) {
                        swap = cursor.left;
                    } else {
                        wrong = cursor.left.val;
                        cursor.left.val = swap.val;
                        swap.val = wrong;
                    }
                }
                cursor = cursor.left;
            } else {
                cursor = stack.pop().right;
            }
            if (cursor.right != null) {
                if (cursor.right.val < cursor.val) {
                    if (swap == null) {
                        swap = cursor.right;
                    } else {
                        wrong = cursor.right.val;
                        cursor.right.val = swap.val;
                        swap.val = wrong;
                    }
                }
            }
        }
    }

    public void recoverTree_Testcase() {
        //
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(6);
        root.left.right.left = new TreeNode(2);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(9);

        recoverTree(root);
        System.out.println(root.left.left.left.val);
    }

    //387
    public int firstUniqChar(String s) {
        int[] counts = new int[26];
        char[] ss = s.toCharArray();
        for (int i = 0; i < ss.length; i++) {
            counts[ss[i] - 'a']++;
        }
        for (int i = 0; i < ss.length; i++) {
            if (counts[ss[i] - 'a'] == 1) return i;
        }
        return -1;
    }

    public int firstUniqeChar_Testcase() {
        System.out.println(firstUniqChar("aadfefdfeaviekvo"));
        return 0;
    }

    //324
    //Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
    //Error: Tested wrong , figure later;
    public void wiggleSort(int[] nums) {
        int swap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0 && nums[i] > nums[i + 1]) || (i % 2 == 1 && nums[i] < nums[i + 1])) {
                swap = nums[i + 1];
                nums[i + 1] = nums[i];
                nums[i] = swap;
            } else if (nums[i] == nums[i + 1]) {
                for (int j = i + 1; j < nums.length; j++) {
                    if ((i % 2 == 0 && nums[i] < nums[j]) || (i % 2 == 1 && nums[i] > nums[j])) {
                        swap = nums[j];
                        nums[j] = nums[i + 1];
                        nums[i + 1] = swap;
                    }
                }
            }

        }
    }

    public void wiggleSort_Testcase() {
        //int[] nums = {1,5,1,1,6,4};
        int[] nums = {1, 2, 2, 3, 1, 5, 6, 2, 6, 8, 3, 5, 4};
        wiggleSort(nums);
        for (int i : nums) {
            System.out.print(i);
        }
    }

    //477
    //The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
    //Now your job is to find the total Hamming distance between all pairs of the given numbers.
    public int totalHammingDistance(int[] nums) {
        int[] one_counts = new int[10000];
        int maxwidth = 0;
        for (int i = 0; i < nums.length; i++) {
            String binStr = Integer.toBinaryString(nums[i]);
            if(binStr.length()>maxwidth) maxwidth = binStr.length();
            for (int j = binStr.length() - 1; j >= 0; j--) {
                if (binStr.charAt(j) == '1') one_counts[binStr.length() - 1 - j]++;
            }
        }
        int dist = 0;
        for (int i = 0; i < maxwidth; i++) {
            if (one_counts[i] > 0) {
                dist += (one_counts[i] * (nums.length - one_counts[i]));
            }
        }
        return dist;
    }
    public int totalHammingDistance2(int[] nums){
        int total = 0, n = nums.length;
        for (int j=0;j<32;j++) {                //int32位
            int bitCount = 0;
            for (int i=0;i<n;i++)
                bitCount += (nums[i] >> j) & 1;
            total += bitCount*(n - bitCount);
        }
        return total;
    }

    public int totalHammingDistance_Testcase() {
        int nums[] = {4, 14, 2};
        System.out.println(totalHammingDistance(nums));
        return 0;
    }

    //36
    //Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
    //The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
    public boolean isValidSudoku(char[][] board){
        int[] nums = new int[9];

        for(int i = 0; i < 9; i++){
            for( int j =0; j < 9; j++){
                if( board[i][j] == '.') continue;
                int figure = Integer.valueOf(String.valueOf(board[i][j]));
                if(nums[figure-1] == 1) return false;
                nums[figure-1] = 1;
            }
            nums = new int[9];
        }

        for(int i = 0; i < 9; i++){
            for( int j =0; j < 9; j++){
                if( board[j][i] == '.') continue;
                int figure = Integer.valueOf(String.valueOf(board[j][i]));
                if(nums[figure-1] == 1) return false;
                nums[figure-1] = 1;
            }
            nums = new int[9];
        }
        for(int base_horz = 0; base_horz < 3; base_horz++){
            for(int base_vert = 0; base_vert < 3; base_vert++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if(board[base_horz*3+i][base_vert*3+j] == '.') continue;
                        int figure = Integer.valueOf(String.valueOf(board[base_horz * 3 + i][base_vert * 3 + j]));
                        if (nums[figure-1] == 1) return false;
                        nums[figure-1] = 1;
                    }
                }
                nums = new int[9];
            }
        }
        return true;
    }

    public boolean isValidSuduku2(char[][] board){
        for (int i =0 ; i < 9; i++){
            HashSet<Character> rows = new HashSet<>();
            HashSet<Character> columns = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for (int j = 0 ; j < 9 ; j++){
                if( board[i][j]!='.' && !rows.add(board[i][j])) return false;
                if( board[j][i]!='.' && !columns.add(board[j][i])) return false;
                if( board[3*(i/3) + j/3][3*(i%3)+j%3] != '.' && !cube.add(board[3*(i/3)+j/3][3*(i%3)+j%3])) return false;
            }
        }
        return true;
    }

    public void isValidSudoku_Testcase(){
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(isValidSudoku(board));
    }

    //39
    //Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
    //The same repeated number may be chosen from C unlimited number of times.
    //Note:
    //All numbers (including target) will be positive integers.
    //The solution set must not contain duplicate combinations.
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);

        recurseCombinationSum(result, new ArrayList<Integer>(), candidates, target,0);
        return result;
    }
    public void recurseCombinationSum(List<List<Integer>> result, List<Integer> cur, int[] candidates, int target, int start){
        if( target > 0){
            for( int i = start; i < candidates.length && target >= candidates[i]; i++){
                cur.add(candidates[i]);
                recurseCombinationSum(result, cur, candidates, target-candidates[i], i);
                cur.remove(cur.size()-1);
            }
        }else if( target==0){
            result.add(new ArrayList<Integer>(cur));
        }

    }
    public void combinationSum_Testcase(){
        int[] nums = {2,3,6,5,7,9,11,35,13,8,12,15};
        List<List<Integer>> result = combinationSum(nums, 15);
        System.out.println(result);
    }

    public int largestPalindrome(int n) {
        if (n==1) return 9;
        int max=(int)Math.pow(10, n)-1;
        for (int v=max-1;v>max/10;v--) {
            long u=Long.valueOf(v+new StringBuilder().append(v).reverse().toString());
            for (long x=max;x*x>=u;x--)
                if (u%x==0)
                    return (int)(u%1337);
        }
        return 0;
    }
    public void largestPalindrome_testcase(){
        int n = 2;
        System.out.println(largestPalindrome(n));
    }

    public void twitter_testcase(){
        Twitter t = new Twitter();
        t.postTweet(1,5);
        t.getNewsFeed(1);
        t.follow(1,2);
        t.postTweet(2,6);
        t.getNewsFeed(1);
        t.unfollow(1,2);
        t.getNewsFeed(1);
    }

    //368
    /*
        Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
        If there are multiple solutions, return any subset is fine.
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> resultset = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int maxcount = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++ ) {
            int count = 0;
            temp.add(nums[i]);
            count++;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % nums[i] == 0) {
                    temp.add(nums[j]);
                    count++;
                    i = j;
                    continue;
                }
            }
            if (count > maxcount) {
                resultset = new ArrayList<>(temp);
                maxcount = count;
            }
            temp.clear();
        }
        return resultset;
    }

//  failed , stay unsolved;
    public void largestDivisibleSubset_testcase(){
        //int[] nums = {1,2,3,4,5,6,7,8,9,12,16};
        //int[] nums = {1,2,3};
        int[] nums = {832,33,531,416,335,298,365,352,582,936,366,305,930,530,97,349,71,295,840,108,299,804,925,627,953,571,658,732,429,136,563,462,666,330,796,315,695,500,896,982,217,200,912,98,297,612,169,943,628,593,959,904,219,240,857,789,897,940,569,384,502,382,401,184,716,230,29,963,211,597,515,122,163,86,215,105,889,842,49,847,267,87,954,407,245,975,719,746,709,471,281,238,186,510,618,149,73,214,663,194,260,825,631,474,519,668,329,718,765,947,156,353,490,962,679,560,59,387,31,692,976,568,201,273,159,730,819,418,906,801,892,672,559,866,389,675,812,744,164,737,57,195,115,933,158,909,598,359,853,314,983,11,395,153,781,301,838,625,704,256,351,996,225,644,521,509,674,417,272,622,937,723,632,331,228,412,181,435,469,157,368,524,38,132,325,420,127,731,771,604,505,634,67,374,894,3,448,878,686,641,316,207,76,363,795,235,770,446,820,493,177,816,615,410,117,944,829,190,831,289,516,964,170,134,671,885,682,119,402,82,485,901,375,68,858,739,56,974,683,884,815,872,715,104,290,348,588,834,788,472,466,867,550,779,65,802,459,440,870,753,608,808,623,642,44,437,865,758,540,506,691,958,854,546,39,595,369,504,63,311,722,441,786,899,338,651,874,946,811,848,939,284,824,309,653,133,514,460,678,54,399,759,468,61,480,783,266,900,400,237,403,534,213,914,473,198,380,373,288,154,844,535,409,249,285,168,69,345,647,851,846,264,102,246,106,648,576,212,438,981,987,379,360,667,95,172,101,580,891,385,747,161,927,361,818,657,171,342,232,734,714,362,425,475,28,41,551,142,131,51,229,9,607,326,522,687,792,845,665,358,91,720,155,565,99,26,650,539,780,589,950,935,372,227,424,750,833,554,841,552,60,757,430,916,140,790,426,776,96,199,923,806,949,755,711,659,911,611,310,774,265,880,690,706,761,286,255,756,204,444,478,601,529,669,241,784,566,528,208,270,511,236,271,378,58,453,467,233,250,567,296,932,989,367,626,35,162,887,572,603,564,797,280,406,970,689,408,431,638,489,85,50,357,803,47,555,793,422,763,110,869,861,253,320,538,347,405,769,64,875,630,537,328,553,166,948,303,160,800,507,920,922,90,693,636,17,455,183,210,856,762,656,174,873,579,176,688,640,1,938,902,341,740,581,427,111,972,443,22,791,304,574,575,725,477,700,817,381,479,248,121,411,547,182,871,599,203,13,224,541,724,178,775,388,4,251,321,52,88,100,279,614,839,84,151,735,40,752,773,376,77,476,708,396,988,961,24,231,445,609,952,965,986,414,451,881,42,257,32,334,130,596,527,94,333,317,244,960,710,852,862,421,81,37,452,274,187,268,520,491,778,18,743,620,145,72,370,118,748,633,997,436,143,573,495,180,34};
        System.out.println(largestDivisibleSubset(nums));
    }

    //118 Pascal's Triangle
    /*
        Given numRows, generate the first numRows of Pascal's triangle.
     */
    public List<List<Integer>> pascalTriangle(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if(numRows <= 0) return result;
        List<Integer> line1 = Arrays.asList(1);
        result.add(line1);
        if(numRows == 1) return result;
        List<Integer> line2 = Arrays.asList(1,1);
        result.add(line2);
        if(numRows == 2) return result;
        for(int i = 2 ; i < numRows; i++){
            List<Integer> line = new ArrayList<>();
            line.add(1);
            for(int j = 1; j < i ; j++){
                line.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
            }
            line.add(1);
            result.add(line);
        }
        return result;
    }
    public void pascalTriangle_testcase(){
        System.out.println(pascalTriangle(6));
    }

    //215 Kth Largest Element in an Array
    /*
        Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
     */
    public int findKthLargest(int[] nums, int k) {
        if(k>nums.length || k <= 0) return -1;
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    public void findKthLargest_testcase(){
        int[] nums = {3,2,1,5,6,4};
        System.out.println(findKthLargest(nums,1));
    }

    //373 Find K Pairs with Smallest Sums
    /*
        You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
        Define a pair (u,v) which consists of one element from the first array and one element from the second array.
        Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        return result;
    }

    //62. Unique Paths
    /*
        A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
        The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
        How many possible unique paths are there?
     */
    public int uniquePaths(int m, int n) {
        if(m==1 || n ==1) return 1;
        return uniquePaths(m-1,n) + uniquePaths(m,n-1);
    }
    public int uniquePaths2(int m, int n){
        Integer[][] map = new Integer[m][n];
        for(int i = 0; i < m; i++) map[i][0] = 1;
        for(int i = 0; i < n; i++) map[0][i] = 1;
        for(int i =1; i < m; i++){
            for(int j = 1; j < n; j++){
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }
        return map[m-1][n-1];
    }
    public int uniquePaths3(int m, int n){
        BigInteger ret = factorial(m+n-2).divide(factorial(m-1).multiply(factorial(n-1)));
        return ret.intValue();
    }
    public BigInteger factorial(int x) {
        if (x <= 0) {
            return BigInteger.ONE;
        }
        BigInteger fact = BigInteger.ONE;
        for (int i = 2; i <= x; i++) {
            fact = fact.multiply(BigInteger.valueOf((long)i));
        }
        return fact;
    }
    public void uniquePaths_testcase(){
        System.out.println(uniquePaths3(36,7));
    }

    //273. Integer to English Words
    /*
        Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
        For example,
        123 -> "One Hundred Twenty Three"
        12345 -> "Twelve Thousand Three Hundred Forty Five"
        1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
     */
    public String numberToWords(int num) {
        Map<Integer,String> corres = new HashMap<>();
        corres.put(0,"Zero");corres.put(1,"One");corres.put(2,"Two");corres.put(3,"Three");corres.put(4,"Four");
        corres.put(5,"Five");corres.put(6,"Six");corres.put(7,"Seven");corres.put(8,"Eight");corres.put(9,"Nine");
        corres.put(10,"Ten");corres.put(11,"Eleven");corres.put(12,"Twelve");corres.put(13,"Thirteen");corres.put(14,"Fourteen");
        corres.put(15,"Fifteen");corres.put(16,"Sixteen");corres.put(17,"Seventeen");corres.put(18,"Eighteen");corres.put(19,"Nineteen");
        corres.put(20,"Twenty");corres.put(30,"Thirty");corres.put(40,"Forty");corres.put(50,"Fifty");corres.put(60,"Sixty");
        corres.put(70,"Seventy");corres.put(80,"Eighty");corres.put(90,"Ninety");corres.put(100,"Hundred");corres.put(1000,"Thousand");
        corres.put(1000000,"Million");corres.put(1000000000,"Billion");

        int result = num/1000;
        int remained = num%1000;
        int h = remained/100;
        int r = remained%100;
        return "";
    }
    //remain unsolved


    //198. House Robber
    /*
        You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
        Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
     */
    public int rob(int[] nums) {
        int a = 0, b = 0;
        for(int i = 0; i < nums.length; i++){
            if(i%2==0){
                a = Math.max(a+nums[i],b);
            }else {
                b = Math.max(a,b+nums[i]);
            }
        }
        return Math.max(a,b);
    }
    public int rob2(int[] nums){
        int ifRobbedPrevious = 0; 	// max monney can get if rob current house
        int ifDidntRobPrevious = 0; // max money can get if not rob current house

        // We go through all the values, we maintain two counts, 1) if we rob this cell, 2) if we didn't rob this cell
        for(int i=0; i < nums.length; i++)
        {
            // If we rob current cell, previous cell shouldn't be robbed. So, add the current value to previous one.
            int currRobbed = ifDidntRobPrevious + nums[i];

            // If we don't rob current cell, then the count should be max of the previous cell robbed and not robbed
            int currNotRobbed = Math.max(ifDidntRobPrevious, ifRobbedPrevious);

            // Update values for the next round
            ifDidntRobPrevious  = currNotRobbed;
            ifRobbedPrevious = currRobbed;
        }

        return Math.max(ifRobbedPrevious, ifDidntRobPrevious);
    }

    public void rob_testcase(){
        int[] nums = {2,6,7,1,3,4,7,4,3,5,6,9,1,5};
        System.out.println(rob(nums));
        System.out.println(rob2(nums));
    }

    //50. Pow(x, n)
    /*
        Implement pow(x, n).
     */
    // O(n) solution
    public double myPow(double x, int n) {
        if(n==0) return 1;
        double num = 1;
        for(int i = 0; i < Math.abs(n); i++){
            num *= x;
        }
        return n>0?num:1/num;
    }
    public double myPow2(double x, int n) {
        if(n == 0) return 1;
        if(x == 0.0) return 0;
        if(n<0 && n > Integer.MIN_VALUE) {
            n = -n;
            x = 1/x;
        }
        return n%2==0?myPow2(x*x,n/2):x*myPow2(x*x,n/2);
    }

    public void myPow_testcase(){
        System.out.println(myPow2(0.1,Integer.MIN_VALUE));
        System.out.println(myPow(0.1,Integer.MIN_VALUE));
        System.out.println(Math.pow(0.1,Integer.MIN_VALUE));
//        System.out.println(Math.pow(1.2,5));
//        System.out.println(myPow2(1.2,-5));
//        System.out.println(Math.pow(1.2,-5));
//        System.out.println(myPow2(1.2,0));
//        System.out.println(Math.pow(1.2,0));
//        System.out.println(myPow2(-1.2,2));
//        System.out.println(Math.pow(-1.2,2));
//        System.out.println(myPow2(0.0001,2147483647));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(Double.MAX_VALUE);
//        System.out.println(Double.MIN_VALUE);
    }

    //384. Shuffle an Array
    /*
        Shuffle a set of numbers without duplicates.
     */
    private int[] nums;
    private Random random;
    public Solution(int[] nums){
        this.nums = nums;
        this.random = new Random();
    }
    public void setNums(int[] nums){
        this.nums = nums;
    }
    public void setRandom(){ this.random = new Random(); }
    public int[] reset(){
        return this.nums;
    }
    public int[] shuffle(){
        int[] ret = this.nums.clone();
        for(int i = 0; i < ret.length; i++) ret[i] = this.nums[i];
        for(int i = 0; i < ret.length; i++){
            int t = random.nextInt(ret.length);
            swap(ret,i,t);
        }
        return ret;
    }

    public int[] shuffle2(){
        int[] ret = new int[this.nums.length];
        List<Integer> clone = new ArrayList<>();
        for(int n:this.nums) clone.add(n);
        for(int i =0; i < ret.length; i++){
            int r = random.nextInt(ret.length-i);
            ret[i] = clone.get(r);
            clone.remove(r);
        }
        return ret;
    }



    public void shuffle_testcase(){
        int[] nums = {1,2,3,4,5,6,7,8,9};
        setNums(nums);
        setRandom();
        for(int n:shuffle()) System.out.print(n);
    }

    //60. Permutation Sequence
    /*
        The set [1,2,3,…,n] contains a total of n! unique permutations.
        By listing and labeling all of the permutations in order,
        We get the following sequence (ie, for n = 3):
        "123"
        "132"
        "213"
        "231"
        "312"
        "321"
        Given n and k, return the kth permutation sequence.
        Note: Given n will be between 1 and 9 inclusive.
     */
    public String getPermutation(int n, int k) {
        int pos = 0;
        List<Integer> numbers = new ArrayList<Integer>();
        int[] factorial = new int[n+1];
        StringBuilder sb = new StringBuilder();

        // create an array of factorial lookup
        int sum = 1;
        factorial[0] = 1;
        for(int i=1; i<=n; i++){
            sum *= i;
            factorial[i] = sum;
        }
        // factorial[] = {1, 1, 2, 6, 24, ... n!}

        // create a list of numbers to get indices
        for(int i=1; i<=n; i++){
            numbers.add(i);
        }
        // numbers = {1, 2, 3, 4}

        k--;

        for(int i = 1; i <= n; i++){
            int index = k/factorial[n-i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            k-=index*factorial[n-i];
        }

        return String.valueOf(sb);
    }

    public String getPermutation2(int n, int k){
        List<Integer> num = new LinkedList<>();
        for(int i = 1; i <=n; i++) num.add(i);
        int[] factorial = new int[n];
        factorial[0] = 1;
        for(int i = 1; i < n; i++) factorial[i] = i*factorial[i-1];
        k = k-1;
        StringBuilder sb = new StringBuilder();
        for(int i = n; i > 0; i--){
            int ind = k/factorial[i-1];
            k = k%factorial[i-1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }

    public void getPermutation_testcase(){
        int n = 1, k = 1;
        System.out.println(getPermutation2(n,k));
    }

    //14. Longest Common Prefix
    /*
        Write a function to find the longest common prefix string amongst an array of strings.
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "" ;
        String pre = strs[0];
        for(int i=1; i<strs.length ;i++){
            while (!strs[i].startsWith(pre)){
                pre = pre.substring(0,pre.length()-1);

            }
        }
        return pre;
    }

    //149. Max Points on a Line
    /*
    Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
     */
    public int maxPoints(Point[] points) {
        if(points.length<=2) return points.length;
        int result = 0;
        for(int i =0; i<points.length-1; i++){
            Map<Integer,Map<Integer,Integer>> slope_count = new HashMap<>();
            int max = 0,inc = 0;
            for(int j =i+1;j<points.length; j++){
                double slope = 0;
                int deltay = points[j].y-points[i].y;
                int deltax = points[j].x-points[i].x;
                if(deltay==0 && deltax == 0) {
                    inc+=1;
                    continue;
                }
                int gcd = generateGCD(deltax,deltay);
                if(gcd != 0){
                    deltax /= gcd;
                    deltay /= gcd;
                }
                int count = 1;
                if(slope_count.containsKey(deltax)){
                    if(slope_count.get(deltax).containsKey(deltay)){
                        count = slope_count.get(deltax).get(deltay)+1;
                        slope_count.get(deltax).put(deltay, count);
                    }else {
                        slope_count.get(deltax).put(deltay, 1);
                    }
                }else {
                    Map<Integer,Integer> m = new HashMap<>();
                    m.put(deltay,1);
                    slope_count.put(deltax,m);
                }
                max = Math.max(max,count);
            }
            result = Math.max(result, max + inc +1);
        }
        return result;
    }
    private int generateGCD(int a,int b){
        if (b==0) return a;
        else return generateGCD(b,a%b);
    }
    public void maxPoints_testcase(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(94911151,94911150);
        Point p3 = new Point(94911152,94911151);
        System.out.println(maxPoints((Point[])Arrays.asList(p1,p2,p3).toArray()));
    }

    //75. Sort Colors
    /*Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
        Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
    */
    public void sortColors(int[] nums) {
        final int RED = 0, WHITE = 1, BLUE = 2;
        int frontWhiteStart = -1 , backWhiteStart = nums.length;

        for(int i = 0,j=nums.length-1; i<=j ; ){
            while ( nums[i] == RED && i<=j) {
                if( frontWhiteStart>=0) {
                    nums[frontWhiteStart++] = RED;
                    nums[i] = WHITE;
                }
                i++;
                if(i>j) return;
            }
            while ( nums[j] == BLUE  && i<=j){
                if( backWhiteStart <nums.length) {
                    nums[backWhiteStart--] = BLUE;
                    nums[j] = WHITE;
                }
                j--;
                if(i>j) return;
            }


            if(nums[i] == BLUE && nums[j] == RED){
                if(frontWhiteStart>=0){
                    nums[frontWhiteStart++] = RED;
                    nums[i] = WHITE;
                }else {
                    nums[i] = RED;
                }
                if(backWhiteStart<nums.length){
                    nums[backWhiteStart--] = BLUE;
                    nums[j] = WHITE;
                }else {
                    nums[j] = BLUE;
                }
                i++;
                j--;
                continue;
            }
            if(nums[i] == WHITE){
                if(frontWhiteStart<0) frontWhiteStart = i;
                i++;
            }
            if(nums[j] == WHITE ) {
                if (backWhiteStart >= nums.length) backWhiteStart = j;
                j--;
            }
        }

    }
    public void sortColors2(int[] nums){
        int front = 0, back = nums.length-1, temp = 0;
        for(int i =0; i<nums.length;i++){
            while (nums[i] == 2 && i < back) {
                temp = nums[i];
                nums[i] = nums[back];
                nums[back] = temp;
                back--;
            }
            while (nums[i] == 0 && i > front) {
                temp = nums[i];
                nums[i] = nums[front];
                nums[front] = temp;
                front++;
                //swap(nums, i, front++);
            }
        }
    }
    public void sortColors_testcase(){
        //int[] nums = {0,2,2,1,1,1,2,0,0,2,1,1,0,0,0,2,0};
        //int[] nums = {1,0};
        int[] nums = {2};
        //int[] nums = {1,2,0};
        //int[] nums = {2,1,1,2,2,1,2,1};
        //int[] nums = {1,2,2,1,2,2,1,1,2,1,1};
        sortColors2(nums);
        for(int n: nums) System.out.print(n);
    }

    //53. Maximum Subarray
    /*
        Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
        For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
        the contiguous subarray [4,-1,2,1] has the largest sum = 6.
        click to show more practice.
        More practice:
        If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle
     */
    public int maxSubArray(int[] nums) {
        int maxLeft = nums[0], maxCurrent=nums[0];
        for(int i = 1; i<nums.length; i++){
            maxCurrent = Math.max(maxCurrent + nums[i], nums[i]);
            maxLeft = Math.max(maxCurrent, maxLeft);
        }
        return maxLeft;
    }
    public int maxSubArray2(int[] nums){
        int sum = 0, min = 0, rest=nums[0];
        for(int i = 0; i<nums.length; i++){
            sum += nums[i];
            if(sum-min > rest) rest = sum-min;
            if(sum < min ) min = sum;
        }
        return rest;
    }

    //divide and conquer
    public int maxSubArray3(int[] nums){
        return 0;
    }

    public void maxSubArray_testcase(){
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray2(nums));
    }

    //h-index
    /*
        Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
        According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n == 0) return 0;
        int[] count = new int[n+1];
        for (int i = 0; i < n; i++) {
            if (citations[i] > n ) {
                count[n] += 1;
                continue;
            }
            count[citations[i]] += 1;
        }
        int sum = 0;
        for(int i = n; i>=0; i--){
            sum += count[i];
            if (sum >= i ) return i;
        }
        return 0;
    }

    public int hIndex2(int[] citations){
        int length = citations.length;
        int start = 0;
        int end = length - 1;
        int hIndex = 0;

        while (start <= end)
        {
            int current = divideByPartition(citations, start, end);
            if (length - current <= citations[current])
            {
                hIndex = length - current;
                end = current - 1;
            }
            else
                start = current + 1;
        }

        return hIndex;
    }

    private int divideByPartition(int[] a, int start, int end)
    {
        if (start == end) return end;

        int p = a[end];
        int head = start;
        for (int current = start; current < end; current++)
        {
            if (a[current] < p)
            {
                int temp = a[head];
                a[head] = a[current];
                a[current] = temp;
                head++;
            }
        }
        a[end] = a[head];
        a[head] = p;
        return head;
    }

    public void hIndex_testcase(){
        int[] citations = {3, 0, 6, 1, 5};
        //int[] citations = {1};
        System.out.println(hIndex(citations));
    }

    //404. Sum of Left Leaves
    /*
        Find the sum of all left leaves in a given binary tree.
     */
    boolean nowLeft = false;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        if(root.left != null) {
            nowLeft = true;
            sum += sumOfLeftLeaves(root.left);
        }
        if(root.right != null) {
            nowLeft = false;
            sum += sumOfLeftLeaves(root.right);
        }
        if(root.left == null && root.right == null && nowLeft) return root.val;
        return sum;
    }

    public void sumOfLeftLeaves_testcase() {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(8);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        n4.left = n6;
        //System.out.println(sumOfLeftLeaves(n1));
        System.out.println(sumOfLeftLeaves(null));
    }

    //209. Minimum Size Subarray Sum
    /*
        Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
     */
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) return 0;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= s) return 1;
            int sum = nums[i];
            for(int j = i+1; j< nums.length; j++) {
                sum += nums[j];
                if (sum >= s && j-i+1 < min) min = j-i+1 ;
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        if(nums.length == 0) return 0;
        int min = Integer.MAX_VALUE, sum = 0, head = 0, end = 0;
        while (end < nums.length){
            sum += nums[end++];
            while (sum >= s){
                min = Math.min(min, end-head);
                sum -= nums[head++];
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }

    public void minSubArrayLen_testcase(){
        int[] nums = {2,3,1,2,4,3};
        int s = 10;
        System.out.println(minSubArrayLen(s, nums));
    }

    //219. Contains Duplicate II
    /*
        Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            if (i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }

    public void containNearbyDuplicate_testcase(){
        //int[] nums = {1,2};
        //int[] nums = {-1, -1};
        int[] nums = {1,2,1};
        int k = 1;
        System.out.println(containsNearbyDuplicate(nums, k));
    }

    //101. Symmetric Tree
    /*
        Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     */
    public boolean isSymmetric(TreeNode root) {
        return  false;
    }

    @Override
    public void exec() {
        //myPow_testcase();
        //shuffle_testcase();
        //getPermutation_testcase();
        //maxPoints_testcase();
        //sortColors_testcase();
        //maxSubArray_testcase();
        //hIndex_testcase();
        //sumOfLeftLeaves_testcase();
        //minSubArrayLen_testcase();
        containNearbyDuplicate_testcase();
    }

    public static void main(String[] args) {
        Solution solu = new Solution();
        int[] nums = {-5, -2, -1, 0, 2, 3, 5, 7, 9};
        // System.out.println(solu.threeSum(nums).toString());

        int[] canjump = {5, 2, 6, 2, 0, 0, 2, 5, 2, 1, 6, 0, 0, 0, 0, 0, 1};
        // System.out.println(solu.canJump(canjump));

        // solu.recoverTree_Testcase();
        //solu.fourSum_Testcase();
        //solu.firstUniqeChar_Testcase();
        //solu.wiggleSort_Testcase();
        //solu.totalHammingDistance_Testcase();
        //solu.isValidSudoku_Testcase();
        //solu.combinationSum_Testcase();
        //solu.largestPalindrome_testcase();
        //solu.largestDivisibleSubset_testcase();
        //solu.pascalTriangle_testcase();
        //solu.findKthLargest_testcase();
        //solu.uniquePaths_testcase();
        //solu.rob_testcase();
        solu.testTime(solu);
    }
}
