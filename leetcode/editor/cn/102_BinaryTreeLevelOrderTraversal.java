package leetcode.editor.cn;
//binary-tree-level-order-traversal
//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层序遍历结果： 
//
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 
// 👍 744 👎 0

public class BinaryTreeLevelOrderTraversal{
    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
        
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
    //广度优先搜索BFS：方法一：套用层序遍历的BFS框架进行编写，利用levelSize记录每一层的节点数
    /*public List<List<Integer>> levelOrder(TreeNode root) {
        List<Integer> innerList = new ArrayList<>();
        List<List<Integer>> outerList = new ArrayList<>();
        if(root == null)
            return outerList;

        Queue<TreeNode> queue = new LinkedList<>(); //队列，先进先出
        queue.offer(root);

        int levelSize = 1; //记录每层的节点数，从第一层算起，第一层只有一个根节点
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            innerList.add(node.val);
            levelSize--;

            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }

            if(levelSize == 0){ //说明下一次循环要开始遍历下一层节点
                levelSize = queue.size();
                outerList.add(innerList);
                innerList = new LinkedList<>(); //innerList.clear(),本质上还是同一个innerList，然而outerList的元素是不同的innerList
            }
        }
        return outerList;
    }*/

    //广度优先搜索BFS：方法二：套用层序遍历的BFS框架进行编写，利用queue.size()记录每一层的节点数，for循环来遍历这一层的节点数
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> outerList = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root != null)
            queue.add(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> innerList = new ArrayList<>();

            for(int i=0 ; i<levelSize ; i++){
                TreeNode node = queue.poll();
                innerList.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }

            outerList.add(innerList);
        }
        return outerList;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}