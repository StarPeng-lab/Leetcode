package _05_队列;

import _02_动态数组.List;
import _03_链表.LinkedList;

//Deque，双端队列，可以在队头出队入队，在队尾出队入队
//官方源码中，Deque接口继承了Queue接口，实现类是LinkedList类
public class Deque<E> {

    private List<E> list = new LinkedList<>();

    //队头入队
    //还有一个方法：boolean offerFirst(E e);
    void addFirst(E e){
        list.add(0,e);
    }

    //队尾入队
    //还有一个方法：boolean offerLast(E e);
    void addLast(E e){
        list.add(e);
    }

    //队头出队，取队首并删除
    //还有一个方法，E pollFirst()
    E removeFirst(){
        return list.remove(0);
    }

    //队尾出队，取队尾并删除
    //还有一个方法，E pollLast()
    E removeLast(){
        return list.remove(list.size()-1);
    }

    //取队头，不删除
    //还有一个方法，E peekFirst()
    E getFirst(){
        return list.get(0);
    }

    //取队尾，不删除
    //还有一个方法，E peekLast()
    E getLast(){
        return list.get(list.size()-1);
    }

    int size(){
        return list.size();
    }

    boolean isEmpty(){
        return list.isEmpty();
    }

}
