package _03_链表;

import _02_动态数组.List;

//底层是双向链表
public class LinkedList<E> implements List<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E>{
        E element;
        Node<E> next;
        Node<E> prev;
        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> node(int index){
        rangeCheck(index);
        if(index < (size >>1)){
            Node<E> x = first;
            for(int i = 0 ; i < index ; i++)
                x = x.next;
            return x;
        }else{
            Node<E> x = last;
            for(int i = size-1 ; i > index ; i--)
                x = x.prev;
            return x;
        }

    }

    private void rangeCheckForAdd(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Size: "+size+", Index: "+index);
        }
    }

    private void rangeCheck(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Size: "+size+", Index: "+index);
        }
    }

    public LinkedList(){
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(E o) {
        return false;
    }

    @Override
    public boolean add(E element) {
        Node<E> prev = node(size-1);
        prev.next = new Node<>(element,prev.next);
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if(index == 0){
            first = new Node<>(element,first);
        }else{
            Node<E> prev = node(index-1);
            prev.next = new Node<>(element,prev.next);
        }
        size++;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf((E)o);
        remove(index);
        return false;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E old ;
        if(index == 0 ){
            old = first.element;
            first = first.next;
        }else{
            Node<E> del = node(index);
            old = del.element;
            Node<E> tmp = del.next;
            node(index-1).next = tmp;
            del.next = null;
        }
        return old;
    }

    @Override
    public int indexOf(E element) {
        int index = 0 ;
        if(element == null){
            for(Node<E> x = first ; x != null ; x = x.next){
                if(x.element == null)
                    return index;
                index++;
            }
        }else{
            for(Node<E> x = first ; x != null ; x = x.next){
                if(x.element == element)
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        size = 0;
        for(Node<E> x = first ; x != null ; ){
            Node<E> next = x.next;
            x.element = null; //无论element存着的是基本数据类型还是对象地址，都置空
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = null;
        last = null;
    }

}
