public class RepeatLoopQueue<E> implements Queue<E> {

    private E[] data;
    private int size;
    private int front, tail;

    public RepeatLoopQueue(int capacity){
        data = (E[])new Object[capacity+1];
        size = 0;
        front = tail = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        // 队满
        if ((tail+1)%data.length == front)
            resize(data.length*2);

        // 添加
        data[tail] = e;

        tail = (tail+1)%data.length;
        size++;
    }

    @Override
    public E dequeue() {
        // 队空
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E ret = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size--;

        // 缩容
        if (size == data.length/4 && data.length/2 != 0)
            resize(data.length/2);

    }

    @Override
    public E getFront() {
        return data[front];
    }


    private void resize(int newCapacity) {

        E[] newData = (E[])new Object[newCapacity+1];
        for (int i = 0; i < size; i ++)
            newData[i] = data[(i+front)%data.length];

        data = newData;
        front = 0;
        tail = size;
    }

}