package _03_链表;

import _02_动态数组.AbstractList;


// 底层：单向循环链表，修改add(int,E),remove(int)：将尾节点.next指向头节点
//      用了两种方式：有虚拟头节点，无虚拟头节点
//      （都要考虑添加/删除头节点的情况，并在此情况下，添加是否原来没有元素进行添加/删除是否原来只有一个元素进行删除；
//        以及在中间和末尾添加/删除元素，由于在头节点已经将尾节点的next指向了头节点，因此在中间和末尾处理方式相同）
public class SingleCircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    private static class Node<E>{
        E element;
        Node<E> next;
        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {

            return element + "_" + next.element ;
        }
    }

    public SingleCircleLinkedList(){
        //1、创建一个虚拟头结点（共修改了6处：构造方法,node,add,remove,indexOf,toString）
        //LinkedList底层没有用虚拟头结点（虚拟头结点在测试题的链表元素删除中比较常用）
        first = new Node<E>(null,null);
    }

    private Node<E> node(int index){
        rangeCheck(index);

        //Node<E> node = first;
        Node<E> node = first.next; //2、虚拟头结点.next

        for(int i=0 ; i<index ; i++){
            node = node.next;
        }
        return node;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

/*
        if(index == 0){
            //单向循环链表，最后一个节点指向第一个节点，这里要拿到最后一个节点，使其指向新的第一个节点
            Node<E> newFirst = new Node<E>(element,first); //先创建头节点

            Node<E> last = size==0 ? newFirst : node(size-1); //拿到最后一个节点，若没有用newFirst暂存新的头节点,那么node(size-1)中找到的最后一个节点是错的（因为first被改变了）
            last.next = newFirst;

            first = newFirst;
        }else{
            Node<E> prev = node(index-1);
            prev.next = new Node<E>(element,prev.next);
        }*/

        //3、
        Node<E> prev = index==0 ? first : node(index-1); //前一个节点
        if(prev == first){ //如果前一个节点是虚拟头结点，那么prev.next是真正的头节点
            Node<E> newFirst = new Node<>(element,prev.next);
            Node<E> last = size==0 ? newFirst : node(size-1);
            last.next = newFirst;
            prev.next = newFirst;
        }else{
            prev.next = new Node<>(element,prev.next);
        }

        size++;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public boolean remove(Object o) { //参数是Object类型，因此删除一个基本数据类型的元素，jdk8后会自动装箱；int类型的数据是特例，由于重载了remove(int index)方法，因此，需要手动装箱删除元素为20，以免被jvm认为是index为20的元素
        int index = indexOf((E)o);
        if(index != ELEMENT_NOT_FOUND){
            remove(index);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public E remove(int index) {
        rangeCheck(index); //虽然后面有node(index)里进行了边界检查，然而，当链表为空时，若直接remove(0)，此时first=null，会报NPE异常，因此需要一开始就rangeCheck，当index=0且size=0时抛出异常


       /* Node<E> node = first;
        if(index == 0){

            if(size == 1){
                first = null; //如果只有一个元素，删除后，单向循环链表的first指向null
            }else{
                Node<E> last = node(size-1); // 要先获取尾节点；如果先执行first=first.next，那么在node(size-1)中，会抛出越界异常
                first = first.next;
                last.next = first;
            }

        }else{
            Node<E> prev = node(index-1);
            node = prev.next;
            prev.next = node.next; // prev.next = prev.next.next;
        }*/

        //4、
        Node<E> prev = index==0 ? first : node(index-1);
        Node<E> node = prev.next;
        if(prev == first ){
            if(size == 1){
                first = null;
            }else{
                Node<E> last = node(size-1);//如果删除头节点，并且此时链表元素超过1个，那么要把尾节点指向新的头节点

                prev.next = node.next;

                last.next = prev.next;
            }
        }else{
            prev.next = node.next;
        }

        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        //Node<E> node = first;
        Node<E> node = first.next; //5、虚拟头结点.next

        if(element == null){ //判断空值的情况
            for(int i=0 ; i<size ; i++){
                if(node.element == null)
                    return i;
                node = node.next;
            }
        }else{
            for(int i=0 ; i<size ; i++){
                if(element.equals(node.element))
                    return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size: ").append(size).append(", [");

        //Node<E> node = first;
        Node<E> node = first.next; //6、虚拟头结点.next

        for(int i=0 ; i < size ; i++){
            if(i != 0)
                sb.append(",");
            //sb.append(node.element);
            sb.append(node); //用内部类中node的toString()
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
