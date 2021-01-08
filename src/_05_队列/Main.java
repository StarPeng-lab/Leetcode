package _05_队列;

public class Main {
    public static void main(String[] args) {
        //队列
      /*  Queue<Integer> queue = new Queue<>();
        queue.add(11);
        queue.add(12);
        queue.add(13);
        queue.add(14);
        while(!queue.isEmpty()){
            System.out.println(queue.remove()); // 11 12 13 14
        }
        System.out.println("-----------------------------------------------");*/

       /* //双端队列
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(11);
        deque.addFirst(22);
        deque.addLast(33);
        deque.addLast(44);
        while(!deque.isEmpty()){
            System.out.println(deque.removeFirst()); // 头 22 11 33 44 尾
        }
        System.out.println("-----------------------------------------------");*/

        //循环队列
        CircleQueue<Integer> circle = new CircleQueue<Integer>();
        //[0,1,2,3,4,5,6,7,8,9]
        for(int i=0 ; i<10 ; i++){
            circle.addLast(i);
        }
        //[null,null,null,null,null,5,6,7,8,9]
        for(int i=0 ; i<5; i++){
            circle.removeFirst();
        }
        //[15,16,17,18,19,5,6,7,8,9]
        //10扩容为15,15扩容为22后：[5,6,7,8,9,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,null]
        for(int i=15 ; i<31; i++){
            circle.addLast(i);
        }
        System.out.println(circle);

        //22缩容为11：[null,null,null,null,null,null,null,null,null,30,null]
        for(int i=0 ; i<20; i++){
            circle.removeFirst();
        }
        System.out.println(circle);
        while(!circle.isEmpty()){
            System.out.println(circle.removeFirst());
        }

    }
}
