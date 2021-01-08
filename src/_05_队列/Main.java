package _05_队列;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.add(11);
        queue.add(12);
        queue.add(13);
        queue.add(14);
        while(!queue.isEmpty()){
            System.out.println(queue.remove()); // 11 12 13 14
        }
    }
}
