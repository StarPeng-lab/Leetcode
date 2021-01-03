package _03_链表;

import Tools.Assert;
import _02_动态数组.List;

public class Main {
    public static void test(List<Integer> list){
        list.add(20); //size = 1
        list.add(30); //size = 2
        list.add(0,10); //size = 3

        list.add(list.size(),40); // Size: 4, [10,20,30,40]

        list.remove(0);
        list.remove(new Integer(20));
        System.out.println(list); // Size: 2, [10,40]

        list.remove(list.size()-1);
        System.out.println(list); // Size: 1, [10]

        Assert.test(list.indexOf(30)==0);
        System.out.println("-----------------------------");
    }
    public static void main(String[] args) {
        test(new SingleLinkedList<>());
        test(new LinkedList<>());
        test(new SingleCircleLinkedList<>());
        test(new CircleLinkedList<>());
    }
}
