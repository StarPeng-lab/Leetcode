package _02_动态数组;

import Tools.Assert;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        //测试add,remove,set,get
        list.add(31);
        list.add(32);
        list.add(33);
        list.add(34);
        list.add(35);
        list.remove(2);
        System.out.println(list);
        list.set(0,5);
        Assert.test(list.get(0)==6); // 抛出异常，测试未通过
        System.out.println(list);

        //测试扩容
        for(int i=0 ; i<30 ; i++){
            list.add(i);
        }
        System.out.println(list);

        //测试缩容
        for(int i=0 ; i<30 ; i++){
            list.remove(Integer.valueOf(i));
        }
        System.out.println(list);
    }
}
