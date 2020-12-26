package _02_动态数组;

import Tools.Assert;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        //测试add,remove,set,get
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.remove(2);
        System.out.println(list);
        list.set(0,5);
        Assert.test(list.get(0)==6);
        System.out.println(list);

        //测试扩容
        for(int i=0 ; i<30 ; i++){
            list.add(i);
        }
        System.out.println(list);
    }
}
