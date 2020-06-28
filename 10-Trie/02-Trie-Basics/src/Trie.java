import java.util.TreeMap;

public class Trie {

    private class Node {

        // 是否单词的最后一个字母
        public boolean isWord;

        // 某字母的下一个字母, 如果是小写英文就是26个位置
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

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    // 添加单词到字典树
    public void add(String word){

        Node cur = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c,new Node());
            cur = cur.next.get(c);
        }

        // 添加结束符
        if (!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    // 是否包含单词word
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return true;
    }
}
