package leetcode.editor.cn;
//invert-binary-tree
//翻转一棵二叉树。
// 示例：
// 输入：
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// 输出：
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// 备注: 
//这个问题是受到 Max Howell 的 原问题 启发的 ： 
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。 
// Related Topics 树 
// 👍 700 👎 0

public class InvertBinaryTree{
    public static void main(String[] args) {
        Solution solution = new InvertBinaryTree().new Solution();
        
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
    public TreeNode invertTree(TreeNode root) {
        //这道题先考虑，能遍历到每个节点，再考虑将每个节点的左右子树进行交换
        //遍历到的每个节点下方左右子树进行交换，直到遍历的节点为null(遍历到的节点可以是根节点，可以是叶子节点)
        //方法一：按中序遍历
        /*
        if(root==null) return root;

        invertTree(root.left);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left); //这里的root.left相当于中序遍历中的右子树，因为右子树在上面已经交换到root.left了

        return root;*/

        //方法二：按前序遍历
        /*
        if(root==null)
            return root;

        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;

        invertTree(root.left); //除了中序遍历，其他遍历无需考虑交换了左右子树之后继续遍历的是左还是右，因为都会遍历到的，这里可以不分先后
        invertTree(root.right);

        return root;*/

        //方法三：按后序遍历
        /*
        if(root==null)
            return root;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        return root;*/

        //方法四、层序遍历
        if(root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}