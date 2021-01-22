package leetcode.editor.cn;
//maximum-width-of-binary-tree
//给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节
//点为空。 
//
// 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。 
//
// 示例 1: 
//
// 
//输入: 
//
//           1
//         /   \
//        3     2
//       / \     \  
//      5   3     9 
//
//输出: 4
//解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
// 
//
// 示例 2: 
//
// 
//输入: 
//
//          1
//         /  
//        3    
//       / \       
//      5   3     
//
//输出: 2
//解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
// 
//
// 示例 3: 
//
// 
//输入: 
//
//          1
//         / \
//        3   2 
//       /        
//      5      
//
//输出: 2
//解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
// 
//
// 示例 4: 
//
// 
//输入: 
//
//          1
//         / \
//        3   2
//       /     \  
//      5       9 
//     /         \
//    6           7
//输出: 8
//解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
// 
//
// 注意: 答案在32位有符号整数的表示范围内。 
// Related Topics 树 
// 👍 187 👎 0

public class MaximumWidthOfBinaryTree{
    public static void main(String[] args) {
        Solution solution = new MaximumWidthOfBinaryTree().new Solution();
        
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
    //层宽 = 每一层的最右侧编号 - 最左侧编号 + 1（无论编号从0还是1开始）
    //将节点的val，替换为满二叉树中节点对应的编号，根节点以编号0开始
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        Deque<TreeNode> deque = new LinkedList<>();
        root.val = 0; //根节点的编号设为0，直接赋给val
        deque.add(root);

        int mSize = 0;
        int size = 0;
        while(!deque.isEmpty()){
            size = deque.size();
            mSize = Math.max(mSize , deque.getLast().val - deque.getFirst().val + 1); //层宽
            while(size > 0){ //开始遍历这一层节点，并将下一层节点入队
                TreeNode node = deque.remove();
                if(node.left != null){
                    node.left.val = node.val*2+1; //入队前修改val，val = 满二叉树中的节点编号
                    deque.add(node.left);
                }
                if(node.right != null){
                    node.right.val = node.val*2+2;
                    deque.add(node.right);
                }
                size--;
            }
        }
        return mSize;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}