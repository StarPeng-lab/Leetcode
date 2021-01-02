package _03_链表;

import _02_动态数组.AbstractList;
import _02_动态数组.List;

//底层是双向链表
public class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    private static class Node<E>{
        E element;
        Node<E> next;
        Node<E> prev;
        public Node(Node<E> prev, E element, Node<E> next){
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> node(int index){
        rangeCheck(index);
        if(index < (size >>1)){
            Node<E> node = first;
            for(int i = 0 ; i < index ; i++)
                node = node.next;
            return node;
        }else{
            Node<E> node = last;
            for(int i = size-1 ; i > index ; i--)
                node = node.prev;
            return node;
        }

    }

    public LinkedList(){
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if(index == size){
            Node<E> oldLast = last;
            last = new Node<E>(oldLast,element,null);
            if(oldLast == null) { //添加的是第一个元素
                first = last; //first和last都指向新添加的元素，新添加的元素的prev和next都指向null
            }else{ //添加的是最后一个元素
                oldLast.next = last;
            }
        }else{
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<E>(prev,element,next);
            next.prev = node;
            if(prev == null){
                first = node;
            }else{
                prev.next = node;
            }
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
    public boolean remove(Object o) {
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
        rangeCheck(index);

        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if(prev == null){ //index == 0
            first = next;
        }else {
            prev.next = next;
        }

        if(next == null){ //index == size-1
            last = prev;
        }else{
            next.prev = prev;
        }

        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        int index = 0 ;
        if(element == null){ //判断空值的情况
            for(Node<E> x = first ; x != null ; x = x.next){
                if(x.element == null)
                    return index;
                index++;
            }
        }else{
            for(Node<E> x = first ; x != null ; x = x.next){
                if(element.equals(x.element))
                    return index;
                index++;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        for(Node<E> x = first ; x != null ; ){ //帮助gc先回收暂时不用的对象（未被迭代器引用的对象，或者先回收已经不被引用的对象）
            Node<E> next = x.next;
            x.element = null; //无论element存着的是基本数据类型还是对象地址，都置空
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = null;
        last = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size: ").append(size).append(", [");
        Node<E> node = first;
        while(node != null){
            if(node != first){
                sb.append(",");
            }
            sb.append(node.element);
            node = node.next;
        }
       /* for(int i=0 ; i < size ; i++){
            if(i != 0)
                sb.append(",");
            sb.append(node.element);
            node = node.next;
        }*/
        sb.append("]");
        return sb.toString();
    }

}
