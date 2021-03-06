package _08_红黑树;

import _06_二叉搜索树.Tree.BST;

import java.util.Comparator;

public class RBTree<E> extends BST<E> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public RBTree(){
        this(null);
    }

    public RBTree(Comparator<E> comparator){
        super(comparator);
    }

    //辅助函数
    private TreeNode<E> color(TreeNode<E> node , boolean color){ //染色，可以返回void，这里选择返回一个TreeNode节点
        if(node == null)
            return node; //节点为空，不染色
        ((RBTreeNode<E>)node).color = color;
        return node;
    }
    private TreeNode<E> red(TreeNode<E> node){ //把节点染红
        return color(node,RED);
    }
    private TreeNode<E> black(TreeNode<E> node){ //把节点染黑
        return color(node,BLACK);
    }
    private boolean colorOf(TreeNode<E> node){ //判断节点的颜色
        return node == null ? BLACK : ((RBTreeNode<E>)node).color;
    }
    private boolean isRed(TreeNode<E> node){ //判断节点是否是红色
        return colorOf(node) == RED;
    }
    private boolean isBLACK(TreeNode<E> node){ //判断节点是否是黑色
        return colorOf(node) == BLACK;
    }

    private static class RBTreeNode<E> extends TreeNode<E>{

        boolean color;

        public RBTreeNode(E element, TreeNode<E> parent) {
            super(element, parent);
        }
    }



}
