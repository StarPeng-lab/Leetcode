package _06_二叉搜索树.Tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

//重构二叉搜索树，继承二叉树BinaryTree
public class BST<E> extends BinaryTree<E>{

    private Comparator<E> comparator;

    public BST(){ //如果没有传入比较器对象，那么需要 E 本身是实现了Comparable接口的，里面有compareTo(E e)方法
        this(null);
    }

    public BST(Comparator comparator){ //传入一个比较器对象，以后可以在调用构造函数创建对象时，传入不同的构造器，完成不同的比较规则
        this.comparator = comparator;
    }

    public boolean contains(E val){
        return node(val) != null;
    }

    //为二叉搜索数延伸出的平衡二叉树进行扩展：
    //1、创建节点（改动add方法即可；BST中用new TreeNode<>()，AVL&红黑树中重写createNode方法）
    protected TreeNode<E> createNode(E element , TreeNode<E> parent){
        return new TreeNode<E>(element,parent);
    }

    //2、添加节点，之后的调整操作（二叉搜索树中不实现，让子类去实现，比如AVL树）
    protected void afterAdd(TreeNode<E> node){
    }

    //3、删除节点，之后的调整
    protected void afterRemove(TreeNode<E> node){

    }

    //添加节点
    public void add(E element){
        elementNotNullCheck(element);

        if(root == null){
            root = createNode(element,null);
            size++;
            afterAdd(root); //新添加节点之后的处理
            return;
        }

        TreeNode<E> node = root; //找到根节点，从根节点开始遍历
        TreeNode<E> parent = null; //记录父节点
        int cmp = 0; //局部变量，JVM不会为其初始化
        while(node != null){

            cmp = compare(element,node.val);
            parent = node;

            if(cmp > 0){ //说明 element 比 node.element 大，往下遍历到二叉搜索树的右节点 （具体二叉搜索数大的放那边，自己定义）
                node = node.right;
            }else if(cmp < 0){ //说明 element 比 node.element 小，往下遍历到二叉搜索树的左节点
                node = node.left;
            }else{ //说明 element 等于 node.element ，对这个节点重新赋值（重新赋值的原因时，如果类型为自定义类User，此时虽然按自定义比较逻辑：age相等，两个对象即相等，但这其实还是两个不同的对象，需要新值覆盖旧值）
                node.val = element;
                return;
            }

        }

        //定义要插入的节点，并确定要插入到父节点的哪个位置
        node = createNode(element,parent);
        if(cmp > 0){
            parent.right = node;
        }else if(cmp < 0){
            parent.left = node;
        }

        size++;
        afterAdd(node);

    }
    private int compare(E e1, E e2){
        if(comparator != null){
            //返回值>0 ，说明e1>e2；返回值=0 ，说明e1=e2；返回值<0，说明e1<e2
            return comparator.compare(e1,e2); //如果调用有参构造函数创建BinarySearchTree对象时传入了构造器对象，那么我们的比较规则按传入的自定义或默认Comparator的compare(E e1,E e2)方法实现
        }
        //如果构造器为null，说明没有传入构造器，需要类型E本身就实现了Comparable接口，即<E extends Comparable>，但如果直接这样写，会限制E必须实现这个接口，因此我们选择在未传入比较器的情况下，对e1进行强制类型转换，如果转换出错，jvm会为我们抛出错误
        return ((Comparable<E>)e1).compareTo(e2);
    }
    private void elementNotNullCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("Value must not be null !");
        }
    }

    //删除值为val的节点
    public void remove(E val){
        remove(node(val));
    }
    private void remove(TreeNode<E> node){
        if(node == null)
            return;

        size--;

        if(node.hasTwoChildren()){ //情况1、删除度为2的节点，这里取前驱节点（也可以取后继节点）
            TreeNode<E> pre = predecessor(node);
            node.val = pre.val; //用前驱节点的val赋给node.val
            node = pre; // 删除前驱节点：这里直接将前驱节点赋给node，在后面代码删去node，无论是度为几，都到达删除node的代码进行删除，达到代码复用的效果
        }

        TreeNode<E> child = node.left != null ? node.left : node.right;

        if(child != null){ //情况2、删除度为1的节点

            child.parent = node.parent;
            if(node.parent == null){
                root = child;
            }else if(node == node.parent.left){
                node.parent.left = child;
            }else{ // node == node.parent.right
                node.parent.right = child;
            }

        }else if(node.parent == null){ //度为0，并且node == root
            root = null;
        }else{ //情况3、删除度为0的节点
            if(node == node.parent.left){
                node.parent.left = null;
            }else{ // node == node.parent.right
                node.parent.right = null;
            }
        }

        afterRemove(node); //删除节点之后，的操作处理（删除逻辑中，没有node.parent = null; 因此传入的node，虽然没有节点指向node，但是node.parent的指向没有断）
    }
    private TreeNode<E> node(E val){ //根据element找到对应的node节点
        elementNotNullCheck(val);
        TreeNode<E> node = root;
        while(node != null){
            int cmp = compare(val,node.val);
            if(cmp == 0) //找到了element对应的node
                return node;
            if(cmp > 0){
                node = node.right;
            }else if(cmp < 0){
                node = node.left;
            }
        }
        return null; //没有element对应的node
    }

}
