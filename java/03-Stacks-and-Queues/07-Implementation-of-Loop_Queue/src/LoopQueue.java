/**
 * 避免出队, 所有元素移动, 使用循环队列避免元素移动
 * 循环队列, 当队尾无空间但队首有空间时, 将数据填充到队首
 *          但是, 不能填满, 要留一个位置
 * 空队, front == tail
 * 满队, (tail+1) % queue.length == front
 *
 * 复杂度
 *     void enqueue(E e);   O(1) 均摊
 *     E dequeue();         O(1) 均摊
 *     E getFront();        O(1)
 *     int getSize();       O(1)
 *     boolean isEmpty();   O(1)
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front,tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[])(new Object[capacity+1]);
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity() {
        return data.length-1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }


    @Override
    public void enqueue(E e) {
        // 队列满了,扩容
        if ((tail+1) % data.length == front)
            resize(getCapacity()*2);

        data[tail] = e;
        tail = (tail+1) % data.length;

        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E ret = data[front];
        data[front] = null;
        front = (front+1) % data.length;
        size--;

        // 缩容
        if (size == getCapacity() / 4 && getCapacity()/2 != 0)
            resize(getCapacity()/2);

        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        return data[front];
    }

    public void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity+1];
        for (int i = 0; i < size; i++)
            newData[i] = data[(i+front)%data.length];

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i=front; i!=tail; i=(i+1)%data.length) {
            res.append(data[i]);
            if ((i+1)%data.length != tail)
                res.append(", ");
        }
        res.append("] tail");

        return res.toString();
    }

    public static void main(String[] args) {

        LoopQueue<Integer> queue = new LoopQueue<>(4);
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
