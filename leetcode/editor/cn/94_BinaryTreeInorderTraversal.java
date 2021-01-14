package leetcode.editor.cn;
//binary-tree-inorder-traversal
//给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
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
//输出：[2,1]
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
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 822 👎 0

public class BinaryTreeInorderTraversal{
    public static void main(String[] args) {
        Solution solution = new BinaryTreeInorderTraversal().new Solution();
        
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
   /* 方法一：递归
   public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        inOrder(root,list);
        return list;
    }
    private void inOrder(TreeNode node , List<Integer> list){
        if(node == null) return;

        inOrder(node.left , list);
        list.add(node.val);
        inOrder(node.right , list);
    }*/

    //方法二：迭代（利用栈）
    /*public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root); //将当前节点压入栈
                root = root.left; //遍历当前节点的左子树，直到节点为null
            }
            root = stack.pop(); //弹出栈顶节点
            list.add(root.val); //将栈顶节点的值添加到list
            root = root.right; //遍历栈顶节点的右子树
        }
        return list;
    }*/

    //方法三：颜色标记法
    /*其核心思想如下：
        使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
        如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
        如果遇到的节点为灰色，则将节点的值输出。

    将其右子节点、自身、左子节点依次入栈原因： 栈是一种 先进后出的结构，那么入栈顺序必须调整为倒序
     * 如果是前序遍历，入栈顺序为 右，左，根；出栈顺序就可以为 根, 左, 右
     * 如果是中序遍历，入栈顺序为 右，根，左；出栈顺序就可以为 左, 根, 右
     * 如果是后序遍历，入栈顺序为 根，右，左；出栈顺序就可以为 左, 右, 根
    */
    /*class ColorNode {
        TreeNode node;
        String color;

        public ColorNode(TreeNode node,String color){
            this.node = node;
            this.color = color;
        }
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();

        List<Integer> list = new ArrayList<>();
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(root,"white"));

        while(!stack.empty()){
            ColorNode cn = stack.pop();

            if(cn.color.equals("white")){
                if(cn.node.right != null)
                    stack.push(new ColorNode(cn.node.right,"white"));
                stack.push(new ColorNode(cn.node,"gray"));
                if(cn.node.left != null)
                    stack.push(new ColorNode(cn.node.left,"white"));
            }else{
                list.add(cn.node.val);
            }
        }

        return list;
    }
*/
    //使用一个颜色或者任何东西，记录节点的访问次数。在中序中，节点第二次访问会被输出。因此使用颜色，
    // 或者hash表来记录节点的访问次数，次数使用hash表来记录
    public List<Integer> inorderTraversal(TreeNode root) {

        HashMap<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        if(root == null)
            return list;

        stack.push(root);
        map.put(root,1);//1表示第一次访问,2表示第二次访问。

        while (!stack.isEmpty())
        {
            TreeNode node = stack.pop(); //弹栈

            if (1==map.get(node)) //如果是第一次访问的话，则将其右子节点，自身，左子结点入栈
            {
                if(node.right!=null) //将右子节点入栈
                {
                    stack.push(node.right);
                    map.put(node.right,1);
                }

                stack.push(node); //将自身入栈，改变访问次数
                map.put(node,2);  //更新访问次数

                if(node.left!=null) //将左子节点入栈
                {
                    stack.push(node.left);
                    map.put(node.left,1);
                }

            }else {     //else表示是第2次访问，就输出
                list.add(node.val);
            }
        }
        return  list;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}