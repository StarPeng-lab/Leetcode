package _04_栈;

import _02_动态数组.ArrayList;
import _02_动态数组.List;

public class Stack<E> {
    //利用组合，实现栈；不要用继承，否则栈的特性就被破坏了，比如可以取到栈中任意位置的值
    private List<E> list = new ArrayList<E>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        list.clear();
    }

    public void push(E element){
        list.add(element);
    }

    public E pop(){
        return list.remove(list.size()-1);
    }

    public E peek(){
        return list.get(list.size()-1);
    }
}
