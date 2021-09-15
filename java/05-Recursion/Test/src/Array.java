public class Array<E> {

    private int size;
    private E[] data;

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array(){
        this(10);
    }

    public int getCapacity(){
        return data.length;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed.");

        // 扩容
        if (size == getCapacity())
            resize(getCapacity()*2);

        // 移动
        for (int i=size; i > index; i--)
            data[i] = data[i-1];

        // 添加
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
            throw new IllegalArgumentException("Get failed");

        return data[index];
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed");

        data[index] = e;
    }

    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }

        return false;
    }

    // 返回元素e 的索引, 不存在返回 -1
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }

        return -1;
    }

    // 删除元素, 并返回元素内容
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed.");

        E ret = data[index];
        // 移动元素
        for (int i=index+1; i<size; i++)
            data[i-1] = data[i];

        size--;
        data[size] = null;
        // 缩容
        if (size == getCapacity() / 4 && getCapacity()/2 != 0)
            resize(getCapacity()/2);

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

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Arrays: size = %d, capacity = %d\n",size,getCapacity()));
        res.append("[");
        for (int i=0; i<size; i++){
            res.append(data[i]);
            if (i != size-1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];

        // 移动数据
        for (int i=0; i<size; i++)
            newData[i] = data[i];

        data = newData;
    }


    public static void main(String[] args) {

        Array<Integer> arr = new Array<>();
        for(int i = 0 ; i < 10 ; i ++)
            arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);
        // [-1, 0, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);
    }
}
