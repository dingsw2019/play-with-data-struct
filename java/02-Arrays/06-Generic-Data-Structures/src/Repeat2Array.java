public class Repeat2Array<E> {

    private E[] data;
    private int size;

    public Repeat2Array(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    public int getCapacity(){
        return data.length;
    }

    public int getSize(){
        return size;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index Illegal");

        for (int i = size; i > index; i --)
            data[i] = data[i-1];

        data[index] = e;
        size++;
    }

    public void addFirst(E e){
    }

    public void addLast(E e){
    }

    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        return data[index];
    }

    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e))
                return true;
        }

        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i ++) {
            if (data[i].equals(e))
                return i;
        }

        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        E ret = data[index];

        for (int i = index+1; i < size; i ++)
            data[i-1] = data[i];

        size--;
        data[size] = null;

        return ret;
    }

    public void removeElement(E e){

    }
}
