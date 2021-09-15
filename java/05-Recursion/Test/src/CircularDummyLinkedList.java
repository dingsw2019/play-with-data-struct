import java.util.Random;

public class CircularDummyLinkedList<E> {

    public Node<E> dummyHead, tail;
    private int size;

    public CircularDummyLinkedList(){
        dummyHead = new Node<E>(null,null);
        tail = null;
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
            throw new IllegalArgumentException("Add failed.");
        
        // 跳过元素
        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

        // 添加元素
        prev.next = new Node(e,prev.next);
        size++;

        // 更新尾指针
        if (size == 1 || index+1 == size){
            tail = prev.next;
            tail.next = dummyHead;
        }
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed.");

        Node<E> prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

        Node<E> retNode = prev.next;
        prev.next = retNode.next;
        size--;

        // 更新尾指针
        if (index == size)
            tail = prev;

        retNode.next = null;
        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    // 返回元素e的索引
    public int find(E e){

        Node<E> cur = dummyHead.next;
        for(int i=0; i<size; i++) {
            if (cur.e.equals(e))
                return i;
            cur = cur.next;
        }

        return -1;
    }

    // 获取随机元素
    public Node<E> getRandomNode(){
        if (isEmpty())
            throw new IllegalArgumentException("random failed. empty list");

        Random random = new Random();
        int index = random.nextInt(size);
        return getNode(index);
    }

    // 获取指定index的元素地址
    public Node<E> getNode(int index){

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get node failed.");

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        return cur;
    }

    // 获取下一个元素地址, 跳过dummyHead
    public Node<E> getNextNode(Node<E> node){
        if (node == tail)
            node = node.next;
        return node.next;
    }

    // 获取指定元素之后,偏移 offset 个元素的元素
    public Node<E> getOffsetNode(Node<E> cur,int offset){

        for (int i=0; i<offset; i++)
            cur = getNextNode(cur);

        return cur;
    }

    // 按元素地址删除元素
    public E removeNode(Node<E> cur){

        Node<E> prev = dummyHead.next;
        while (prev.next != cur)
            prev = prev.next;

        prev.next = cur.next;

        // 更新尾指针
        if (cur == tail)
            tail = prev;

        size--;

        return cur.e;
    }
    
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        for (int i=0; i<size; i++){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append(cur.next + "->Loop");
        return res.toString();
    }

    public static void main(String[] args) {

        CircularDummyLinkedList<Integer> linkedlist = new CircularDummyLinkedList<>();

        System.out.println("链表头添加");
        for (int i = 0; i < 5; i++){
            linkedlist.addFirst(i);
            System.out.println(linkedlist);
            System.out.println("head:" + linkedlist.dummyHead.next + ", tail:" + linkedlist.tail + ", tail.next:" + linkedlist.tail.next.next);
        }

        System.out.println("\n链表尾添加");
        for (int i = 5; i < 10; i++){
            linkedlist.addLast(i);
            System.out.println(linkedlist);
            System.out.println("head:" + linkedlist.dummyHead.next + ", tail:" + linkedlist.tail + ", tail.next:" + linkedlist.tail.next.next);
        }

        System.out.println("\n移除元素");
        linkedlist.removeFirst();
        System.out.println("移除第一个元素, " + linkedlist);

        linkedlist.removeLast();
        System.out.println("移除最后一个元素, " + linkedlist);

        linkedlist.remove(3);
        System.out.println("移除第四个元素, " + linkedlist);
    }
}
