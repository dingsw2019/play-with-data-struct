/**
 * 二分搜索树
 * @param <K>
 * @param <V>
 */
public class BST<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    // 根节点
    private Node root;
    // 节点数
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }



    public void add(K key, V value){
        root = add(root,key,value);
    }

    // 向以 node 为根的树中, 添加 k,v 节点, 递归算法
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
