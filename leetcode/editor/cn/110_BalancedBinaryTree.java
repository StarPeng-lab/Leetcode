package leetcode.editor.cn;
//balanced-binary-tree
//给定一个二叉树，判断它是否是高度平衡的二叉树。 
//
// 本题中，一棵高度平衡二叉树定义为： 
//
// 
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,2,3,3,null,null,4,4]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在范围 [0, 5000] 内 
// -104 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 588 👎 0

public class BalancedBinaryTree{
    public static void main(String[] args) {
        Solution solution = new BalancedBinaryTree().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    //方法一：自顶向下递归；不过对于同一个节点，函数height() 会被重复调用，导致时间复杂度较高。
    //具体做法类似于二叉树的前序遍历，即对于当前遍历到的节点，首先计算左右子树的高度，如果左右子树的高度差是否不超过 1，再分别递归地遍历左右子节点，并判断左子树和右子树是否平衡。这是一个自顶向下的递归的过程。
    //注意，在AVLTree.java中，判断平衡时我们只判断root即可，因为在一个一个添加/删除节点时，从最开始就在判断节点是否失衡并及时调整
    //而这道题中，我们不仅要判断root，还要判断root.left，root.right是否失衡，利用递归进行判断
   /* public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        int leftH = root.left == null ? 0 : height(root.left);
        int rightH = root.right == null ? 0 : height(root.right);
        return Math.abs(leftH - rightH) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode root){
        if(root == null)
            return 0;
        return 1 + Math.max( height(root.left) , height(root.right) );
    }
    */

    //方法二：自底向上的递归，则对于每个节点，函数height() 只会被调用一次
    //具体做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 -1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。
    public boolean isBalanced(TreeNode root){
        return height(root) >= 0;
    }

    public int height(TreeNode root){
        if(root == null)
            return 0;
        int leftH = height(root.left);
        int rightH = height(root.right);
        if(leftH == -1 || rightH == -1 || Math.abs(leftH - rightH) > 1){ //直接在递归高度时，判断是否失衡
            return -1;
        }else{
            return Math.max(leftH , rightH) + 1;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}