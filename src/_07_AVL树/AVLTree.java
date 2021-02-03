package _07_AVL树;

import _06_二叉搜索树.Tree.BST;

import java.util.Comparator;

public class AVLTree<E> extends BST {

    //虽然继承了BST的所有非私有成员，但是要用到比较器，还是需要重写AVLTree的构造方法
    public AVLTree(){
        this(null);
    }

    public AVLTree(Comparator<E> comparator){
        super(comparator);
    }

    private static class AVLTreeNode<E> extends TreeNode<E>{
        int height;

        public AVLTreeNode(E element, TreeNode<E> parent) {
            super(element, parent);
        }

        public int balanceFactor(){
            int leftHeight = left == null ? 0 : ((AVLTreeNode<E>)left).height; //由于left在TreeNode类中，TreeNode类中没有height，因此需要将left转换为AVLTreeNode类型的节点
            int rightHeight = right == null ? 0 : ((AVLTreeNode<E>)right).height;
            return leftHeight - rightHeight;
        }
    }

    //节点是否平衡
    private boolean isBalanced(TreeNode<E> node){
        return false;
    }

    @Override
    protected void afterAdd(TreeNode node) { //利用这种重写的方法，既不影响原本的BST，也可以实现AVL树的自身特点
        super.afterAdd(node);
        while((node = node.parent) != null){

        }
    }

    @Override
    protected TreeNode createNode(Object element, TreeNode parent) {
        return new AVLTreeNode(element, parent);
    }
}
