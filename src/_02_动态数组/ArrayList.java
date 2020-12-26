package _02_动态数组;

import org.omg.CORBA.Object;

public class ArrayList<E> implements List<E> {

    private E[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capacity){
        capacity = (capacity <= DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        //elements = new E[capacity]; //不能用E来new一个数组
        elements = (E[])new Object[capacity]; //对象数组中放的不是对象本身，而是对象的地址值，节约堆空间；
                                              //对象数组中可以存放不同类型的对象，并且数组的每个元素所占内存空间相同（因为都存的是对象地址）
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
    public boolean contains(E o) {
        return indexOf(o)!=ELEMENT_NOT_FOUND;
    }

    @Override
    public boolean add(E o) {
        add(size,o); //在size位置，添加元素；关于是否可以存储空元素，由开发者决定，java中建议可以存储null
        return true;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacity(size+1); //添加一个元素，保证此时容量有size+1

       /* for(int i = size-1; i >=index ; i--){
            elements[i+1] = this.elements[i];
        } */
        for(int i=size; i>index; i--){ //优化，减少了size-1和i=index的运算
            elements[i] = elements[i-1];
        }

        elements[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public boolean remove(java.lang.Object o) {
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
        if(element == null){
            for(int i = 0 ; i < size ; i++){
                if(elements[i] == null)
                    return i;
            }
        }else {
            for (int i = 0 ; i < size ; i++) {
                if (element.equals(elements[i])) //若为对象数组，那么对象类中可重写equals()，来自定义相等；默认equals()中是比较内存地址，基本数据类型的包装类中都重写了equals()
                    return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {

        // clear to let GC do its work，若是对象数组，当对象地址置为null时，不再引用对象
        // 数组的内存没有销毁，销毁的是对象数组中指向的对象
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
        //扩容为原来的1.5倍，扩容因子据自己的要求设定
        int newCapacity = oldCapacity + (oldCapacity>>1); //即newCapacity = oldCapacity*1.5，由于浮点数的运算比位运算耗时，因此用>>1（除以2^1）
        E[] newElements = (E[])new Object[newCapacity];
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
            //用API，arraycopy()是一连串的内存拷贝，比我们用for循环一个一个元素挪要快
            System.arraycopy(elements,index+1,elements,index,numMoved);
        }
        /*for(int i=index+1 ; i<size ; i++){
            elements[i-1] = elements[i];
        }
        size--;*/
        elements[--size] = null; //clear to let GC do its work（对象数组的情况下，释放对象的引用）
    }

}
