public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
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

    public void add(E e){
        root = add(root,e);
    }

    // 添加元素, 递归算法
    private Node add(Node node, E e){
        if (node == null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add(node.left,e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right,e);

        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node, E e){

        if (node == null)
            return false;

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left,e);
        } else { // e.compareTo(node.e) > 0
            return contains(node.right,e);
        }
    }

    // 最小值
    public E minimum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty.");

        return minimum(root).e;
    }

    private Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    // 删除最小值
    private Node removeMin(Node node){
        if (node.right != null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        return removeMin(node.left);
    }

    public void remove(E e){
        root = remove(root,e);
    }

    // 删除任一元素, 递归算法
    private Node remove(Node node, E e){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
            return node;

        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
            return node;

        } else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }
}
