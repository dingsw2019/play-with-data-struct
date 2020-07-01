import java.util.TreeMap;

/**
 * 字典树, 专门为字符串搜索设计的数据结构
 */
public class Trie2 {

    private class Node{
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
    // 单词数量
    private int size;

    public Trie2(){
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
            // 字母不存在, 创建一个字母和其映射
            if (cur.next.get(c) == null)
                cur.next.put(c,new Node());

            // 指向下一个字母
            cur = cur.next.get(c);
        }

        // 单词结束标识符
        if (!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    // 字典树是否包含单词 word
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i ++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        // 不是单词末尾, 说明不是完整单词
        return cur.isWord;
    }

    // 是否包含以 prefix 为前缀的单词
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
