import util.TreeNode;

/**
 * Created by yuanxm on 1/17/17.
 */

//Definition for a binary tree node.

public class Codec {
    // Encodes a tree to a single string.
    private String SEP = ",";


    public String serialize(TreeNode root) {
        StringBuilder tar = new StringBuilder();
        tar.append(root.val).append(SEP);
        if(root.left != null){
            String left = serialize(root.left);
            tar.append(left);
        }
        if(root.right != null){
            String right = serialize(root.right);
            tar.append(right);
        }
        return tar.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] segs = data.split(SEP);
        TreeNode root = new TreeNode(Integer.parseInt(segs[0]));
        StringBuilder leftsub = new StringBuilder();
        StringBuilder rightsub = new StringBuilder();
        for(int i = 1; i < segs.length; i ++){
            if(Integer.parseInt(segs[i]) > Integer.parseInt(segs[0])){
                rightsub.append(segs[i]).append(SEP);
            }else {
                leftsub.append(segs[i]).append(SEP);
            }
        }
        if (leftsub.toString().length()>0) {
            TreeNode left = deserialize(leftsub.toString());
            root.left = left;
        }
        if (rightsub.toString().length()> 0) {
            TreeNode right = deserialize(rightsub.toString());
            root.right = right;
        }
        return root;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(6);
        TreeNode left1 = new TreeNode(4);
        TreeNode left2 = new TreeNode(3);
        TreeNode right1 = new TreeNode(5);
        TreeNode left3 = new TreeNode(2);
        TreeNode right2 = new TreeNode(9);
        TreeNode right3 = new TreeNode(10);
        TreeNode left4 = new TreeNode(7);
        root.left = left1;
        root.right = right2;
        left1.left = left2;
        left2.left = left3;
        left1.right = right1;
        right2.right = right3;
        right2.left = left4;

        Codec codec = new Codec();
        String data = codec.serialize(root);
        System.out.println(codec.serialize(root));

        TreeNode root2 = codec.deserialize(data);
        System.out.println(root2.right.right.val);

    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));