import util.TreeNode;


public class BinaryTreeMaximumPathSum124 {

    /**
     * Given a non-empty binary tree, find the maximum path sum.
     *
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
     * along the parent-child connections. The path must contain at least one node and does not need to go through
     * the root.
     *
     * Example 1:
     *
     * Input: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * Output: 6
     * Example 2:
     *
     * Input: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * Output: 42
     */


    public static int maxPathSum(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};
        Integer result = LRD(root, maxSum);
        return result >= maxSum[0]? result: maxSum[0];
    }

    public static Integer LRD(TreeNode root, int[] maxSum) {
        if (root==null) return null;
        if (root.left == null && root.right == null) return root.val;
        Integer left = LRD(root.left, maxSum);      //左边的最大和
        Integer right = LRD(root.right, maxSum);    //右边的最大和
        int v = left==null?right:right==null?left:Math.max(left,right);     //左右的最大和
        int result = v + root.val;    //返回的结果
        int s = (left==null?0:left) + (right==null?0:right) + root.val;     //左中右的和
        maxSum[0] = Math.max(maxSum[0], result);
        maxSum[0] = Math.max(maxSum[0], v);
        maxSum[0] = Math.max(maxSum[0], s);
        maxSum[0] = Math.max(maxSum[0], root.val);
        return result;
    }


    public static TreeNode setUp() {
        /**
         *                  0
         *                 / \
         *                1   2
         *               / \   \
         *              3  4    5
         *             /       / \
         *            6       7   8
         *           / \
         *          9   10
         */

        TreeNode root = new TreeNode(-1);
        TreeNode node1  = new TreeNode(5);      root.left = node1;
//        TreeNode node2  = new TreeNode(-3);      root.right = node2;
        TreeNode node3  = new TreeNode(4);      node1.left = node3;
        TreeNode node4 = new TreeNode(3);       node1.right = node4;
//        TreeNode node5  = new TreeNode(-2);      node2.right = node5;
        TreeNode node6  = new TreeNode(-1);      node3.left = node6;
//        TreeNode node7  = new TreeNode(7);      node5.left = node7;
//        TreeNode node8  = new TreeNode(8);      node5.right = node8;
//        TreeNode node9  = new TreeNode(9);      node6.left = node9;
//        TreeNode node10  = new TreeNode(10);    node6.right = node10;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = setUp();
        System.out.println(maxPathSum(root));
    }

}
