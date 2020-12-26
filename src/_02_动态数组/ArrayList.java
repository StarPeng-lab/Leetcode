package _02_动态数组;

public class ArrayList<E> implements List<E> {

    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capacity){
        capacity = (capacity <= DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = new Object[capacity];
    }

    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf((E) o)!=ELEMENT_NOT_FOUND;
    }

    @Override
    public boolean add(Object o) {
        add(size,(E)o); //在size位置，添加元素
        return true;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacity(size+1); //添加一个元素，保证此时容量有size+1

        for(int i = size-1; i >=index ; i--){
            elements[i+1] = this.elements[i];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E)elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        Object old = elements[index];
        elements[index] = element;
        return (E)old;
    }

    @Override
    public boolean remove(Object o) {
        if(o==null){
            for(int i=0 ; i<size ; i++){
                if(elements[i]==null){
                    fastRemove(i);
                    return true;
                }
            }
        }else{
            for(int i=0 ; i<size ; i++){
                if(o.equals(elements[i])){
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E remove(int index){
        rangeCheck(index);
        E oldValue = (E) elements[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public int indexOf(E element) {
        for(int i = 0; i < size; i++){
            if(elements[i] == element)
                return i;
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {

        // clear to let GC do its work
        for(int i=0 ; i<size ; i++){
            elements[i] = null;
        }

        size = 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("size: ").append(size).append(" , [");
        for(int i = 0; i< size; i++){
            if(i != 0)
                sb.append(",");
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(capacity <= oldCapacity)
            return;
        //扩容为原来的1.5倍
        int newCapacity = oldCapacity + (oldCapacity>>1); //即newCapacity = oldCapacity*1.5，由于浮点数的运算比位运算耗时，因此用>>1（除以2^1）
        Object[] newElements = new Object[newCapacity];
        for(int i=0 ; i<size ; i++){
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity+"扩容为"+newCapacity);
    }

    private void rangeCheck(int index){ //这里检查的下标，都是已经存在的元素的下标，既然是已经存在的元素，那么一定在0~size-1之间
        if(index < 0 || index >=size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void rangeCheckForAdd(int index){ //例如：size=5,此时是允许往数组中插入元素，如往最后一个位置size-1插入元素，那么size-1的元素会挪到size,因此允许index=size
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.size;
    }

    private void fastRemove(int index){
        int numMoved = size-1-index; //删除index位置的元素后，需要往前移动的元素个数
        if(numMoved > 0){
            //从elements[index+1]~elements[size-1]，移动numMove个元素，到elements[index]~elements[size-2]
            System.arraycopy(elements,index+1,elements,index,numMoved);
        }
        /*for(int i=index+1 ; i<size ; i++){
            elements[i-1] = elements[i];
        }
        size--;*/
        elements[--size] = null; //clear to let GC do its work
    }

}
