import java.util.ArrayList;

public class AVLTree2<K extends Comparable<K>,V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        // 每个节点的高度, 用于计算节点的平衡因子
        public int height;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1; // 节点初始高度
        }
    }

    // 根节点
    private Node root;
    // 节点数量
    private int size;

    public AVLTree2(){
        root = new Node(null,null);
        size = 0;
    }

    public int getSize(){ return size; }

    public boolean isEmpty() {return size == 0;}

    // 判断树是否满足二分搜索树的性质, 左树小于根, 右树大于根
    public boolean isBST(){
        // 中序遍历树, 结果存入链表
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);

        // 中序遍历的值是从小到大的, 如果不是, 说明不满足二分搜索树性质
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i-1).compareTo(keys.get(i)) > 0)
                return false;
        }

        return true;
    }

    private void inOrder(Node node,ArrayList<K>keys){
        if (node == null)
            return;

        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    // 判断树是否满足平衡二叉树的性质, 最大深度与最小深度相差1
    public boolean isBalanced(){
        return isBalanced(root);
    }
    // 递归算法, 以node为根, 判断其左右子树高度是否相差1
    private boolean isBalanced(Node node){
        // 遍历到最后了, 没有拦下来就是正确
        if (node == null)
            return true;

        // 获取 node 的平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 获取节点node的高度
    private int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }

    // 获取节点node的平衡因子
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 右旋
    public Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;

        // 更新 x,y的高度, y的优先
        y.height = 1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }

    // 左旋
    public Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        // 更新 x,y的高度, y的优先
        y.height = 1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }

    public void add(K key, V value){
        root = add(root,key,value);
    }

    // 向以 node 为根的树中添加 k,v 节点, 递归算法
    private Node add(Node node, K key, V value){

        // 触底了, 萌新上吧
        if (node == null){
            size++;
            return new Node(key,value);
        }

        // 左小右大, 寻找适合添加的子树
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left,key,value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right,key,value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        // 更新节点高度
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡检测, 将不平衡的子树变平衡
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
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
}
