import com.sun.org.apache.regexp.internal.RE;

/**
 * 红黑树
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
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
    // 节点数
    private int size;

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

    // 判断节点node的颜色
    private boolean isRed(Node node){
        if (node == null)
            return BLACK;
        return node.color;
    }

    //      node                        x
    //     /    \       左旋转         /    \
    //    T1     x    --------->   node    T3
    //         /   \              /    \
    //        T2   T3            T1    T2
    private Node leftRotate(Node node){
        Node x = node.right;

        // 左旋
        node.right = x.left;
        x.left = node;

        // 颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    //          node                    x
    //         /    \    右旋转        /   \
    //        x     T2  -------->    y   node
    //      /   \                       /    \
    //     y    T1                     T1    T2
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

    // 颜色翻转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void add(K key, V value){
        root = add(root,key,value);
        root.color = BLACK; // 最终根节点为黑色节点
    }

    // 向以 node 为根的红黑树中, 添加节点(k,v), 递归算法
    private Node add(Node node, K key, V value){

        // 找到末尾就是找到 适合添加的位置了
        if (node == null){
            size++;
            return new Node(key,value);
        }

        // 查找
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left,key,value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right,key,value);
        else
            node.value = value;

        // 红黑树规则维持机制
        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    // node 为根的树中, 获取 key 的节点, 递归算法
    private Node getNode(Node node, K key){
        // 到底了, 也没找到 key
        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left,key);
        else
            return getNode(node.right,key);
    }

    public boolean contains(K key){
        return getNode(root,key) != null;
    }

    public V get(K key){
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root,key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist");
        node.value = newValue;
    }

    public V minimum(){
        Node node = minimum(root);
        return node == null ? null : node.value;
    }

    // 以 node 为根的树, 获取其最小节点
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除 node 为根的树 的最小节点
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public V maximum(){
        Node node = maximum(root);
        return node == null ? null : node.value;
    }

    private Node maximum(Node node){
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    // 删除 key 的节点
    public V remove(K key){
        Node node = getNode(root,key);
        if (node != null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    // 在以 node 为根的树, 搜索并删除 key 节点, 递归算法
    private Node remove(Node node, K key){

        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            return node;

        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right,key);
            return node;

        } else {

            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;

            } else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;

            } else {
                // 左右子树都存在
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;
                return successor;
            }
        }
    }

}
