import javax.swing.border.AbstractBorder;
import java.util.ArrayList;

/**
 * 红黑树的五个规则
 * 1. 节点非黑即红
 * 2. 根节点是黑色
 * 3. null节点是黑色
 * 4. 红色节点的子节点都是黑色
 * 5. 任意节点到叶子节点经过的黑色节点的数量是一样的
 */
public class RBTree<K extends Comparable<K>,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left,right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    // 根节点
    private Node root;
    // 节点数量
    private int size;

    // 初始化
    public RBTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 返回当前节点的颜色是否是红色
    private boolean isRed(Node node){
        if (node == null)
            return BLACK;
        return node.color;
    }

    // 左旋
    //      node                  x
    //     /    \               /   \
    //   T1      x  ------->  node  T3
    //         /   \         /    \
    //        T2   T3       T1    T2
    private Node leftRotate(Node node){
        Node x = node.right;

        // 左旋
        node.right = x.left;
        x.left = node;

        // 颜色变化
        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 右旋
    //      node                  x
    //     /    \              /     \
    //    x     T3  ------>   T1    node
    //  /   \                      /    \
    // T1   T2                    T2    T3
    private Node rightRotate(Node node){
        Node x = node.left;

        // 右旋
        node.left = x.right;
        x.right = node;

        // 颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 颜色翻转, 根节点变红色, 左右子节点变黑色
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    // 添加元素(key,value), 如果key已存在, 更新 value
    // 完成添加后, 检查是否满足红黑树规则
    public void add(K key, V value){
        root = add(root,key,value);
        root.color = BLACK;
    }

    // 遍历查找适合添加节点的位置, 然后添加元素, 递归算法
    private Node add(Node node, K key, V value){

        // 递归结束条件, node 为null 代表找到适合插入几点
        if (node == null){
            size++;
            return new Node(key,value);
        }

        // 递归查找适合插入位置
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left,key,value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right,key,value);
        else
            node.value = value;

        // 新节点经过的所有节点都要检查是否满足红黑树规则
        // 左旋, 右树是红的, 不可能, 红色只能出现在左树
        if (!isRed(node.left) && isRed(node.right))
            node = leftRotate(node);

        // 右旋, 左树和左树的左树都是红色
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        // 颜色翻转
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    // 返回 key 的节点, 递归算法
    private Node getNode(Node node,K key){
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0)
            return getNode(node.left,key);
        else if (key.compareTo(node.key) > 0)
            return getNode(node.right,key);
        else
            return node;
    }

    public boolean contains(K key){
        return getNode(root,key) != null;
    }

    // 查询节点值
    public V get(K key){
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }

    // 修改节点值
    public void set(K key, V newValue){
        Node node = getNode(root,key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist");
        node.value = newValue;
    }



    // 查找并返回树中的最小值, 递归算法
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除树中的最小节点, 递归算法
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            size--;
            node.right = null;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 删除节点
    public V remove(K key){
        Node node = getNode(root,key);
        if (node != null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    // 查找key, 找到后删除, 递归算法
    private Node remove(Node node, K key){
        // 没找到目标节点
        if (node == null)
            return null;

        // 查找目标节点
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left,key);
            return node;
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right,key);
            return node;
        }
        else {
            // 找到目标节点, 开始删除

            if (node.left == null){
                // 左树为null, 将右树挂在父节点上
                Node rightNode = node.right;
                size--;
                node.right = null;
                return rightNode;

            } else if (node.right == null){
                // 右树为null, 将左树挂在父节点上
                Node leftNode = node.left;
                size--;
                node.left = null;
                return leftNode;

            } else {
                // 左右树都存在
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;
                return successor;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        String dir = "Test/";
        if(FileOperation.readFile(dir + "pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Test RBTree
            long startTime = System.nanoTime();

            RBTree<String, Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word))
                    rbt.set(word, rbt.get(word) + 1);
                else
                    rbt.add(word, 1);
            }

            for(String word: words)
                rbt.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time + " s");

        }
    }


}
