/**
 * 红黑树
 * 满足二分搜索树的基础上，实现红黑树的规则
 * 1. 节点要么是黑色, 要么是红色
 * 2. 根节点时黑色
 * 3. 叶子节点是黑色(最后的空节点)
 * 4. 如果节点是红色, 那么它的孩子节点都是黑色
 * 5. 任一节点到达叶子节点, 经过的黑色节点数量是一样的
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        // 节点颜色
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

    // 节点是否是红色节点
    public boolean isRed(Node node){
        if (node == null)
            return false;
        return node.color;
    }

    // 左旋
    public Node leftRotate(Node node){
        Node x = node.right;

        // 左旋
        node.right = x.left;
        x.left = node;

        // 颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 右旋
    public Node rightRotate(Node node){
        Node x = node.left;

        // 左旋
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 颜色翻转
    public void flipColors(Node node){
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    public void add(K key, V value){
        root = add(root,key,value);
        // 根节点是黑色的
        root.color = BLACK;
    }

    // 在以 node 为根节点的树中, 添加节点(key,value), 递归算法
    // 添加节点后, 检查树是否还满足红黑树规则
    private Node add(Node node, K key, V value){

        // 递归结束条件, node 为 null 说明找到要添加的位置了
        if (node == null){
            size++;
            return new Node(key,value);
        }

        // 查找合适的位置
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left,key,value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right,key,value);
        } else {
            node.value = value;
        }

        // 检查添加完节点的树, 是否还满足红黑树的性质
        // 左旋
        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        // 右旋
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        // 颜色翻转
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }
}
