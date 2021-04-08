package hundred1;

import util.TreeNode;

import java.util.*;

public class _103_BinaryTreeZigzagLevelOrderTraversal {

    /**
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right,
     * then right to left for the next level and alternate between).
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its zigzag level order traversal as:
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     */

    public static List<List<Integer>> zigzagLevelOrder(final TreeNode root) {
        if (root==null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> nodes = new LinkedList<>();
        Deque<TreeNode> tempNodes = new LinkedList<>();
        nodes.add(root);
        List<Integer> list = new ArrayList<>();
        boolean fromLeft = false;      //direction false for left to right & true for right to left;
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pollLast();
            list.add(node.val);
            if (fromLeft) {
                if (node.right!=null) {
                    tempNodes.add(node.right);
                }
                if (node.left!=null) {
                    tempNodes.add(node.left);
                }
            } else {
                if (node.left!=null) {
                    tempNodes.add(node.left);
                }
                if (node.right!=null) {
                    tempNodes.add(node.right);
                }
            }
            if (nodes.isEmpty() && !tempNodes.isEmpty()) {
                nodes = tempNodes;
                tempNodes = new LinkedList<>();
                fromLeft = !fromLeft;
                result.add(list);
                list = new ArrayList<>();
            } else if (nodes.isEmpty() && tempNodes.isEmpty()) {
                result.add(list);
            }
        }
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

        TreeNode root = new TreeNode(0);
        TreeNode node1  = new TreeNode(1);      root.left = node1;
        TreeNode node2  = new TreeNode(2);      root.right = node2;
        TreeNode node3  = new TreeNode(3);      node1.left = node3;
        TreeNode node4 = new TreeNode(4);       node1.right = node4;
        TreeNode node5  = new TreeNode(5);      node2.right = node5;
        TreeNode node6  = new TreeNode(6);      node3.left = node6;
        TreeNode node7  = new TreeNode(7);      node5.left = node7;
        TreeNode node8  = new TreeNode(8);      node5.right = node8;
        TreeNode node9  = new TreeNode(9);      node6.left = node9;
        TreeNode node10  = new TreeNode(10);    node6.right = node10;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root =  setUp();
        List<List<Integer>> result = zigzagLevelOrder(root);
        //result.forEach( l-> System.out.println(l));

    }

}
