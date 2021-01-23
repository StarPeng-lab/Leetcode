package leetcode.editor.cn;
//maximum-depth-of-binary-tree
//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 递归 
// 👍 776 👎 0

public class MaximumDepthOfBinaryTree{
    public static void main(String[] args) {
        Solution solution = new MaximumDepthOfBinaryTree().new Solution();
        
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
    //方法一：DFS + 递归
    //找出终止条件：当前节点为空
    public int maxDepth(TreeNode root){
        if(root == null)
            return 0;

        int leftH = maxDepth(root.left); //左
        int rightH = maxDepth(root.right); //右
        return Math.max(leftH,rightH) + 1; //中 ； 遍历到最后一层，0+1=1，记作最后一层高度为1，往回递归，开始计算深度
    }

    //方法二：广度优先搜索 + 队列
    //      使用的队列里存放的是「当前层的所有节点」，这里需要做一些改动：
    //      每次拓展下一层的时候，不同于广度优先搜索的每次只从队列里拿出一个节点，我们需要将队列里的所有节点都拿出来进行拓展，
    //      这样能保证每次拓展完的时候队列里存放的是当前层的所有节点，即我们是一层一层地进行拓展，拓展的次数即为深度
    //      用size记录每层的节点数，depth记录层数
    /*public int maxDepth(TreeNode root) {
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null)
            queue.add(root);

        int size = queue.size();
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            size--;

            if(node.right != null)
                queue.offer(node.right);
            if(node.left != null)
                queue.offer(node.left);

            if(size == 0){
                size = queue.size();
                depth++;
            }
        }
        return depth;
    }*/

    //方法三：直接用for循环，把一层的节点遍历掉，再进入下一层
    /*public int maxDepth(TreeNode root) {
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null)
            queue.add(root);

        while(!queue.isEmpty()){
            depth++;
            int size = queue.size();
            for(int i=0 ; i<size ; i++){
                TreeNode node = queue.poll();

                if(node.right != null)
                    queue.offer(node.right);
                if(node.left != null)
                    queue.offer(node.left);
            }
        }
        return depth;
    }*/
}
//leetcode submit region end(Prohibit modification and deletion)

}