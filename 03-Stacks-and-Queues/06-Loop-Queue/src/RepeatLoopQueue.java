public class RepeatLoopQueue<E> implements Queue<E>{

    private E[] data;
    private int size;
    private int front;
    private int tail;

    private RepeatLoopQueue(){
        data = (E[])new Object();
        size = 0;
        size = front;
        size = tail;
    }

    @Override
    public int getSize(){return size;}

    @Override
    public boolean isEmpty(){return front == tail;}

    @Override
    public void enqueue(E e) {

        int offset = (tail+1)%data.length;
        // 队满
        if (offset == front)
            // 扩容
            resize(data.length*2);

        data[tail] = e;
        tail = offset;

        size++;
    }

    @Override
    public E dequeue(){
        // 队空
        if (isEmpty())
            throw new IllegalArgumentException("queue empty");

        E ret = data[front];
        data[front] = null;

        front = (front+1)%data.length;
        size--;

        // 缩容
        if (size == data.length/4 && data.length/2 != 0)
            resize(data.length/2);

        return ret;
    }

    @Override
    public E getFront(){
        // 队空
        if (isEmpty())
            throw new IllegalArgumentException("queue empty");

        return data[front];
    }

    private void resize(int newCapacity) {

        E[] newData = (E[])new Object[newCapacity+1];
        for (int i = 0; i < size; i ++)
            newData[i] = data[i+front % data.length];

        data = newData;
        front = 0;
        tail = size;
    }
}
