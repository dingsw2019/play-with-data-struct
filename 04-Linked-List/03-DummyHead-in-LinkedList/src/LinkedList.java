/**
 * 为链表添加虚拟头节点
 * 问题描述：链表指定位置添加, 需要找到指定位置的节点的前一个节点, 但是 head 指向第一个节点
 *         第一个节点之前没有节点了, 所以 addFirst 要特殊判断 index == 0, 不需要寻找前置节点
 *
 * 解决方法：添加虚拟头节点, 这样所有的节点都有前置节点了, 可以用一套逻辑处理
 *
 *  ________    _____    _____    _____    _____    _____    ________
 *  | NULL | -> | 0 | -> | 1 | -> | 2 | -> | 3 | -> | 4 | -> | NULL |
 *  --------    -----    -----    -----    -----    -----    --------
 *  dummyHead
 */
public class LinkedList<E> {

    private class Node {

        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }


    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyHead;
        for (int i=0; i<index; i++)
            prev = prev.next;

        prev.next = new Node(e,prev.next);
        size++;
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

}
