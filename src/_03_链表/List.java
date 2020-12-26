package _03_链表;

public interface List<E> {

    /**
     * 元素的数量
     * @return
     */
    public int size() ;

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() ;

    /**
     * 是否包含某个元素
     * @param o
     * @return
     */
    public boolean contains(Object o) ;

    /**
     * 添加元素到最后面
     * @param o
     * @return
     */
    public boolean add(Object o) ;

    /**
     * 返回index位置对应的元素
     * @param index
     * @return
     */
    Object get(int index);

    /**
     * 设置index位置的元素
     * @param index
     * @param element
     * @return
     */
    Object set(int index, E element);

    /**
     * 往index位置添加元素
     * @param index
     * @param element
     */
    void add(int index, E element);

    /**
     * 删除index位置对应的元素
     * @param o
     * @return
     */
    E remove(Object o);

    /**
     * 查看元素的位置
     * @param element
     * @return
     */
    public int indexOf(E element);

    /**
     * 清除所有元素
     */
    public void clear() ;

}
