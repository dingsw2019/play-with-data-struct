public class LinkedListLoopQueue<E> implements Queue<E> {
    
    private class Node {
        public E e;
        public Node next;
        
        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }
    }
    
    private Node head,tail;
    private int size;
    
    @Override
    public int getSize(){
        return size;
    }
    
    @Override
    public boolean isEmpty(){
        return size == 0;   
    }
    
    @Override
    public void enqueue(E e){
        // 尾节点入队
        if (tail == null){
            // 首次添加
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }
    
    @Override
    public E dequeue(){
        // 首节点出队
        if (isEmpty())
            throw new IllegalArgumentException("empty");
        
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        
        if (head == null)
            tail = null;
        
        size--;
        return retNode.e;
    }
    
    public E getFront(){
        if (isEmpty())
            throw new IllegalArgumentException("empty");
        return head.e;
    }
}
