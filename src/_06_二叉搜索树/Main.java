package _06_二叉搜索树;

import java.util.Comparator;

public class Main {

    //下面的自定义类实现了Comparetor接口，这个类也可以另外写成单独的类文件，由于.java文件名必须与public修饰的类名一致，因此下面的这个类都用private修饰
    //这个类不是内部类，不要搞混
    private static class User2Comparator implements Comparator<User2>{ //自定义比较器1
        public int compare(User2 e1 , User2 e2){
            return e1.getAge() - e2.getAge();
        }
    }

    public static void main(String[] args) {
        //1、二叉搜索树的元素类型为：基本数据类型的包装类
        Integer data[] = new Integer[]{7,4,9,2,5,8,11,3};
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>(); //由于基本数据类型的包装类，如Integer，都实现了Comparable<Integer>，重写了compareTo(E e)方法，因此我们直接使用即可
        for(int i=0 ; i<data.length ; i++){
            bst1.add(data[i]);
        }
        System.out.println(bst1);
        System.out.println("----------------------------------------");

        //2、二叉搜索树的元素类型为：自定义类User1，User1类实现了Comparable接口，重写了compareTo(E e)方法
        BinarySearchTree<User1> bst2 = new BinarySearchTree<>();
        bst2.add(new User1(7));
        bst2.add(new User1(4));
        bst2.add(new User1(9));
        bst2.add(new User1(2));
        System.out.println(bst2);
        System.out.println("----------------------------------------");

        //3、二叉搜索树的元素类型为：自定义类User2，需要传入比较器给二叉搜索树
         //3.1、新建类，实现比较器；可以新建多个类，一个类即为一种比较逻辑
        BinarySearchTree<User2> bst3 = new BinarySearchTree<>(new User2Comparator());
        bst3.add(new User2(7));
        bst3.add(new User2(4));
        bst3.add(new User2(9));
        bst3.add(new User2(2));
        System.out.println(bst3);
        System.out.println("----------------------------------------");

         //3.2、用匿名内部类，实现比较器
        BinarySearchTree<User2> bst4 = new BinarySearchTree<>(new Comparator<User2>() {
            @Override
            public int compare(User2 e1, User2 e2) {
                return e2.getAge() - e1.getAge();
            }
        });
        bst4.add(new User2(7));
        bst4.add(new User2(4));
        bst4.add(new User2(9));
        bst4.add(new User2(2));
        System.out.println(bst4);
        System.out.println("----------------------------------------");

    }
}
