package _05_队列;

import _02_动态数组.List;
import _03_链表.LinkedList;

//队列，先进先出，因此要在首尾操作数据，尾进首出，用LinkedList实现
//官方源码中，Queue接口的实现类是LinkedList类，offer方法内部就是调用了LinkedList的add方法
public class Queue<E> {
    private List<E> list = new LinkedList<E>();

    /**
     * 添加元素到队尾，即入队，出错会抛出异常
     * 还有一个方法：boolean offer(E e),出错会返回false/null
     * @param element
     * @return
     */
    boolean add(E element){
        if(list.add(element)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取队首元素并从队列中删除，即出队，出错会抛出异常
     * 还有一个方法：E poll(),出错会返回false/null
     * @return
     */
    E remove(){
        return list.remove(0);
    }

    /**
     * 获取队首元素但不删除，即获取队列的头元素，出错会抛出异常
     * 还有一个方法：E peek(),出错会返回false/null
     * @return
     */
    E element(){
        return list.get(0);
    }

    /**
     * 获取队列长度
     * @return
     */
    int size(){
        return list.size();
    }

    /**
     * 队列是否为空
     * @return
     */
    boolean isEmpty(){
        return list.isEmpty();
    }
}

