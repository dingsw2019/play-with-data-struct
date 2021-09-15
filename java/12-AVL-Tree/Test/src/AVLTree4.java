public class AVLTree4<K extends Comparable<K>,V> {

    private class Node {
        public K key;
        public V value;
        public int height;
        public Node left, right;

        public Node (K key, V value){
            this.key = key;
            this.value = value;
            left = right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree4(){
        root = null;
        size = 0;
    }

    public int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    public int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 左旋
    //      y                           x
    //    /   \                      /     \
    //   T1    x                    y       z
    //       /   \    ------>     /   \   /   \
    //      T2    z              T1   T2 T3   T4
    //          /   \
    //         T3   T4
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

    // 右旋
    //         y                       x
    //       /   \                  /     \
    //      x    T4                z       y
    //     /  \      ------>     /   \   /   \
    //    z    T3               T1   T2 T3   T4
    //  /   \
    // T1   T2
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

    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node, K key, V value){
        if (node == null) {
            size++;
            return new Node(key,value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left,key,value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right,key,value);
        } else {
            node.value = value;
        }

        // 高度增加
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));

        // 计算平衡因子
        int factor = getBalanceFactor(node);

        // LL
        if (factor > 1 && getBalanceFactor(node.left) >= 0) {
            node = rightRotate(node);
        }

        // RR
        if (factor < -1 && getBalanceFactor(node.right) <= 0) {
            node = leftRotate(node);
        }

        // LR
        if (factor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            node = rightRotate(node);
        }

        // RL
        if (factor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            node = leftRotate(node);
        }

        return node;
    }

    private void remove(K key){
        root = remove(root,key);
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

    private Node remove(Node node,K key){

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
                retNode = node.right;
                node.right = null;
                size--;

            } else if (node.right == null){
                retNode = node.left;
                node.left = null;
                size--;

            } else {
                Node successor = minimum(node.right);
                successor.right = remove(node.right,successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        if (retNode == null)
            return null;

        // 维持平衡
        // 计算高度
        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));

        // 计算平衡因子
        int factor = getBalanceFactor(retNode);

        // LL
        if (factor > 1 && getBalanceFactor(retNode.left) >= 0) {
            retNode = rightRotate(retNode);
        }

        // RR
        if (factor < -1 && getBalanceFactor(retNode.right) <= 0) {
            retNode = leftRotate(retNode);
        }

        // LR
        if (factor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            retNode = rightRotate(retNode);
        }

        // RL
        if (factor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            retNode = leftRotate(retNode);
        }

        return retNode;

    }
}
