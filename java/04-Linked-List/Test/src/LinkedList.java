/**
 * 链表
 */
public class LinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
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

    // 添加元素
    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("index Illegal");

        Node prev = dummyHead;
        // 跳过index之前的节点
        for (int i = 0; i < index; i++)
            prev = prev.next;

        prev.next = new Node(e,prev.next);
        size++;
    }

    // 头部添加元素
    public void addFirst(E e){
        add(0,e);
    }

    // 末尾添加元素
    public void addLast(E e){
        add(size,e);
    }

    // 返回 index 索引的元素值
    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++)
            cur = cur.next;

        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    // 更新index的值
    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set Failed. Illegal index.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        
        cur.e = e;
    }
    
    
    // 是否包含元素 e
    public boolean contains(E e){
        Node cur = dummyHead.next;

        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }

        return false;
    }

    // 删除元素
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        Node prev = dummyHead;
        for (int i = 0; i < index; i ++)
            prev = prev.next;

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    // 删除头节点
    public E removeFirst(){
        return remove(0);
    }

    // 删除尾节点
    public E removeLast(){
        return remove(size-1);
    }
}
