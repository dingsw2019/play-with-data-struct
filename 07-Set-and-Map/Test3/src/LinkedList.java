public class LinkedList<K extends Comparable<K>,V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key,V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node(null,null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private Node getNode(K key) {

        Node cur = dummyHead.next;
        while (cur != null) {
            if (key.equals(cur.key))
                return cur;
            cur = cur.next;
        }

        return null;
    }

    public boolean contains(K key) {
        return getNode(key) != null;
    }

    public V get(K key){
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            size++;
            dummyHead.next = new Node(key,value,dummyHead.next);
        } else {
            node.value = value;
        }
    }

    public void set(K key, V newValue){
        Node node = getNode(key);
        if (node != null)
            node.value = newValue;
    }

    public V remove(K key){

        Node prev = dummyHead;
        while (prev.next != null) {
            if (key.equals(prev.next.key))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node retNode = prev.next;
            prev.next = retNode.next;
            retNode.next = null;
            size--;
            return retNode.value;
        }

        return null;
    }
}
