package _07_AVL树;


import Tools.Printer.BinaryTreeInfo;
import Tools.Printer.BinaryTrees;
import _06_二叉搜索树.Tree.BST;

public class Main {
    static int[] data = new int[]{85,19,69,3,7,99,95,2,1,70,44,58,11,21,14,93,57,4,56};

    //测试搜索二叉树的打印
    public static void test1(){
        BST<Integer> bst = new BST<>();
        for(int i=0 ; i<data.length ; i++){
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        System.out.println("------------------------------------------------------------------------");
    }

    //测试平衡二叉树的打印
    public static void test2(){
        AVLTree<Integer> avlTree = new AVLTree<>();
        for(int i=0 ; i<data.length ; i++){
            avlTree.add(data[i]);
        }
        BinaryTrees.println(avlTree);
        System.out.println("------------------------------------------------------------------------");
    }

    //测试AVL树的删除
    public static void test3(){
        AVLTree<Integer> avlTree = new AVLTree<>();
        for(int i=0 ; i<data.length ; i++){
            avlTree.add(data[i]);
        }
        avlTree.remove(85);
        BinaryTrees.println(avlTree);
        System.out.println("------------------------------------------------------------------------");
        avlTree.remove(56);
        avlTree.remove(21);
        BinaryTrees.println(avlTree);
    }

    public static void main(String[] args) {
        //test1();
        test2();
        test3();
    }
}
