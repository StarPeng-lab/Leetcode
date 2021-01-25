package _06_二叉搜索树.Tree;

import Tools.Files;
import Tools.Printer.BinaryTreeInfo;
import Tools.Printer.BinaryTrees;

public class Main {

    static int[] num = {7,4,9,2,5,8,11,1,3,10,12};

    //测试1：测试用BinaryTrees类的println方法打印一棵树
    //      BinaryTree实现了BinaryTreeInfo接口，BinaryTree类的对象 作为方法参数
    public static void test1(){
        BST<Integer> bst1 = new BST<>();
        for(int i = 0 ; i < num.length ; i++){
            bst1.add(num[i]);
        }
        BinaryTrees.println(bst1); //默认是 lever_order 顺序打印出一棵树
        System.out.println("--------------------------------------------------");
    }

    //测试2：直接调用BinaryTrees类的println方法打印一棵树
    //      使用匿名内部类 作为方法参数
    public static void test2(){
        BinaryTrees.println(new BinaryTreeInfo() {
            @Override
            public Object root() { //树的根是谁
                return "A";
            }

            @Override
            public Object left(Object node) { //树的左边是谁
                if(node == "A")
                    return "B";
                if(node == "B")
                    return "D";
                return null;
            }

            @Override
            public Object right(Object node) { //树的右边是谁
                if(node == "A")
                    return "C";
                if(node == "B")
                    return "E";
                return null;
            }

            @Override
            public Object string(Object node) { //如何打印
                return node;
            }
        }, BinaryTrees.PrintStyle.INORDER); //指定按照 inorder 顺序打印
    }

    //测试3：将这棵树输入到文件中，利用我们写的Files工具类
    public static void test3(){
        BST<Integer> bst3 = new BST<>();
        for(int i = 0 ; i < num.length ; i++){
            bst3.add(num[i]);
        }
        BST<Integer> bst4 = new BST<>();
        for(int i = 0 ; i < num.length ; i++){
            bst4.add((int)(Math.random()*30));
        }

        String data1 = BinaryTrees.printString(bst3); //将树转换为字符串，默认是lever_order顺序
        data1 += "\n" + "\n"; //如果要往文件追加数据，那么这里把 原有数据 和 追加数据 换两行隔开
        Files.writeToFile("D:/test/0125/1.txt",data1); //利用Files类的writeToFile方法，将data1输入到文件，默认添加方式为不追加

        String data2 = BinaryTrees.printString(bst4,BinaryTrees.PrintStyle.INORDER);
        Files.writeToFile("D:/test/0125/1.txt",data2,true);

    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
