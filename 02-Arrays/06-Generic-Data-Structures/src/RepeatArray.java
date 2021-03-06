public class RepeatArray<E> {

    private E[] data;
    private int size;

    public RepeatArray(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    public int getCapacity() {return data.length;}

    public int getSize() {return size;}

    public boolean isEmpty() {return size == 0;}

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index Illegal");

        for (int i = size; i > index; i --)
            data[i] = data[i-1];

        data[index] = e;
        size++;
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get Illegal");

        return data[index];
    }

    public void set(int index, E e) {
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
        for (int i = 0; i < size; i ++)
            if (data[i].equals(e))
                return i;
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        E ret = data[index];

        for (int i = index + 1; i < size; i ++)
            data[i-1] = data[i];

        size--;
        data[size] = null;

        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size-1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }
}
