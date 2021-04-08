package hundred1;

import util.TreeNode;

import java.util.Arrays;

public class _108_ConvertSortedArraytoBinarySearchTree {


    /**
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
     * subtrees of every node never differ by more than 1.
     *
     * Example:
     *
     * Given the sorted array: [-10,-3,0,5,9],
     *
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     */


     public static TreeNode sortedArrayToBST(int[] nums) {
         if (nums==null||nums.length==0) {
             return null;
         } else if (nums.length==1) {
             return new TreeNode(nums[0]);
         } else if (nums.length==2) {
             TreeNode root = new TreeNode(nums[0]);
             root.right = new TreeNode(nums[1]);
             return root;
         } else if (nums.length == 3) {
             TreeNode root = new TreeNode(nums[1]);
             root.left = new TreeNode(nums[0]);
             root.right = new TreeNode(nums[2]);
         }
         TreeNode root = new TreeNode(nums[nums.length/2]);
         root.left = sortedArrayToBST(Arrays.copyOfRange(nums,0,nums.length/2));
         root.right = sortedArrayToBST(Arrays.copyOfRange(nums,nums.length/2+1, nums.length));
         return root;
     }

    public static void main(String[] args) {
        int[] nums = new int[]{-10,-3,0,5,9};
        nums = new int[] {-10,-3,0,5,9,10, 11, 13};
        TreeNode root = sortedArrayToBST(nums);
        root.printLDR();
        //root.printLRD();
        //root.printDLR();
    }
}
