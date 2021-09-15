import java.util.TreeMap;

/**
 * 字典树, 专为字符串搜索设计
 * 时间复杂度取决于字母的长度, 而不是树的大小, O(w)
 */
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

    // 根节点
    private Node root;
    // 单词数量
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }


    // 添加单词到字典树, 一个字母一个字母添加
    public void add(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            // 不存在c这个字母的, 创建一个, 并生成映射表
            if (cur.next.get(c) == null)
                cur.next.put(c,new Node());
            cur = cur.next.get(c);
        }

        // 单词结束标识
        if (!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    // 单词 word 是否存在字典树中
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        // 如果不是一个单词, 返回 false
        return cur.isWord;
    }

    // 查询字典树中是否有前缀为 prefix 的单词
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
