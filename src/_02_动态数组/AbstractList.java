package _02_动态数组;

public abstract class AbstractList<E> implements List<E> { //抽象出这层抽象类，实现ArrayList和LinkedList相同的方法代码，这层对外界不可见
    protected int size;

    protected void rangeCheck(int index){ //这里检查的下标，都是已经存在的元素的下标，既然是已经存在的元素，那么一定在0~size-1之间
        if(index < 0 || index >=size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    protected void rangeCheckForAdd(int index){ //例如：size=5,此时是允许往数组中插入元素，如往最后一个位置size-1插入元素，那么size-1的元素会挪到size,因此允许index=size
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    protected String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.size;
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
    public boolean add(E element) {
        add(size,element); //在size位置，添加元素；关于是否可以存储空元素，由开发者决定，java中建议可以存储null
        return true;
    }
}
