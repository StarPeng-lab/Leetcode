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

    @Override
    protected void afterAdd(TreeNode node) { //利用这种重写的方法，既不影响原本的BST，也可以实现AVL树的自身特点
        super.afterAdd(node);
    }
}
