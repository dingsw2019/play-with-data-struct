import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V>{

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 判断该二叉树是否是一颗二分搜索树(左子节点小于根节点, 右子节点大于根节点)
    public boolean isBST(){

        // 将二叉树中序遍历的值放入链表
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);

        // 中序遍历, 前一个节点值一定小于后一个节点
        for (int i = 1; i < keys.size(); i++)
            if (keys.get(i-1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    private void inOrder(Node node,ArrayList<K> keys){
        if (node == null)
            return;

        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    // 判断该二叉树是否是一颗平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    // 判断以 node 为根的二叉树是否是一颗平衡二叉树, 递归算法
    private boolean isBalanced(Node node){
        if (node == null)
            return true;

        // 当前节点的平衡因子>1, 说明不是平衡二叉树
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;

        // 左右子树是否满足
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 计算节点node的高度
    private int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }

    // 计算节点node的平衡因子
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 对节点 y 进行右旋转, 返回旋转后的根节点 x
    //          y                               x
    //         / \                            /   \
    //        x  T4    向右旋转 (y)           z      y
    //       / \      ------------>        /  \   /  \
    //      z  T3                         T1  T2 T3   T4
    //     / \
    //    T1  T2
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;

        // 旋转
        x.right = y;
        y.left = T3;

        // 更新x,y 的height
        y.height = 1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }

    // 对节点y 进行左旋, 返回旋转后的根节点x
    // T1 < y < T2 < x < T3 < z < T4
    //              y                                   x
    //           /     \                             /     \
    //          T1      x        向左旋转 (y)         y      z
    //                /   \     ------------->     /  \   /   \
    //               T2    z                      T1  T2 T3   T4
    //                   /   \
    //                  T3   T4
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        // 左旋
        x.left = y;
        y.right = T2;

        // 更新x,y的高度, 先更新y的, 因为x的高度受y高度的影响
        y.height = 1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }

    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node, K key, V value){

        if (node == null){
            size++;
            return new Node(key,value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left,key,value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right,key,value);
        else
            node.value = value;

        // 更新路径上其他节点的高度
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡维护
        // LL, 左子树过大, getBalanceFactor(node.left) >= 0 代表当前节点的左孙子节点存在
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        // RR, 右子树过大, 进行左旋转
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }


        return node;
    }

    private Node getNode(Node node, K key){

        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left,key);
        else // (key.compareTo(node.key) > 0)
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
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    private Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

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

    public V remove(K key){
        Node node = getNode(root,key);
        if (node != null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if (node == null)
            return null;

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left,key);
            retNode = node;

        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right,key);
            retNode = node;

        } else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;

            } else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;

            } else {
                Node successor = minimum(node.right);
                successor.right = remove(node.right,successor.key);
                successor.left = node.left;

                node.left = node.right = null;
                retNode = successor;
            }
        }

        // 删除叶子节点,
        if (retNode == null)
            return null;

        // 更新路径上其他节点的高度
        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    public static void main(String[] args){

        String dir = "02-Calculating-Balance-Factor/";

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(dir + "pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST: " + map.isBST());
            System.out.println("is Balanced: " + map.isBalanced());

            for (String word: words){
                map.remove(word);
                if (!map.isBST() || !map.isBalanced())
                    throw new RuntimeException();
            }
        }

        System.out.println();
    }
}
