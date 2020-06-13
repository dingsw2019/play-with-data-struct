import java.util.Objects;

/**
 * 第一版数组, 只能存放整型. 但数组作为一种数据结构, 应该支持存放任意类型数据
 * 而可以存放任意类型的结构,包含用户自定义类型, 称为泛型。
 *
 * Java 泛型需要传入类对象(基本数据类型也要传入类对象)
 *
 * 基本数据类型的类对象:
 * Boolean, Byte, Char, Short, Integer, Long, Float, Double
 *
 */
public class Array<E> {

    // 存储数据
    private E[] data;

    // 已用长度
    private int size;

    // 构造函数, 初始化数组参数
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }


    // 无参数的构造函数
    public Array(){
        this(10);
    }

    // 获取数组长度
    public int getCapacity() {
        return data.length;
    }

    // 获取已用长度
    public int getSize() {
        return size;
    }

    // 是否空数组
    public boolean isEmpty(){
        return size == 0;
    }

    // 在指定索引添加元素
    public void add(int index, E e) {

        if (size == data.length)
            throw new IllegalArgumentException("Add failed. Array is full.");

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        // 移动index之后的元素
        for (int i = size-1; i >= index; i--) {
            data[i+1] = data[i];
        }

        // 添加新元素
        data[index] = e;
        size++;
    }

    // 数组尾添加元素
    public void addLast(E e) {
        add(size,e);
    }

    // 数组头添加元素
    public void addFirst(E e) {
        add(0,e);
    }

    // 获取index索引位置的元素
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. index is illegal");

        return data[index];
    }


    // 修改index索引位置的元素
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. index is illegal.");

        data[index] = e;
    }


    // 数组是否包含元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }

        return false;
    }


    // 查找元素并返回其索引
    // 元素不存在, 返回 -1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }


    // 删除index位置的元素, 返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        for (int i=index+1; i<size; i++)
            data[i-1] = data[i];

        size--;
        data[size] = null;
        return ret;
    }


    // 删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    // 删除最后一个元素
    public E removeLast() {
        return remove(size-1);
    }

    // 删除元素
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    // Override, 告诉编辑器这是一个覆盖父类的方法,
    // 如果父类不存在此方法, 会提示
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
