package leetcode.editor.cn;
//binary-tree-preorder-traversal
//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 489 👎 0

public class BinaryTreePreorderTraversal{
    public static void main(String[] args) {
        Solution solution = new BinaryTreePreorderTraversal().new Solution();
        
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
    /* 方法一：递归（隐式地维护了一个栈）
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList();

        preOrder(root , list);

        return list;
    }
    private void preOrder(TreeNode node, List<Integer> list){
        if(node == null)
            return;
        list.add(node.val);
        preOrder(node.left , list);
        preOrder(node.right , list);
    }*/

    //迭代（显式地将这个栈模拟出来）
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList();

        if(root == null)
            return list;

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null){
            while(node != null){
                list.add(node.val); //先把当前节点的值放入list
                stack.push(node); //把当前节点压入栈
                node = node.left; //遍历左子树，直到node == null 退出循环
            }
            node = stack.pop(); //把栈顶的节点弹出
            node = node.right; //遍历此节点的右子树
        }

        return list;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}