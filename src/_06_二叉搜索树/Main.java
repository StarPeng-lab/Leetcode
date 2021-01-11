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

    static Integer data[] = new Integer[]{7,4,2,1,3,5,9,8,11,10,12};

    //1、二叉搜索树的元素类型为：基本数据类型的包装类
    public static void test1(){
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>(); //由于基本数据类型的包装类，如Integer，都实现了Comparable<Integer>，重写了compareTo(E e)方法，因此我们直接使用即可
        for(int i=0 ; i<data.length ; i++){
            bst1.add(data[i]);
        }

        bst1.preOrderTraversal(); //前序遍历
        System.out.println();
        System.out.println("----------------------------------------");
    }

    //2、二叉搜索树的元素类型为：自定义类User1，User1类实现了Comparable接口，重写了compareTo(E e)方法
    public static void test2(){
        BinarySearchTree<User1> bst2 = new BinarySearchTree<>();
        for(int i=0 ; i<data.length ; i++){
            bst2.add(new User1(data[i]));
        }

        bst2.inOrderTraversal(); //中序遍历
        System.out.println();
        System.out.println("----------------------------------------");
    }

    //3、二叉搜索树的元素类型为：自定义类User2，需要传入比较器给二叉搜索树
    //3.1、新建类，实现比较器；可以新建多个类，一个类即为一种比较逻辑
    public static void test3(){
        BinarySearchTree<User2> bst3 = new BinarySearchTree<>(new User2Comparator());
        for(int i=0 ; i<data.length ; i++){
            bst3.add(new User2(data[i]));
        }

        bst3.postOrderTraversal(); //后序遍历
        System.out.println();
        System.out.println("----------------------------------------");
    }

    //3.2、用匿名内部类，实现比较器
    public static void test4(){
        BinarySearchTree<User2> bst4 = new BinarySearchTree<>(new Comparator<User2>() {
            @Override
            public int compare(User2 e1, User2 e2) {
                return e2.getAge() - e1.getAge();
            }
        });
        for(int i=0 ; i<data.length ; i++){
            bst4.add(new User2(data[i]));
        }

        bst4.levelOrderTraversal(); //层序遍历
        System.out.println();
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}
