package hundred1;

import util.TreeNode;

import java.util.Stack;

public class _112_PathSum {

    /**
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the
     * values along the path equals the given sum.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Given the below binary tree and sum = 22,
     *
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \      \
     * 7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     */


    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum-root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode a1 = new TreeNode(4);  root.left = a1;
        TreeNode a2 = new TreeNode(8);  root.right = a2;
        TreeNode b1 = new TreeNode(11); a1.left = b1;
        TreeNode b3 = new TreeNode(13); a2.left = b3;
        TreeNode b4 = new TreeNode(4);  a2.right = b4;
        TreeNode c1 = new TreeNode(7);  b1.left = c1;
        TreeNode c2 = new TreeNode(2);  b1.right = c2;
        TreeNode c8 = new TreeNode(1);  b4.right = c8;

        System.out.println(hasPathSum(root, 22));
    }
}
