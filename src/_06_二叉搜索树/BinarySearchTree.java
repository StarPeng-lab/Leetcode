package _06_二叉搜索树;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> {

    private int size;
    private Tree<E> root;
    private Comparator<E> comparator;

    private static class Tree<E>{

        private E element;
        private Tree<E> parent;
        private Tree<E> left;
        private Tree<E> right;

        public Tree(E element , Tree<E> parent){
            this.element = element;
            this.parent = parent;
        }
    }

    public BinarySearchTree(){ //如果没有传入比较器对象，那么需要 E 本身是实现了Comparable接口的，里面有compareTo(E e)方法
        this(null);
    }

    public BinarySearchTree(Comparator comparator){ //传入一个比较器对象，以后可以在调用构造函数创建对象时，传入不同的构造器，完成不同的比较规则
        this.comparator = comparator;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;

    }

    public void clear(){

    }

    public boolean contains(E element){
        return false;
    }

    public void add(E element){
        elementNotNullCheck(element);

        if(root == null){
            root = new Tree<E>(element,null);
            size++;
            return;
        }

        Tree<E> node = root; //找到根节点，从根节点开始遍历
        Tree<E> parent = null; //记录父节点
        int com = 0; //局部变量，JVM不会为其初始化
        while(node != null){

            com = compare(element,node.element);
            parent = node;

            if(com > 0){ //说明 element 比 node.element 大，往下遍历到二叉搜索树的右节点 （具体二叉搜索数大的放那边，自己定义）
                node = node.right;
            }else if(com < 0){ //说明 element 比 node.element 小，往下遍历到二叉搜索树的左节点
                node = node.left;
            }else{ //说明 element 等于 node.element ，对这个节点重新赋值（重新赋值的原因时，如果类型为自定义类User，此时虽然按自定义比较逻辑：age相等，两个对象即相等，但这其实还是两个不同的对象，需要新值覆盖旧值）
                node.element = element;
                return;
            }

        }

        //定义要插入的节点，并确定要插入到父节点的哪个位置
        node = new Tree<E>(element,parent);
        if(com > 0){
            parent.right = node;
        }else if(com < 0){
            parent.left = node;
        }

        size++;


    }

    public void remove(E element){

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

    /*前序遍历*/
    public void preOrderTraversal(){
        preOrderTraversal(root);
    }
    private void preOrderTraversal(Tree<E> node){
        if(node == null)
            return;

        System.out.print(node.element+" ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    /*中序遍历*/
    public void inOrderTraversal(){
        inOrderTraversal(root);
    }
    private void inOrderTraversal(Tree<E> node){
        if(node == null)
            return;

        inOrderTraversal(node.left);
        System.out.print(node.element+" ");
        inOrderTraversal(node.right);
    }

    /*后序遍历*/
    public void postOrderTraversal(){
        inOrderTraversal(root);
    }
    private void postOrderTraversal(Tree<E> node){
        if(node == null)
            return;

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.element+" ");
    }

    /*层序遍历*/
    public void levelOrderTraversal(){
        Queue<Tree<E>> queue = new LinkedList<>(); // 用队列存储节点，先根节点入队，之后开始重复：队头元素出队，把队头元素的左右子树入队
        queue.offer(root);
        while(!queue.isEmpty()){
            Tree<E> node = queue.poll();
            System.out.print(node.element+" ");
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }

}
