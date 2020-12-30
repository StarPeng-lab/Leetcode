package _03_链表;

import _02_动态数组.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new SingleLinkedList<>();
        list.add(20); //size = 1
        list.add(30); //size = 2
        list.add(0,10); //size = 3

        list.add(list.size(),40); // Size: 4, [10,20,30,40]
        list.remove(2);
        list.remove(new Integer(20));
        System.out.println(list); // Size: 2, [10,40]
        System.out.println(list.indexOf(10)); //0
    }
}
