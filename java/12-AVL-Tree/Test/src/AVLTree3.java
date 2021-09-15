public class AVLTree3<K extends Comparable<K>,V> {

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    // 根节点
    private Node root;
    // 节点数量
    private int size;

    public AVLTree3(){
        root = null;
        size = 0;
    }

    // 返回子节点最大的高度
    private int maxHeight(Node node){
        return Math.max(getHeight(node.left),getHeight(node.right));
    }

    // 获取节点高度
    private int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }

    // 计算平衡因子
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 左旋
    //           y                            x
    //         /   \                       /     \
    //        T1    x                     y       z
    //            /   \       --------> /   \   /   \
    //           T2    z               T1   T2 T3   T4
    //               /   \
    //              T3   T4
    private Node leftRotate(Node y){
        Node x = y.right;

        // 左旋
        y.right = x.left;
        x.left = y;

        // 更新节点高度
        y.height = 1 + maxHeight(y);
        x.height = 1 + maxHeight(x);

        return x;
    }

    // 右旋
    //           y                           x
    //         /   \                      /     \
    //        x    T4                    z       y
    //      /   \       -------->      /   \   /   \
    //     z    T3                    T1   T2 T3   T4
    //   /   \
    //  T1   T2
    private Node rightRotate(Node y){
        Node x = y.left;

        // 右旋
        y.left = x.right;
        x.right = y;

        // 更新高度
        y.height = 1 + maxHeight(y);
        x.height = 1 + maxHeight(x);

        return x;
    }

    // 添加节点(key,value)
    public void add(K key, V value){
        root = add(root,key,value);
    }

    // 查找合适的位置并添加节点, 添加完成检查平衡性
    private Node add(Node node, K key, V value){
        // 递归结束条件, node为空表示找到合适添加的位置
        if (node == null){
            size++;
            return new Node(key,value);
        }

        // 查找合适添加新元素的位置
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left,key,value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right,key,value);
        else // 相同key, 更新节点值
            node.value = value;

        // 添加完成, 计算高度
        node.height = 1 + maxHeight(node);

        // 计算节点的平衡因子
        int balanceFactor = getBalanceFactor(node);

        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            node = rightRotate(node);

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            node = leftRotate(node);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            node = rightRotate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            node = leftRotate(node);
        }

        return node;
    }

    // 节点数量
    public int getSize(){
        return size;
    }

    // 是否空树
    public boolean isEmpty(){
        return size == 0;
    }

    // 查找key, 找到返回节点, 递归
    private Node getNode(Node node,K key){
        if (node == null)
            return null;

        if (key.compareTo(node.key) > 0)
            return getNode(node.left,key);
        else if (key.compareTo(node.key) < 0)
            return getNode(node.right,key);
        else
            return node;
    }

    // 是否包含key
    public boolean contains(K key){
        return getNode(root,key) != null;
    }

    // 更新key的值
    public void set(K key, V newValue){
        Node node = getNode(root,key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist");

        node.value = newValue;
    }

    // 获取key的值
    public V get(K key){
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }

    // 返回树的最小节点
    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除 key 节点, 并维持平衡性
    private Node remove(Node node, K key){
        // 递归结束条件, 未找到key节点
        if (node == null)
            return null;

        Node retNode;
        // 查找节点
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left,key);
            retNode = node;
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right,key);
            retNode = node;
        }
        else {
            // 找到key节点

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

        if (retNode == null)
            return null;

        // 更新高度
        retNode.height = 1 + maxHeight(retNode);

        // 计算节点的平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            retNode = rightRotate(retNode);

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            retNode = leftRotate(retNode);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            retNode = rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            retNode = leftRotate(retNode);
        }

        return retNode;
    }
}
