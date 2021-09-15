public class RepeatLinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private Node dummyHead;
    private int size;

    public RepeatLinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("index Illegal");

        Node prev = dummyHead;
        for (int i = 0; i < index; i ++)
            prev = prev.next;

        prev.next = new Node(e,prev.next);
        size++;
    }

    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++)
            cur = cur.next;

        return cur.e;
    }

    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("index Illegal");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++)
            cur = cur.next;

        cur.e = e;
    }

    public boolean contains(E e){

        Node cur = dummyHead.next;
        while (cur != null) {
            if (e.equals(cur.e))
                return true;
            cur = cur.next;
        }

        return false;
    }

    public E remove(int index){

        if (index < 0 || index > size)
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


}