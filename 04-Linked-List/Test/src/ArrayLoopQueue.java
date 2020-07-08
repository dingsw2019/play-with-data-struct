/**
 * 循环队列, 数组实现
 * 队空判断条件：front == tail
 * 队满判断条件：(tail+1) % queue.length == front
 * @param <E>
 */
public class ArrayLoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front,tail;
    private int size;

    public ArrayLoopQueue(int capacity){
        data = (E[])new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public ArrayLoopQueue(){
        this(10);
    }

    public int getCapacity(){return data.length-1;}

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public void enqueue(E e){
        // 队列满了, 扩容
        if ((tail+1) % data.length == front)
            resize(getCapacity()*2);

        data[tail] = e;
        tail = (tail+1) % data.length;
        size++;
    }

    @Override
    public E dequeue(){
        // 队列空
        if (isEmpty())
            throw new IllegalArgumentException("empty");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        // 缩容
        if (size == getCapacity() / 4 && getCapacity()/2 != 0)
            resize(getCapacity()/2);

        return ret;
    }

    @Override
    public E getFront(){
        if (isEmpty())
            throw new IllegalArgumentException("empty");
        return data[front];
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];

        // 数据移动到新结构
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i+front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }
}
