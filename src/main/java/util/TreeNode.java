package util;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    private enum VISIT {
        DLR,LDR,LRD
    }
    //先序输出
    public  void printDLR() {
        print(this, VISIT.DLR);
    }
    //中序输出
    public void printLDR() {
        print(this, VISIT.LDR);
    }
    //后序输出
    public void printLRD() {
        print(this, VISIT.LRD);
    }

    private void print(TreeNode node, VISIT visit) {
        if (node == null) return;
        switch (visit) {
            case DLR:
                System.out.print(node.val+",");
                print(node.left,visit);
                print(node.right,visit);
                break;
            case LDR:
                print(node.left, visit);
                System.out.print(node.val+",");
                print(node.right, visit);
                break;
            case LRD:
                print(node.left, visit);
                print(node.right, visit);
                System.out.print(node.val+",");
                break;
        }
    }

    public TreeNode parseTreeLDR(Integer[] nums) {
        if (nums == null || nums.length == 0) return null;
        TreeNode root = new TreeNode(nums[0]);
        return root;
    }

    private TreeNode parseTree(TreeNode root, Integer[] nums, int pos, VISIT visit) {
        switch (visit) {
            case LDR:
                if (nums[pos++]!= null) {
                    root.left = new TreeNode(nums[pos-1]);
                }
                if (nums[pos++]!= null) {
                    root.right = new TreeNode(nums[pos-1]);
                }
                if (root.left!= null) {
                    parseTree(root.left, nums, pos, visit);
                }
                if (root.right != null){
                    parseTree(root.right, nums, pos, visit);
                }
        }
        return root;
    }

}
