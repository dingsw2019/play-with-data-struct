public class AVLTree5<K extends Comparable<K>,V> {

    private class Node {
        public K key;
        public V value;
        public int height;
        public Node left,right;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree5(){
        root = new Node(null,null);
        size = 0;
    }

    public int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }

    public int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        y.right = T2;
        x.left = y;

        y.height = 1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }
}
