package leetcode.editor.cn;
//n-ary-tree-preorder-traversal
//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 131 👎 0

public class NAryTreePreorderTraversal{
    public static void main(String[] args) {
        Solution solution = new NAryTreePreorderTraversal().new Solution();
        
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
    //方法一：递归
    public List<Integer> preorder(Node root) {
        List<Integer> list = new LinkedList<>();
        dfs(root,list);
        return list;
    }
    private void dfs(Node node, List<Integer> list){
        if(node == null)
            return;
        list.add(node.val);
        for(Node tmp : node.children){
            dfs(tmp,list);
        }
    }

    //方法二：迭代
    //使用一个栈来帮助我们得到前序遍历，需要保证栈顶的节点就是我们当前遍历到的节点。
    // 我们首先把根节点入栈，因为根节点是前序遍历中的第一个节点。
    // 随后每次我们从栈顶取出一个节点 u，它是我们当前遍历到的节点，并把 u 的所有子节点逆序推入栈中。
    // 例如 u 的子节点从左到右为 v1, v2, v3，那么推入栈的顺序应当为 v3, v2, v1，这样就保证了下一个遍历到的节点（即 u 的第一个子节点 v1）出现在栈顶的位置
    /*public List<Integer> preorder(Node root) {
        LinkedList<Integer> list = new LinkedList<>();
        if(root == null)
            return list;
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node node = stack.pop();
            list.add(node.val);
            Collections.reverse(node.children); //将节点的子节点反转
            for(Node tmp : node.children){
                stack.push(tmp);
            }
        }

        return list;
    }*/

}
//leetcode submit region end(Prohibit modification and deletion)

}