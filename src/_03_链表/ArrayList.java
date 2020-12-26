package _03_链表;

public class ArrayList<E> implements List<E>{

    private Object[] num ;
    private static final int DEFAULT_SIZE = 10;
    private int length ;

    public ArrayList(){
        num = new Object[DEFAULT_SIZE];

    }

    public ArrayList(int size){
        num = new Object[size];
    }

    @Override
    public int size() {
        return num.length;
    }

    @Override
    public boolean isEmpty() {
        if(length > 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean contains(Object o) {
        for(int i=0 ; i<length; i++){
            if(num[i].equals(o))
                return true;
        }
        return false;
    }

    @Override
    public boolean add(Object o) {
        if(length > num.length)
            copyNum(num);
        num[++length] = o;
        return true;
    }

    private Object[] copyNum(Object[] num){
        Object[] num2 = new Object[length<<1];
        for(int i = 0 ; i < length ; i++){
            num2[i] = num[i];
        }
        length = num2.length;
        num = num2;
        return num;
    }

    @Override
    public Object get(int index) {
        return num[index];
    }

    @Override
    public Object set(int index, E element) {
        Object oldNum = num[index];
        num[index] = element;
        return oldNum;
    }

    @Override
    public void add(int index, E element) {
        if(length+1 > num.length)
            copyNum(num);
        for(int i = length ; i >=index ; i--){
            num[i] = num[i-1];
        }
        num[index] = element;
    }

    @Override
    public E remove(Object o) {
        int index = indexOf((E) o);
        if(index == -1)
            return null;
        E n = (E)num[index];
        for(int i = index ; i < length-1 ; i--){
            num[i] = num[i+1];
        }
        return n;
    }

    @Override
    public int indexOf(E element) {
        for(int i = 0 ; i < length ; i++){
            if(num[i].equals(element))
                return i;
        }
        return -1;
    }

    @Override
    public void clear() {
        if(length <= DEFAULT_SIZE)
            length = 0;
        else{
            length = 0;
            num = null;
        }
    }
}
