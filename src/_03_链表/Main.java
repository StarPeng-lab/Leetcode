package _03_链表;

import Tools.Assert;
import _02_动态数组.List;

public class Main {
    public static void test(List<Integer> list){
        list.add(20); //size = 1
        list.add(30); //size = 2
        list.add(0,10); //size = 3

        list.add(list.size(),40); // Size: 4, [10,20,30,40]
        list.remove(2);
        list.remove(new Integer(20));
        System.out.println(list); // Size: 2, [10,40]
        Assert.test(list.indexOf(10)==0);
    }
    public static void main(String[] args) {
        test(new SingleLinkedList<>());
        test(new LinkedList<>());
    }
}
