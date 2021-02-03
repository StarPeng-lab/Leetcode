package _07_AVL树;

import _06_二叉搜索树.Tree.BST;

import java.util.Comparator;

public class AVLTree<E> extends BST<E> {

    //虽然继承了BST的所有非私有成员，但是要用到比较器，还是需要重写AVLTree的构造方法
    public AVLTree(){
        this(null);
    }

    public AVLTree(Comparator<E> comparator){
        super(comparator);
    }

    private static class AVLTreeNode<E> extends TreeNode<E>{
        int height = 1; //叶子节点的高度为1

        public AVLTreeNode(E element, TreeNode<E> parent) {
            super(element, parent);
        }

        public int balanceFactor(){ //计算平衡因子
            int leftHeight = left == null ? 0 : ((AVLTreeNode<E>)left).height; //由于left在TreeNode类中，TreeNode类中没有height，因此需要将left转换为AVLTreeNode类型的节点
            int rightHeight = right == null ? 0 : ((AVLTreeNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight(){ //变为非叶子节点后，更新维护其高度（不需要用递归，在afterAdd的循环找父节点的过程中同时调用此方法，不断更新父节点高度即可，因为添加节点后，叶子节点变为了 所添加节点 的父节点）
            int leftHeight = left == null ? 0 : ((AVLTreeNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLTreeNode<E>)right).height;
            height =  1 + Math.max(leftHeight, rightHeight); //节点高度 = 1 + 左右子节点的高度的最大值
        }
    }

    @Override
    protected TreeNode createNode(E element, TreeNode<E> parent) {
        return new AVLTreeNode(element, parent);
    }

    @Override
    protected void afterAdd(TreeNode<E> node) { //利用这种重写的方法，既不影响原本的BST，也可以实现AVL树的自身特点
        while((node = node.parent) != null){ //这里用到的是BinaryTree的内部类TreeNode的成员变量，将parent访问修饰符改为 public 才可以访问
            if(isBalanced(node)){ //如果节点平衡，那么更新维护节点的高度（叶子节点高度默认为1，来到while循环中，说明添加节点的父节点已经不是叶子节点，更新其父节点的高度，从下往上一层层更新上去，父节点~祖父节点）
                updateHeight(node);
            }else{ //节点不平衡，调整回平衡状态
                rebalance(node); //此时找到的node，是所有失衡节点中高度最低的那个祖先节点
                break; //整棵树已经恢复平衡了，可以跳出循环
            }
        }
    }

    //节点是否平衡，左子节点高度-右子节点高度，值为：-1,0,1，都为平衡状态
    private boolean isBalanced(TreeNode<E> node){
        return Math.abs( ( (AVLTreeNode)node ).balanceFactor() )<=1;
    }

    //更新维护节点的高度，另外创建一个方法，是为了封装强制类型转换的过程
    private void updateHeight(TreeNode<E> node){
        ((AVLTreeNode<E>)node).updateHeight();
    }

    //恢复平衡，grand是高度最低的那个不平衡节点
    private void rebalance(TreeNode<E> grand){

    }



}
