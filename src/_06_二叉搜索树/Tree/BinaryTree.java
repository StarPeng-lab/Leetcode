package _06_二叉搜索树.Tree;

import Tools.Printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

//二叉树
//这里的方法都是二叉树通用的方法；无需在这里实现添加删除方法，因为没有二叉树没有添加的规则
//特定的add,remove,contain,compare逻辑，是要写成二叉搜索数还是红黑树... 都是在BinaryTree的基础上扩展即可
//实现了BinaryTreeInfo接口，可以直接打印出这棵树
public class BinaryTree<E> implements BinaryTreeInfo {

    @Override
    public Object root() { //指明树的根节点
        return root;
    }

    @Override
    public Object left(Object node) { //说明树的左边的内容
        return ((TreeNode<E>)node).left;
    }

    @Override
    public Object right(Object node) { //说明树的右边的内容
        return ((TreeNode<E>)node).right;
    }

    @Override
    public Object string(Object node) { //定义打印内容，这里是打印出 父节点.val 和 节点.val
        TreeNode<E> myNode = (TreeNode<E>)node;
        String parentString = "null";
        if(myNode.parent != null)
            parentString = myNode.parent.val.toString();
        return "[" + parentString + "]_" + myNode.val;
    }

    protected int size; //供同包下的类以及子类使用，修饰符改为protected
    protected TreeNode<E> root;

    protected static class TreeNode<E>{

        E val;
        TreeNode<E> parent;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E element , TreeNode<E> parent){
            this.val = element;
            this.parent = parent;
        }

        public boolean isLeaf(){
            return left == null && right == null;

        }

        public boolean hasTwoChildren(){
            return left != null && right != null;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void clear(){
        root = null;
        size = 0;
    }

    @Override
    public String toString() { //一般采用前序遍历打印出树状结构
        StringBuilder sb = new StringBuilder("");
        toString(root,sb,"");
        return sb.toString();
    }
    private void toString(TreeNode<E> node, StringBuilder sb, String prefix){ //前序遍历
        if(node == null) return;

        sb.append(prefix).append(node.val).append("\n"); //拼接 前缀 节点值 换行符
        toString(node.left , sb , prefix+"L---"); //开始遍历左子树，并将前面拼接好的值sb传入，继续传入前缀 prefix+"L---"
        toString(node.right , sb , prefix+"R---");
    }


    //判断树的高度（即判断 根节点root的高度）
    public int heigth(){
        return nodeHeight(root);
    }
    private int nodeHeight(TreeNode<E> node){ //计算节点的高度
        if(node == null)
            return 0 ;
        return 1 + Math.max(nodeHeight(node.left) , nodeHeight(node.right)); //当前节点所在的一层 + 左子树和右子树中的层数最大值
    }


    //是否是完全二叉树，利用队列，用层序遍历的方式遍历节点
    public boolean isComplete(){
        if(root == null)
            return false;

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;

        while(!queue.isEmpty()){
            TreeNode<E> node = queue.poll();

            if(leaf && !node.isLeaf())
                return false;

            /*if(node.left != null && node.right != null){ //1、如果节点有左右子树，那么将左右子树入队
                queue.offer(node.left);
                queue.offer(node.right);
            }else if(node.left == null && node.right != null){ //2、如果节点只有右子树，则说明不是完全二叉树
                return false;
            }else{ //3、其他情况：节点有左子树，但没有右子树；或节点没有左右子树；这两种情况说明之后遍历的节点都必须是叶子节点，否则就不是完全二叉树
                leaf = true;
                if(node.left != null){
                    queue.offer(node.left);
                }
            }*/
            //另一种方式：这个方法可以减少重复判断
            // 建议先把层序遍历的框架写出来，保证每个节点都能入队；再进行具体操作
            if(node.left != null){
                queue.offer(node.left);
            }else if(node.right != null){ //node.left == null && node.right != null
                return false;
            }

            if(node.right != null){
                queue.offer(node.right);
            }else{
                //node.left != null && node.right == null ;
                //node.left == null && node.right == null
                leaf = true;
            }

        }
        return true;
    }

    //得到node节点的【前驱节点】
    protected TreeNode<E> predecessor(TreeNode<E> node){
        if(node == null)
            return null;

        TreeNode<E> p = node.left;
        if(p != null){ //node.left != null , node.left.right.right......（前驱节点在左子树中）
            while(p.right != null){
                p = p.right;
            }
            return p;
        }

        while(node.parent != null && node == node.parent.left){ //node.left == null && node.parent != null（从父节点、祖父节点中寻找，前驱节点在父节点的右子树中）
           node = node.parent;
        }

        //node = node.parent.right --> 前驱节点为node.parent
        //node.left == null && node.parent == null  --> 没有前驱节点
        return node.parent;
    }

    //得到节点的【后继节点】
    protected TreeNode<E> successor(TreeNode<E> node){
        if(node == null)
            return null;

        TreeNode<E> p = node.right; //node.right != null , node.right.left.left.left......
        if(p != null){
            while(p.left != null){
                p = p.left;
            }
            return p;
        }

        if(node.parent != null && node == node.parent.right){ //node.right == null && node.parent != null（从父节点、祖父节点中寻找，后继节点在父节点的左子树中）
            node = node.parent;
        }

        //node = node.parent = node.parent.left -> 后继节点为node.parent
        //node.right == null && node.parent == null -> 没有后继节点
        return node.parent;
    }


    /*前序遍历*/
    public void preOrderTraversal(){
        preOrderTraversal(root);
    }
    private void preOrderTraversal(TreeNode<E> node){
        if(node == null)
            return;

        System.out.print(node.val+" ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    /*中序遍历，比较逻辑是左小右大，中序遍历的数据按从小到大升序排序；如果比较逻辑为左大右小，那按降序排序*/
    public void inOrderTraversal(){
        inOrderTraversal(root);
    }
    private void inOrderTraversal(TreeNode<E> node){
        if(node == null)
            return;

        inOrderTraversal(node.left);
        System.out.print(node.val+" ");
        inOrderTraversal(node.right);
    }

    /*后序遍历*/
    public void postOrderTraversal(){
        postOrderTraversal(root);
    }
    private void postOrderTraversal(TreeNode<E> node){
        if(node == null)
            return;

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.val+" ");
    }

    /*层序遍历*/
    public void levelOrderTraversal(){
        if(root == null)
            return;

        Queue<TreeNode<E>> queue = new LinkedList<>(); // 用队列存储节点，先根节点入队，之后开始重复：队头元素出队，把队头元素的左右子树入队
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode<E> node = queue.poll();
            System.out.print(node.val+" ");
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }

    //设计一接口，允许外界遍历二叉树元素，即我们放权给外界，允许外界自由支配在遍历二叉树时，对元素的修改
    /*public static interface Visitor<E>{ //一般内部类、内部接口都定义为static
        void visit(E element); // public abstract void visit();
    }*/

    //增强二叉树的遍历，允许到达某个节点后停止遍历：利用visit(E)的返回值，如果返回为true，则终止遍历；java中返回类型为boolean的方法默认返回值为false，因此false代表继续遍历
    //用成员变量stop来接受visit(E)的返回值，由于接口中不允许设置成员变量，因此改为抽象类
    public static abstract class Visitor<E>{
        boolean stop; //stop不能放在抽象类外，这个变量应设置为线程私有；stop不能放在遍历方法中，因为要保持状态唯一；因此在Visitor抽象类中是最好的
        abstract boolean visit(E element);
    }

    //1、前序遍历
    public void preOrder(Visitor<E> visitor){
        if(visitor == null) return;
        preOrder(root,visitor);
    }
    private void preOrder(TreeNode<E> node, Visitor<E> visitor){
        if(node == null || visitor.stop == true) return;

        visitor.stop = visitor.visit(node.val);
        preOrder(node.left,visitor);
        preOrder(node.right,visitor);
    }

    //2、中序遍历
    public void inOrder(Visitor<E> visitor){
        if(visitor == null) return;
        inOrder(root,visitor);
    }
    private void inOrder(TreeNode<E> node, Visitor<E> visitor){
        if(node == null || visitor.stop == true) return; //这里的visitor为true时，方法不再往下遍历节点

        inOrder(node.left,visitor);
        if(visitor.stop == true) //这里的visitor为true时，方法不再打印遍历到的节点，这里再次判断是为了让inOrder(node.left,visitor);中遍历到visit(E)的返回值为true的节点后，不再打印后面的节点
            return;
        visitor.stop = visitor.visit(node.val);
        inOrder(node.right,visitor);
    }

    //3、后序遍历
    public void postOrder(Visitor<E> visitor){
        if(visitor == null) return;
        postOrder(root,visitor);
    }
    private void postOrder(TreeNode<E> node, Visitor<E> visitor){
        if(node == null || visitor.stop == true) return;

        postOrder(node.left,visitor);
        postOrder(node.right,visitor);
        if(visitor.stop == true)
            return;
        visitor.stop = visitor.visit(node.val);
    }

    //4、层序遍历
    public void levelOrder(Visitor<E> visitor){
        if(root == null || visitor == null)
            return;

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode<E> node = queue.poll();

            if(visitor.visit(node.val)) //只要返回值为true，就停止方法，由于层序遍历没有递归，因此直接判断返回值即可
                return;

            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }

    }


}
