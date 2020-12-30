package _03_链表;

import _02_动态数组.AbstractList;


// 若用单向链表实现
public class SingleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    private static class Node<E>{
        E element;
        Node<E> next;
        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
    }

    public SingleLinkedList(){
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
            first = new Node<E>(element,first);
        }else{
            Node<E> prev = node(index-1);
            prev.next = new Node<E>(element,prev.next);
        }*/
        //3、
        Node<E> prev = index==0 ? first : node(index-1);
        prev.next = new Node<E>(element,prev.next);

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

        /*
        Node<E> node = first;
        if(index == 0){
            first = first.next;
        }else{
            Node<E> prev = node(index-1);
            node = prev.next;
            prev.next = node.next; // prev.next = prev.next.next;
        }*/
        //4、
        Node<E> prev = index==0 ? first : node(index-1);
        Node<E> node = prev.next;
        prev.next = node.next;

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
            sb.append(node.element);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
