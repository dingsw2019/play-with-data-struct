/**
 * 队列基础概念
 *  队列是一种线性结构, 是数组的子集
 *  从队尾添加元素, 从队首取出元素
 *  先进先出, FIFO(First In First Out)
 *
 * 队列结构
 *      | 4 |  队尾
 *      | 3 |
 *      | 2 |
 *      | 1 |  队首
 *      —————
 *
 * 数组队列复杂度
 *     void enqueue(E e);       O(1) 均摊
 *     E dequeue();             O(n)
 *     E getFront();            O(1)
 *     int getSize();           O(1)
 *     boolean isEmpty();       O(1)
 */

public class ArrayQueue<E> implements Queue<E>{

    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i=0; i<array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");

        return res.toString();
    }

    public static void main(String[] args) {

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i=0; i<10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
