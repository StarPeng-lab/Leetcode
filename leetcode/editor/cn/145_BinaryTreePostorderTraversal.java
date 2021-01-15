package leetcode.editor.cn;
//binary-tree-postorder-traversal
//给定一个二叉树，返回它的 后序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 503 👎 0

public class BinaryTreePostorderTraversal{
    public static void main(String[] args) {
        Solution solution = new BinaryTreePostorderTraversal().new Solution();
        
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
    //递归
    /*public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root , list);
        return list;
    }
    private void postOrder(TreeNode node , List<Integer> list){
        if(node == null)
            return;
        postOrder(node.left , list);
        postOrder(node.right , list);
        list.add(node.val);
    }*/

    //迭代：方法一
    //从根节点开始遍历，先遍历根节点的左子树，直到节点root.left == null，把遍历到的节点全部入栈；
    //之后弹出栈顶节点root，
    // * 如果栈顶节点root.right == null，那么直接把root.val入list，即取得了左节点；
    //   # 设prev记录操作的前一个节点，prev=root，root置空；
    //   # 如果栈顶弹出的节点为右节点root，那么由root.right==null进入if条件，右节点入list；此时prev是右子树，prev = root,root=null；
    //   # 在下一轮循环中，弹出的栈顶节点root是根节点，由root.right = prev进入if条件，根节点入list
    // * 如果栈顶节点root.right != null，那么把root重新入栈，并开始遍历右子树，即让root = root.right; 在下一轮循环中遍历root的左子树

    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        Stack<TreeNode> stack = new Stack<>();

        TreeNode prev = null; //记录遍历到的前一个节点
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root); //将当前节点压入栈
                root = root.left; //遍历当前节点的左子树，直到节点为null
            }
            root = stack.pop(); //弹出栈顶节点
            if(root.right == null || root.right == prev){ //root.right == null:将为左子树的叶子节点入list；root.right == prev:将根节点入list
                list.add(root.val); //节点入list
                prev = root; //记录右节点
                root = null; //此时已可判断遍历完毕左子树，并且右子树为null，因此直接设root为null，在下一次循环中，直接跳过while(root!=null)，直接从栈顶弹出节点
            }else{ //root.right != null
                stack.push(root); //将栈顶节点root重新压入栈
                root = root.right; //遍历右节点
            }
        }

        return list;
    }

    //方法二：颜色标记法，white是未访问，gray是已访问；完全利用栈的先进后出特点，将树的节点先全部按根，右，左进栈，那么出栈自然是 左，右，根；
    /*class ColorTree{
        TreeNode node;
        String color;
        public ColorTree(TreeNode node , String color){
            this.node = node;
            this.color = color;
        }
    }
    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList();
        if(root == null)
            return list;

        Stack<ColorTree> stack = new Stack();
        ColorTree croot = new ColorTree(root,"white");
        stack.push(croot);

        while(!stack.isEmpty()){
            ColorTree cnode = stack.pop();
            if(cnode.color.equals("white")){
                stack.push(new ColorTree(cnode.node,"gray")); //根，将根节点重新画颜色为gray，压入栈
                if(cnode.node.right != null){
                    stack.push(new ColorTree(cnode.node.right,"white")); //右节点
                }
                if(cnode.node.left != null){
                    stack.push(new ColorTree(cnode.node.left,"white")); //左节点
                }
            }else{
                list.add(cnode.node.val);
            }
        }
        return list;
    }*/

    //方法三：
    /*public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> set = new HashSet<>(); //用于记录为左子树的叶子节点，相当于方法一的prev
        while(!stack.isEmpty() || root != null){
            if(root == null && set.contains(stack.peek())){
                list.add(stack.pop().val);
            }else if(root == null){
                set.add(stack.peek());
                root = stack.peek().right;
            }else{
                stack.push(root);
                root = root.left;
            }
        }
        return list;
    }*/

    /**
     * 在做迭代版本之前，先思考各类“遍历”算法的本质是什么？是最后输出的那一串有序的数字吗？数字的顺序是对的，遍历算法就是对的吗？
     *      答案：否。“遍历”的本质是对内存的有序访问，失去了访问顺序，即便你用各种数据结构恢复了这个次序，遍历本身也显得毫无意义。
     * 如同单词串翻转，String in  = "it is a word" 翻转成：String out = "word a is it"
     *      有一种很优雅的写法是先reverse(in)，然后再逐词翻转——“两次反转”，最后得到的就是正确顺序
     * 回到二叉树后序遍历，也可以利用这种思想，利用双向链表的addFirst，对外部次序和内含次序进行同时翻转，同样会得到一种非常”优雅”的解法，结构简单明晰，甚至还有点好背（狗头）。
     * 但是，它并没有真正实现“遍历”——仔细看会发现，该算法其实在使用一种异构的前序遍历：“中->右->左”，而非传统意义上的“中->左->右”，而这种异构也正是它的第一次反转。而第二次反转就在输出序列上。
     * 所以本质上，这是一个“前序遍历”，而不是所谓的“后序遍历”。只有当你的各个节点以“左->右->中”的次序依次出现在迭代的loop当中时，它才是真正的后序遍历
     */
    //方法四：后序遍历即是前序遍历的翻转，前序遍历是往后加元素；后序遍历是往前加元素
    //       要么LinkedList+栈：利用LinkedList的addFirst存储节点（即类似reverse反转 栈中出来的节点）
    //       要么LinkedList+LinkedList，利用LinkedList的addFirst存储节点，利用LinkedList的addFirst,removeFirst遍历节点
    /*public List<Integer> postorderTraversal(TreeNode root){
        LinkedList<Integer> list = new LinkedList<>(); //由于要用到LinkedList类的addFirst()，因此这里不用List
        if(root == null)
            return list;

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root); //stack.addFirst(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop(); //TreeNode node = stack.removeFirst();
            list.addFirst(node.val);

            if(node.left != null){
                stack.push(node.left); //stack.addFirst(node.left);
            }
            if(node.right != null){
                stack.push(node.right); //stack.addFirst(node.right);
            }
        }
        return list;

    }*/

}
//leetcode submit region end(Prohibit modification and deletion)

}