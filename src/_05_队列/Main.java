package _05_队列;

public class Main {
    public static void main(String[] args) {
        //队列
        Queue<Integer> queue = new Queue<>();
        queue.add(11);
        queue.add(12);
        queue.add(13);
        queue.add(14);
        while(!queue.isEmpty()){
            System.out.println(queue.remove()); // 11 12 13 14
        }
        System.out.println("-----------------------------------------------");

        //双端队列
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(11);
        deque.addFirst(22);
        deque.addLast(33);
        deque.addLast(44);
        while(!deque.isEmpty()){
            System.out.println(deque.removeFirst()); // 头 22 11 33 44 尾
        }

    }
}
