package _03_链表;

// 约瑟夫问题
public class Josephus<E> {
    private Node<E> current;
    public void reset(){
        current = first;
    }
    public E next(){
        if(current == null)
            return null;
        current = current.next;
        return current.element;
    }
    public E remove(){ //删除成功后，current要指向下一个节点
        if(current == null)
            return null;
        Node<E> next = current.next;
        E element = remove(current);
        if(next == null){
            current = null;
        }else{
            current = next;
        }
        return element;
    }

    static void josephus(int n,int m){
        Josephus<Integer> list = new Josephus<>();
        for(int i=1 ; i<=n; i++){
            list.add(i);
        }
        list.reset(); //指向头节点

        System.out.println(list);

        int tmp = m;
        while(!list.isEmpty()){
            while(--tmp>0){ //循环m-1次（不能是tmp--）
                list.next();
            }
            tmp = m;
            /*list.next();
            list.next();*/
            System.out.println(list.remove());
        }
    }
    public static void main(String[] main){
        int n = 8; //n个人围成一圈
        int m = 3; //从第一个人开始报数，第n个被淘汰
        josephus(n,m);
    }

    //用双向循环链表
    int size = 0;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E>{
        private Node<E> prev;
        private E element;
        private Node<E> next;
        public Node(Node<E> prev, E element, Node<E> next){
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public Node<E> node(int index){
        rangeCheck(index);
        if(index < size>>1){
            Node<E> node = first;
            for(int i=0 ; i<index ; i++){
                node = node.next;
            }
            return node;
        }else{
            Node<E> node = last;
            for(int i=size-1 ; i>index ; i--){
                node = node.prev;
            }
            return node;
        }
    }

    public int indexOf(E element) {
        int index = 0 ;
        if(element == null){ //判断空值的情况
            for(Node<E> x = first; x != null ; x = x.next){
                if(x.element == null)
                    return index;
                index++;
            }
        }else{
            for(Node<E> x = first; x != null ; x = x.next){
                if(element.equals(x.element))
                    return index;
                index++;
            }
        }
        return -1;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(E element){
        Node<E> oldLast = last;
        last = new Node<E>(oldLast,element,first);
        if(oldLast == null){
            first = last;
            first.prev = first;
            first.next = first;
        }else{
            oldLast.next = last;
            first.prev = last;
        }
        size++;
    }

    public E remove(Node<E> node){
        int index = indexOf(node.element);
        rangeCheck(index);
        if(size == 1){
            first = null;
            last = null;
        }else{
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;
            if(node == first){
                first = next;
            }
            if(node == last){
                last = prev;
            }
        }
        size--;
        return node.element;
    }

    private void rangeCheck(int index){ //这里检查的下标，都是已经存在的元素的下标，既然是已经存在的元素，那么一定在0~size-1之间
        if(index < 0 || index >=size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+this.size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size: ").append(size).append(", [");
        Node<E> node = first;
        for(int i=0 ; i < size ; i++){
            if(i != 0)
                sb.append(",");
            sb.append(node.element);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
