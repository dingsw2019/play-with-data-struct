public class Array<E> {

    private E[] data;

    private int size;

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array(){
        this(10);
    }

    // 获取数组长度
    public int getCapacity(){
        return data.length;
    }

    // 获取已用长度
    public int getSize(){
        return size;
    }

    // 是否空数组
    public boolean isEmpty(){
        return size == 0;
    }

    // 在指定索引添加元素
    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index Illegal");

        if (size == getCapacity())
            resize(2*getCapacity());

        // index 之后的元素后移
        for (int i = size-1; i >= index; i--) {
            data[i+1] = data[i];
        }

        data[index] = e;
        size++;
    }

    public void addLast(E e){
        add(size,e);
    }

    public void addFirst(E e){
        add(0,e);
    }


    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        return data[index];
    }

    public E getLast(){return get(size-1);}

    public E getFirst(){return get(0);}

    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        data[index] = e;
    }

    public boolean contains(E e){
        for (int i = 0; i < size; i ++){
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    // 返回元素e的索引值, 没找到返回 -1
    public int find(E e){
        for (int i = 0; i < size; i ++){
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        E ret = data[index];
        for (int i = index+1; i < size; i++)
            data[i-1] = data[i];

        size--;
        if (size < getCapacity() / 4 && getCapacity()/2 != 0)
            resize(getCapacity() / 2);

        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public void removeElement(E e){
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];

        // 移动旧数据
        for (int i = 0; i < size; i++)
            newData[i] = data[i];

        data = newData;
    }
}
