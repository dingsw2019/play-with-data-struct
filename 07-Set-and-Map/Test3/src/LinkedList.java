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
        
    }

    public void add(K key, V value) {

    }
}
