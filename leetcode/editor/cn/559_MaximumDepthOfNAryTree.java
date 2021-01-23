package leetcode.editor.cn;
//maximum-depth-of-n-ary-tree
//给定一个 N 叉树，找到其最大深度。 
//
// 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。 
//
// N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,null,3,2,4,null,5,6]
//输出：3
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
//null,13,null,null,14]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树的深度不会超过 1000 。 
// 树的节点数目位于 [0, 104] 之间。 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 144 👎 0

public class MaximumDepthOfNAryTree{
    public static void main(String[] args) {
        Solution solution = new MaximumDepthOfNAryTree().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    //递归（深度优先搜索）方法一：
    public int maxDepth(Node root) {
        if (null == root) {
            return 0;
        }

        int depth = 0;
        for (Node child : root.children) {
            depth = Math.max(depth, maxDepth(child));
        }
        return depth + 1;
        /*int depth = 1;
        for (Node child : root.children) {
            depth = Math.max(depth, 1 + maxDepth(child));
        }
        return depth;*/
    }

    //递归，方法二：
    /*public int maxDepth(Node root){
        if (root == null) {
            return 0;
        } else if (root.children.isEmpty()) {
            return 1;
        } else {
            List<Integer> heights = new LinkedList<>();
            for (Node item : root.children) {
                heights.add(maxDepth(item));
            }
            return Collections.max(heights) + 1;
        }
    }*/

    //迭代：方法一
    //在堆栈的帮助下将上面的递归转换为迭代。
    //思路是是使用深度优先搜索策略访问每个节点，同时更新每次访问时的最大深度。
    //所以可以从包含根节点的、对应深度为 1 的栈开始。
    //然后继续迭代，从栈中弹出当前节点并将子节点压入栈中，每次都更新对应深度。
   /* public int maxDepth(Node root) {
        Queue<Pair<Node, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<Node, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                for (Node c : root.children) {
                    stack.add(new Pair(c, current_depth + 1));
                }
            }
        }
        return depth;
    }*/

    //迭代：方法二
    /*public int maxDepth(Node root) {
        if(root == null)
            return 0;
        int depth = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0 ; i<size ; i++){
                Node node = queue.remove();
                for(Node tmp : node.children){
                    queue.add(tmp);
                }
            }
            depth++;
        }
        return depth;
    }*/
}
//leetcode submit region end(Prohibit modification and deletion)

}