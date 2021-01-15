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
    /* 递归（隐式地维护了一个栈）
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

    //迭代（显式地将这个栈模拟出来）：方法一：
    //从根节点开始遍历，一直遍历根节点的左子树，将遍历到的所有节点值添加到list，将遍历到的所有节点入栈，直到节点为null
    //之后弹出栈顶节点root
    //由于root的左子树确定为null，因此之后开始遍历root.right
    /*public List<Integer> preorderTraversal(TreeNode root) {
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
    }*/

    //方法二：颜色标记法，1代表第一次访问，2代表第二次访问
    //      完全利用栈的先进后出特点，将树的节点先全部按 右，左，根 进栈，那么出栈自然是 根，左，右；
    /*public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Map<TreeNode,Integer> map= new HashMap<TreeNode,Integer>();

        stack.push(root);
        map.put(root,1);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop(); //弹栈

            if(map.get(node) == 1){ //第一次访问的节点，将其左右子树入栈
                if(node.right != null){
                    stack.push(node.right); //右节点进栈
                    map.put(node.right,1);
                }

                if(node.left != null){
                    stack.push(node.left); //左节点进栈
                    map.put(node.left,1);
                }

                stack.push(node); //节点进栈
                map.put(node,2);

            }else{ //第二次访问的节点，直接输出
                list.add(node.val);
            }
        }
        return list;
    }*/

    //方法三：不是真正意义上的遍历，只是用数据结构恢复了这个前序遍历次序，并未真正对内存进行前序遍历；
    // 后序遍历即是前序遍历的翻转，它们都可以通过 LinkedList->栈，push存储节点，pop遍历节点
    //                               或 LinkedList->双端队列，addFirst存储节点，removeFirst遍历节点
    // 区别一，在添加到stack时，都是先添加stack弹出的栈顶元素，然后前序遍历是先遍历右子树入栈，再遍历左子树入栈；后序遍历是先遍历左子树入栈，再遍历右子树入栈
    // 区别二，在添加到list时，前序遍历是list往后加元素addLast；后序遍历是往前加元素addFirst；注意list的类型为LinkedList，因为要用addLast
    public List<Integer> preorderTraversal(TreeNode root){
        LinkedList<Integer> list = new LinkedList<>();
        if(root == null)
            return list;

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);//stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.removeFirst();//TreeNode node = stack.pop();
            list.addLast(node.val);

            if(node.right != null){
                stack.addFirst(node.right);//stack.push(node.right);
            }
            if(node.left != null){
                stack.addFirst(node.left);//stack.push(node.left);
            }

        }
        return list;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}