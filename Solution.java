import java.util.*;

/**
 * Created by yuanxm on 1/19/17.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}


public class Solution {

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
    public List<List<Integer>> combinationSum(Integer[] candidates, int target){
        List<List<Integer>> result = new ArrayList<>();
        if(target > 0) Arrays.sort(candidates);
        else if(target < 0) target = -target;

        if (candidates[0] > target ) return result;
        int i = candidates.length;
        while (candidates[--i] > target);
        Integer[] subCandidates = Arrays.copyOfRange(candidates,0,i);
        for( int j = i ; j >=0 ; j--){
            if(target % candidates[j] == 0 ){
                Integer [] array = new Integer[target/candidates[j]];
                for(int n = 0; n < array.length; n++) array[n] = target%candidates[j];
                result.add(Arrays.asList(array));
                List<List<Integer>> solve = new ArrayList<>();
                solve = combinationSum(subCandidates, -target);
            }
        }

        //result.add(combinationSum());
        return result;
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
        solu.isValidSudoku_Testcase();
    }
}
