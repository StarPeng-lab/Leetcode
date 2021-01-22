package leetcode.editor.cn;
//binary-tree-level-order-traversal-ii
//给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历） 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其自底向上的层序遍历为： 
//
// 
//[
//  [15,7],
//  [9,20],
//  [3]
//]
// 
// Related Topics 树 广度优先搜索 
// 👍 394 👎 0

public class BinaryTreeLevelOrderTraversalIi{
    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversalIi().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> outerList = new LinkedList<>();
        if(root == null)
            return outerList;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int size = queue.size();
        LinkedList<Integer> innerList = new LinkedList<>();

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            innerList.add(node.val);
            size--;

            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);

            if(size == 0){
                size = queue.size();
                //outerList.addFirst(innerList); //改变此处为addFirst即可
                outerList.add(0,innerList);
                innerList = new LinkedList<>();
            }
        }

        return outerList;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}