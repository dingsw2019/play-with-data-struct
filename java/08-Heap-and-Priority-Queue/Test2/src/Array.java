/**
 * 什么是优先队列
 *      普通队列: 先进先出, 后进后出
 *      优先队列: 出队顺序与入队顺序无关, 和优先级相关
 *              类似医院, 根据患者严重程度安排手术
 *              操作系统, 任务的调度, 动态选择优先级最高的任务执行
 *
 *
 * 优先队列
 *                   入队      出队(拿出最大元素)
 *      普通线性结构   O(1)       O(n)
 *      顺序线性结构   O(n)       O(1)
 *      堆           O(logn)    O(logn)
 *
 * 二叉堆是一种满足特殊条件的二叉树
 *      二叉堆是一颗完全二叉树 (完全二叉树: 把元素顺序排列成树的形状)
 *      堆中节点值总是大于等于其左右节点的值(最大堆, 也有最小堆)
 */
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

    public Array(E[] arr){
        data = (E[])new Object[arr.length];
        for(int i = 0; i < arr.length; i++)
            data[i] = arr[i];
        size = arr.length;
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
            throw new IllegalArgumentException("Add failed. index Illegal");

        if (size == data.length)
            resize(2*data.length);

        for (int i=size-1; i>=index; i--)
            data[i+1] = data[i];

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
            throw new IllegalArgumentException("Get failed. index Illegal");

        return data[index];
    }

    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. index Illegal");
        data[index] = e;
    }

    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. index Illegal");

        E ret = data[index];

        for (int i=index+1; i<size; i++)
            data[i-1] = data[i];

        size--;
        data[size] = null;
        if (size == data.length/4 && data.length / 2 != 0)
            resize(data.length/2);

        return ret;
    }

    public E removeLast(){
        return remove(size-1);
    }

    private void resize(int newCapacity){

        E[] newData = (E[])new Object[newCapacity];
        for(int i=0; i<size; i++)
            newData[i] = data[i];
        data = newData;
    }

    public void swap(int i, int j){
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("index Illegal");

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
