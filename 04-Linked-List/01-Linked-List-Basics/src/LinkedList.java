/**
 * 线性数据结构
 *      动态数组, 栈, 队列 => 底层依托静态数组(resize解决固定容量问题)
 *      链表, 真正的动态数据结构
 *
 * 为什么链表很重要?
 *      链表是最简单的动态数据结构,后面会说的平衡二叉树,红黑树也是动态数据结构
 *      更深入的理解引用(C++中的指针)
 *      更深入的理解递归
 *      辅助组成其他数据结构 (图结构, 哈希表, 栈)
 *
 * 认识 Linked List
 *      数据存储在节点(Node)中, 节点间相连, 尾节点指向 NULL
 *      class Node {
 *          E e;
 *          Node next;
 *      }
 *
 * 优点：真正的动态, 不需要处理固定容量的问题。用一个申请一个, 不用担心容量问题
 * 缺点：失去了随机访问的能力, arr[0]不行了. 因为数组是连续的内容空间, 所以可以计算出空间地址
 *      但是链表的内存空间时分散的, 故无法计算空间地址
 *
 * 数组和链表的对比
 *      数组最好用于索引有语意的情况, 最大优点: 支持快速查询
 *      链表不适合用于索引有语意的情况, 最大优点: 动态
 */
public class LinkedList<E> {

    // 节点类, 内部类
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    // 获取链表的元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表头添加新元素
    public void addFirst(E e) {
        head = new Node(e, head);
        size++;
    }

    // 添加元素到指定位置
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        if (index == 0)
            addFirst(e);
        else {

            Node prev = head;
            for (int i = 0; i < index-1; i++)
                prev = prev.next;

            prev.next = new Node(e,prev.next);
            size++;
        }
    }

    public void addLast(E e) {
        add(size,e);
    }

















}
