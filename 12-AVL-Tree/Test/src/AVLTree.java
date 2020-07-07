import java.util.ArrayList;

/**
 * 平衡二叉树
 * @param <K>
 * @param <V>
 */
public class AVLTree<K extends Comparable<K>,V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        // 节点高度, 用于计算平衡因子
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1; // 初始节点高度为 1
        }
    }

    // 根节点
    public Node root;
    // 节点数量
    public int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    // 是否是一个二分搜索树
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);

        for (int i = 1; i < keys.size(); i++){
            if (keys.get(i-1).compareTo(keys.get(i)) > 0)
                return false;
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys){

        if (node == null)
            return;

        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    // 判断是否是平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }
    private boolean isBalanced(Node node){

        if (node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 获取节点 node 的高度
    public int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }

    // 获取节点 node 的平衡因子
    public int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 左旋, 以 y 为根的树进行左旋操作, 返回旋转后的根节点
    public Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        // 更新 x,y 的高度, 先更新y的
        y.height = 1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }

    // 右旋, 以 y 为根的树进行右旋操作, 返回旋转后的根节点
    public Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;

        // 更新 x,y 的高度, 先更新y的
        y.height = 1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }

    // 添加 K,V 到平衡二叉树, 递归算法
    public void add(K key, V value){
        root = add(root,key,value);
    }

    // 向以 node 为根的平衡二叉树中, 添加 K,V
    private Node add(Node node, K key, V value){

        // 结束条件
        if (node == null){
            return new Node(key,value);
        }

        // K 在左子树
        if (node.key.compareTo(key) < 0){
            node.left = add(node.left,key,value);
        // K 在右子树
        } else if (node.key.compareTo(key) > 0){
            node.right = add(node.right,key,value);
        } else {
            node.value = value;
        }

        // 更新路径上其他节点的高度
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡节点
        // LL, 右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        // RR, 左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        // LR, 先左旋, 再右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL, 先右旋, 在左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void remove(K key){
        root = remove(root,key);
    }

    // 查找以 node 为根的树的最小值
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 在以 node 为根的树中, 查找并删除 key 节点, 递归算法
    private Node remove(Node node, K key){

        if (node == null)
            return null;

        Node retNode;
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right,key);
            retNode = node;
        } else {

            // 左子树为空
            if (node.left == null){
                retNode = node.right;
                node.right = null;
                size--;

            // 右子树为空
            } else if (node.right == null){
                retNode = node.left;
                node.left = null;
                size--;

            // 左右子树不为空
            } else {
                Node successor = minimum(node.right);
                successor.right = remove(node.right,successor.key);
                successor.left = node.left;
                
                node.left = node.right = null;
                
                retNode = successor;
            }
        }

        // 删除叶子节点, 没有子节点
        if (retNode == null)
            return null;

        // 更新路径上其他节点的高度
        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        // LL, 右旋
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        // RR, 左旋
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        // LR, 先左旋, 再右旋
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL, 先右旋, 在左旋
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }
}
