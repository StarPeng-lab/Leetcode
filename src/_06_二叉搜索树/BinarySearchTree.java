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

        private boolean isLeaf(){
            return left == null && right == null;

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

    @Override
    public String toString() { //一般采用前序遍历打印出树状结构
        StringBuilder sb = new StringBuilder("");
        toString1(root,sb,"");
        return sb.toString();
    }
    private void toString1(Tree<E> node, StringBuilder sb, String prefix){ //前序遍历
        if(node == null) return;

        sb.append(prefix).append(node.element).append("\n"); //拼接 前缀 节点值 换行符
        toString1(node.left , sb , prefix+"L---"); //开始遍历左子树，并将前面拼接好的值sb传入，继续传入前缀 prefix+"L---"
        toString1(node.right , sb , prefix+"R---");
    }
    private void toString2(Tree<E> node, StringBuilder sb, String prefix){ //中序遍历
        if(node == null) return;

        toString2(node.left , sb , prefix+"L---");
        sb.append(prefix).append(node.element).append("\n");
        toString2(node.right , sb , prefix+"R---");
    }
    private void toString3(Tree<E> node, StringBuilder sb, String prefix){ //后序遍历
        if(node == null) return;

        toString3(node.left , sb , prefix+"L---");
        toString3(node.right , sb , prefix+"R---");
        sb.append(prefix).append(node.element).append("\n");
    }
    private void toString4(Tree<E> node, StringBuilder sb, String prefix){ //层序遍历
        if(node == null) return; //这里的node就是root

        int levelSize = 1;

        Queue<Tree<E>> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            Tree<E> temp = queue.poll();
            sb.append(temp.element).append("---");
            levelSize--;

            if(temp.left != null){
                queue.offer(temp.left);
            }
            if(temp.right != null){
                queue.offer(temp.right);
            }

            if(levelSize == 0){ //说明开始遍历下一层的节点，重新对levelSize赋值，并给sb拼接换行符
                levelSize = queue.size();
                sb.append("\n");
            }
        }
    }

    //判断树的高度（即判断 根节点root的高度）
    public int heigth(){
        return nodeHeight2(root);
    }
    //方法一：递归
    private int nodeHeight1(Tree<E> node){ //计算节点的高度
        if(node == null)
            return 0 ;
        return 1 + Math.max(nodeHeight1(node.left) , nodeHeight1(node.right)); //当前节点所在的一层 + 左子树和右子树中的层数最大值
    }
    //方法二：迭代，利用层序遍历
    private int nodeHeight2(Tree<E> node){
        if(node == null) return 0;

        int levelSize = 1; //记录每一层的节点数，初始值设为1，代表根节点所在层数
        int height = 0; //树的高度从0开始计算
        Queue<Tree<E>> queue = new LinkedList<>();
        queue.offer(node);

        while(!queue.isEmpty()){
            Tree<E> temp = queue.poll();
            levelSize--;

            if(temp.left != null){
                queue.offer(temp.left);
            }
            if(temp.right != null){
                queue.offer(temp.right);
            }

            if(levelSize == 0){ //说明这一层的节点已全部出队列，此时的队列中存的是下一层的全部节点，即意味着开始遍历下一层的节点
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }


    //是否是完全二叉树，利用队列，用层序遍历的方式遍历节点
    public boolean isComplete(){
        if(root == null)
            return false;

        Queue<Tree<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;

        while(!queue.isEmpty()){
            Tree<E> node = queue.poll();

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

    /*中序遍历，比较逻辑是左小右大，中序遍历的数据按从小到大升序排序；如果比较逻辑为左大右小，那按降序排序*/
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
        postOrderTraversal(root);
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
        if(root == null)
            return;

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
    private void preOrder(Tree<E> node, Visitor<E> visitor){
        if(node == null || visitor.stop == true) return;

        visitor.stop = visitor.visit(node.element);
        preOrder(node.left,visitor);
        preOrder(node.right,visitor);
    }

    //2、中序遍历
    public void inOrder(Visitor<E> visitor){
        if(visitor == null) return;
        inOrder(root,visitor);
    }
    private void inOrder(Tree<E> node, Visitor<E> visitor){
        if(node == null || visitor.stop == true) return; //这里的visitor为true时，方法不再往下遍历节点

        inOrder(node.left,visitor);
        if(visitor.stop == true) //这里的visitor为true时，方法不再打印遍历到的节点，这里再次判断是为了让inOrder(node.left,visitor);中遍历到visit(E)的返回值为true的节点后，不再打印后面的节点
            return;
        visitor.stop = visitor.visit(node.element);
        inOrder(node.right,visitor);
    }

    //3、后序遍历
    public void postOrder(Visitor<E> visitor){
        if(visitor == null) return;
        postOrder(root,visitor);
    }
    private void postOrder(Tree<E> node, Visitor<E> visitor){
        if(node == null || visitor.stop == true) return;

        postOrder(node.left,visitor);
        postOrder(node.right,visitor);
        if(visitor.stop == true)
            return;
        visitor.stop = visitor.visit(node.element);
    }

    //4、层序遍历
    public void levelOrder(Visitor<E> visitor){
        if(root == null || visitor == null)
            return;

        Queue<Tree<E>> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            Tree<E> node = queue.poll();

            if(visitor.visit(node.element)) //只要返回值为true，就停止方法，由于层序遍历没有递归，因此直接判断返回值即可
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
