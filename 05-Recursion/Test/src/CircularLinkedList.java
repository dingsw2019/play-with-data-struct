public class CircularLinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
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

    private Node head, tail;
    private int size;

    public CircularLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return head == tail;
    }

    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed.");

        if (index == 0){
            head = new Node(e,head);
            if (size == 0)
                tail = head;
            tail.next = head;

        } else {
            // 跳过元素
            Node prev = head;
            for (int i=0; i<index; i++)
                prev = prev.next;

            // 添加元素
            prev.next = new Node(e,prev.next);
            if (index+1 == size)
                tail = prev.next;
        }
        size++;
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size-1,e);
    }

    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed.");

        Node retNode;
        if (index == 0) {

            // 删除的目标元素
            retNode = head;

            // 变更头尾指针
            head = head.next;
            tail.next = head;

        } else {

            // 查找目标元素
            Node prev = head;
            for (int i=0; i<index-1; i++)
                prev = prev.next;

            // 变更头尾指针
            if (index+1 == size){
                tail = prev.next;
                tail.next = head;
            }

            // 要删除的元素
            retNode = prev.next;
            prev.next = retNode.next;
        }

        retNode.next = null;
        size--;

        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node cur = head;
        for (int i=0; i<size+1; i++){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }

    public E get(int index){
        Node cur = head;
        for (int i=0; i<index; i++)
            cur = cur.next;

        return cur.e;
    }

    public static void main(String[] args) {

        CircularLinkedList<Integer> linkedlist = new CircularLinkedList<>();

        System.out.println("链表头添加");
        for (int i = 0; i < 5; i++){
            linkedlist.addFirst(i);
            System.out.println(linkedlist);
            System.out.println("head:" + linkedlist.head + ", tail:" + linkedlist.tail + ", tail.next:" + linkedlist.tail.next);
        }

        System.out.println("\n链表尾添加");
        for (int i = 5; i < 10; i++){
            linkedlist.addLast(i);
            System.out.println(linkedlist);
            System.out.println("head:" + linkedlist.head + ", tail:" + linkedlist.tail + ", tail.next:" + linkedlist.tail.next);
        }

        System.out.println("\n移除元素");
        linkedlist.removeFirst();
        System.out.println("移除第一个元素, " + linkedlist);

        linkedlist.removeLast();
        System.out.println("移除最后一个元素, " + linkedlist);

        // 移除
        linkedlist.remove(3);
        System.out.println("移除第四个元素, " + linkedlist);
    }
}
