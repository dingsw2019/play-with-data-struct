/**
 * 栈的定义
 *      栈是一种线性结构, 是数组的子集
 *      只能从栈顶添加/取出元素, 后进先出, LIFO(Last In First Out)
 *
 * 栈的结构
 *      | 4 |
 *      | 3 |
 *      | 2 |
 *      | 1 |
 *      -----
 *
 * 栈的应用
 *      IDE的Undo操作(撤销), word文档的撤销操作
 *      程序调用的系统栈, A 调用 B, B 调用 C
 *
 * 栈的复杂度
 *    void push(E e);       O(1) 均摊
 *    E pop();              O(1) 均摊
 *    E peek();             O(1)
 *    int getSize();        O(1)
 *    boolean isEmpty();    O(1)
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
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
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != (array.getSize() - 1))
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }
}
