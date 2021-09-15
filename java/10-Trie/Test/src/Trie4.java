import java.util.TreeMap;

public class Trie4 {

    private class Node {
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie4(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public void add(String word){

        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char w = word.charAt(i);
            if (cur.next.get(w) == null)
                cur.next.put(w,new Node());
            cur = cur.next.get(w);
        }
        
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }
    
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        
        return cur.isWord;
    }
    
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i ++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return true;
    }
}
