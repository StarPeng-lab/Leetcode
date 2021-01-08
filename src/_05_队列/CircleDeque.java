package _05_队列;

//循环双端队列，底层用数组实现，动态数组（前面的02_动态数组的优化，就类似下面的方法，只是动态数组的优化还需要考虑在数组中间数的操作）
public class CircleDeque<E> {

    private int size; //已使用的数组大小
    private E[] elements; //数组
    private int front; //存储队头元素的数组下标
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque(){
        elements = (E[])new Object[DEFAULT_CAPACITY];
    }

    //队头入队
    public void addFirst(E element){
        enSureCapacity(size+1);
        front = index(-1); //front指向前一位
        elements[front] = element; //在队头的前一个位置添加
        size++;
    }


    //队尾入队
    public void addLast(E element){
        enSureCapacity(size+1); //至少需要size+1容量，检查是否需要扩容
        elements[index(size)] = element;
        size++;
    }

    //队头出队
    public E removeFirst(){
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        trim(); //检查是否需要缩容
        return frontElement;
    }

    //队尾出队
    public E removeLast(){
        int lastIndex = index(size-1);
        E lastElement = elements[lastIndex];
        elements[lastIndex] = null;
        size--;
        return lastElement;
    }

    //获取队头元素
    public E getFirst(){
        return elements[front];
    }

    //获取队尾元素
    public E getLast(){
        return elements[index(size-1)];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void clear(){
        for(int i = 0 ; i < size ; i++){
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Capacity："+elements.length+"，Size："+size+", Front："+front+", [");
        for(int i = 0 ; i < elements.length ; i++){
            if(i != 0){
                sb.append(",");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    //循环队列中，得到真正的所求元素索引
    private int index(int index){

        //return (front+index)%elements.length; //(队头下标+真正下标) % 整个数组长度，得到真正的真正的下标

        //完整版，这里是循环双端队列，要考虑在队头添加元素时，传入-1后，front+index为负数的情况
        index = index + front;
        if(index < 0){
            index = index + elements.length; //比如front=0时，在队头添加元素，此时的front应该=elements.length
        }

        // return index%elements.length;

        //模数优化：
        return index - (index < elements.length ? 0 : elements.length);



    }

    //扩容
    private void enSureCapacity(int capacity){
        int oldCapacity = elements.length;

        if(oldCapacity >= capacity)
            return;

        int newCapacity = oldCapacity + (oldCapacity>>1); //注意加括号，体现优先级
        E[] newElements = (E[])new Object[newCapacity];
        for(int i = 0 ; i < size ; i++){
            newElements[i] = elements[index(i)];
        }
        elements = newElements;

        front = 0; //重置front

        System.out.println(oldCapacity+"扩容为："+newCapacity);
    }

    //缩容
    private void trim(){
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity>>1;

        if(size >= newCapacity || newCapacity <=DEFAULT_CAPACITY)
            return;
        if(oldCapacity <= DEFAULT_CAPACITY)
            return;

        E[] newElements = (E[])new Object[newCapacity];
        for(int i = 0 ; i < size ; i++){
            newElements[i] = elements[index(i)];
        }
        elements = newElements;

        front = 0;

        System.out.println(oldCapacity+"缩容为："+newCapacity+",Size："+size);
    }

}
