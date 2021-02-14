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

    @Override
    public Object string(Object node) { //重写了BinaryTreeInfo接口的方法，定义打印内容，这里是打印出 直接打印node.toString()
        return node;
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

        public TreeNode<E> tallerNode(){ //找到节点的左右子节点中高度最高的那个
            int leftHeight = left == null ? 0 : ((AVLTreeNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLTreeNode<E>)right).height;

            if(leftHeight < rightHeight)
                return right;
            if(leftHeight > rightHeight)
                return left;
            return isLeftChild() ? left : right; //如果节点的左右子树高度相等，那么若节点是其父节点的左子节点，就返回节点的左子节点，即返回同方向的子节点

        }

        @Override
        public String toString(){
            String parentString = "null";
            if(parent != null)
                parentString = parent.val.toString();
            return "[" + parentString + "]_" + val + "_[h:" + height + "]";
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
                rebalance1(node); //此时找到的node，是所有失衡节点中高度最低的那个祖先节点；将node调整平衡后，整棵树即可平衡
                break; //整棵树已经恢复平衡了，可以跳出循环
            }
        }
    }

    @Override
    protected void afterRemove(TreeNode<E> node) {
        while((node = node.parent) != null){
            if(isBalanced(node)){
                updateHeight(node);
            }else{
                rebalance2(node); //由于node的父节点、祖父节点都可能不平衡，因此这里不用break，而是继续while向上找是否有失衡的节点
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

    //方式一：单选分左旋和右旋，双旋是单旋的结合
    // 恢复平衡，grand是高度最低的那个不平衡节点
    private void rebalance1(TreeNode<E> grand){
        TreeNode<E> parent = ((AVLTreeNode<E>)grand).tallerNode(); //grand的左右子节点中高度最高的节点
        TreeNode<E> node = ((AVLTreeNode<E>)parent).tallerNode(); //parent的左右子节点中高度最高的节点
        //判断旋转方向
        if(parent.isLeftChild()){ //L
            if(node.isLeftChild()){ //LL
                rotateRight(grand);
            }else{ // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else{ //R
            if(node.isLeftChild()){ //RL
                rotateRight(parent);
                rotateLeft(grand);
            }else{ //RR
                rotateLeft(grand);
            }

        }
    }

    private void rotateLeft(TreeNode<E> grand){ //左旋
        TreeNode<E> parent = grand.right;
        TreeNode<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand,parent,child);
    }

    private void rotateRight(TreeNode<E> grand){ //右旋
        TreeNode<E> parent = grand.left;
        TreeNode<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand,parent,child);
    }

    private void afterRotate(TreeNode<E> grand , TreeNode<E> parent , TreeNode<E> child){
        //让parent成为子树的根节点（将g的父节点改为p的父节点；将g的父节点的子节点改为p）
        parent.parent = grand.parent;
        if(grand.isLeftChild()){
            grand.parent.left = parent;
        }else if(grand.isRightChild()){
            grand.parent.right = parent;
        }else{ //grand为根节点
            root = parent;
        }

        //更新child节点的父节点
        if(child != null){
            child.parent = grand;
        }

        //更新grand节点的父节点
        grand.parent = parent;

        //更新节点的高度（先更新下面的，再更新上面的）
        updateHeight(grand);
        updateHeight(parent);
    }

    //方式二：统一，无论是单旋还是双旋，都是调用rotate方法
    private void rebalance2(TreeNode<E> grand){
        TreeNode<E> parent = ((AVLTreeNode<E>)grand).tallerNode();
        TreeNode<E> node = ((AVLTreeNode)parent).tallerNode();
        if(parent.isLeftChild()){ // L
            if(node.isLeftChild()){ // LL
                rotate(grand, node.left,node,node.right, parent, parent.right,grand,grand.right);
            }else{ //LR
                rotate(grand, parent.left,parent,node.left, node, node.right,grand,grand.right);
            }
        }else{ //R
            if(node.isLeftChild()){ //RL
                rotate(grand, grand.left,grand,node.left, node, node.right,parent,parent.right);
            }else{ //RR
                rotate(grand, grand.left,grand,parent.left, parent, node.left,node,node.right);
            }
        }
    }
    private void rotate(TreeNode<E> r, //失衡的节点，即子树的根节点
                        TreeNode<E> a , TreeNode<E> b , TreeNode<E> c, //中序遍历这棵子树，分a,b,c,d,e,f,g节点；a,b,c是旋转后的左子树
                        TreeNode<E> d , //d是旋转后的子树的新的根节点
                        TreeNode<E> e , TreeNode<E> f , TreeNode<E> g){ //e,f,g是旋转后的右子树
       //1、确定新的根节点d

        d.parent = r.parent;
        if(r.isRightChild()){
            r.parent.right = d;
        }else if(r.isLeftChild()){
            r.parent.left = d;
        }else{ //r没有父节点，即r是根节点
            root = d;
        }

        //2、确定旋转后的子树根节点d的 左子树（a-b-c）
        b.left = a;
        b.right = c;
        if(a != null)
            a.parent = b;
        if(c != null)
            c.parent = b;
        updateHeight(b);

        //3、确定旋转后的子树根节点d的 右子树（e-f-g）
        f.left = e;
        f.right = g;
        if(e != null)
            e.parent = f;
        if(g != null)
            g.parent = f;
        updateHeight(f);

        //4、将子树的 根节点与左右子树 结合（b-d-f，由于确定左右子树的根节点b,f一定存在，因此无需判断null；因为判断失衡节点时，是由下往上判断，因此中间的b,f一定存在，还不理解的话，具体画图理解）
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);

    }

}
