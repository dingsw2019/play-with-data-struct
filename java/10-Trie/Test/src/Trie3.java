import java.util.TreeMap;
import java.util.TreeSet;

public class Trie3 {

    private class Node {
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }
    
    private Node root;
    private int size;
    
    public Trie3(){
        root = new Node();
        size = 0;
    }
    
    public int getSize(){
        return size;
    }

    // 单词 word 添加到字典树
    public void add(String word){
        
        Node cur = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        
        // 添加结束符
        if (!cur.isWord) {
            size++;
            cur.isWord = true;
        }
    }
    
    // word是否存在字典树中
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        
        return cur.isWord;
    }
    
    // 字典树中存在包含prefix前缀的单词
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return true;
    }
}
